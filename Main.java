
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        OrderSystem os = new OrderSystem(null);
        int select = 0;
        String choice;
        int order_index = 0;
        boolean isFirst = false;
        boolean valid = false;
        boolean mainLoop = false;
        ArrayList<String[]> order_list = new ArrayList<>(); 
        ArrayList<String[]> cur_order = new ArrayList<>();
        while(!mainLoop)
        {
            if(isFirst == false)
            {
                valid = false;
                String type = null;
                while(!valid)
                {
                    System.out.print("\n---------------------------\n||Order System Management||\n---------------------------\n");
                    System.out.print("\nPlease select an alphabet type(en, tr): ");
                    type = sc.next();
                    if(!"en".equals(type) && !"tr".equals(type)) System.err.print("\nERROR: You can only chose 'en' or 'tr' as language type.\n");
                    else
                    {
                        String a_type = type.equals("en") ? "English" : "Turkish";
                        System.out.print("\nAlphabet is succesfully settled up as " + a_type + "\n");
                        valid = true;
                    }
                }
                os.setAlphabetType(type);
                isFirst = true;
            }
            valid = false;
            System.out.print("\n-------------\n |Main Menu|\n-------------\n1.Add Order\n2.Cancel Order\n3.Querying the Product Set\n4.Print\n5.Exit\n-------------");
            while(!valid)
            {
                System.out.print("\nChoice: ");
                choice = sc.next();
                if(isInteger(choice))
                {
                    select = Integer.parseInt(choice);
                    valid = true;
                } 
                else
                    System.err.print("\nERROR: Please type your choice as integer.\n");
            }
            clearScreen();
            switch(select) 
            {
                case 1 -> 
                {
                    valid = false;
                    while(!valid)
                    {              
                        System.out.print("\n-----------\n|Add Order|\n-----------\n");
                        System.out.print("Please enter your order(as order1,order2,order3): ");
                        if (sc.hasNextLine()) sc.nextLine(); // Clean buffer
                        boolean parts_valid = true;   
                        String text = sc.nextLine(); // Save the current order to text variable.
                        text = text.replaceAll("\\s", "");
                        String[] parts = text.split(","); // Split text variable with "," to sepereate orders.
                        String[] parts_to_list = Arrays.copyOf(parts, parts.length); // When you equal directly it will get address of it so it will be changing the original array not the parts_to_list copy
                        for (int i = 0; i < parts.length; i++) 
                        {
                            if(parts[i].length() < 1){
                                parts_valid = false;
                                break;
                            } 
                            else parts_to_list[i] = parts_to_list[i].toLowerCase(); // Send all the strings at lowercase situation while sending it to order_list in order to delete easily.
                            System.out.println(parts[i] + " " + parts_to_list[i]);
                        }
                        if(parts.length <= 1 && !parts_valid)
                            System.err.print("\nERROR: Please enter orders in appropriate structure(as order1,order2,order3)\n");
                        else
                        {
                            valid = true;
                            cur_order.add(parts);
                            order_index++;
                            order_list.add(parts_to_list); // Add order to order_list, as full text.
                        }
                    }   
                    os.AddOrder(cur_order.get(order_index - 1), null);
                }
                case 2 ->{

                }
                case 4 -> {
                    os.print(null, ""); 
                    System.out.print("\nType anything to continue...");
                    char ch = sc.next().charAt(0);
                }
                case 5 ->                 {
                    System.out.print("\nThank you for using our Order Management System.\nLeaving...\n");
                    mainLoop = true;
                }
                default -> System.err.println();
            }
            if (!mainLoop) clearScreen();
        }

    }
    public static void clearScreen() 
    {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    public static boolean isInteger(String str) 
    { 
        try { 
            Integer.parseInt(str); 
            return true; 
        } catch (NumberFormatException e) { 
            return false; 
        } 
    } 
}