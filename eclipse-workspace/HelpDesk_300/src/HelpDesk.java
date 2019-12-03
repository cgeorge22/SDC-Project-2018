//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Help Desk
// Files: SupportTicket.java, HelpDeskInterface.java, HelpDesk.java, HelpDeskTestSuite.java
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
 * Uses priority queue data structure to model a Help Desk for a business with support tickets of
 * different priorities
 * 
 * @author chrisgeorge
 *
 */
public class HelpDesk implements HelpDeskInterface {
  protected SupportTicket[] array; // zero-indexed max-heap
  protected int size;

  /**
   * constructor for
   * 
   * @param capacity
   */
  public HelpDesk(int capacity) {
    array = new SupportTicket[capacity];
  }

  /**
   * Creates and adds a new SupportTicket to this HelpDesk.
   * 
   * @param message names the client and describes their need for support.
   * @throws NullPointerException      when the String message argument is null.
   * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
   */
  @Override
  public void createNewTicket(String message) {
    if (array.length == size) {
      throw new IndexOutOfBoundsException("Error: the HelpDesk queue is full.");
    }
    SupportTicket newTicket = new SupportTicket(message);
    array[size] = newTicket; // initially add the new ticket to the end of the array
    propagateUp(size); // percolate new SupportTicket up to maintain HelpDesk order
    size++; // increment size after adding is complete

  }

  /**
   * Returns the message within this HelpDesk that has the highest priority. This method does not
   * change the state of this HelpDesk.
   * 
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String checkNextTicket() {
    if(size == 0) {
      throw new IllegalStateException("Error: the HelpDesk queue is empty");
    }
    return array[0].toString(); // highest priority is the first element
  }

  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   * 
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String closeNextTicket() {
    if(size == 0) {
      throw new IllegalStateException("Error: the HelpDesk queue is empty");
    }
    SupportTicket removed = array[0]; // save reference to node before removal
    array[0] = array[size - 1]; // make last node the new root
    array[size - 1] = null; // remove extra copy of the last node
    size--; // decrement size after removal
    propagateDown(0); // percolate new root node down to maintain Help desk order
    
    return removed.toString();
  }

  /**
   * Given an index into the heap array, this method returns that index's parent index.
   * 
   * @param index - an index in the heap array
   * @return index of the parent
   */
  protected static int parentOf(int index) {
    return (index - 1) / 2; // using algorithm to find parent index in heap array
  }

  /**
   * Given an index into the heap array, this method returns that index's left child index.
   * 
   * @param index - an index in the heap array
   * @return index of the left child
   */
  protected static int leftChildOf(int index) {
    return 2 * index + 1; // using algorithm to find the left child in heap array
  }

  /**
   * Given an index into the heap array, this method returns that index's right child index.
   * 
   * @param index - an index in the heap array
   * @return index of the right child
   */
  protected static int rightChildOf(int index) {
    return 2 * index + 2; // using algorithm to find right child in heap array
  }

  /**
   * Given two indexes into the heap array, this method swaps the SupportTickets at those indexes.
   * 
   * @param indexA - the first index to swap
   * @param indexB - the other index to swap
   */
  protected void swap(int indexA, int indexB) {
    SupportTicket temp = array[indexA];
    array[indexA] = array[indexB];
    array[indexB] = temp;
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and the heap's root.
   * 
   * @param index - of the SupportTicket to percolate up
   */
  protected void propagateUp(int index) {
    // swap the SupportTicket and its parent if the SupportTicket is greater than its parent
    if (index > 0 && array[index].compareTo(array[parentOf(index)]) > 0) {
      swap(index, parentOf(index));
      propagateUp(parentOf(index)); // recursive call with new position of the SupportTicket
    }
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and it's children.
   * 
   * @param index - of the SupportTicket to percolate down
   */
  protected void propagateDown(int index) {
    int leftChild = -1;
    int rightChild = -1;
    int largerChild = -1;
    if (leftChildOf(index) < size) { // if the leftChild index is in range
      leftChild = leftChildOf(index);
    }
    if (rightChildOf(index) < size) { // if the rightChild index is in range
      rightChild = rightChildOf(index);
    }

    // determine index of larger child:
    if (leftChild == -1 && rightChild == -1) { // if there are no children
      largerChild = -1;
    } else if (rightChild == -1) { // if there is only one child, will always be left child
      largerChild = leftChild;
    } else if (array[leftChild].compareTo(array[rightChild]) >= 0) { // left child is greater
      largerChild = leftChild;
    } else { // right child is greater
      largerChild = rightChild;
    }
    // swap nodes if a child that is larger than the current node exists
    if (largerChild != -1 && array[index].compareTo(array[largerChild]) < 0) {
      swap(index, largerChild);
      propagateDown(largerChild); // recursive call with new position to continue percolation
    }
  }
}
