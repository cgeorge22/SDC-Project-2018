//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Finite Iterator
// Files: Finiteiterator.java, InfiniteIterator.java
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
 * retrieves a fixed number of elements from any InfiniteIterator
 * 
 * @author chrisgeorge
 * @param <T> any object type
 */
public class FiniteIterator<T> implements Iterator<T> {
  InfiniteIterator<T> infiniteIterator; // Infinite iterator object to retreive from
  int length; // number of times to iterate
  private int current; // current iteration - 1

  /**
   * constructor-- creates new FiniteIterator, defining the number of iterations and the
   * InfiniteIterator to use
   * 
   * @param infiniteIterator -- InfiniteIterator to retrieve values from
   * @param length           -- number of times to iterate
   */
  public FiniteIterator(InfiniteIterator<T> infiniteIterator, int length) {
    this.infiniteIterator = infiniteIterator;
    this.length = length;
  }

  /**
   * dictates whether another iteration can be made
   * 
   * @return true if specified number of iterations has not been reached, false otherwise
   */
  @Override
  public boolean hasNext() {
    return current < length;
  }

  /**
   * increments iteration counter, returns the next iteration
   * 
   * @return the next iteration
   */
  @Override
  public T next() {
    current++;
    return infiniteIterator.next();
  }
}
