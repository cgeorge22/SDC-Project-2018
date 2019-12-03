//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Infinite Iterator
// Files: InfiniteIterator.java
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
import java.util.function.Function;

/**
 * generates a sequence of objects of Type T, where the next object is created by transforming the
 * current object.
 * 
 * @author chrisgeorge
 * @param <T> : any object type
 *
 */
public class InfiniteIterator<T> implements Iterator<T> {
  T out;
  Function<T, T> transformer; // function used to get next object

  /**
   * constructor-- creates new Infinite iterator, defining starting object and the function that
   * will be used to return the next object
   * 
   * @param startingPoint- the first object
   */
  public InfiniteIterator(T startingPoint, Function<T, T> transformer) {
    this.out = startingPoint;
    this.transformer = transformer;
  }

  /**
   * always true-- objects are infinitely iterated
   */
  public boolean hasNext() {
    return true;
  }

  /**
   * iterates to next object by transforming the current object, returns the current object
   */
  public T next() {
    T temp = out; // store the current object before changing it
    out = transformer.apply(out); // change the current object-- iterate
    return temp;
  }
}
