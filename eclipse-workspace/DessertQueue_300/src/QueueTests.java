//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Queue Tests
// Files: QueueTests.java, Guest.java, ServingQueue.java, DessertSolvers.java
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
 * some tests of the implementations of the queue methods in ServingQueue
 * 
 * @author chrisgeorge
 *
 */
public class QueueTests {
  /**
   * tests if isEmpty() returns correct value
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testIsEmpty() {
    ServingQueue test = new ServingQueue(3);
    if (!test.isEmpty()) { // queue is empty
      System.err.println("Error: isEmpty() incorrectly returned true for empty queue");
      return false;
    }
    test.add(new Guest());
    if (test.isEmpty()) { // queue is not empty
      System.err.println("Error: isEmpty() incorrectly returned false for a non-empty queue");
      return false;
    }
    return true;
  }

  /**
   * tests if add() works properly, also indirectly tests if toString() works properly
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testAdd() {
    // full queue
    Guest.resetNextGuestIndex();
    ServingQueue test = new ServingQueue(3);
    // add 3 items to fill queue
    test.add(new Guest());
    test.add(new Guest("nuts"));
    test.add(new Guest());
    String correctResult = "[#1, #2(nuts), #3]"; // what the queue is supposed to be
    String testString = test.toString();
    if (!correctResult.equals(testString)) {
      System.err.println("Error: 3 items not added to queue correctly ");
      return false;
    }
    // empty spaces in queue
    Guest.resetNextGuestIndex();
    ServingQueue test2 = new ServingQueue(3);
    test2.add(new Guest()); // add 1 item to leave 2 empty spaces in queue
    correctResult = "[#1]"; // what the queue is supposed to be
    testString = test2.toString();
    if (!correctResult.equals(testString)) {
      System.err.println("Error: 1 item not added to queue correctly ");
      return false;
    }

    return true;
  }

  /**
   * tests if peek() and remove() work properly, also indirectly tests if toString() works properly
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testPeekAndRemove() {
    // full queue
    Guest.resetNextGuestIndex();
    ServingQueue test = new ServingQueue(3);
    // add 3 items to fill queue
    test.add(new Guest());
    test.add(new Guest("nuts"));
    test.add(new Guest());
    // checks if peek() and remove() are both #1
    if (!test.peek().toString().equals("#1") || !test.peek().equals(test.remove())) {
      System.err.println("Error: peek() and remove() should both return the first guest");
      return false;
    }
    String correctResult = "[#2(nuts), #3]"; // what the queue is supposed to be
    String testString = test.toString();
    if (!correctResult.equals(testString)) { // checks that the proper queue is printed
      System.err.println("Error: toString() did not properly output queue after remove()");
      return false;
    }

    // empty spots in queue
    Guest.resetNextGuestIndex();
    ServingQueue test2 = new ServingQueue(3);
    test2.add(new Guest()); // add 1 item to leave 2 empty spaces in queue
    // checks if peek() and remove() are both #1
    if (!test2.peek().toString().equals("#1") || !test2.peek().equals(test2.remove())) {
      System.err.println("Error: peek() and remove() should both return the first guest");
      return false;
    }
    testString = test2.toString();
    if (!testString.equals("[]")) { // queue is empty after the only guest is removed
      System.err.println("Error: toString() did not properly output queue after remove()");
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println("testIsEmpty(): " + testIsEmpty());
    System.out.println("testAdd(): " + testAdd());
    System.out.println("testPeekAndRemove(): " + testPeekAndRemove());
  }

}
