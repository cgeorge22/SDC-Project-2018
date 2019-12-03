//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Storage Unit Tests
// Files: Box.java, LinkedBoxNode.java, LinkedBoxList.java, StorageUnitTests.java
// Course: CS 300 Spring 2019
//
// Author: Chris George
// Email: crgeorge@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * this class contains tests for various functions of the Box class
 * 
 * @author chrisgeorge
 *
 */
public class StorageUnitTests {
  /**
   * Checks whether the behavior of equals method is correct
   * 
   * return true if all tests pass, false otherwise
   */
  public static boolean testBoxEquals() {
    boolean test = true;
    Box box1 = new Box(100, 15);
    Box box2 = new Box(100, 15);
    Box box3 = new Box(99, 14);

    if (!box1.equals(box2)) { // 2 boxes with the same values should be equal
      System.err.println("Error: boxes incorrectly evaluated as not equal");
      test = false;
    }
    if (box1.equals(box3)) { // 2 boxes with different values should not be equal
      System.err.println("Error: boxes incorrectly evaluated as equal");
      test = false;
    }

    return test;
  }

  /**
   * Checks whether the behavior of compareTo method is correctly implemented
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBoxCompareTo() {
    boolean test = true;
    Box box1 = new Box(100, 15); // two identical boxes
    Box box2 = new Box(100, 15);
    Box box3 = new Box(100, 14); // lighter box
    Box box4 = new Box(100, 16); // heavier box

    if (box1.compareTo(box2) != 0) { // equal weight
      System.err.println("Error: boxes of equal weight should return 0");
      test = false;
    }
    if (box1.compareTo(box3) <= 0) { // box 1 heavier than box 3
      System.err.println("Error: when box is heavier than otherBox, return positive number");
      test = false;
    }
    if (box1.compareTo(box4) >= 0) { // box 1 lighter than box 4
      System.err.println("Error: when box is lighter than otherBox, return negative number");
      test = false;
    }

    return test;
  }

  /**
   * Checks whether add method defined in LinkedBoxList works correctly
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLinkedBoxListAdd() {
    boolean test = true;

    // add 1 box
    {
      LinkedBoxList testList = new LinkedBoxList(5);
      Box toAdd = new Box(150, 24);
      testList.add(toAdd);
      // check size and if the box is in the list
      if (testList.size() != 1 || !testList.contains(toAdd)) {
        test = false;
        System.err.println("Error: Adding 1 box failed");
      }
    }
    // add multiple boxes with same weight
    {
      LinkedBoxList testList = new LinkedBoxList(5);
      Box toAdd = new Box(150, 24);
      Box toAdd2 = new Box(150, 24);
      Box toAdd3 = new Box(150, 24);
      testList.add(toAdd);
      testList.add(toAdd2);
      testList.add(toAdd3);
      if (testList.size() != 3) { // check size
        test = false;
        System.err.println("Error: size wrong");
      }
      if (!testList.contains(toAdd)) { // check box 1
        test = false;
        System.err.println("Error: adding 1st box failed");
      }
      if (!testList.contains(toAdd2)) { // check box 2
        test = false;
        System.err.println("Error: adding 2nd box failed");
      }
      if (!testList.contains(toAdd3)) { // check box 3
        test = false;
        System.err.println("Error: adding 3rd box failed");
      }
    }
    // add multiple boxes with different weight
    {
      LinkedBoxList testList = new LinkedBoxList(5);
      Box toAdd = new Box(150, 24);
      Box toAdd2 = new Box(150, 27);
      Box toAdd3 = new Box(150, 23);
      testList.add(toAdd);
      testList.add(toAdd2);
      testList.add(toAdd3);
      if (testList.size() != 3) { // check size
        test = false;
        System.err.println("Error: size wrong");
      }
      if (!testList.contains(toAdd)) { // check box 1
        test = false;
        System.err.println("Error: adding 1st box failed");
      }
      if (!testList.contains(toAdd2)) { // check box 2
        test = false;
        System.err.println("Error: adding 2nd box failed");
      }
      if (!testList.contains(toAdd3)) { // check box 3
        test = false;
        System.err.println("Error: adding 3rd box failed");
      }
    }

    return test;
  }

  /**
   * Checks whether remove method defined in LinkedBoxList works correctly
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLinkedBoxListRemove() {
    boolean test = true;

    // remove head
    {
      LinkedBoxList testList = new LinkedBoxList(3);
      Box toRemove = new Box(150, 21); // record reference to head box
      testList.add(toRemove);
      testList.add(new Box(150, 20));
      testList.add(new Box(150, 19));
      if (!testList.remove(0).equals(toRemove)) { // attempt to remove head, check which was removed
        test = false;
        System.err.println("Error: head box not removed correctly");
      }
    }
    // remove tail
    {
      LinkedBoxList testList = new LinkedBoxList(3);
      Box toRemove = new Box(150, 19); // record reference to tail box
      testList.add(toRemove);
      testList.add(new Box(150, 21));
      testList.add(new Box(150, 20));
      if (!testList.remove(2).equals(toRemove)) { // attempt to remove tail, check which was removed
        test = false;
        System.err.println("Error: tail box not removed correctly");
      }
    }
    // remove a box in the middle
    {
      LinkedBoxList testList = new LinkedBoxList(3);
      Box toRemove = new Box(150, 20); // record reference to middle box
      testList.add(toRemove);
      testList.add(new Box(150, 21));
      testList.add(new Box(150, 19));
      if (!testList.remove(1).equals(toRemove)) { // attempt to remove middle box, check removed
        test = false;
        System.err.println("Error: tail box not removed correctly");
      }
    }
    // when there is only one box
    {
      LinkedBoxList testList = new LinkedBoxList(3);
      Box toRemove = new Box(150, 20);
      testList.add(toRemove);
      if (!testList.remove(0).equals(toRemove)) { // attempt to remove the only box, check removed
        test = false;
        System.err.println("Error: when only one box, removal not done correctly");
      }
    }

    return test;
  }

  /**
   * Checks whether clear method defined in LinkedBoxList works correctly
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLinkedBoxListClear() {
    boolean test = true;
    LinkedBoxList testList = new LinkedBoxList(5);
    // add some items , then clear them
    testList.add(new Box(150, 24));
    testList.add(new Box(150, 24));
    testList.add(new Box(150, 24));
    testList.clear();

    if (!testList.isEmpty()) { // checks if the list is empty after clear
      test = false;
      System.err.println("Error: LinkedBoxList.clear() failed");
    }

    return test;
  }

  /**
   * main method for test class: prints out the result for every test
   * 
   * @param args -- command line arguments (unused)
   */
  public static void main(String[] args) {
    System.out.println("testBoxEquals(): " + testBoxEquals());
    System.out.println("testBoxCompareTo(): " + testBoxCompareTo());
    System.out.println("testLinkedBoxListRemove(): " + testLinkedBoxListRemove());
    System.out.println("testLinkedBoxListAdd(): " + testLinkedBoxListAdd());
    System.out.println("testLinkedBoxListClear(): " + testLinkedBoxListClear());


  }

}
