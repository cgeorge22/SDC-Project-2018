//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Action
// Files: Action.java, Thing.java, VisibleThing.Java, ClickableThing.java,
//        DraggableThing.java, DragAndDroppableThing.java
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

import java.util.ArrayList;

/**
 * represents the response to an object being clicked or dragged onto another
 * 
 * @author chrisgeorge
 *
 */
public class Action {
  private String message; // message printed by this action (or null to do nothing)
  private Thing thing;

  /**
   * initialize this new action
   * 
   * @param message: action message (may be null)
   */
  public Action(String message) {
    this.message = message;
  }
  
  public Action(Thing thing) {
    this.thing = thing;
  }
  
  public Action(String message, Thing thing) {
    this.message = message;
    this.thing = thing;
  }

  /**
   * if the Action object's Thing is not null, the Thing should be activated, added to the 
   * ArrayList, then made null. When message is not null, message is printed to System.out
   * 
   * @param things -- an ArrayList of Thing objects
   * 
   */
  public void act(ArrayList<Thing> things) {
    if (thing != null) {
      thing.activate();
      things.add(thing);
      thing = null;
    }
    if (this.message != null) { // checks if message is null
      System.out.println(message);
    }
  }
}
