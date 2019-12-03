//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dictionary Tests
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

import java.util.NoSuchElementException;

/**
 * This class contains several methods that test the proper functioning of the DictionaryBST class
 * 
 * @author chrisgeorge
 *
 */
public class DictionaryTests {
  /**
   * tests the proper functioning of addWord() and getAllWords() with one word, then multiple words
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddWordGetAllWords() {
    DictionaryBST testDict = new DictionaryBST();

    // add 1 word, check getAllWords
    testDict.addWord("a", "meaning");
    String correct = "[a]"; // what the allWords array should be
    String allWords = testDict.getAllWords().toString();
    if (!correct.equals(allWords)) {
      System.err.println("Error: attempted to add 1 word; not properly added");
      return false;
    }

    // make sure addWord returns false for duplicate word
    if (testDict.addWord("a", "meaning") == true) { // attempt to add a previously added word
      System.err.println("Error: duplicate word incorrectly handled");
      return false;
    }

    // add multiple words (not in alphabetical order), check getAllWords
    testDict.addWord("d", "meaning");
    testDict.addWord("c", "meaning");
    testDict.addWord("e", "meaning");
    testDict.addWord("b", "meaning");
    correct = "[a, b, c, d, e]"; // what the array should be
    allWords = testDict.getAllWords().toString();
    if (!correct.equals(allWords)) {
      System.err.println("Error: attempted to add multiple words; not properly added");
      return false;
    }
    return true;
  }

  /**
   * tests the proper functioning of isEmpty() for empty and non-empty DictionaryBSTs
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testIsEmpty() {
    DictionaryBST testDict = new DictionaryBST();

    // empty dictionary: should return true
    if (!testDict.isEmpty()) {
      System.err.println("Error: isEmpty() incorrect output for empty dictionary");
      return false;
    }

    // non empty dictionary: should return false;
    testDict.addWord("a", "meaning"); // add a word-- not empty anymore
    if (testDict.isEmpty()) {
      System.err.println("Error: isEmpty() incorect output for non-empty dictionary");
      return false;
    }
    return true;
  }

  /**
   * test the proper functioning of size() for multiple BST sizes
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testSize() {
    DictionaryBST testDict = new DictionaryBST();

    // empty dictionary
    if (testDict.size() != 0) {
      System.err.println("Error: empty dictionary should have size 0");
      return false;
    }

    // 1 item in dictionary
    testDict.addWord("a", "meaning");
    if (testDict.size() != 1) {
      System.err.println("Error: after 1 item added, dictonary should have size 1");
      return false;
    }

    // multiple items in dictionary
    testDict.addWord("b", "meaning");
    testDict.addWord("c", "meaning");
    testDict.addWord("d", "meaning");
    testDict.addWord("e", "meaning");
    if (testDict.size() != 5) {
      System.err.println("Error: after 5 item added, dictonary should have size 5");
      return false;
    }
    return true;
  }

  /**
   * tests the proper functioning of height() with several heights
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testHeight() {
    DictionaryBST testDict = new DictionaryBST();

    // 1 node (root node) -- 1 height
    testDict.addWord("d", "meaning");
    if (testDict.height() != 1) {
      System.err.println("Error: 1-node BST should have height 1");
      return false;
    }
    // 2 nodes -- 2 height
    testDict.addWord("c", "meaning");
    if (testDict.height() != 2) {
      System.err.println("Error: 2-node BST should have height 2");
      return false;
    }
    // 3 nodes -- 2 height (balanced)
    testDict.addWord("e", "meaning");
    if (testDict.height() != 2) {
      System.err.println("Error: 3-node BST should have height 2");
      return false;
    }
    // 4 nodes -- 3 height
    testDict.addWord("f", "meaning");
    if (testDict.height() != 3) {
      System.err.println("Error: 4-node BST should have height 3");
      return false;
    }
    // 5 nodes -- 4 height (unbalanced)
    testDict.addWord("g", "meaning");
    if (testDict.height() != 4) {
      System.err.println("Error: 5-node BST should have height 4");
      return false;
    }
    // 6 nodes -- 5 height (unbalanced)
    testDict.addWord("h", "meaning");
    if (testDict.height() != 5) {
      System.err.println("Error: 6-node BST should have height 5");
      return false;
    }
    return true;
  }

  /**
   * tests proper functioning of lookup() for words in and not in the dictionary
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testLookUp() {
    DictionaryBST testDict = new DictionaryBST();

    testDict.addWord("word", "meaning");
    testDict.addWord("another", "definition");

    // lookup() for words in the dictionary should return meaning
    if (!testDict.lookup("word").equals("meaning")) {
      System.err.println("Error: correct meaning not returned for word 1 in the dictionary");
      return false;
    }
    if (!testDict.lookup("another").equals("definition")) {
      System.err.println("Error: correct meaning not returned for word 2 in the dictionary");
      return false;
    }

    // lookup() for word not in the dictionary should throw exception
    try {
      testDict.lookup("not");
      // execution in try block should terminate-- next 2 statements should not be reached
      System.err
          .println("Error: exception not properly thrown for lookup() on word not in dictionary");
      return false;
    } catch (NoSuchElementException e) {
    }
    return true;
  }

  /**
   * main method: calls each test method, displays results
   * 
   * @param args command line arguments (unused)
   */
  public static void main(String[] args) {
    System.out.println("testAddWordGetAllWords(): " + testAddWordGetAllWords());
    System.out.println("testIsEmpty(): " + testIsEmpty());
    System.out.println("testSize(): " + testSize());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testLookUp(): " + testLookUp());

  }

}
