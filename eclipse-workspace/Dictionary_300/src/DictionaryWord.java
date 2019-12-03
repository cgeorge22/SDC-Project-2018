//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dictionary Word
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
 * Each binary node (aka instance of DictionaryWord) defines its data consisting of a pair of word
 * and its meaning, and a link to each child (right and left) of the node
 * 
 * @author chrisgeorge
 *
 */
public class DictionaryWord {
  private final String word; // word that represents the search key for this dictionary word
  private final String meaning; // The meaning of the word that this dictionary node defines
  private DictionaryWord leftChild; // The leftChild of the the current DictionaryWord
  private DictionaryWord rightChild; // The rightChild of the the current DictionaryWord

  /**
   * Creates a new dictionary word with the provided word and its meaning pair
   * 
   * @param word    to create
   * @param meaning to pair with the word
   * @throws IllegalArgumentException when the word or meaning are either references to an empty
   *                                  string or null references. The thrown exception should include
   *                                  a significant error message describing which of these problems
   *                                  was encountered.
   */
  public DictionaryWord(String word, String meaning) {
    if (word == null || word.equals("")) { // throw exception if word is empty or null
      throw new IllegalArgumentException(
          "Error: cannot create a word with an empty word parameter");
    }
    if (meaning == null || meaning.equals("")) { // throw exception when meaning is empty or null
      throw new IllegalArgumentException(
          "Error: cannot create a word with empty meaning parameter");
    }

    this.word = word;
    this.meaning = meaning;
  }

  /**
   * Getter for the left child of this dictionary word
   * 
   * @return left child
   */
  public DictionaryWord getLeftChild() {
    return leftChild;
  }

  /**
   * Setter for the left child of this dictionary word
   * 
   * @param leftChild DictionaryWord to set as the left child
   */
  public void setLeftChild(DictionaryWord leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * Getter for the right child of this dictionary word
   * 
   * @return right child
   */
  public DictionaryWord getRightChild() {
    return rightChild;
  }

  /**
   * Setter for the right child of this dictionary word
   * 
   * @param rightChild DictionaryWord to set as the right child
   */
  public void setRightChild(DictionaryWord rightChild) {
    this.rightChild = rightChild;
  }

  /**
   * Getter for the word of this dictionary word
   * 
   * @return this word
   */
  public String getWord() {
    return word;
  }

  /**
   * Getter for the meaning of the word of this dictionary word
   * 
   * @return this word's meaning
   */
  public String getMeaning() {
    return meaning;
  }

  /**
   * Returns a String representation of this DictionaryWord. This String should be formatted as
   * follows. "<word>: <meaning>"
   * 
   * @return the DictionaryWord in specified format
   */
  public String toString() {
    return word + ": " + meaning;
  }
}
