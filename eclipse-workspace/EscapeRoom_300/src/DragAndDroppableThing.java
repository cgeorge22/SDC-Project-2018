//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Drag and Droppable Thing
// Files: Action.java, Thing.java, VisibleThing.Java, ClickableThing.java, DraggableThing.java, 
//        DragAndDroppableThing.java
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
 * specifies a target for a DragAndDroppable to be dropped on along with an action that is produced 
 * when this happens
 * 
 * @author chrisgeorge
 *
 */
public class DragAndDroppableThing extends DraggableThing {
  private VisibleThing target; // object over which this object can be dropped
  private Action action; // action that results from dropping this object over target
   
  /**
   * constructor: initialize new DragAndDroppableThing
   * 
   * @param name of Thing
   * @param x -- h. position
   * @param y -- v. position
   * @param target -- place to drop the DragAndDroppableThing
   * @param action -- action that happens when target is hit
   */
  public DragAndDroppableThing(String name, int x, int y, VisibleThing target, Action action) {
    super(name,x,y);
    this.target = target;
    this.action = action;
  } 
  
  /**
   * deactivates objects in response to a successful drop (when the object is over its target and
   * its target is active). Deactivates both objects on success.
   * 
   * @return Action when target and object and active, null otherwise
   */
  @Override
  protected Action drop() {
    // if DragAndDroppableThing is on top of target and that both are active
    if( this.isOver(target) && this.isActive() && target.isActive()) {
      this.deactivate();
      target.deactivate();
      return this.action;
    } else {
      return null;
    }
  }
}
