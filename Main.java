
import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int select = 0;
        int order_index = 0;
        ArrayList<String> order_list = new ArrayList<>(); 
        ArrayList<String[]> cur_order = new ArrayList<>();
        while(true)
        {
            System.out.print("||Order System Management||\n");
            System.out.print("\n1.Add Order\n2.Cancel Order\n3.Querying the Product Set\n4.Print\n5.Exit\nPlease select: ");
            select = sc.nextInt();
            switch(select) 
            {
                case 1:
                    System.out.print("/n/n|Add Order|/n/n");
                    System.out.print("Please enter your order(as: E,M,K): ");
                    order_list.add(sc.nextLine());
                    String text = order_list.get(order_index);
                    String[] parts = text.split(text)
                    
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }
}