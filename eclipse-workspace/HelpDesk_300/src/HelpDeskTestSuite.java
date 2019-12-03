//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Help Desk Test Suite
// Files: SupportTicket.java, HelpDeskInterface.java, HelpDesk.java, HelpDeskTestSuite.java
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
 * this class contains a comprehensive set of test methods that demonstrate the correctness of the
 * implementation of HelpDesk
 * 
 * @author chrisgeorge
 *
 */
public class HelpDeskTestSuite extends HelpDesk {

  /**
   * required constructor-- unused (HelpDeskTestSuite object is never created)
   * 
   * @param capacity of the HelpDesk array
   */
  public HelpDeskTestSuite(int capacity) {
    super(capacity);
  }

  /**
   * tests for proper functioning of createNewTicket() for several new ticket creations and when the
   * array is full;
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testCreateNewTicket() {
    HelpDesk hd = new HelpDesk(5);

    hd.createNewTicket("a"); // add a single ticket to the HelpDesk; size should be 1
    if (hd.size != 1 || !hd.array[0].toString().equals("a")) {
      return false;
    }
    hd.createNewTicket("z"); // size should increment for every addition
    if (hd.size != 2 || !hd.array[0].toString().equals("z")) {
      return false;
    }
    hd.createNewTicket("aaa");
    if (hd.size != 3 || !hd.array[0].toString().equals("aaa")) {
      return false;
    }
    hd.createNewTicket("aa"); // root should stay as ticket with the longest message
    if (hd.size != 4 || !hd.array[0].toString().equals("aaa")) {
      return false;
    }
    hd.createNewTicket("bb");
    try {
      hd.createNewTicket("cc"); // the array is full, should throw exception
      return false; // should not be reached if exception properly thrown
    } catch (IndexOutOfBoundsException e) {
    }

    return true;
  }

  /**
   * tests for proper functioning of checkNextTicket() with no tickets, then additions of various
   * sizes, relies on proper function of createNewTicket()
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testCheckNextTicket() {
    HelpDesk hd = new HelpDesk(5);
    try {
      hd.checkNextTicket(); // should throw exception when size = 0
      return false; // this should not be reached, exception should be thrown
    } catch (IllegalStateException e) {
    }

    hd.createNewTicket("a");
    if (!hd.checkNextTicket().equals("a")) {
      return false;
    }
    hd.createNewTicket("z"); // should be the new largest-- lexicographically larger
    if (!hd.checkNextTicket().equals("z")) {
      return false;
    }
    hd.createNewTicket("aaa"); // should be the new largest -- longest length
    if (!hd.checkNextTicket().equals("aaa")) {
      return false;
    }
    hd.createNewTicket("aa"); // should not be the new largest
    if (!hd.checkNextTicket().equals("aaa")) {
      return false;
    }

    return true;
  }

  /**
   * tests for proper functioning of closeNextTicket(), when the HelpDesk is empty, and then with
   * several tickets to see if return, propagation and size are properly implemented
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testCloseNextTicket() {
    HelpDesk hd = new HelpDesk(5);

    try {
      hd.closeNextTicket();// should throw exception when size = 0
      return false; // this should not be reached, exception should be thrown
    } catch (IllegalStateException e) {
    }
    // add and remove 1 ticket
    hd.createNewTicket("a");
    if (!hd.closeNextTicket().equals("a") || hd.size != 0) {
      return false;
    }

    // add multiple tickets then remove them, checking for order
    hd.createNewTicket("a");
    hd.createNewTicket("b");
    hd.createNewTicket("bbb");
    if (!hd.closeNextTicket().equals("bbb") || hd.size != 2
        || !hd.array[0].toString().equals("b")) {
      return false;
    }
    if (!hd.closeNextTicket().equals("b") || hd.size != 1 || !hd.array[0].toString().equals("a")) {
      return false;
    }
    if (!hd.closeNextTicket().equals("a") || hd.size != 0 || hd.array[0] != null) {
      return false;
    }

    return true;
  }

  /**
   * tests for proper functioning of parentOf() for several indexes
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testParentOf() {
    if (HelpDesk.parentOf(1) != 0)
      return false;
    if (HelpDesk.parentOf(2) != 0)
      return false;
    if (HelpDesk.parentOf(5) != 2)
      return false;
    if (HelpDesk.parentOf(6) != 2)
      return false;

    return true;
  }

  /**
   * tests for proper functioning of leftChildOf() and rightChildOf() for several indexes
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testChildOf() {
    if (HelpDesk.leftChildOf(0) != 1)
      return false;
    if (HelpDesk.rightChildOf(0) != 2)
      return false;
    if (HelpDesk.leftChildOf(1) != 3)
      return false;
    if (HelpDesk.rightChildOf(1) != 4)
      return false;
    if (HelpDesk.leftChildOf(5) != 11)
      return false;
    if (HelpDesk.rightChildOf(5) != 12)
      return false;

    return true;
  }

  /**
   * tests for proper functioning of swap() for several indexes
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testSwap() {
    HelpDesk hd = new HelpDesk(5);
    // add several elements in order
    hd.createNewTicket("aaaaa");
    hd.createNewTicket("aaaa");
    hd.createNewTicket("aaa");
    hd.createNewTicket("aa");
    hd.createNewTicket("a");

    hd.swap(0, 4);
    if (!hd.array[0].toString().equals("a") || !hd.array[4].toString().equals("aaaaa")) {
      return false;
    }
    hd.swap(1, 3);
    if (!hd.array[1].toString().equals("aa") || !hd.array[3].toString().equals("aaaa")) {
      return false;
    }
    hd.swap(2, 0);
    if (!hd.array[2].toString().equals("a") || !hd.array[0].toString().equals("aaa")) {
      return false;
    }

    return true;
  }

  /**
   * tests for proper functioning of propagateUp() for several cases
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testPropagateUp() {
    HelpDesk hd = new HelpDesk(5);
    // add some elements in order
    hd.array[0] = new SupportTicket("aaaa");
    hd.array[1] = new SupportTicket("aa");
    hd.array[2] = new SupportTicket("a");

    hd.array[3] = new SupportTicket("aaaaa"); // and another ticket-- should be the first element
    hd.propagateUp(3);
    // check order
    if (!(hd.array[0].toString().equals("aaaaa") && hd.array[1].toString().equals("aaaa")
        && hd.array[2].toString().equals("a") && hd.array[3].toString().equals("aa"))) {
      return false;
    }

    hd.array[4] = new SupportTicket("aaa"); // add ticket which should not be moved
    hd.propagateUp(4);
    // check order
    if (!(hd.array[0].toString().equals("aaaaa") && hd.array[1].toString().equals("aaaa")
        && hd.array[2].toString().equals("a") && hd.array[3].toString().equals("aa")
        && hd.array[4].toString().equals("aaa"))) {
      return false;
    }
    return true;
  }

  /**
   * tests for proper functioning of propagateDown() for several cases
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testPropagateDown() {
    HelpDesk hd = new HelpDesk(5);
    // add some elements in order
    hd.array[1] = new SupportTicket("aaaaa");
    hd.size++;
    hd.array[2] = new SupportTicket("aaa");
    hd.size++;
    hd.array[3] = new SupportTicket("aa");
    hd.size++;

    hd.array[0] = new SupportTicket("a"); // should be moved down
    hd.size++;
    hd.propagateDown(0);
    // check order
    if (!(hd.array[0].toString().equals("aaaaa") && hd.array[1].toString().equals("aa")
        && hd.array[2].toString().equals("aaa") && hd.array[3].toString().equals("a"))) {
      return false;
    }

    hd.array[0] = new SupportTicket("aaaa"); // new addition, should be moved down (internal)
    hd.size++;
    hd.array[1] = new SupportTicket("aaaaa");
    hd.array[2] = new SupportTicket("aa");
    hd.array[3] = new SupportTicket("aaa");
    hd.array[4] = new SupportTicket("a");
    hd.propagateDown(0);
    // check order
    if (!(hd.array[0].toString().equals("aaaaa") && hd.array[1].toString().equals("aaaa")
        && hd.array[2].toString().equals("aa") && hd.array[3].toString().equals("aaa")
        && hd.array[4].toString().equals("a"))) {
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println("testCreateNewTicket: " + testCreateNewTicket());
    System.out.println("testCheckNextTicket: " + testCheckNextTicket());
    System.out.println("testCloseNextTicket: " + testCloseNextTicket());
    System.out.println("testParentOf: " + testParentOf());
    System.out.println("testChildOf: " + testChildOf());
    System.out.println("testSwap: " + testSwap());
    System.out.println("testPropagateUp: " + testPropagateUp());
    System.out.println("testPropagateDown: " + testPropagateDown());



  }

}
