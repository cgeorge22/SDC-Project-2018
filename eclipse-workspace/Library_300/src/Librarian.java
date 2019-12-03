//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Librarian
// Files: Librarian.java
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
 * This class models a librarian who works at a book library. A Librarian is responsible for
 * managing the books in the library and helping the library subscribers to checkout and return
 * books
 * 
 * @author chrisgeorge
 *
 */
public class Librarian {
  // instance fields
  private String username; // librarian's username
  private String password; // librarian password to have access to this application

  /**
   * Default constructor for Librarian; sets instance variables to empty
   */
  public Librarian() {
    // empty strings
    username = "";
    password = "";
  }

  /**
   * Librarian constructor; sets username and password instance variables according to arguments
   * 
   * @param username
   * @param password
   */
  public Librarian(String username, String password) {
    // assign instance variables according to arguments
    this.username = username;
    this.password = password;
  }

  /**
   * Returns the name of this librarian
   * 
   * @return the name of this librarian
   */
  public String getUsername() {
    return username;
  }

  /**
   * Checks if a given password equals the password of this librarian
   * 
   * @param password - a given password
   * @return true if a given password equals the password of this librarian, false otherwise
   */
  public boolean checkPassword(String password) {
    // checks if the entered password matches the real password
    return (password.equals(this.password));

  }



}
