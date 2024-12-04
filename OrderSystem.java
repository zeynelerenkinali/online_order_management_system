import java.text.Collator;
import java.util.*;
/*
 * Algorithm
 * OrderSystem()
 * 1. Declare index = 0
 * 2. Declare this.root = null
 * 3. Declare boolean isAlph = false
 * 
 * - AddOrder(String order[], Node root)
 * 1. If isAlph false
 *    1.1 Sent order to Alphabetical(String order[], String AlphabetType) *ENG and TUR*
 *    1.2 isAlph = true
 * 2. Declare cur_order_node with order[index] data
 * 3. Initialize next_order_node as null
 * 4. If (index+1) >= order.length
 *     4.1 Increase cur_order_node's quantity with 1
 *     4.2 print("order addition successfully operated")
 *     4.3 Set Index = 0
 *     4.4 Set isAlph = false;
 *     4.5 return
 * 5. Else
 *     5.1 Declare next_order_node with order[index+1] data
 * 6. If this.root of the list is null 
 *      6.1 Set this.root as cur_order_node
 *      6.2 Set this.root's parent as null
 *      6.3 Set this.root's sibling as null
 *      6.4 Increase this.root's quantity with 1
 *      6.5 Increase order's index with 1
 *      6.6 Set this.root.child as next_order_node
 *      6.7 Set next_order_node's parent as cur_order_node
 *      6.8 AddOrder(order, next_order_node)
 * 
 * // In order to solve order quantity update problem, we can hold a quantity in each node in order to hold, amount of product
 * // at each node for each different path easily. Then we can increase that whenever we want, with holding previous pointer
 * 
 * 7. If (root is equal to null) and (this.root equal to cur_order_node)
 *      7.1 Increase this.root's quantity with 1
 *      7.2 Increase order's index with 1
 *      7.3 Set this.root.child.sibling = next_order_node
 *      7.4 Set next_order_node's parent = cur_order_node
 *      7.5 AddOrder(order, next_order_node)
 * 
 * 8. Else if root.data equal to cur_order_node.data 
 *      8.1 Increase root's quantity with 1
 *      8.2 Increase order's index with 1
 *      8.3 Set root.child = next_order_node
 *      8.4 Set next_order_node's parent = cur_order_node
 *      8.5 AddOrder(order, next_order_node)
 * 
 * 9. Else if root.sibling == null
 *      9.1 Set root's sibling = cur_order_node
 *      9.2 Set cur_order_node's parent = root.parent (root is previous sibling)
 *      9.3 Increase order's index with 1
 *      9.4 Increase cur_order_node's quantity with 1
 *      9.5 Set cur_order_node's child = next_order_node
 *      9.6 Set next_order_node's parent = cur_order_node
 *      9.7 AddOrder(order, next_order_node) // this was cur_order_node I thought this might be wrong check later.
 * 10. Else
 *      10.1 AddOrder(order, next_order_node)
 */


public class OrderSystem 
{
    private int index;
    private Node root;
    private boolean isAlph;
    private String alphabetType;
    private Node cur_order_node;
    private Node next_order_node;

    OrderSystem(String alphabetType)
    {
        this.index = 0;
        this.root = null;
        this.isAlph = false;
        this.alphabetType = alphabetType;
    }

    public void AddOrder(String order[], Node root)
    {
        if(isAlph == false) // 1.
        {
            Alphabetical(order, this.alphabetType); 
            isAlph = true; 
        }
        cur_order_node = new Node(order[index]); // 2.
        next_order_node = null; // 3.
        if((index + 1) >= order.length) //4. 
        {
            cur_order_node.increase_quaintity();
            System.out.println("Order addition successfully operated");
            index = 0;
            isAlph = false;
            // Do not call recursive will end operation // return
            return;
        }   
        else // 5.
            next_order_node = new Node(order[index+1]);
        if(this.root == null) // 6.
        {
            this.root = cur_order_node;
            this.root.set_parent_node(null);
            this.root.set_sibling_node(null);
            this.root.increase_quaintity();
            index++;
            this.root.set_child_node(next_order_node);
            next_order_node.set_parent_node(cur_order_node);
            AddOrder(order, next_order_node);
        }
        if(root == null && this.root.get_data().equals(cur_order_node.get_data())) // 7.
        {
            this.root.increase_quaintity();
            index++;
            this.root.get_child_node().set_sibling_node(next_order_node);
            next_order_node.set_parent_node(cur_order_node);
            AddOrder(order, next_order_node);
        }
        else if(root.get_data().equals(cur_order_node.get_data())) // 8.
        {
            root.increase_quaintity();
            index++;
            root.set_child_node(next_order_node);
            next_order_node.set_parent_node(cur_order_node);
            AddOrder(order, next_order_node);
        }
        else if(root.get_sibling_node() == null)
        {
            root.set_sibling_node(cur_order_node);
            cur_order_node.set_parent_node(root.get_parent_node());
            index++;
            cur_order_node.increase_quaintity();
            cur_order_node.set_child_node(next_order_node);
            next_order_node.set_parent_node(cur_order_node);
            AddOrder(order, next_order_node);
        }
        else // on process 
            AddOrder(order, next_order_node);
    }


    public void setAlphabetType(String alphabetType)
    {
        this.alphabetType = alphabetType;
    }

    public String[] Alphabetical(String[] order, String AlphabetType)
    {
        switch (AlphabetType.toLowerCase()) {
            case "en" -> Arrays.sort(order); // If Alphabet is english, then that means we can sort the array in Alphabetical order.
            case "tr" -> 
            {
                // Turn order Array to list in order to make changes
                List<String> list = Arrays.asList(order);
                Collator collator = Collator.getInstance(new Locale("tr", "TR"));
                list.sort((s1, s2) -> collator.compare(s1, s2));
                list.toArray(order);
            }
            default -> 
            {
                return null;
            }
        }
        return order;
    }
    
}