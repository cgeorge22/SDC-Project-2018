//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Library
// Files: Library.java, Book.java, Subscriber.java, Librarian.java
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
import java.util.Scanner;

/**
 * This class models a simple book library. The main method of this class implements the management
 * system for this library.
 * 
 * @author chrisgeorge
 *
 */
public class Library {
  // instance fields
  private String address; // Street address of this library
  private Librarian librarian; // this library's librarian. This library must have only ONE
                               // librarian
  private ArrayList<Book> books; // list of the books in this library
  private ArrayList<Subscriber> subscribers; // list of this library's subscribers

  /**
   * default library constructor: instance variables all set to empty values
   */
  public Library() {
    // create empty instance arrayLists
    books = new ArrayList<Book>();
    subscribers = new ArrayList<Subscriber>();
    address = ""; // empty address
    librarian = new Librarian("", ""); // creates librarian object with empty attributes
  }

  /**
   * Library constructor; books and subscribers arrayLists initialized, address set according to
   * argument, and a Librarian object is created
   * 
   * @param address           - Address of this Library
   * @param librarianUsername - username of the librarian of this book library
   * @param librarianLogin    - password of the librarian of this book library
   */
  public Library(String address, String librarianUsername, String librarianLogin) {
    // create new instance arrayLists for book and subscriber objects
    books = new ArrayList<Book>();
    subscribers = new ArrayList<Subscriber>();
    this.address = address; // assigns address according to argument
    // creates Librarian instance with attributes according to arguments
    librarian = new Librarian(librarianUsername, librarianLogin);
  }

  /**
   * Returns the librarian of this library
   * 
   * @return the librarian
   */
  public Librarian getLibrarian() {
    return librarian;
  }

  /**
   * Returns the address of this library
   * 
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns a Book given a book identifier if found, and null otherwise. If the book is not found,
   * this method displays the following message: "Error: this book identifier didn't match any of
   * our books identifiers."
   * 
   * @param bookId - identifier of the book to find
   * @return reference to the Book if found and null otherwise
   */
  public Book findBook(int bookId) {
    if (bookId - 1 < books.size()) { // if the bookId is a valid index in books arrayList
      return books.get(bookId - 1); // return Book at the bookId index
    } else { // the bookId is not a valid index in the arrayList
      System.out.println("Error: this book identifier didn't match any of our books identifiers.");
      return null;
    }
  }

  /**
   * Returns the list of books having a given title in this library. The comparison used by this
   * method is case insensitive
   * 
   * @param title - title of the book(s) to find
   * @return ArrayList of the books having a given title in this library (0 or more books can be
   *         found)
   */
  public ArrayList<Book> findBookByTitle(String title) {
    // stores Book objects with the same title
    ArrayList<Book> booksWithTitle = new ArrayList<Book>();

    for (int i = 0; i < books.size(); i++) { // assess every element in the books arrayList
      if (books.get(i).getTitle().equalsIgnoreCase(title)) { // if the title matches
        booksWithTitle.add(books.get(i)); // add the book reference to the created array
      }
    }
    return booksWithTitle;
  }

  /**
   * Returns the list of books having a given author. The comparison used by this method is case
   * insensitive
   * 
   * @param author - author of the book(s) to find
   * @return ArrayList of the books having a given author (0 or more books can be found)
   */
  public ArrayList<Book> findBookByAuthor(String author) {
    // stores Book objects with the same author
    ArrayList<Book> booksWithAuthor = new ArrayList<Book>();

    for (int i = 0; i < books.size(); i++) { // assess every element in the books arrayList
      if (books.get(i).getAuthor().equalsIgnoreCase(author)) { // if the author matches
        booksWithAuthor.add(books.get(i)); // add the book reference to the created array
      }
    }
    return booksWithAuthor;
  }

  /**
   * Adds a new book to the library (to the books list). This method displays the following message:
   * "Book with Title " + title + " is successfully added to the library."
   * 
   * @param title  - title of the new book
   * @param author - author of the new book
   */
  public void addBook(String title, String author) {
    Book newBook = new Book(title, author); // create new book object using arguments
    books.add(newBook); // add book to the library
    System.out.println("Book with Title " + title + " is successfully added to the library.");
  }

