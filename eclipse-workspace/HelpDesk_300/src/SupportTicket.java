//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Support Ticket
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
 * this class models a support request ticket in a helpDeskQueue
 * 
 * @author chrisgeorge
 *
 */
public class SupportTicket implements Comparable<SupportTicket> {
  private String message;

  /**
   * constructor-- creates a new SupportTicket with a specified message
   * 
   * @param message for the supportTicket to display
   * @throws NullPointerException if the message parameter is null
   */
  public SupportTicket(String message) {
    if (message == null) {
      throw new NullPointerException("Error: Null String for message input");
    }
    this.message = message;
  }

  /**
   * string representation of a SupportTicket is its message
   * 
   * @return this SupportTicket's message
   */
  @Override
  public String toString() {
    return message;
  }

  /**
   * compares this ticket to another ticket, first for length, and if the lengths are equal then
   * lexicographically
   * 
   * @param o - another SupportTicket
   * @return positive number if this ticket this ticket is greater than the other ticket, negative
   *         if it is less, zero if they are equal
   */
  @Override
  public int compareTo(SupportTicket o) {
    if (this.toString().length() == o.toString().length()) { // if the messages are the same length
      return this.toString().compareTo(o.toString()); // compare lexicographically
    } else {
      // longer message means larger in ordering
      return this.toString().length() - o.toString().length();
    }
  }


}
