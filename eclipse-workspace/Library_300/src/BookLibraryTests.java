//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Book Library Tests
// Files:           Book.java, Subscriber.java, Librarian.java, Library.java
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

import java.util.ArrayList;

public class BookLibraryTests {
  /**
   * checks whether the constructor of the Book class initializes correctly the new Book instance
   * fields: title, author, borrowerCardBarCode, ID, and increments nextID static field.
   *
   * @return true if all tests pass, false if any fail
   */
  public static boolean testBookConstructorGetters() {
    boolean test = true;
    Book testBook = new Book("test", "Chris");
    if (!testBook.getTitle().equals("test")) {
      System.out.println("title name not set properly");
      test = false;
    }
    if (!testBook.getAuthor().equals("Chris")) {
      System.out.println("author name not set properly");
      test = false;
    }
    if (testBook.getBorrowerCardBarCode() != null) {
      System.out.println("initial borrow status not set properly");
      test = false;
    }
    if (testBook.getID() != 1) {
      System.out.println("ID number not set properly");
      test = false;
    }
    Book testBook2 = new Book("test2", "Chris");
    if (testBook2.getID() != 2) {
      System.out.println("ID number not incremented properly");
      test = false;
    }
    if (testBook2.getBorrowerCardBarCode() != null) {
      System.out.println("initial borrow status not set properly");
      test = false;
    }


    return test;
  }

  /**
   * tests if the borrowBook and returnBook methods work properly on a single book
   * 
   * @return true if all tests pass, false if any fail
   */
  public static boolean testBookReturnBook() {
    boolean test = true;
    Book testBook = new Book("test", "Chris");
    testBook.borrowBook(12345);
    if (testBook.isAvailable()) {
      System.out.println("borrowBook failed");
      test = false;
    }
    testBook.returnBook();
    if (testBook.getBorrowerCardBarCode() != null) {
      System.out.println("returnBook failed");
      test = false;
    }

    return test;
  }

  /**
   * checks for the proper function of all Subscriber instance methods except checkoutBook,
   * returnBook, and the instructor-provided methods
   * 
   * @return true if all tests pass, false if any fail
   */
  public static boolean testSubscriberMethods() {
    boolean test = true;
    Subscriber testSub = new Subscriber("Chris", 1234, "123 F drive", "1111111111");
    Subscriber testSub2 = new Subscriber("George", 4321, "321 F drive", "2222222222");

    if (!testSub.getName().equals("Chris")) {
      System.out.println("Error in assigning name");
      test = false;
    }
    if (testSub.getPin() != 1234) {
      System.out.println("Error in assigning pin");
      test = false;
    }
    testSub.setAddress("124 F drive");
    if (!testSub.getAddress().equals("124 F drive")) {
      System.out.println("Error in assigning address");
      test = false;
    }
    testSub.setPhoneNumber("1111111115");
    if (!testSub.getPhoneNumber().equals("1111111115")) {
      System.out.println("Error in assigning phone number");
      test = false;
    }
    if (!testSub2.getCARD_BAR_CODE().equals(2019000002)) {
      System.out.println("Error in incrementing Bar Code");
      test = false;
    }


    return test;
  }

  /**
   * tests if the checkoutBook method works properly, when adding 1 book and then too many books
   * also checks if the returnBook method works properly
   * 
   * @return true if all tests pass, false if any fail
   */
  public static boolean testSubscriberCheckoutBook() {
    boolean test = true;
    Subscriber testSub = new Subscriber("Chris", 1111, "635 Elm Dr", "1234567890");
    Book testBook1 = new Book("test1", "Chris");
    testSub.checkoutBook(testBook1);
    if (!testBook1.getBorrowerCardBarCode().equals(2019000001)) {
      System.out.println("subscriber bar code assign failed");
      test = false;
    }
    if (!testSub.isBookInBooksCheckedOut(testBook1)) {
      System.out.println("Book not added to booksCheckedOut properly");
      test = false;
    }
    Book testBook2 = new Book("test2", "Chris");
    testSub.checkoutBook(testBook2);
    Book testBook3 = new Book("test3", "Chris");
    testSub.checkoutBook(testBook3);
    Book testBook4 = new Book("test4", "Chris");
    testSub.checkoutBook(testBook4);
    Book testBook5 = new Book("test5", "Chris");
    testSub.checkoutBook(testBook5);
    Book testBook6 = new Book("test6", "Chris");
    testSub.checkoutBook(testBook6);
    Book testBook7 = new Book("test7", "Chris");
    testSub.checkoutBook(testBook7);
    Book testBook8 = new Book("test8", "Chris");
    testSub.checkoutBook(testBook8);
    Book testBook9 = new Book("test9", "Chris");
    testSub.checkoutBook(testBook9);
    Book testBook10 = new Book("test10", "Chris");
    testSub.checkoutBook(testBook10);
    Book testBook11 = new Book("test11", "Chris");
    testSub.checkoutBook(testBook11);

    testSub.returnBook(testBook1);
    if (testSub.isBookInBooksCheckedOut(testBook1) && !(testSub.isBookInBooksReturned(testBook1))) {
      System.out.println("returnBook not executed properly");
      test = false;
    }

    return test;
  }

  /**
   * Checks the good functioning of findBookByAuthor(String) method defined in the Library class.
   * 
   * @return true if test passes, false otherwise
   */
  public static boolean testLibraryFindBookByAuthor() {
    boolean test = true;
    Library testLibrary = new Library("123 Elm", "un", "pw");
    
    testLibrary.addBook("book1", "chris");
    testLibrary.addBook("book2", "notChris");
    testLibrary.addBook("book3", "chris");
    
    ArrayList <Book> commonAuthor = new ArrayList<Book>();
    commonAuthor.add(testLibrary.findBook(1));
    commonAuthor.add(testLibrary.findBook(3));
    
    if (!testLibrary.findBookByAuthor("chris").equals(commonAuthor)) {
      test = false;
      System.out.println("Error in findBookByAuthor");
    }

    return test;
  }


  public static void main(String[] args) {
    System.out.println("testBookConstructorGetters: " + testBookConstructorGetters());
    System.out.println("testBookReturnBook: " + testBookReturnBook());
    System.out.println("testSubscriberCheckoutBook: " + testSubscriberCheckoutBook());
    System.out.println("testBookReturnBook: " + testBookReturnBook());
    System.out.println("testLibraryFindBookByAuthor: " + testLibraryFindBookByAuthor());
  }



}
