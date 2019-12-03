//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Thing
// Files: Action.java, Thing.java
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

import processing.core.PApplet;

/**
 * organizes the capabilities that are common to all interactive things in the Escape Room game
 * 
 * @author chrisgeorge
 *
 */
public class Thing {
  private final String NAME; // the constant name identifying this object
  private boolean isActive; // active means thing is visible and can be interacted with
  private static PApplet processing = null;

  /**
   * constructor: initialize name and set isActive to true
   * 
   * @param name to set to instance
   */
  public Thing(String name) {
    this.NAME = name;
    this.isActive = true;
  }

  /**
   * initializes processing field
   * 
   * @param processing PApplet object to set equal to static processing field of Thing
   */
  public static void setProcessing(PApplet processing) {
    Thing.processing = processing;
  }

  /**
   * accessor method to retrieve this static field
   * 
   * @return processing PApplet object
   */
  protected static PApplet getProcessing() {
    return processing;
  }

  /**
   * evaluates whether this instance of Thing has a certain name
   * 
   * @param name to compare with instance's name
   * @return true only when contents of name equal NAME
   */
  public boolean hasName(String name) {
    return this.NAME.equals(name);
  }

  /**
   * evaluates the state of isActive for an instance of Thing
   * 
   * @return true only when isActive is true
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * changes isActive to true
   */
  public void activate() {
    this.isActive = true;
  }

  /**
   * changes isActive to false
   */
  public void deactivate() {
    this.isActive = false;
  }

  /**
   * base Action method, will be overridden by subclasses. only returns null
   * 
   * @return null
   */
  public Action update() {
    return null;
  }
}
