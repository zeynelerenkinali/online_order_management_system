import java.text.Collator;
import java.util.*;
/*
 * OrderSystem class  
 * Includes; 
 * 1. OrderSystem(String alphabetType) - Constructor
 * 2. AddOrder() - public boolean AddOrder(String order[], Node root, int index, boolean siblingSearch)
 * 3. CancelOrder() - public boolean CancelOrder(String order[], Node current_root, Node previous_root, int index)
 * 4. Query() - public void Query(String Query[], Node root, int index)
 * 5. getQueryCount() - public int getQueryCount()
 * 6. setAlphabetType() - public void setAlphabetType(String alphabetType)
 * 7. alphabetical() - public String[] alphabetical(String[] order, String AlphabetType)
 * 8. print() - public void print(Node root, String space)
 */
public class OrderSystem 
{
    private Node root;
    private int queryCount;
    private boolean isAlph;
    private String alphabetType;

    OrderSystem(String alphabetType)
    {
        this.root = null;
        this.isAlph = false;
        this.alphabetType = alphabetType;
    }

    public boolean AddOrder(String order[], Node root, int index, boolean siblingSearch)
    {
        if(isAlph == false){
            alphabetical(order, this.alphabetType); 
            isAlph = true;
         }
        if(index >= order.length){
            isAlph = false;
            return true;
        } 
        Node newNode = new Node(order[index], index);
        root = (root == null) ? this.root : root;
        if(this.root == null){
            this.root = newNode;
            root = newNode;
        }
        if(root.get_data().equals(order[index])){
            root.increase_quaintity();
            return AddOrder(order, root.get_child_node() == null ? root : root.get_child_node(), index + 1, false);
        }
        else if(root.get_index() == index) siblingSearch = true;
        if(root.get_child_node() == null && !siblingSearch){
            root.set_child_node(newNode);
            newNode.set_parent_node(root);
            newNode.increase_quaintity();
            return AddOrder(order, root.get_child_node(), index + 1, false);
        }
        if(root.get_sibling_node() == null){
            Node siblingNode = new Node(order[index], index);
            root.set_sibling_node(siblingNode);
            siblingNode.set_parent_node(root.get_parent_node());
            siblingNode.increase_quaintity();
            return AddOrder(order, root.get_sibling_node(), index + 1, false);
        }
        else return AddOrder(order, root.get_sibling_node(), index, true); // At sibling we are searching for equal sibling that's why we sent isSibling as true
    }
    
    public boolean CancelOrder(String order[], Node current_root, Node previous_root, int index)
    {
        if(index >= order.length) return true;
        if(current_root == null) current_root = this.root;
        if(Objects.equals(current_root.get_data(), order[index]))
        {
            if(current_root.get_quantity() >= 2)
            {
                current_root.decrease_quaintity();
                return CancelOrder(order, current_root.get_child_node(), null, index + 1);
            }
            else // In delete operation there is two option, it will be itself, or will be sibling.
            {
                if(this.root.get_data() == null ? order[index] == null : this.root.get_data().equals(order[index]))
                {
                    if(this.root.get_sibling_node() != null) this.root = this.root.get_sibling_node();
                    else this.root = null;
                }
                else if(previous_root == null) current_root.get_parent_node().set_child_node(current_root.get_sibling_node()); 
                else previous_root.set_sibling_node(null);   
                return true;
            }
        }
        else return CancelOrder(order, current_root.get_sibling_node(), current_root, index);
    }
    
    public void Query(String Query[], Node root, int index)
    {
        if(root == null)
        {
            root = this.root;
            queryCount = 0;
        }
        if(root.get_data().equals(Query[index]))
        {
            if(index == Query.length - 1)
            {
                queryCount += root.get_quantity();
                return;
            }
            else index++;
        }
        if(root.get_child_node() != null) Query(Query, root.get_child_node(), index);
        if(root.get_sibling_node() != null) Query(Query, root.get_sibling_node(), index);
    } 
    
    public int getQueryCount()
    {
        return this.queryCount;
    }

    public void setAlphabetType(String alphabetType)
    {
        this.alphabetType = alphabetType;
    }

    public String[] alphabetical(String[] order, String AlphabetType)
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
        System.out.print(space + "└──" + root.get_data() + " (" + root.get_quantity() + ")" + " [P(" + (root.get_parent_node() == null ? "null" : root.get_parent_node().get_data())  + ") " + "| S(" + (root.get_sibling_node() == null ? "null" : root.get_sibling_node().get_data())  + ")]\n");
        if(root.get_child_node() != null) print(root.get_child_node(), space + "   ");
        if(root.get_sibling_node() != null) print(root.get_sibling_node(), space + "");        
    }    
}