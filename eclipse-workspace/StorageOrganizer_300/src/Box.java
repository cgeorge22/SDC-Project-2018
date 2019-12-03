//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Box
// Files: Box.java
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

import java.util.Random;

/**
 * This class models a box to be stored in a Storage Unit using our StorageUnitOrganizer application
 * 
 * @author chrisgeorge
 */
public class Box implements Comparable<Box> {
  private static Random randGen = new Random(); // generator of random numbers
  private int color; // color of this box
  private int weight; // weight of this box in lbs between 1 inclusive and 31 exclusive

  /**
   * Creates a new Box and initializes its instance fields color and weight to random values
   */
  public Box() {
    color = randGen.nextInt(); // random color integer
    weight = randGen.nextInt(31) + 1; // random weight between 1 inclusive and 31 exclusive
  }

  /**
   * Creates a new Box and initializes its instance fields color and weight to the specified values
   * 
   * @param color
   * @param weight
   * @throws IllegalArgumentException if the provided weight value is out of the range of [1..30]
   */
  public Box(int color, int weight) throws IllegalArgumentException {
    this.color = color;
    if (weight >= 1 && weight <= 30) { // makes sure weight is in range
      this.weight = weight;
    } else { // if weight is out of range
      throw new IllegalArgumentException("Error: weight should be in range [1-30]");
    }

  }

  /**
   * equals method defined in Object class
   * 
   * @param other - another object to compare to this box object
   * @return true true if the specified other object is a Box and this box and other have the same
   *         color and same weight. Otherwise, it returns false
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Box) { // first checks if other is of Box type
      Box otherBox = (Box) other; // downcast other object to Box
      // compare color/weight of the two box objects
      return (this.color == otherBox.color && this.weight == otherBox.weight);
    } else {
      return false;
    }
  }

  /**
   * compareTo method defined in Comparable<Box> interface
   * 
   * @param otherBox - another box object to compare to this box object
   * @return a negative integer, a positive integer, or zero as this box is lighter than, heavier
   *         than, or has the same weight as the specified otherBox
   */
  @Override
  public int compareTo(Box otherBox) {
    // a simple subtraction of the weights (this - other) should return intended values
    return this.weight - otherBox.weight;
  }

  /**
   * Getter for the instance field color of this box
   * 
   * @return color integer value
   */
  public int getColor() {
    return this.color;
  }

  /**
   * Getter for the instance field weight of this box
   * 
   * @return weight integer value
   */
  public int getWeight() {
    return this.weight;
  }

}
