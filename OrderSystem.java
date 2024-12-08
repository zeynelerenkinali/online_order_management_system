import java.text.Collator;
import java.util.*;
/*
 * Algorithm of Each Funciton 
 * - OrderSystem()
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
 * 4. If (index+1) >= order.length // Means add operation is end, base condition of recursive loop
 *     4.1 Increase root's quantity with 1
 *     4.2 print("order addition successfully operated")
 *     4.3 Set Index = 0
 *     4.4 Set isAlph = false;
 *     4.5 return true (boolean return will end previous one's)
 * 5. Else
 *     5.1 Declare next_order_node with order[index+1] data
 * 6. If this.root of the list is null  // Means this is our first order's first iteration
 *      6.1 Set this.root as cur_order_node
 *      6.2 Set this.root's parent as null
 *      6.3 Set this.root's sibling as null
 *      6.4 Increase this.root's quantity with 1
 *      6.5 Increase order's index with 1
 *      6.6 Set next_order_node's parent as this.root
 *      6.7 Set this.root.child as next_order_node
 *      6.8 AddOrder(order, this.root.child) // return the second iteration original root's child
 * 
 * // In order to solve order quantity update problem, we can hold a quantity in each node in order to hold, amount of product
 * // at each node for each different path easily. Then we can increase that whenever we want, with holding previous pointer
 * 
 * 7. If (root is equal to null) and (this.root equal to cur_order_node) // Means we are adding second or more order but at first iteration * We can add change main root with adding else if down side easily
 *      7.1 Increase this.root's quantity with 1
 *      7.2 Increase order's index with 1
 *      *Now we have two option roots are same but we don't know about new order's second element, so we don't know are we gonna connect as child or not but we know we have to connect as parent.
 *      *So we have to check and select how.
 *      *Now for testing purposes we sent the root.child 
 *      7.3 AddOrder(order, this.root.child)
 * 
 * 8. Else if root.data equal to cur_order_node.data // This means, original tree's part is meets with cur_order's index numbered element, it could meet anywhere except this.root 
 *      8.1 Increase root's quantity with 1
 *      8.2 Increase order's index with 1 
 *      *At here we don't know last element of the array is same as last element(child) of the current tree so
 *      8.3 If !(root.child equals next_order_node,) // that means we need to add this child as child's sibling so I'm saying take the root but don't end the loop
 *          8.3.1 Decrease order's index with 1
 *      8.4 Else
 *          8.4.1 Set next_order_node parent = root
 *          8.4.2 Set root.child = next_order_node          
*       8.4 AddOrder(order, root.child)
 * 
 * 9. Else if root.sibling == null
 *      9.1 Set cur_order_node's parent = root.parent (root is previous sibling) 
 *      9.2 Increase order's index with 1 // order index is increase now it will finishes the loop
 *      9.3 Set root's sibling = cur_order_node
 *      9.4 If (next_order_node != null)
 *          9.4.1 Increase root's sibling quantitiy
 *          9.4.2 Set next_order_node.parent = root's sibling
 *          9.4.3 Set root's sibling.child = next_order_node
 *          9.4.4 AddOrder(order, root's sibling.child)
 *      9.5 AddOrder(order, root.sibling) 
 * 10. Else
 *      10.1 AddOrder(order, root.sibling) // go through sibling until reaches null sibling
 * 
 * - print(Node root)
 * **Logic: Every node except root node have only one child and unknown amount of siblings
 *          Root, does not have sibling only one child
 * 1. If root equals null (means first call)
 *      1.1 set root node to this.root
 * 2. Print node printing structure
 * 3. Print current root node with (quantity) in parantheses
 * 4. If root.child not equals null
 *      4.1 print child passing gui structure
 *      4.2 print(root.child)
 * 5. If root.sibling not equals null
 *      5.1 print sibling passing structure
 *      5.2 print(root.sibling) 
 */
public class OrderSystem 
{
    private int index;
    private int space_index;
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

    public boolean AddOrder(String order[], Node root)
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
            root.increase_quaintity();
            System.out.println("CONDITION: Order addition successfully operated.");
            index = 0;
            isAlph = false;
            // Returning true will end recursive operations
            return true;
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
            if (next_order_node != null) {
                next_order_node.set_parent_node(cur_order_node);
            }
            this.root.set_child_node(next_order_node);
            return AddOrder(order, this.root.get_child_node());
        }
        if(root == null && (this.root.get_data() == null ? cur_order_node.get_data() == null : this.root.get_data().equals(cur_order_node.get_data()))) // 7.
        {
            this.root.increase_quaintity();
            index++;
            return AddOrder(order, this.root.get_child_node());
        }
        else if(root.get_data() == null ? cur_order_node.get_data() == null : root.get_data().equals(cur_order_node.get_data())) // 8.
        {
            root.increase_quaintity();
            index++;
            if (root.get_child_node() == null ? next_order_node == null : !root.get_child_node().get_data().equals(next_order_node.get_data()))
                    index--;
            else if(root.get_child_node() == null)
            {
                next_order_node.set_parent_node(root);
                root.set_child_node(next_order_node);
            }
            return AddOrder(order, root.get_child_node());
        }
        else if(root.get_sibling_node() == null)
        {
            if(next_order_node.get_data() == null ? root.get_data() == null : next_order_node.get_data().equals(root.get_data()))
            {
                cur_order_node = next_order_node;
                next_order_node = null;
            }
            cur_order_node.set_parent_node(root.get_parent_node());
            index++;
            root.set_sibling_node(cur_order_node);
            if(next_order_node != null)
            {
                root.get_sibling_node().increase_quaintity();
                next_order_node.set_parent_node(root.get_sibling_node());
                root.get_sibling_node().set_child_node(next_order_node);
                return AddOrder(order, root.get_sibling_node().get_child_node());
            }
            return AddOrder(order, root.get_sibling_node());
        }
        else
        {
            return AddOrder(order, root.get_sibling_node());
        }
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
    public void print(Node root)
    {
        if(root == null) // 1.
        {
            root = this.root; // 1.1
            this.space_index = 1;
            System.out.print("root");
            System.out.println();
        }
        System.out.print("└──"); // 2.
        System.out.print(root.get_data() + " (" + root.get_quantity() + ")");
        System.out.println();
        if(root.get_child_node() != null)
        {
            for(int i = 0; i < this.space_index; i++)
            {
                System.out.print("   ");
            }
            this.space_index++;
            print(root.get_child_node());
        }
        if(root.get_sibling_node() != null)
        {
            this.space_index = 2;
            for(int i = 0; i < this.space_index - 1; i++)
            {
                System.out.print("   ");
            }
            print(root.get_sibling_node());
        }
    }    

}