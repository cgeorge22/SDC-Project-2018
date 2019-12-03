//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Subscriber
// Files: Subscriber.java, Book.java
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

import java.util.ArrayList;

/**
 * This class models a public library subscriber. A subscriber is a card holder who can borrow
 * (checkout) and return library books
 * 
 * @author chrisgeorge
 */
public class Subscriber {
  // static fields
  private final static int MAX_BOOKS_CHECKED_OUT = 10; // maximum number of books to be checked out
                                                       // one subscriber
  private static int nextCardBarCode = 2019000001; // class variable that represents the card bar
                                                   // code of the next subscriber to be created
  // Instance fields
  private int pin; // 4-digits Personal Identification Number to verify the identity of this
                   // subscriber
  private final Integer CARD_BAR_CODE; // card bar code of this subscriber

  private String name; // name of this subscriber
  private String address; // address of this subscriber
  private String phoneNumber; // phone number of this subscriber

  private ArrayList<Book> booksCheckedOut; // list of books checked out by this subscriber and not
                                           // yet
                                           // returned. A subscriber can have at most 10 checked out
                                           // books
  private ArrayList<Book> booksReturned; // list of the books returned by this subscriber

  /**
   * default Subscriber constructor; when no arguments are entered, empty values are given to the
   * instance variables
   */
  public Subscriber() {
    // empty strings
    name = "";
    address = "";
    phoneNumber = "";
    // both instance arrayLists do not exist
    booksCheckedOut = null;
    booksReturned = null;
    // negative bar code - invalid
    CARD_BAR_CODE = -1;
  }

  /**
   * Subscriber constructor, initializes instance variables according to arguments, creates
   * arrayLists, assigns bar code
   * 
   * @param name        - name of this subscriber
   * @param pin         - 4-digits personal information number of this subscriber
   * @param address     - address of this subscriber
   * @param phoneNumber - phone number of this subscriber
   */
  public Subscriber(String name, int pin, String address, String phoneNumber) {
    // assign instance variables according to arguments
    this.name = name;
    this.pin = pin;
    this.address = address;
    this.phoneNumber = phoneNumber;
    // instance bar code assigned to the static bar code number
    CARD_BAR_CODE = nextCardBarCode;
    nextCardBarCode++; // static bar code incremented for the next object creation
    // initialize instance arrayLists
    booksCheckedOut = new ArrayList<Book>();
    booksReturned = new ArrayList<Book>();
  }

  /**
   * Returns this subscriber's address
   * 
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Updates this subscriber's address
   * 
   * @param address- the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * returns this subscriber's phone number
   * 
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Updates this subscriber's phone number
   * 
   * @param phoneNumber - the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns this subscriber PIN (4-digits Personal Identification Number)
   * 
   * @return the pin
   */
  public int getPin() {
    return pin;
  }

  /**
   * Returns this subscriber's card bar code
   * 
   * @return the CARD_BAR_CODE
   */
  public Integer getCARD_BAR_CODE() {
    return CARD_BAR_CODE;
  }

  /**
   * Returns this subscriber's name
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Checks out an available book. The checkout operation fails if the maximum number of checked out
   * books by this subscriber is already reached
   * 
   * @param book - reference to the book to be checked out by this subscriber
   */
  public void checkoutBook(Book book) {
    boolean okToCheckOut = true; // needs to be true at end of method for book to be checked out

    if (booksCheckedOut.size() >= MAX_BOOKS_CHECKED_OUT) { // if booksCheckedOut is already full
      System.out.println(
          "Checkout Failed: You cannot check out more than " + MAX_BOOKS_CHECKED_OUT + " books.");
      okToCheckOut = false; // fails condition to check out
    }
    if (isBookInBooksCheckedOut(book)) { // book has already been checked out
      System.out.println("You have already checked out " + book.getTitle() + " book.");
      okToCheckOut = false; // fails condition to check out
    }
    if (book.getBorrowerCardBarCode() != null) { // another subscriber already checked out the book
      System.out.println("Sorry, " + book.getTitle() + " is not available.");
      okToCheckOut = false; // fails condition to check out
    }
    if (okToCheckOut) { // if no conditions fail
      book.borrowBook(CARD_BAR_CODE); // assign a bar code to the book
      booksCheckedOut.add(book); // add book to the checked out arrayList
    }
  }

  /**
   * Returns a library book
   * 
   * @param book - reference to the book to return by this subscriber
   */
  public void returnBook(Book book) {
    book.returnBook(); // remove bar code from book
    booksCheckedOut.remove(book); // remove book from checked out arrayList
    booksReturned.add(book); // add book to returned arrayList
  }

  /**
   * Checks if this subscriber booksCheckedOut list contains a given book
   * 
   * @param book book to check if it is within this subscriber booksCheckedOut list
   * @return true if booksCheckedOut contains book, false otherwise
   */
  public boolean isBookInBooksCheckedOut(Book book) {
    return booksCheckedOut.contains(book); // search arrayList for the book reference
  }

  /**
   * Checks if this subscriber booksReturned list contains a given book
   * 
   * @param book - book to check if it is within this subscriber booksReturned list
   * @return true if booksReturned contains book, false otherwise
   */
  public boolean isBookInBooksReturned(Book book) {
    return booksReturned.contains(book); // search arrayList for the book reference
  }

  /**
   * Displays the list of the books checked out and not yet returned
   */
  public void displayBooksCheckedOut() {
    if (booksCheckedOut.isEmpty()) // empty list
      System.out.println("No books checked out by this subscriber");
    else
      // Traverse the list of books checked out by this subscriber and display its content
      for (int i = 0; i < booksCheckedOut.size(); i++) {
        System.out.print("Book ID: " + booksCheckedOut.get(i).getID() + " ");
        System.out.print("Title: " + booksCheckedOut.get(i).getTitle() + " ");
        System.out.println("Author: " + booksCheckedOut.get(i).getAuthor());
      }
  }

  /**
   * Displays the history of the returned books by this subscriber
   */
  public void displayHistoryBooksReturned() {
    if (booksReturned.isEmpty()) // empty list
      System.out.println("No books returned by this subscriber");
    else
      // Traverse the list of borrowed books already returned by this subscriber and display its
      // content
      for (int i = 0; i < booksReturned.size(); i++) {
        System.out.print("Book ID: " + booksReturned.get(i).getID() + " ");
        System.out.print("Title: " + booksReturned.get(i).getTitle() + " ");
        System.out.println("Author: " + booksReturned.get(i).getAuthor());
      }
  }

  /**
   * Displays this subscriber's personal information
   */
  public void displayPersonalInfo() {
    System.out.println("Personal information of the subscriber: " + CARD_BAR_CODE);
    System.out.println("  Name: " + name);
    System.out.println("  Address: " + address);
    System.out.println("  Phone number: " + phoneNumber);
  }

}
