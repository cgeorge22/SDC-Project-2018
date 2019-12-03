//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Clickable Thing
// Files: Action.java, Thing.java, VisibleThing.Java, ClickableThing.java
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
 * represents objects that user can interact with by clicking
 * 
 * @author chrisgeorge
 *
 */
public class ClickableThing extends VisibleThing {
  private Action action; // action returned from update when this object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed during the last update()

  /**
   * constructor initializes this ClickableThing object
   * 
   * @param name   of ClickableThing
   * @param x      -- horizontal position
   * @param y      -- vertical position
   * @param action -- Action of the ClickableThing
   */
  public ClickableThing(String name, int x, int y, Action action) {
    super(name, x, y); // use super constructor for name and position
    this.action = action; // assign instance action
  }

  /**
   * calls VisibleThing update, then returns action only when mouse is first clicked
   * 
   */
  @Override
  public Action update() {
    super.update(); // update from VisibleThing class
    if (!mouseWasPressed) { // checks that mouse was not clicked on previous update
      if (ClickableThing.getProcessing().mousePressed && this
          .isOver(ClickableThing.getProcessing().mouseX, ClickableThing.getProcessing().mouseY)) {
        mouseWasPressed = true;
        return this.action; // only returns when mouse is first clicked
      }
    }
    // update mouseWasPressed upon release
    mouseWasPressed = ClickableThing.getProcessing().mousePressed; 
    return null;
  }
}
