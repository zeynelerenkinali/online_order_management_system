/*
 * Algorithm
 * OrderSystem()
 * 1. Declare index = 0
 * 2. Declare this.root = null
 * 3. Declare boolean isAlph = false
 * 
 * - AddOrder(String order[], Node root)
 * 1. If isAlph false
 *     1.1 Sent order to Alphabetical(String order[], String AlphabetType) *ENG and TUR*
 * 2. If (index+2) >= order.size
 *     2.1 print order addition successfully operated.
 *     2.2 index = 0
 *     2.3 isAlph = false;
 *     2.4 return 
 * 3. If this.root of the list is null
 *      3.1 Set first index of the order as this.root
 *      3.2 Set this.root parent as null
 *      3.3 Set this.root sibling as null
 *      3.4 Increase this.root quantity 1
 *      3.5 Increase index 1
 *      3.6 Set this.root.child as order[index]
 *      3.7 AddOrder(order, order[index])
 * 
 * // In order to solve order quantity update problem, we can hold a quantity in each node in order to hold, amount of product
 * // at each node for each different path easily. Then we can increase that whenever we want, with holding previous pointer
 * 
 * 4. Else if is root equal to current order[index]
 *      4.1 Increase root's quantity 
 *      4.2 Increase order's index
 *      4.3 Set root.child = order[index]
 *      4.4 AddOrder(order, order[index])
 * 5. Else if root.sibling == null
 *      5.1 Set current root's sibling = order[index]  
 *      5.2 Increase order's index
 *      5.3 Increase root's quantity
 *      5.4 Set current root's child = order[index]
 *      5.5 AddOrder(order, order[index])
 */


public class OrderSystem 
{

}