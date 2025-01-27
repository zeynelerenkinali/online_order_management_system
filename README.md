# Online Order Management System

This project implements a basic **Online Order Management System** in Java. It allows users to manage orders, including adding, canceling, querying, and printing them using a custom tree structure. The system also provides support for sorting orders alphabetically based on different languages (English or Turkish).

## Features

- **Add Order**: Insert a new order into the system and store it in a custom tree structure.
- **Cancel Order**: Remove an existing order from the system.
- **Query Orders**: Search for specific orders and retrieve the number of occurrences.
- **Alphabetical Sorting**: Orders are alphabetized based on the selected language (English or Turkish).
- **Tree Structure Printing**: Print the order structure to easily visualize the current state of the system.

## Class Structure

### 1. `OrderSystem`

The `OrderSystem` class is responsible for managing the order tree and includes the following key methods:

- **OrderSystem(String alphabetType)**: Initializes the order system with the specified alphabet type (`en` for English or `tr` for Turkish).
- **AddOrder(String[] order, Node root, int index, boolean siblingSearch)**: Adds an order to the tree structure.
- **CancelOrder(String[] order, Node current_root, Node previous_root, int index)**: Cancels an order and adjusts the tree structure accordingly.
- **Query(String[] query, Node root, int index)**: Searches for an order and returns the number of occurrences.
- **getQueryCount()**: Returns the number of occurrences of the queried order.
- **setAlphabetType(String alphabetType)**: Sets the alphabet type for sorting orders (`en` for English, `tr` for Turkish).
- **alphabetical(String[] order, String alphabetType)**: Sorts the given order array alphabetically based on the specified language.

### 2. `Node`

The `Node` class represents a node in the tree structure. It contains information about an order, its relationships to other nodes, and its quantity. The key methods of this class include:

- **Node(String data, int index)**: Constructor to initialize a node with data and an index.
- **set_parent_node(Node parent_node)**: Sets the parent node for the current node.
- **set_child_node(Node child_node)**: Sets the child node for the current node.
- **set_sibling_node(Node sibling_node)**: Sets the sibling node for the current node.
- **set_data(String data)**: Updates the data held by the node.
- **set_quantity(int quantity)**: Updates the quantity of the order represented by the node.
- **set_index(int index)**: Updates the index of the node.
- **get_parent_node()**: Returns the parent node.
- **get_child_node()**: Returns the child node.
- **get_sibling_node()**: Returns the sibling node.
- **get_data()**: Returns the data of the node.
- **get_quantity()**: Returns the quantity of the node's data.
- **get_index()**: Returns the index of the node.
- **increase_quantity()**: Increases the quantity of the node's data by 1.
- **decrease_quantity()**: Decreases the quantity of the node's data by 1.

### 3. `Main`

The `Main` class handles user interaction through a command-line interface and provides the following functionality:

- **Main Menu**: Users can choose from the following options:
  1. Add Order
  2. Cancel Order
  3. Query the Product Set
  4. Print the Order Tree
  5. Exit
- **Input Validation**: The system checks user inputs to ensure that the orders and queries are provided in a valid format.
- **Order List Management**: Orders are stored in an `ArrayList` for easy management and access during the canceling and querying operations.
- **clearScreen()**: Utility method to clear the console screen.
- **isInteger(String str)**: Utility method to check if a string is a valid integer.

## How to Run

1. Clone the repository or download the source code.
2. Compile and run the `Main` class.
3. Follow the on-screen instructions to add, cancel, query, or print orders.

## Example Usage

```bash
---------------------------
||Order Management System||
---------------------------

Please select an alphabet type(en, tr): en

Alphabet is successfully set up as English.

-------------
|Main Menu|
-------------
1.Add Order
2.Cancel Order
3.Querying the Product Set
4.Print
5.Exit
-------------
Choice: 1

------------
|Add Order|
------------

Please enter your order(as order1,order2,order3): bread,milk,eggs

Order Successfully Added.

-------------
|Main Menu|
-------------
1.Add Order
2.Cancel Order
3.Querying the Product Set
4.Print
5.Exit
-------------
Choice: 4

Order Tree:
- bread
  - milk
    - eggs
```
## Requirements

- Java 8 or higher
- Command-line access

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve this system. If you have suggestions for new features, improvements, or bug fixes, please let us know by creating a new issue or submitting a pull request.

## License

This project is licensed under the MIT License. 
