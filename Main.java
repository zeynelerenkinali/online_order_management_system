
import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        OrderSystem os = new OrderSystem(null);
        int select = 0;
        int order_index = 0;
        boolean isFirst = false;
        ArrayList<String> order_list = new ArrayList<>(); 
        ArrayList<String[]> cur_order = new ArrayList<>();
        while(true)
        {
            if(isFirst == false)
            {
                System.out.print("---------------------------\n||Order System Management||\n---------------------------\n");
                System.out.print("\nPlease select an alphabet type(en, tr): ");
                String type = sc.next(); 
                os.setAlphabetType(type);
                isFirst = true;
                System.out.print("\n-------------\n |Main Menu|\n-------------\n1.Add Order\n2.Cancel Order\n3.Querying the Product Set\n4.Print\n5.Exit\n-------------\nPlease select: ");
            }
            else
                System.out.print("\nChoice: ");
            select = sc.nextInt();
            clearScreen();
            switch(select) 
            {
                case 1 -> {
                    System.out.print("\n-----------\n|Add Order|\n-----------\n");
                    System.out.print("Please enter your order(as order1,order2,order3): ");
                    String text = sc.next(); // Save the current order to text variable.
                    String[] parts = text.split(","); // Split text variable with "," to sepereate orders.
                    if(parts.length < 1)
                    {
                        System.err.print("Error on |Add Order| Operation: Please enter orders in appropriate structure(as order1,order2,order3)");
                        break;
                    }
                    else
                    {
                        cur_order.add(parts);
                        order_index++;
                    }
                    order_list.add(text); // Add order to order_list, as full text.
                    boolean a = os.AddOrder(cur_order.get(order_index - 1), null);
                    System.out.print(a);
                }
                case 5 -> os.print(null);
                default -> throw new AssertionError();
            }
        }

    }
    public static void clearScreen() 
    {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
}