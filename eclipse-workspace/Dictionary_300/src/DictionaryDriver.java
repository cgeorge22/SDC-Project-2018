//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dictionary Driver
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
import java.util.Scanner;

/**
 * Program makes use of DictionaryBST to provide the user with an interactive dictionary with
 * several functions
 * 
 * @author chrisgeorge
 *
 */
public class DictionaryDriver {
  /**
   * Method that prints the user menu
   * 
   */
  private static void printMenu() {
    String userCommands = "\n=========================== Dictionary ============================\n"
        + "Enter one of the following options:\n"
        + "[A <word> <meaning>] to add a new word and its definition in the dictionary\n"
        + "[L <word>] to search a word in the dictionary and display its definition\n"
        + "[G] to print all the words in the dictionary in sorted order\n"
        + "[S] to get the count of all words in the dictionary\n"
        + "[H] to get the height of this dictionary implemented as a binary search tree\n"
        + "[Q] to quit the program\n"
        + "======================================================================\n";
    System.out.println(userCommands);
  }

  /**
   * separates the user input string into an array
   * 
   * @param input-- the user input as a string
   * @return-- the user input separated into an array
   */
  private static String[] processInput(String input) {
    String[] array;
    array = input.split("\\s+", 3); // split the input into array with maximum of 3 indexes
    return array;
  }

  /**
   * adds a word to a given dictionary using DictionaryBST.addWord(), printing a warning if the word
   * is a duplicate
   * 
   * @param dict  : the dictionary to add the word to
   * @param input : the 3-index user input array: {"A", <word>, <meaning>}
   */
  private static void add(DictionaryBST dict, String[] input) {
    String word = input[1];
    String meaning = input[2];
    if (dict.addWord(word, meaning) == false) { // if duplicate word
      System.out.println("WARNING: failed to add duplicate word: " + word + ".");
    }
  }

  /**
   * looks up the meaning of a word in a given dictionary using DictionaryBST.lookup(), handling
   * cases of empty dictionary or no definition
   * 
   * @param dict  : the dictionary to look the word up in
   * @param input : the 2- index user input array: {"L", <word>}
   */
  private static void lookup(DictionaryBST dict, String[] input) {
    String meaning;
    String word = input[1];
    if (dict.isEmpty()) { // empty dictionary has no meanings
      System.out.println("There are no definitions in this empty dictionary");
    } else {
      try {
        meaning = dict.lookup(word);
        System.out.println(word + ": " + meaning);
      } catch (NoSuchElementException e) { // no meaning found if exception thrown
        System.out.println("No definition found for the word " + word);
      }
    }
  }

  /**
   * returns all words in a given dictionary using DictionaryBST.getAllWords(), handling case of
   * empty dictionary
   * 
   * @param dict : the dictionary to print all words from
   */
  private static void getAllWords(DictionaryBST dict) {
    ArrayList<String> wordList;
    String output;
    if (dict.isEmpty()) {
      System.out.println("Dictionary is empty.");
    } else {
      wordList = dict.getAllWords();
      output = wordList.toString();
      // remove the brackets created by arrayList.toString()
      output = output.replace("[", "");
      output = output.replace("]", "");
      System.out.println(output);
    }
  }

  /**
   * prints the size of a given dictionary
   * 
   * @param dict : dictionary to get the size of
   */
  private static void size(DictionaryBST dict) {
    System.out.println(dict.size());
  }

  /**
   * prints the height of a given dictionary
   * 
   * @param dict : dictionary to get the height of
   */
  private static void height(DictionaryBST dict) {
    System.out.println(dict.height());
  }

  /**
   * main method calls other methods and displays/processes prompts to create the user experience
   * 
   * @param args command line arguments (unused)
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    DictionaryBST dictionary = new DictionaryBST();
    String userIn;
    String[] inArray;

    do {
      printMenu();
      System.out.print("Enter your command: ");
      userIn = sc.nextLine().trim();
      inArray = processInput(userIn);

      switch (inArray[0]) { // examine first index of the array for a recognized command letter
        case "q":
        case "Q":
          break;
        case "a":
        case "A":
          if (inArray.length == 3) { // checks syntax length-- should be 3
            add(dictionary, inArray);
          } else { // if the number of arguments is wrong
            System.out.println("WARNING: Syntax Error for [A <word> <meaning>] command line");
          }
          break;
        case "l":
        case "L":
          if (inArray.length == 2) {
            lookup(dictionary, inArray);
          } else { // if the number of arguments is wrong
            System.out.println("WARNING: Syntax Error for [L <word>] command line");
          }
          break;
        case "g":
        case "G":
          if (inArray.length == 1) {
            getAllWords(dictionary);
          } else { // if the number of arguments is wrong
            System.out.println("WARNING: Syntax Error for [G] command line");
          }
          break;
        case "s":
        case "S":
          if (inArray.length == 1) {
            size(dictionary);
          } else { // if the number of arguments is wrong
            System.out.println("WARNING: Syntax Error for [S] command line");
          }
          break;
        case "h":
        case "H":
          if (inArray.length == 1) {
            height(dictionary);
          } else { // if the number of arguments is wrong
            System.out.println("WARNING: Syntax Error for [H] command line");
          }
          break;
        default: // user enters anything other than the commands above
          System.out.println("WARNING: Unrecognized command.");
      }

    } while (!inArray[0].equalsIgnoreCase("Q"));

    System.out.println("============================== END ===================================");
  }

}
