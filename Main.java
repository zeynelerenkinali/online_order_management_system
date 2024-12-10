
import java.util.ArrayList;
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
        ArrayList<String> order_list = new ArrayList<>(); 
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
                        String text = sc.next(); // Save the current order to text variable.
                        String[] parts = text.split(","); // Split text variable with "," to sepereate orders.
                        if(parts.length <= 1)
                            System.err.print("\nERROR: Please enter orders in appropriate structure(as order1,order2,order3)\n");
                        else
                        {
                            valid = true;
                            cur_order.add(parts);
                            order_index++;
                            order_list.add(text); // Add order to order_list, as full text.
                        }
                    }   
                    boolean a = os.AddOrder(cur_order.get(order_index - 1), null);
                    System.out.print(a);
                }
                case 4 -> os.print(null, 1);
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