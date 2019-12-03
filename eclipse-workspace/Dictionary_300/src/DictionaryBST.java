//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dictionary BST
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * a BST organization of DictionaryWord objects
 * 
 * @author chrisgeorge
 */
public class DictionaryBST implements Dictionary {
  private DictionaryWord root;

  /**
   * Creates an empty dictionaryBST.
   * 
   */
  public DictionaryBST() {
    root = null;
  }

  /**
   * checks whether the dictionary is empty or not
   * 
   * @return true if dictionary is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * adds this word definition (word and the provided meaning) to the dictionary
   * 
   * @param word    to add
   * @param meaning of the word
   * @return true if the word was successfully added to this dictionary, false if the word was
   *         already in the dictionary
   * @throws IllegalArgumentException if either word or meaning is null or an empty string
   */
  @Override
  public boolean addWord(String word, String meaning) {
    DictionaryWord newWord = new DictionaryWord(word, meaning);
    if (this.isEmpty()) {
      root = newWord; // create new BST with the new word as the root
      return true;
    } else {
      return addWordHelper(newWord, root); // recursive helper method
    }
  }

  /**
   * Returns the meaning of the word s if it is present in this dictionary
   * 
   * @param s -- word to look up
   * @return the meaning of the searched word
   * @throws a NoSuchElementException if the word s was not found in this dictionary
   */
  @Override
  public String lookup(String s) {
    return lookupHelper(s, root);
  }

  /**
   * return size of dictionary
   * 
   * @return the number of words stored in the dictionary
   */
  @Override
  public int size() {
    return sizeHelper(root);
  }

  /**
   * Computes and returns the height of this dictionaryBST, as the number of nodes from root to the
   * deepest leaf DictionaryWord node.
   * 
   * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Returns all the words within this dictionary sorted from A to Z
   * 
   * @return an ArrayList that contains all the words within this dictionary sorted in the ascendant
   *         order
   */
  public ArrayList<String> getAllWords() {
    return getAllWordsHelper(root);
  }


  /**
   * Recursive helper method to add newWord in the subtree rooted at node
   * 
   * @param newWordNode a new DictionaryWord to be added to this dictionaryBST
   * @param current     the current DictionaryWord that is the root of the subtree where newWord
   *                    will be inserted
   * @return true if the newWordNode is successfully added to this dictionary, false otherwise
   */
  private static boolean addWordHelper(DictionaryWord newWordNode, DictionaryWord current) {
    // if new word is alphabetically before the current word
    if (newWordNode.getWord().compareToIgnoreCase(current.getWord()) < 0) {
      if (current.getLeftChild() == null) { // if there is no left child
        current.setLeftChild(newWordNode);
        return true;
      } else { // if there is a left child, call method again, with left child as current
        return addWordHelper(newWordNode, current.getLeftChild());
      }
    }
    // if new word is alphabetically after the current word
    else if (newWordNode.getWord().compareToIgnoreCase(current.getWord()) > 0) {
      if (current.getRightChild() == null) { // no right child
        current.setRightChild(newWordNode);
        return true;
      } else { // call method again with right child as current
        return addWordHelper(newWordNode, current.getRightChild());
      }
    } else { // the new word already exists in this dictionary
      return false;
    }
  }


  /**
   * Recursive helper method to lookup a word s in the subtree rooted at current
   * 
   * @param s       String that represents a word
   * @param current pointer to the current DictionaryWord within this dictionary
   * @return the meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if s is not found in this dictionary
   */
  private static String lookupHelper(String s, DictionaryWord current) {
    if (current != null) {
      if (s.equalsIgnoreCase(current.getWord())) { // the word was located
        return current.getMeaning();
      }
      // word is alphabetically before the current
      else if (s.compareToIgnoreCase(current.getWord()) < 0) {
        return lookupHelper(s, current.getLeftChild());
      } else { // the word is alphabetically after the current
        return lookupHelper(s, current.getRightChild());
      }
    }
    // only reached if a null node is reached because the word is not in the BST
    throw new NoSuchElementException("The word was not found in this dictionary");
  }


  /**
   * Recursive helper method that returns the number of dictionary words stored in the subtree
   * rooted at current
   * 
   * @param current current DictionaryWord within this dictionaryBST
   * @return the size of the subtree rooted at current
   */
  private static int sizeHelper(DictionaryWord current) {
    if (current == null) { // end of a subtree reached
      return 0;
    } else { // recursively solve for the size of left and right subtree, add 1 for the root
      return (sizeHelper(current.getLeftChild()) + sizeHelper(current.getRightChild()) + 1);
    }
  }


  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current DictionaryWord within this DictionaryBST
   * @return height of the subtree rooted at current counting the number of DictionaryWord nodes
   *         from the current node to the deepest leaf in the subtree rooted at current
   */
  private static int heightHelper(DictionaryWord current) {
    if (current == null) {
      return 0;
    }
    // recursively compute height of left and right subtrees
    int leftHeight = heightHelper(current.getLeftChild());
    int rightHeight = heightHelper(current.getRightChild());

    // height = height of the larger subtree + 1 for root node
    return 1 + Math.max(leftHeight, rightHeight);
  }



  /**
   * Recursive Helper method that returns a list of all the words stored in the subtree rooted at
   * current
   * 
   * @param current pointer to the current DictionaryWord within this dictionaryBST
   * @return an ArrayList of all the words stored in the subtree rooted at current
   */
  private static ArrayList<String> getAllWordsHelper(DictionaryWord current) {
    ArrayList<String> allWords = new ArrayList<String>();
    if(current == null) { // empty dictionary
      return allWords;
    }
    // recursively add the left child to the arrayList
    if (current.getLeftChild() != null)
      allWords.addAll(getAllWordsHelper(current.getLeftChild()));
    // visit the current node
    allWords.add(current.getWord());
    // recursively add the right child to the arrayList
    if (current.getRightChild() != null)
      allWords.addAll(getAllWordsHelper(current.getRightChild()));

    return allWords;

  }

}
