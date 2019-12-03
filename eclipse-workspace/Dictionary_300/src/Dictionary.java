//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dictionary Interface
// Files: Dictionary.java, DictionaryTests.java, DictionaryWord.java, DictionaryBST.java,
// DictionaryDriver.java
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
 * this interface models an abstract data type for a dictionary
 * 
 * @author CS300 write up
 */
public interface Dictionary {
  /**
   * checks whether the dictionary is empty or not
   * 
   * @return true if dictionary is empty, false otherwise
   */
  public boolean isEmpty();

  /**
   * adds this word definition (word and the provided meaning) to the dictionary
   * 
   * @param word    to add
   * @param meaning of the word
   * @return true if the word was successfully added to this dictionary, false if the word was
   *         already in the dictionary
   * @throws IllegalArgumentException if either word or meaning is null or an empty string
   */
  public boolean addWord(String word, String meaning);

  /**
   * Returns the meaning of the word s if it is present in this dictionary
   * 
   * @param s -- word to look up
   * @return the meaning of the searched word
   * @throws a NoSuchElementException if the word s was not found in this dictionary
   */
  public String lookup(String s);

  /**
   * return size of dictionary
   * 
   * @return the number of words stored in the dictionary
   */
  public int size();
}
