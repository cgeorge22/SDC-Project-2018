//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Book
// Files:           Book.java
// Course:          CS 300 Spring 2019
//
// Author:          Chris George
// Email:           crgeorge@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * A class to model a simple library book
 * 
 * @author chrisgeorge
 */
public class Book {
  // class/static fields
  private static int nextId = 1; // class variable that represents the identifier of the next
                                 // book
  // Instance fields
  private final int ID; // unique identifier of this book
  private String author; // name of the author of this book
  private String title; // title of this book
  private Integer borrowerCardBarCode; // card bar code of the borrower of this book
                                       // When borrowerCardBarCode == null, the book is available
                                       // (no one has the book)

  /**
   * Default constructor of a new Book object-- all instance fields set to empty values
   * 
   */
  public Book() {
    // negative ID # = no ID
    ID = -1;
    // empty strings
    author = "";
    title = "";
    borrowerCardBarCode = null; // set to available upon creation

  }

  /**
   * Construct a new Book object and initialize its instance fields
   * 
   * @param title: title of this new book
   * @param author: author of this new book
   */
  public Book(String title, String author) {
    ID = nextId; // instance ID assigned to the static ID number
    nextId++; // static ID number is incremented after each Book creation
    // assign instance variables according to arguments
    this.author = author;
    this.title = title;
    borrowerCardBarCode = null; // available upon creation
  }

  /**
   * Records the borrower's card bar code of this book if the book is available.
   * 
   * @param borrowerCardBarCode : library bar code of the assigned borrower
   */
  public void borrowBook(Integer borrowerCardBarCode) {
    this.borrowerCardBarCode = borrowerCardBarCode;
  }

  /**
   * Return the author of this book
   * 
   * @return the author name
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Return the the borrower's card bar code of this book if the book has been checked out or null
   * if not
   * 
   * @return borrowerCardBarCode: bar code assigned to book
   */
  public Integer getBorrowerCardBarCode() {
    return borrowerCardBarCode;
  }

  /**
   * Returns the ID of this Book object
   * 
   * @return ID number of the book
   */
  public int getID() {
    return ID;
  }

  /**
   * Return the title of this book
   * 
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Checks if this book is available
   * 
   * @return true if no one is borrowing this book, false otherwise
   */
  public boolean isAvailable() {
    if (borrowerCardBarCode == null) { // when no one is borrowing book
      return true;
    } else {    // someone is borrowing the book
      return false;
    }
  }

  /**
   * Sets this book to be available. When the borrowerCardBarCode is set to null, no one is
   * borrowing it
   */
  public void returnBook() {
    borrowerCardBarCode = null; // removal of bar code means book is available
  }


}