  /**
   * Removes a book given its identifier from the library (from books list)
   * 
   * @param bookId - identifier of the book to remove
   * @return a reference to the removed book, and null if the book is not found in this library or
   *         if it is not available
   */
  public Book removeBook(int bookId) {
    Book removedBook; // will store reference of removed book
    // if book is in the library and not checked out
    if (bookId - 1 < books.size() && books.get(bookId - 1).isAvailable()) {
      removedBook = books.get(bookId - 1); // store book reference
      books.remove(bookId - 1); // remove book from the library
      return removedBook;
    } else {
      return null;
    }
  }

  /**
   * Adds a new subscriber to this library (to subscribers list). This method displays the following
   * message: "Library card with bar code " + card bar code + " is successfully issued to the new
   * subscriber " + name + "."
   * 
   * @param name        - name of the new subscriber
   * @param pin         - 4-digit personal identifier number of the new subscriber
   * @param address     - address of the new subscriber
   * @param phoneNumber - phone number of the new subscriber
   */
  public void addSubscriber(String name, int pin, String address, String phoneNumber) {
    // create new Subscriber object according to arguments
    Subscriber newSub = new Subscriber(name, pin, address, phoneNumber);
    subscribers.add(newSub); // add the created subscriber to the subscribers list
    System.out.println("Library card with bar code " + newSub.getCARD_BAR_CODE()
        + " is successfully issued to the new subscriber " + newSub.getName() + ".");

  }

  /**
   * Finds a subscriber given its cardBarCode. This method displayed the following message: "Error:
   * this card bar code didn't match any of our records." and returns null if the provided
   * cardBarCode did not match with any of the subscribers' card bar codes
   * 
   * @param cardBarCode - of the subscriber to find
   * @return a reference to the subscriber if found, otherwise null
   */
  public Subscriber findSubscriber(int cardBarCode) {
    for (int i = 0; i < subscribers.size(); i++) { // iterate through subscribers list
      // if the given subscriber's bar code matches
      if (subscribers.get(i).getCARD_BAR_CODE() == cardBarCode) {
        return subscribers.get(i); // return the matching subscriber reference
      }
    }
    // executes if no matches
    System.out.println("Error: this card bar code didn't match any of our records.");
    return null;
  }

  /**
   * Displays a list of books
   * 
   * @param books ArrayList of books
   */
  public static void displayBooks(ArrayList<Book> books) {
    // Traverse the list of books and display book id, title, author, and availability of each book
    for (int i = 0; i < books.size(); i++) {
      System.out.print("<Book ID>: " + books.get(i).getID() + " ");
      System.out.print("<Title>: " + books.get(i).getTitle() + " ");
      System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
      System.out.println("<Is Available>: " + books.get(i).isAvailable());
    }
  }

