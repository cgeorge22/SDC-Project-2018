//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Draggable Thing
// Files: Action.java, Thing.java, VisibleThing.Java, ClickableThing.java, DraggableThing.java
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
 * clickable objects users can interact with by dragging
 * 
 * @author chrisgeorge
 *
 */
public class DraggableThing extends VisibleThing {
  private boolean mouseWasPressed; // similar to use in ClickableThing
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update

  /**
   * constructor initializes this DraggableThing object
   * 
   * @param name of DraggableThing
   * @param x    -- horizontal position
   * @param y    -- vertical position
   */
  public DraggableThing(String name, int x, int y) {
    super(name, x, y);
  }

  /**
   * calls VisibleThing update(), then moves according to mouse drag. each time isDragging changes
   * from true to false, the drop() method below will be called once and any action objects returned
   * from that method should then be returned from update()
   * 
   * @return action from drop method
   */

  @Override
  public Action update() {
    // current mouse position
    int mouseX = DraggableThing.getProcessing().mouseX;
    int mouseY = DraggableThing.getProcessing().mouseY;
    super.update();
    
    Action action = null;

    // if the DraggableThing is clicked on
    if (DraggableThing.getProcessing().mousePressed && mouseWasPressed == false
        && isOver(mouseX, mouseY)) {
      isDragging = true;
      // track mouse position as soon as dragging begins
      oldMouseX = mouseX;
      oldMouseY = mouseY;
    }
    // if the DraggableThing is clicked off of, it is no longer being dragged
    else if (!DraggableThing.getProcessing().mousePressed && isDragging) {
      isDragging = false;
      action = drop();
    }
    // when the Thing is being dragged
    else if (isDragging) {
      // move the Thing by the amount the mouse has moved since the last update
      super.move(mouseX - oldMouseX, mouseY - oldMouseY);
      // update the oldMouse point to equal the current (for the next update)
      oldMouseX = mouseX;
      oldMouseY = mouseY;
    }

    // update mouseWasPressed for the next update cycle at the end of update()
    mouseWasPressed = getProcessing().mousePressed;
    return action;

  }

  /**
   * always returns null; subclasses will do more interesting things
   * 
   * @return null
   */
  protected Action drop() {
    return null;
  }
}
