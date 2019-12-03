//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Shopping Cart
// Files: ShoppingCart.java, ShoppingCartTests.java
// Course: CS 300 Spring 2019
//
// Author: Chris George
// Email: crgeorge@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

/**
 * The Shopping Cart class holds the methods in order to provide the user with an experience that
 * mirrors a real- life grocery store/cart. Users can add items to their cart, remove items, see
 * many of an item is in the cart, and checkout their items.
 * 
 * @author chrisgeorge
 *
 */
public class ShoppingCart {
  // Define final parameters
  private static final int CART_CAPACITY = 20; // shopping cart max capacity
  private static final double TAX_RATE = 0.05; // sales tax

  // a perfect-size two-dimensional array that stores the available items in the market
  // MARKET_ITEMS[i][0] refers to a String that represents the description of the item
  // identified by index i
  // MARKET_ITEMS[i][1] refers to a String that represents the unit price of the item
  // identified by index i in dollars.
  public static final String[][] MARKET_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

  /**
   * adds the item with the given identifier index at the end of the cart
   * 
   * @param index of the item within the marketItems array
   * @param cart  shopping cart
   * @return the number of items present in the cart after the item with identifier index is added
   */
  public static int add(int index, String[] cart, int count) {
    if (count == cart.length) { // if the cart is full
      System.out.println("WARNING: The cart is full. You cannot add any new item.");
      return count;
    } else {
      cart[count] = MARKET_ITEMS[index][0]; // adds the item to the end of the cart
      return count + 1;
    }
  }

  /**
   * Returns how many occurrences of the item with index itemIndex are present in the shopping cart
   * 
   * @param itemIndex identifier of the item to count its occurrences in the cart
   * @param cart      shopping cart
   * @param count     number of items present within the cart
   * @return the number of occurrences of item in the cart
   */
  public static int occurrencesOf(int itemIndex, String[] cart, int count) {
    int occurrences = 0;
    for (int i = 0; i < count; i++) {
      // iterates through cart looking for the item in question, records occurrences
      if (cart[i].equalsIgnoreCase(MARKET_ITEMS[itemIndex][0])) {
        occurrences++;
      }
    }
    return occurrences;
  }

  /**
   * Removes the first (only one) occurrence of itemToRemove if found and returns the number of
   * items in the cart after remove operation is completed either successfully or not
   * 
   * @param itemToRemove name of Item the user wants to remove
   * @param cart         shopping cart
   * @param count        number of items in the cart
   * @return the cart count after an item is/isn't removed
   */
  public static int remove(String itemToRemove, String[] cart, int count) {
    int itemIndex = indexOf(itemToRemove, cart, count); // convert item name to catalog index
    if (itemIndex == -1) { // prints warning if the itemIndex is not in the cart
      System.out.println("WARNING: " + itemToRemove + " not found in the shopping cart.");
    } else {
      cart[itemIndex] = cart[count - 1]; // moves last item in the cart to the index of removed item
      cart[count - 1] = null; // empties old index of last item
      count--; // update count
    }
    return count;
  }

  /**
   * returns the total value (cost) of the cart without tax in $ (double)
   * 
   * @param cart  shopping cart
   * @param count number of items in the cart
   * @return the subTotal cost of the items in the cart
   */
  public static double getSubTotalPrice(String[] cart, int count) {
    double totalPrice = 0;
    for (int i = 0; i < count; i++) {
      // adds together prices of all the items in the cart
      totalPrice += priceOf(cart[i], MARKET_ITEMS); 
    }

    return totalPrice;
  }

