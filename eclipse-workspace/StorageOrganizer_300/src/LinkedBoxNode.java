//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Linked Box Node
// Files: Box.java, LinkedBoxNode.java
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
 * This class models a linked node of type box in our application
 * 
 * @author chrisgeorge
 *
 */
public class LinkedBoxNode {
  private Box box; // box that represents the data for this Linked node
  private LinkedBoxNode next; // reference to the next Linked Box Node

  /**
   * constructor: creates a new LinkedBoxNode object with a given box and without referring to any
   * next LinkedBoxNode
   * 
   * @param box - data in this LinkedBoxNode
   */
  public LinkedBoxNode(Box box) {
    this.box = box;
  }

  /**
   * constructor: creates a new LinkedBoxNode object and sets its instance fields box and next to
   * the specified ones
   * 
   * @param box  - data in this LinkedBoxNode
   * @param next - reference to next LinkedBoxNode
   */
  public LinkedBoxNode(Box box, LinkedBoxNode next) {
    this.box = box;
    this.next = next;
  }

  /**
   * returns reference to next LinkedBoxNode
   * 
   * @return nextLinkedBoxNode
   */
  public LinkedBoxNode getNext() {
    return this.next;
  }

  /**
   * changes the reference to the next LinkedBoxNode
   * 
   * @param next - new reference to next l\LinkedBoxNode
   */
  public void setNext(LinkedBoxNode next) {
    this.next = next;
  }

  /**
   * returns the box data of this LinkedBoxNode
   * 
   * @return data inside this LinkedBoxNode
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * changes the box object inside this LinkedBoxNode
   * 
   * @param box - the new box object for this LinkedBoxNode
   */
  public void setBox(Box box) {
    this.box = box;
  }
}
