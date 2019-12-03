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

/**
 * This class contains a few methods for testing methods in the ShoppingCart class as they are
 * developed. These methods are private since they are only intended for use within this class.
 * 
 * @author chrisgeorge
 *
 */
public class ShoppingCartTests {

  /**
   * Checks whether the total number of items within the cart is incremented after adding one item
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testCountIncrementedAfterAddingOnlyOneItem() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[20]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // Add an item to the cart
    count = ShoppingCart.add(3, cart, count); // add an item of index 3 to the cart
    // Check that count was incremented
    if (count != 1) {
      System.out.println("Problem detected: After adding only one item to the cart, "
          + "the cart count should be incremented. But, it was not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks whether add and OccurrencesOf return the correct output when only one item is added to
   * the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddAndOccurrencesOfForOnlyOneItem() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 10
    String[] cart = new String[10]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // check that OccurrencesOf returns 0 when called with an empty cart
    if (ShoppingCart.occurrencesOf(10, cart, count) != 0) {
      System.out.println("Problem detected: Tried calling OccurrencesOf() method when the cart is "
          + "empty. The result should be 0. But, it was not.");
      testPassed = false;
    }

    // add one item to the cart
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart
    // check that OccurrencesOf("Apples", cart, count) returns 1 after adding the item with key 0
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      System.out.println("Problem detected: After adding only one item with key 0 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 1. "
          + "But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that items can be added more than one time and are found
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddOccurrencesOfDuplicateItems() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 12
    String[] cart = new String[12]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
    
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart
   
    // check that OccurrencesOf("Apples", cart, count) returns 3 after adding the item with key 0
    if (ShoppingCart.occurrencesOf(0, cart, count) != 3) {
      System.out.println("Problem detected: After adding 3 of the item with key 0 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 3. "
          + "But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that the correct output is returned when the user tries to add too much items to the
   * cart exceeding its capacity
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddingTooMuchItems() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // arbitrary size-- 3
    String[] cart = new String[3]; // shopping cart
    int count = 0;
    
    // try to add 4 items to a cart with a max capacity of 3
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    
    // checks if the count is correct after trying to add too many items
    if (count != 3) {
      System.out.println("Problem detected: After meeting capacity of the cart, the count should"
          + " stay at the maximum capacity (3). But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  // checks that occurrencesOf works properly with multiple items in cart (not duplicates)
  public static boolean testOccurrencesOfMultiple() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 12
    String[] cart = new String[12]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
    
    // add items to the cart, including
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(1, cart, count);
    count = ShoppingCart.add(1, cart, count);
    count = ShoppingCart.add(1, cart, count);
    count = ShoppingCart.add(2, cart, count);

    // series of if statements checks if the correct occurrence values are computed
    if (ShoppingCart.occurrencesOf(0, cart, count) != 2) {
      System.out.println("Problem detected: After adding 2 items with key 0 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 2. "
          + "But, it was not the case.");
      testPassed = false;
    }
    if (ShoppingCart.occurrencesOf(1, cart, count) != 3) {
      System.out.println("Problem detected: After adding 3 items with key 1 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 3. "
          + "But, it was not the case.");
      testPassed = false;
    }
    if (ShoppingCart.occurrencesOf(2, cart, count) != 1) {
      System.out.println("Problem detected: After adding only one item with key 2 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 1. "
          + "But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * checks that occurrencesOf returns 0 when there are items in the cart, but not the item in
   * question
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testOccurrencesOfZero() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 12
    String[] cart = new String[12]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
    
    // add some items to cart (not index 1 for this test)
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(2, cart, count);
    
    // checks if occurrences of index 1 is correctly 0
    if (ShoppingCart.occurrencesOf(1, cart, count) != 0) {
      System.out.println("Problem detected: After adding no items with key 1 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 0. "
          + "But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that when only one attempt to remove an item present in the cart is made, only one
   * occurrence of that item is removed from the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveOnlyOneOccurrenceOfItem() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 12
    String[] cart = new String[12]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
    
    // add 2 of the same item, and 2 other items to cart
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(2, cart, count);
    count = ShoppingCart.add(1, cart, count);

    // should remove only 1 apple
    count = ShoppingCart.remove("Apple", cart, count);

    // checks that there is 1 apple remaining after remove method
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      System.out.println("Problem detected: more than 1 occurrence of the item was removed");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that remove does not make any change to count (number of items in the cart) when the
   * user tries to remove an item not present within the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveItemNotFoundInCart() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 12
    String[] cart = new String[12]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
    
    // add some items to cart
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(2, cart, count);
    count = ShoppingCart.add(1, cart, count);

    // tries to remove an item that is not in the cart
    count = ShoppingCart.remove("Grape", cart, count);

    // checks that the count remains 4 (number of items in cart before remove method called)
    if (count != 4) {
      System.out.println("Problem detected: count changed when nothing was removed");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that remove does not change the count if the cart is empty
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveWhenCartEmpty() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 12
    String[] cart = new String[12]; // shopping cart
    int count = 0; // number of items present in the cart (the cart is empty)

    // tries to remove an item that is not in the cart
    count = ShoppingCart.remove("Grape", cart, count);

    // checks that the count remains 0 (number of items in cart before remove method called)
    if (count != 0) {
      System.out.println("Problem detected: count changed when the cart was already empty");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that remove works properly when the last item is removed
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveForLastItem() {
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // set cart size to 4 (want to fill the whole cart
    String[] cart = new String[4]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
    
    // add some items to cart
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(2, cart, count);
    count = ShoppingCart.add(1, cart, count);

    // removes the last item
    count = ShoppingCart.remove("Avocado", cart, count);

    // checks that the count is updated and the last Item is correct
    if (count != 3 || !cart[count - 1].equals("Banana")) {
      System.out.println("Problem detected: remove() did not properly remove the last item");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * checks that the subtotal is properly computed
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testSubtotalCalc() {
    double subTotal = 0.0;
    boolean testPassed = true; // evaluated to true if test passed without problems, false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // set cart size to arbitrary number -- 4
    String[] cart = new String[4]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)
    
    // add the items from the write up demo to the cart (milk, apple, tomato)
    count = ShoppingCart.add(17, cart, count);
    count = ShoppingCart.add(0, cart, count);
    count = ShoppingCart.add(24, cart, count);

    subTotal = ShoppingCart.getSubTotalPrice(cart, count); // calculate subTotal

    if (Math.abs(subTotal - 5.47) > 0.001) { // epsilon comparison because subTotal is a double
      System.out.println("Problem detected: subtotal was not properly computed");
      testPassed = false;
    }

    return testPassed;

  }

  /**
   * main method used to call the unit tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    // print statements show results of the tests
    System.out.println("testCountIncrementedAfterAddingOnlyOneItem(): "
        + testCountIncrementedAfterAddingOnlyOneItem());
    System.out.println(
        "testAddAndOccurrencesOfForOnlyOneItem(): " + testAddAndOccurrencesOfForOnlyOneItem());
    System.out
        .println("testAddOccurrencesOfDuplicateItems(): " + testAddOccurrencesOfDuplicateItems());
    System.out.println("testAddingTooMuchItems(): " + testAddingTooMuchItems());
    System.out.println("testOccurrencesOfMultiple(): " + testOccurrencesOfMultiple());
    System.out.println("testOccurrencesOfZero(): " + testOccurrencesOfZero());
    System.out
        .println("testRemoveOnlyOneOccurrenceOfItem(): " + testRemoveOnlyOneOccurrenceOfItem());
    System.out.println("testRemoveItemNotFoundInCart(): " + testRemoveItemNotFoundInCart());
    System.out.println("testRemoveWhenCartEmpty(): " + testRemoveWhenCartEmpty());
    System.out.println("testRemoveForLastItem(): " + testRemoveForLastItem());
    System.out.println("testSubtotalCalc(): " + testSubtotalCalc());

  }
}
