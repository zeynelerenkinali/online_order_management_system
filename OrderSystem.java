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
    private int index_c;
    private Node root;
    private boolean isAlph;
    private String alphabetType;

    OrderSystem(String alphabetType)
    {
        this.root = null;
        this.isAlph = false;
        this.alphabetType = alphabetType;
    }

    public boolean AddOrder(String order[], Node root, int index)
    {
        if(isAlph == false) 
         {
            Alphabetical(order, this.alphabetType); 
            isAlph = true;
         }
        if(index >= order.length)
        {
            isAlph = false;
            return true;
        } 
        Node newNode = new Node(order[index]);
        root = (root == null) ? this.root : root;
        if(this.root == null)
        {
            this.root = newNode;
            root = newNode;
        }
        if(root.get_data() == null ? order[index] == null : root.get_data().equals(order[index]))
        {
            root.increase_quaintity();
            return AddOrder(order, root == null ? null : root.get_child_node(), index + 1);
        }
        if(root.get_child_node() == null)
        {
            root.set_child_node(newNode);
            newNode.set_parent_node(root);
            newNode.increase_quaintity();
            return AddOrder(order, root.get_child_node(), index + 1);
        }
        if(root.get_sibling_node() == null) 
        {
            Node siblingNode = new Node(order[index]);
            root.set_sibling_node(siblingNode);
            siblingNode.set_parent_node(root.get_parent_node());
            siblingNode.increase_quaintity();
            return AddOrder(order, root.get_sibling_node(), index + 1);
        }
        else return AddOrder(order, root.get_sibling_node(), index); // At sibling we are searching for equal sibling
    }
    
    
    public boolean CancelOrder(String order[], Node root)
    {
        if(index_c >= order.length) return true;
        if(root == null)
        {
            index_c = 0;
            root = this.root;
        }
        if(root.get_data() == null ? order[index_c] == null : root.get_data().equals(order[index_c]))
        {
            if(root.get_quantity() >= 2) root.decrease_quaintity();
            else
            {
                removeNode(root);
                this.root = root;
                return true;
            }
            index_c++;
            return CancelOrder(order, root.get_child_node());
        }
        else return CancelOrder(order, root.get_sibling_node());
    }

    private Node removeNode(Node node) 
    {
        if(node == this.root) this.root = null;
        return null;
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
    
    public void print(Node root, String space)
    {
        if(root == null) // 1.
        {
            root = this.root; // 1.1
            System.out.print("root\n");
        }
        System.out.print(space + "└──" + root.get_data() + " (" + root.get_quantity() + ")" + " ─> Parent(" + (root.get_parent_node() == null ? "null" : root.get_parent_node().get_data())  + ")\n");
        if(root.get_child_node() != null) print(root.get_child_node(), space + "   ");
        if(root.get_sibling_node() != null) print(root.get_sibling_node(), space + "");        
    }    
}