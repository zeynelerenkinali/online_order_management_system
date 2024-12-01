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
 * 2. Declare cur_order_node with order[index] data
 * 3. Initialize next_order_node as null
 * 4. If (index+1) >= order.size
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
 *      7.5 AddOrder(order, this.root.child)
 * 
 * 8. Else if root equal to cur_order_node 
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
 *      9.7 AddOrder(order, cur_order_node)
 */


public class OrderSystem 
{

}