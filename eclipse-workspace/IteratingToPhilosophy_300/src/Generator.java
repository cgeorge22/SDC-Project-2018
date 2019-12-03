//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Generator
// Files: Generator.java, Finiteiterator.java, InfiniteIterator.java
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
 * creates either type of iterator for any object type
 * 
 * @author chrisgeorge
 * @param <T> any object type
 */
public class Generator<T> implements Iterable<T> {
  T firstValue; // starting point for iterator
  Function<T, T> generateNextFromLast; // function for each iteration
  int length; // for finite iterator: how many iterations to do

  /**
   * constructor used when InfiniteIterator needs to be made
   * 
   * @param firstValue- starting point
   * @param generateNextFromLast- function for iterator
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
  }

  /**
   * constructor used when FiniteIterator needs to be made
   * 
   * @param firstValue- starting point
   * @param generateNextFromLast- function for for iterator
   * @param length- number of iterations
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
    this.length = length;
  }

  /**
   * create and return the iterator based on whether length is specified
   * 
   * @return the iterator, either finite or infinite
   */
  @Override
  public Iterator<T> iterator() {
    if (length == 0) { // if length is zero no length was set, infinite iterator
      InfiniteIterator<T> iterator = new InfiniteIterator<>(firstValue, generateNextFromLast);
      return iterator;
    } else { // finite
      FiniteIterator<T> iterator =
          new FiniteIterator<>(new InfiniteIterator<>(firstValue, generateNextFromLast), length);
      return iterator;
    }
  }
}