  /**
   * Displays the main menu for this book library application
   */
  private static void displayMainMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("     Welcome to our Book Library Management System");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <password>] Login as a librarian");
    System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
    System.out.println("[3] Exit"); // Exit the application
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for a Subscriber
   */
  private static void displaySubscriberMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Subscriber's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <book ID>] Check out a book");
    System.out.println("[2 <book ID>] Return a book");
    System.out.println("[3 <title>] Search a Book by title");
    System.out.println("[4 <author>] Search a Book by author");
    System.out.println("[5] Print list of books checked out");
    System.out.println("[6] Print history of returned books");
    System.out.println("[7 <address>] Update address");
    System.out.println("[8 <phone number>] Update phone number");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for the Librarian
   */
  private static void displayLibrarianMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Librarian's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <title> <author>] Add new Book");
    System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
    System.out.println("[3 <card bar code> <book ID>] Check out a Book");
    System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
    System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
    System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
    System.out.println("[7] Display All Books");
    System.out.println("[8 <book ID>] Remove a Book");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Display the Application GoodBye and logout message.
   */
  private static void displayGoodByeLogoutMessage() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("       Thanks for Using our Book Library App!!!!");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Reads and processes the user commands with respect to the menu of this application
   * 
   * @param scanner Scanner object used to read the user command lines
   */
  public void readProcessUserCommand(Scanner scanner) {
    final String promptCommandLine = "ENTER COMMAND: ";
    displayMainMenu(); // display the library management system main menu
    System.out.print(promptCommandLine);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    while (commands[0].trim().charAt(0) != '3') { // '3': Exit the application
      switch (commands[0].trim().charAt(0)) {
        case '1': // login as librarian commands[1]: password
          if (this.librarian.checkPassword(commands[1])) {
            // read and process librarian commands
            readProcessLibrarianCommand(scanner);
          } else { // error password
            System.out.println("Error: Password incorrect!");
          }
          break;
        case '2': // login as subscriber commands[1]: card bar code
          Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
          if (subscriber != null) {
            if (subscriber.getPin() == Integer.parseInt(commands[2])) // correct PIN
              // read and process subscriber commands
              readProcessSubscriberCommand(subscriber, scanner);
            else
              System.out.println("Error: Incorrect PIN.");
          }
          break;
      }
      // read and split next user command line
      displayMainMenu(); // display the library management system main menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Reads and processes the librarian commands according to the librarian menu
   * 
   * @param scanner Scanner object used to read the librarian command lines
   */
  private void readProcessLibrarianCommand(Scanner scanner) {
    Subscriber subscriber;
    final String promptCommandLine = "ENTER COMMAND: ";
    displayLibrarianMenu(); // display the librarian space menu
    System.out.print(promptCommandLine);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command line

    while (commands[0].trim().charAt(0) != '9') { // exit method if user enters '9' (logout)
      switch (commands[0].trim().charAt(0)) {
        case '1': // add new book with commands[1] and commands[2] as arguments
          addBook(commands[1], commands[2]);
          break;
        case '2': // add new subscriber with commands[1]-commands[4] as arguments
          this.addSubscriber(commands[1], Integer.parseInt(commands[2]), commands[3], commands[4]);
          break;
        case '3': // check out a book for a subscriber
          // search for subscriber using commands[1] as the bar code
          subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
          // check out book for subscriber using commands[2] for the book index
          subscriber.checkoutBook(this.findBook(Integer.parseInt(commands[2])));
          break;
        case '4': // return a book for a subscriber
          // search for subscriber using commands[1] as the bar code
          subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
          // return book for subscriber using commands[2] for the book index
          subscriber.returnBook(this.findBook(Integer.parseInt(commands[2])));
          break;
        case '5': // display personal info of a subscriber
          // search for subscriber using commands[1] as the bar code
          subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
          subscriber.displayPersonalInfo();
          break;
        case '6': // display the books the subscriber has checked out
          // search for subscriber using commands[1] as the bar code
          subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
          subscriber.displayBooksCheckedOut();
          break;
        case '7': // display all the books of the library
          Library.displayBooks(books);
          break;
        case '8': // remove a book from the library, using commands[1] as the book index to remove
          this.removeBook(Integer.parseInt(commands[1]));
          break;
      }

      displayLibrarianMenu(); // display the librarian space menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Reads and processes the subscriber commands according to the subscriber menu
   * 
   * @param subscriber Current logged in subscriber
   * @param scanner    Scanner object used to read the librarian command lines
   */
  private void readProcessSubscriberCommand(Subscriber subscriber, Scanner scanner) {
    final String promptCommandLine = "ENTER COMMAND: ";
    displaySubscriberMenu(); // display the subscriber space menu
    System.out.print(promptCommandLine);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command line

    while (commands[0].trim().charAt(0) != '9') { // exit method if user enters '9' (logout)
      switch (commands[0].trim().charAt(0)) {
        case '1': // check out book using commands[1] as the book index to check out
          subscriber.checkoutBook(this.findBook(Integer.parseInt(commands[1])));
          break;
        case '2': // return book using commands[1] as the book index to return
          subscriber.returnBook(this.findBook(Integer.parseInt(commands[1])));
          break;
        case '3': // search for a book by title, using commands[1] as the title to search for
          Library.displayBooks(this.findBookByTitle(commands[1]));
          break;
        case '4': // search for a book by author, using commands[1] as the author to search for
          Library.displayBooks(this.findBookByAuthor(commands[1]));
          break;
        case '5': // print a list of the books this subscriber has checked out
          subscriber.displayBooksCheckedOut();
          break;
        case '6': // print a list of the books this subscriber has returned
          subscriber.displayHistoryBooksReturned();
          break;
        case '7': // change this subscriber's address, using commands[1] as the new address
          subscriber.setAddress(commands[1]);
          break;
        case '8': // change this subscriber's phone number, using commands[1] as the new number
          subscriber.setPhoneNumber(commands[1]);
          break;
      }

      displaySubscriberMenu(); // display the librarian space menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Main method that represents the driver for this application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // create a scanner object to read user inputs
    // create a new library object
    Library madisonLibrary = new Library("Madison, WI", "april", "abc");
    // read and process user command lines
    madisonLibrary.readProcessUserCommand(scanner);
    displayGoodByeLogoutMessage(); // display good bye message
    scanner.close();// close this scanner
  }


}
