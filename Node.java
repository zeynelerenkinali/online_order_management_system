/*
 * Node class of Binary Tree
 * Includes, main node functions: set_parent_node, set_child_node, set_sibling_node, set_data, get_child_node, get_sibling_node, get_parent_node, get_data.
 * When the node first created: data that node going to hold will assigned to it, and other nodes that initialized first going to declared as null. 
 */
public class Node 
{
    private Node parent_node, child_node, sibling_node;
    private int data, quantity;

    Node(int data)
    {
        this.data = data;
        this.quantity = 0;
        this.parent_node = null;
        this.child_node = null;
        this.sibling_node = null;
    }

    public void set_parent_node(Node parent_node)
    {
        this.parent_node = parent_node;
    }
    public void set_child_node(Node child_node)
    {
        this.child_node = child_node;
    }
    public void set_sibling_node(Node sibling_node)
    {
        this.sibling_node = sibling_node;
    }
    public void set_data(int data)
    {
        this.data = data;
    }
    public void set_quantity(int quantity)
    {
        this.quantity = quantity;
    }


    public Node get_parent_node()
    {
        return this.parent_node;
    }
    public Node get_child_node()
    {
        return this.child_node;
    }
    public Node get_sibling_node()
    {
        return this.sibling_node;
    }
    public int get_data()
    {
        return this.data;
    }
    public int get_quantity()
    {
        return this.quantity;
    }

    //----Additional Functions----
    public void increase_quaintity()
    {
        this.quantity++;
    }
}