  /**
   * prints the Market Catalog (item identifiers, description, and unit prices)
   * 
   */
  public static void printMarketCatalog() {
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id" + "\t\t" + "Description" + "    \t " + "Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    for (int i = 0; i < MARKET_ITEMS.length; i++) {
      // prints out every index, item and price with the correct formatting
      System.out.println(i + "\t\t" + MARKET_ITEMS[i][0] + "    \t " + MARKET_ITEMS[i][1]);
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

  /**
   * Displays the cart content (items separated by commas)
   * 
   * @param cart  shopping cart
   * @param count number of items in the cart
   */
  public static void displayCartContent(String[] cart, int count) {
    System.out.print("Cart Content: ");
    for (int i = 0; i < count; i++) {
      // prints all items in the cart with spaces and commas
      System.out.print(cart[i] + ", ");
    }
    System.out.println();
  }

  /**
   * Returns the index of an item within the shopping cart
   * 
   * @param item  description
   * @param cart  Shopping cart
   * @param count number of items present in the shopping cart
   * @return index of the item within the shopping cart, and -1 if the item does not exist in the
   *         cart
   */
  private static int indexOf(String item, String[] cart, int count) {
    for (int i = 0; i < count; i++) {
      // searches the cart for the name of the item in question
      if (cart[i].equalsIgnoreCase(item)) {
        return i; // if item found, returns the index
      }
    }

    return -1; // if the item is not found, returns -1
  }

  /**
   * Returns the price of an item within the catalog
   * 
   * @param item    description
   * @param catalog market items
   * @return price of item
   */
  private static double priceOf(String item, String[][] catalog) {
    double price = 0.0;
    for (int i = 0; i < catalog.length; i++) {
      // searches the catalog for the item name
      if (item.equals(catalog[i][0])) {
        price = Double.valueOf(catalog[i][1].substring(1)); // converts price string to double value
      }
    }

    return price;
  }

  /**
   * Prints out the command menu and "ENTER COMMAND: "
   * 
   */
  public static void printCommandMenu() {
    System.out.print("COMMAND MENU:\n" + " [P] print the market catalog\n"
        + " [A <index>] add one occurrence of an item to the cart given its identifier\n"
        + " [C] checkout\n" + " [D] display the cart content\n"
        + " [O <index>] number of occurrences of an item in the cart given its identifier\n"
        + " [R <index>] remove one occurrence of an item from the cart given its identifier\n"
        + " [Q]uit the application\n" + "\n" + "ENTER COMMAND: ");
  }

  /**
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String command = "";
    String itemToRemove;
    String[] commandArray;
    String[] userCart = new String[CART_CAPACITY]; // creates the user's cart
    char commandFirst = 'x';
    int commandIndex = 0;
    int count = 0;
    double subTotal = 0.0;



    System.out.print("=============   Welcome to the Shopping Cart App   =============\n\n");

    // loop runs until user quits
    while (commandFirst != 'q' && commandFirst != 'Q') {
      System.out.println();
      printCommandMenu();
      command = sc.nextLine().trim();  // reads user input without leading or trailing whitespace
      commandArray = command.split(" "); // separates user input by the space for index commands
      commandFirst = commandArray[0].charAt(0); // first examines the first character
      // switch case assesses the first character of command, pr accordingly
      switch (commandFirst) {
        // Print
        case 'p':     
        case 'P': 
          printMarketCatalog();
          break;
        // Add
        case 'a':
        case 'A':
          // reads the next entry of the command array for item index
          commandIndex = Integer.parseInt(commandArray[1]); 
          count = add(commandIndex, userCart, count); // updates count and adds item
          break;
        // Checkout
        case 'c':
        case 'C':
          subTotal = getSubTotalPrice(userCart, count); // computes subTotal
          // using subTotal, prints and formats tax, total with tax, and # items
          System.out.println("#items: " + count + " Subtotal: $" + String.format("%.2f", subTotal)
              + " Tax: $" + String.format("%.2f", TAX_RATE * subTotal) + " TOTAL: $"
              + String.format("%.2f", (TAX_RATE * subTotal + subTotal)));
          break;
        // Display
        case 'd':
        case 'D':
          displayCartContent(userCart, count);
          break;
        // Occurrences
        case 'o':
        case 'O':
          // reads the next entry of the command array for item index
          commandIndex = Integer.parseInt(commandArray[1]);
          // prints item , index and occurrences in cart
          System.out.println("The number of occurrences of " + MARKET_ITEMS[commandIndex][0]
              + " (id #" + commandIndex + ") is: " + occurrencesOf(commandIndex, userCart, count));
          break;
        // Remove
        case 'r':
        case 'R':
          // reads the next entry of the command array for item index
          commandIndex = Integer.parseInt(commandArray[1]);
          // identifies the name of item to remove using the index
          itemToRemove = MARKET_ITEMS[commandIndex][0];
          count = remove(itemToRemove, userCart, count); // updates count and removes item
          break;
        // Quit
        case 'q':
        case 'Q':
          break;
      }
    }
    System.out.println("=============  Thank you for using this App!!!!!  =============");

  }
}


