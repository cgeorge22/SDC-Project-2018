//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Linked Box List
// Files: Box.java, LinkedBoxNode.java, LinkedBoxList.java
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
 * This class models a dynamic list to store box objects sorted in a descendant order with respect
 * to their weights.
 * 
 * @author chrisgeorge
 *
 */
public class LinkedBoxList {
  private LinkedBoxNode head; // head of this LinkedBoxList (refers to the element
                              // stored at index 0 within this list)
  private int size; // number of boxes already stored in this list
  private int capacity; // capacity of this LinkedBoxList
                        // maximum number of box elements that this LinkedBoxList
                        // can store

  /**
   * Creates an empty LinkedBoxList with a given initial capacity
   * 
   * @param capacity - maximum number of box elements this LinkedBoxList can store
   */
  public LinkedBoxList(int capacity) {
    this.capacity = capacity;
  }

  /**
   * Returns the size of this list
   * 
   * @return size of this LinkedBoxList
   */
  public int size() {
    return size;
  }

  /**
   * Return the capacity of this list
   * 
   * @return maximum capacity of this list
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Expands the capacity of this LinkedBoxList with the specified number a of additional elements
   * 
   * @param a - amount of additional capacity to add to list
   */
  public void expandCapacity(int a) {
    capacity += a;
  }

  /**
   * Checks whether this LinkedBoxList is empty
   * 
   * @return true if this LinkedBoxList is empty, false otherwise
   */
  public boolean isEmpty() {
    return head == null; // if the head is null, the list is empty
  }

  /**
   * Checks whether this LinkedBoxList is full
   * 
   * @return true if this list is full, false otherwise
   */
  public boolean isFull() {
    return size == capacity; // if the size is at capacity, the LinkedBoxList is full
  }

  /**
   * Adds a new box into this sorted list according to weight
   * 
   * @param newBox - new Box object to add to the list
   * @throws IllegalArgumentException if newBox is null
   * @throws IllegalStateException    if this list is full
   */
  public void add(Box newBox) throws IllegalArgumentException, IllegalStateException {
    if (newBox == null) { // if the newBox parameter is null
      throw new IllegalArgumentException("Error: the new box cannot be null.");
    }
    if (isFull()) { // if the linkedBoxList is at capacity
      throw new IllegalStateException("Error: the list is already full.");
    } else if (isEmpty()) { // if the linkedBoxList is empty
      head = new LinkedBoxNode(newBox);
      size++; // increment list size
    }
    // if newBox is heavier than the head box (the current heaviest box)
    else if (newBox.compareTo(head.getBox()) > 0) {
      // new LinkedBoxNode created, with newBox as the box and next as the current head
      LinkedBoxNode newLinkedBox = new LinkedBoxNode(newBox, head);
      head = newLinkedBox; // the newLinkedBox is the new head (heaviest)
      size++;
    } else { // any other case: newBox is less or equal weight to head box
      if (head.getNext() == null) { // there is only 1 box in the list
        head.setNext(new LinkedBoxNode(newBox));
        size++;
      } else {
        LinkedBoxNode lastNode = head;
        while (lastNode.getNext() != null) { // traverse through this LinkedBoxList until exhausted
          // newBox weight is heavier than the box after the current box
          if (newBox.compareTo(lastNode.getNext().getBox()) > 0) {
            // insert newBox between the current node and its next node
            lastNode.setNext(new LinkedBoxNode(newBox, lastNode.getNext()));
            size++; // increment list size
            break;
          }
          // if newBox is lighter or the same weight as the box after the currentBox
          else if (newBox.compareTo(lastNode.getNext().getBox()) <= 0) {
            lastNode = lastNode.getNext(); // increment position in list
            if (lastNode.getNext() == null) { // if the new position is the last spot in the list
              lastNode.setNext(new LinkedBoxNode(newBox)); // newBox goes at the end of the list
              size++; // increment list size
              break;
            }
          }
        }
      }
    }
  }

  /**
   * Checks if this list contains a box that matches with (equals) a specific box object
   * 
   * @param findBox Box to search for in the LinkedBoxList
   * @return true if this list contains findBox, false otherwise
   */
  public boolean contains(Box findBox) {
    LinkedBoxNode currNode = head;
    while (currNode != null) { // traverse through contents of the list
      if (currNode.getBox().equals(findBox)) { // true if findBox found
        return true;
      } else {
        currNode = currNode.getNext(); // increment through list
      }
    }
    return false;
  }

  /**
   * Returns a box stored in this list given its index
   * 
   * @param index - index of box to return
   * @return box at specified index in list
   * @throws IndexOutOfBoundsException if index is out of the range 0..size-1
   */
  public Box get(int index) throws IndexOutOfBoundsException {
    if (index < 0 && index > size() - 1) { // index is out of range
      throw new IndexOutOfBoundsException("Error: this index does not exist in the list.");
    }
    LinkedBoxNode currNode = head;
    // advance through list <index> number of times to get to the correct index
    for (int i = 0; i < index; i++) {
      currNode = currNode.getNext();
    }
    return currNode.getBox(); // box at <index>
  }

  /**
   * Removes a returns the box stored at index from this LinkedBoxList
   * 
   * @param index of box to remove
   * @return removed Box
   * @throws IndexOutOfBoundsException if index is out of bounds. index should be in the range of
   *                                   [0.. size()-1]
   */
  public Box remove(int index) throws IndexOutOfBoundsException {
    Box removed;
    if (index < 0 && index > size() - 1) { // index is out of range
      throw new IndexOutOfBoundsException("Error: this index does not exist in the list.");
    }
    if (index == 0) {
      removed = head.getBox(); // stores old head before changing head
      if (size == 1) {
        head = null;
      } else {
        head = head.getNext();
      }
      size--;
    } else {
      LinkedBoxNode currNode = head;
      for (int i = 0; i < index - 1; i++) { // advances to linkedBoxNode before the one to remove
        currNode = currNode.getNext();
      }
      removed = currNode.getNext().getBox(); // stores removed box before removing it from list
      // set removed node's next to previous node's next unless the last node is removed
      if (index != size() - 1) {
        currNode.setNext(currNode.getNext().getNext());
      } else { // last node was removed: previous node is now the last node
        currNode.setNext(null);
      }
      size--;
    }

    return removed;
  }

  /**
   * Removes all the boxes from this list
   */
  public void clear() {
    head = null; // clearing the head loses access to the rest of the list
    size = 0; // reset size
  }

  /**
   * Returns a String representation for this LinkedBoxList
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(); // creates a StringBuilder object
    String newLine = System.getProperty("line.separator");
    result.append("------------------------------------------------" + newLine);
    if (!isEmpty()) {
      LinkedBoxNode runner = head;
      int index = 0;
      // traverse the list and add a String representation for each box
      while (runner != null) {
        result.insert(0,
            "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs" + newLine);
        runner = runner.getNext();
        index++;
      }
      result.insert(0, "Box List Content:" + newLine);
    }
    result.insert(0, "List size: " + size + " box(es)." + newLine);
    result.insert(0, "Box List is empty: " + isEmpty() + newLine);
    result.insert(0, "------------------------------------------------" + newLine);
    return result.toString();
  }
}

