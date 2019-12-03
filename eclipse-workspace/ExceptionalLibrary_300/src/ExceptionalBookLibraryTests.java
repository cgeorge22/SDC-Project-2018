//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Exceptional Book Library Tests
// Files: Book.java, Subscriber.java, Librarian.java, ExceptionalLibrary.java
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

import java.text.ParseException;

public class ExceptionalBookLibraryTests {
  /**
   * Check for proper function of parseCardBarCode()
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLibraryParseCardBarCode() {
    boolean test = true;
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "chris", "abc");

    try {
      // extreme case: max Bar code number
      if (testLibrary.parseCardBarCode("2019999999", 0) != 2019999999) {
        test = false;
        System.err.println("Error: valid max number not properly parsed");
      }
    } catch (ParseException e) {
      test = false; // parseException should not be thrown
      System.err.println("Error: ParseException improperly thrown");
    }
    try {
      // extreme case: minimum bar code number
      if (testLibrary.parseCardBarCode("2019000001", 0) != 2019000001) {
        test = false;
        System.err.println("Error: valid min number not properly parsed");
      }
    } catch (ParseException e) {
      test = false; // parseException should not be thrown
      System.err.println("Error: ParseException improperly thrown");
    }
    try {
      // outside of bar code range
      testLibrary.parseCardBarCode("2018999999", 0); // should throw parseException
      // code under this should not execute as parseException thrown
      System.err.println("Error: ParseException not thrown");
      test = false;
    } catch (ParseException e) {
    }

    return test;
  }

  /**
   * Check for proper function of parsePinCode()
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLibraryParsePinCode() {
    boolean test = true;
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "chris", "abc");

    try {
      // extreme case: max pin number
      if (testLibrary.parsePinCode("9999", 0) != 9999) {
        test = false; // 9999 is valid
        System.err.println("Error: max pin not parsed correctly");
      }
    } catch (ParseException e) {
      test = false; // parseException should not be thrown
      System.err.println("Error: parseException improperly thrown");
    }
    try {
      // extreme case: min pin number
      if (testLibrary.parsePinCode("1000", 0) != 1000) {
        test = false; // 1000 is valid
        System.err.println("Error: min pin not parsed correctly");
      }
    } catch (ParseException e) {
      test = false; // parseException should not be thrown
      System.err.println("Error: parseException improperly thrown");
    }
    try {
      // too many digits
      testLibrary.parsePinCode("10000", 0); // exception should be thrown
      // execution in try block should stop as exception thrown
      System.err.println("Error: parseException not thrown");
      test = false;
    } catch (ParseException e) {
    }
    try {
      // out of range
      testLibrary.parsePinCode("0100", 0); // exception should be thrown
      // execution in try block should stop as exception thrown
      System.err.println("Error: parseException not thrown");
      test = false;
    } catch (ParseException e) {
    }

    return test;
  }

  /**
   * Check for proper function of parseBookId()
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLibraryBookId() {
    boolean test = true;
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "chris", "abc");

    try {
      if (testLibrary.parseBookId("1", 0) != 1) {
        test = false; // valid bookID
        System.err.println("Error: correct input not parsed");
      }
    } catch (ParseException e) {
      test = false; // parseException should not be thrown
      System.err.println("Error: parseException improperly thrown");
    }
    try {
      if (testLibrary.parseBookId("12345678", 0) != 12345678) {
        test = false; // valid bookID
        System.err.println("Error: correct input not parsed");
      }
    } catch (ParseException e) {
      test = false; // parseException should not be thrown
      System.err.println("Error: parseException improperly thrown");
    }
    try {
      testLibrary.parseBookId("1208E", 0); // invalid input-- parseException
      // try block execution should stop at exception
      System.err.println("Error: parseException not thrown");
      test = false;
    } catch (ParseException e) {
    }

    return test;
  }

  /**
   * Check for proper function of parseRunLibrarianCheckoutBookCommand()
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLibraryParseRunLibrarianCheckoutBookCommand() {
    boolean test = true;
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "chris", "abc");
    testLibrary.addBook("cs300", "cg"); // book that will be checked out

    try { // create subscriber, check instantiation exception
      testLibrary.addSubscriber("chris", 1234, "Madison", "1111111111");
    } catch (InstantiationException e) {
      test = false; // fails test if the subscriber cannot be created for some reason
      System.err.println("Error: Subscriber could not be created");
    }

    try {
      String[] commands = {"3", "2019000001"}; // wrong number of commands
      testLibrary.parseRunLibrarianCheckoutBookCommand(commands); // should throw exception
      // lines after throw should not execute
      test = false;
      System.err.println("Error: command array incorrectly parsed as valid");
    } catch (ParseException e) {
    }
    try {
      String[] commands = {"3", "2019000001", "1"}; // proper input
      testLibrary.parseRunLibrarianCheckoutBookCommand(commands);
    } catch (ParseException e) {
      test = false; // exception should not be thrown for proper input
      System.err.println("Error: parseException thrown incorrectly");
    }

    return test;
  }

  /**
   * Check for proper function of parseRunSubscriberReturnBookCommand()
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean parseRunSubscriberReturnBookCommand() {
    boolean test = true;
    ExceptionalLibrary testLibrary = new ExceptionalLibrary("Madison", "chris", "abc");
    testLibrary.addBook("cs200", "cg");
    
    try { // create new subscriber, check instantiation exception
      testLibrary.addSubscriber("george", 1234, "Madison", "1111111111");
    } catch (InstantiationException e) {
      test = false; // fails test if subscriber is not created
      System.err.println("Error: Subscriber could not be created");
    }
    Subscriber sub = testLibrary.findSubscriber(2019000002); // subscriber that will return book

    try {
      String[] commands = {"1", "2"}; 
      testLibrary.parseRunSubscriberCheckoutBookCommand(commands, sub); // check out book to return
    } catch (ParseException e) { // should be thrown
      System.err.println("Error: book was not checked out");
      test = false;
    }

    try {
      String[] commands = {"2", "2"}; // proper command line
      testLibrary.parseRunSubscriberReturnBookCommand(commands, sub); // return book
    } catch (ParseException e) { // should not be thrown
      System.err.println("Error: not returned properly. parseException incorrectly thrown");
      test = false;
    }
    try {
      String[] commands = {"2", "2wq"}; // syntax error
      testLibrary.parseRunSubscriberReturnBookCommand(commands, sub); // throws exception
      test = false; // should not execute
      System.err.println("Error: parseException should have been thrown for invalid input");
    } catch (ParseException e) {
    }
    
    return test;
  }

  public static void main(String[] args) {
    System.out.println("testLibraryParseCardBarCode(): " + testLibraryParseCardBarCode());
    System.out.println("testLibraryParsePinCode(): " + testLibraryParsePinCode());
    System.out.println("testLibraryBookId(): " + testLibraryBookId());
    System.out.println("testLibraryParseRunLibrarianCheckoutBookCommand(): "
        + testLibraryParseRunLibrarianCheckoutBookCommand());
    System.out
        .println("parseRunSubscriberReturnBookCommand(): " + parseRunSubscriberReturnBookCommand());


  }

}
