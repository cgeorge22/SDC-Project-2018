//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Even Numbers
// Files: EvenNumbers.java
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

import java.util.Iterator;

/**
 * generates a sequence of even numbers started a specified starting point.
 * 
 * @author chrisgeorge
 *
 */
public class EvenNumbers implements Iterator<Integer> {
  int outNum;

  /**
   * constructor-- creates new EvenNumbers iterator, defining starting point for numbers
   * 
   * @param startingPoint-- will always be even
   */
  public EvenNumbers(int startingPoint) {
    this.outNum = startingPoint;
  }

  /**
   * always true-- even numbers never end
   */
  public boolean hasNext() {
    return true;
  }

  /**
   * advances to next even number, returns the current number
   */
  public Integer next() {
    Integer temp = outNum; // store the current number before changing it
    outNum = outNum + 2; // advance to the next even number
    return temp;
  }
}
