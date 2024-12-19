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
        boolean valid;
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
                        System.out.print("\n------------\n|Add Order|\n------------\n");
                        System.out.print("Please enter your order(as order1,order2,order3): ");
                        if (sc.hasNextLine()) sc.nextLine(); // Clean buffer
                        boolean parts_valid = true;   
                        String text = sc.nextLine(); // Save the current order to text variable.
                        text = text.replaceAll("\\s", "");
                        String[] parts = text.split(","); // Split text variable with "," to sepereate orders.
                        int checkCounter = 0;
                        for (String part : parts) {
                            checkCounter = 0;
                            if (part.equals("")) {
                                parts_valid = false;
                                break;
                            }
                            for(String partCheck : parts){
                                if(part.equals(partCheck)) checkCounter++;
                            }
                            if(checkCounter >= 2){
                                parts_valid = false;
                                break;
                            }
                        }
                        if(parts.length < 1 || !parts_valid)
                        {
                            if(checkCounter >= 2)
                                System.err.print("\nERROR: You cannot add same order more than one.\n");
                            else
                                System.err.print("\nERROR: Please enter orders in appropriate structure(as order1,order2,order3)\n");
                        }
                        else
                        {
                            valid = true;
                            cur_order.add(parts);
                            order_index++;
                            order_list.add(parts); // Add order to order_list, as full text.
                        }
                    }   
                    os.AddOrder(cur_order.get(order_index - 1), null, 0, false);
                }
                case 2 ->
                {
                    valid = false;
                    if(!order_list.isEmpty())
                    {
                        while(!valid)
                        {
                            System.out.print("\n--------------\n|Cancel Order|\n--------------\n");
                            System.out.print("\n--------\n|ORDERS|\n--------");
                            // Print the currently available order for user to select one of the array for cancel operation
                            for(int i = 0; i < order_list.size(); i++)
                            {
                                System.out.print("\n|" + (i+1) + "->|[");
                                for(int j = 0; j < order_list.get(i).length; j++)
                                {
                                    String comma = (j == order_list.get(i).length - 1) ? "" : ",";
                                    System.out.print(order_list.get(i)[j] + comma);
                                }
                                System.out.print("]");
                            }
                            boolean valid_select = false;
                            int selected_order = 0;
                            // Select an order from the list that previously given
                            while(!valid_select)
                            {
                                System.out.print("\nPlease select an order to cancel(select by index number): ");
                                if (sc.hasNextLine()) sc.nextLine(); // Clean buffer
                                String line = sc.nextLine();
                                line = line.replaceAll("\\s", "");
                                if(line.matches("\\d+"))
                                {
                                    selected_order = Integer.parseInt(line);
                                    if(selected_order <= order_list.size() && selected_order > 0) valid_select = true;
                                }
                            }
                            // Send the order to Cancel_order operation at OrderSystem and delete the order from order_list
                            boolean check = os.CancelOrder(order_list.get(selected_order - 1), null, null, 0);
                            order_list.remove(selected_order - 1);
                            if(check) System.out.print("\nOrder Successfully Cancelled.\n");
                            else System.err.println("\nCould not able to Cancel Order.\n");
                            valid = true;
                        }
                    }
                    else
                    {
                        System.err.print("\nERROR: There is no order to cancel, please add an order first.\n");
                        if (sc.hasNextLine()) sc.nextLine(); // Clean buffer 
                        System.out.print("\nPress Enter to continue...");
                        sc.nextLine();
                    } 
                }
                case 3 ->{
                    valid = false;
                    while(!valid)
                    {              
                        System.out.print("\n--------------------------\n|Querying the Product Set|\n--------------------------\n");
                        System.out.print("Please enter the set of query(as query1,query2,query3): ");
                        if (sc.hasNextLine()) sc.nextLine(); // Clean buffer
                        boolean query_set_valid = true;   
                        String text = sc.nextLine(); // Save the current order to text variable.
                        text = text.replaceAll("\\s", "");
                        String[] query_set = text.split(","); // Split text variable with "," to sepereate orders.
                        for (String query : query_set) {
                            if (query.length() < 1) {
                                query_set_valid = false;
                                break;
                            }                         }
                        if(query_set.length <= 1 && !query_set_valid)
                            System.err.print("\nERROR: Please enter the set of query in appropriate structure(as query1,query2,query3)\n");
                        else
                        {
                            os.Query(query_set, null, 0);
                            if(os.get_query_count() <= 0) System.err.print("\nERROR: The query set of '" + Arrays.toString(query_set) + "' is not exist.\n");
                            else
                            {
                                System.out.print("\nThere is "+ os.get_query_count()+ " amount of " + Arrays.toString(query_set) + " query set exist in this product.\n");
                                System.out.print("\nPress Enter to continue...");
                                sc.nextLine();
                                valid = true;
                            }
                        }
                    }   
                }
                case 4 -> {
                    if(!order_list.isEmpty()) os.print(null, "");
                    else System.err.print("\nERROR: There is no order to print, please add an order first.\n");
                    if (sc.hasNextLine()) sc.nextLine(); // Clean buffer 
                    System.out.print("\nPress Enter to continue...");
                    sc.nextLine();
                }
                case 5 ->                 {
                    System.out.print("\nThank you for using our Order Management System.\nLeaving...\n");
                    mainLoop = true;
                    sc.close();
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