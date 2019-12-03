//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ServingQueue
// Files: QueueTests.java, Guest.java, ServingQueue.java, DessertSolvers.java
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
 * a queue of Guest objects
 * 
 * @author chrisgeorge
 *
 */
public class ServingQueue {
  private Guest[] array;
  private int size; // how many guests in the array
  private int enqueueTo = 0; // where in the array a new guest will be put in (circular indexing)

  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" guests. This queue should be
   * initialized to be empty.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   */
  public ServingQueue(int seatsAtTable) {
    array = new Guest[seatsAtTable];
  }

  /**
   * Checks whether there are any guests in this serving queue.
   * 
   * @return true when this queue contains zero guests, and false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Adds a single new guest to this queue (to be served after the others that were previously added
   * to the queue).
   * 
   * @param newGuest is the guest that is being added to this queue.
   * @throws IllegalStateException when called on a ServingQueue with an array that is full
   */
  public void add(Guest newGuest) {
    if (size == array.length) { // array full
      throw new IllegalStateException("Error: the ServingQueue is full");
    }
    array[enqueueTo] = newGuest;
    size++;
    enqueueTo = (enqueueTo + 1) % array.length; // update based on circular indexing

  }

  /**
   * Accessor for the guest that has been in this queue for the longest. This method does not add or
   * remove any guests.
   * 
   * @return a reference to the guest that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest peek() {
    return array[(enqueueTo - size + array.length) % array.length]; // circular indexing
  }

  /**
   * Removes the guest that has been in this queue for the longest.
   * 
   * @return a reference to the specific guest that is being removed.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest remove() {
    if (size == 0) { // if the array is empty
      throw new IllegalStateException("Error: ServingQueue empty");
    }
    // copy before removing, finding the correct index with circular indexing
    Guest tmp = array[(enqueueTo - size + array.length) % array.length];
    array[(enqueueTo - size + array.length) % array.length] = null; // empty the index
    size--; // decrement size
    return tmp; // return reference of removed Guest
  }

  /**
   * The string representation of the guests in this queue should display each of the guests in this
   * queue (using their toString() implementation), and should display them in a comma separated
   * list that is surrounded by a set of square brackets. (this is similar to the formatting of
   * java.util.ArrayList.toString()). The order that these guests are presented in should be (from
   * left to right) the guest that has been in this queue the longest, to the guest that has been in
   * this queue the shortest. When called on an empty ServingQueue, returns "[]".
   * 
   * @return string representation of the ordered guests in this queue
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    int printIndex;
    String toReturn = "";
    int itemsLeft = size; // keeps track of how many items left in array
    while (itemsLeft != 0) {
      // same algorithm as remove(), but does not change array
      printIndex = (enqueueTo - itemsLeft + array.length) % array.length;
      toReturn += ", " + array[printIndex].toString();
      itemsLeft--;
    }
    if (!toReturn.equals("")) // if the queue is not empty (commas added)
      toReturn = toReturn.substring(2); // cut off the extra comma and space in the beginning
    toReturn = "[" + toReturn + "]";

    return toReturn;
  }
}
