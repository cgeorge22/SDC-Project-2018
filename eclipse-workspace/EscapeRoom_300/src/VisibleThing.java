//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Visible Thing
// Files: Action.java, Thing.java, VisibleThing.Java
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

import java.io.File;
import processing.core.PImage;

/**
 * represents a visible object with a graphical representation in the game
 * 
 * @author chrisgeorge
 *
 */
public class VisibleThing extends Thing {
  private PImage image; // the graphical representation of this thing
  private int x; // the horizontal position (in pixels of this thing's left side)
  private int y; // the vertical position (in pixels of this thing's top side)

  /**
   * constructor-- initializes this new VisibleThing
   * 
   * @param   name-- argument for super constructor for base class name
   * @param x -- horizontal position
   * @param y -- vertical position
   */
  public VisibleThing(String name, int x, int y) {
    super(name); // use previously defined Thing constructor to set name and isActive()
    this.x = x;
    this.y = y;
    // the image for this visible thing should be loaded from :
    // "images"+File.separator+ name +".png"
    image = VisibleThing.getProcessing().loadImage("images" + File.separator + name + ".png");

  }

  /**
   * draws an image at its position
   * 
   * return null Action
   */
  @Override
  public Action update() {
    VisibleThing.getProcessing().image(image, x, y);
    return null;
  }

  /**
   * changes x by adding dx to it (and y by dy)
   * 
   * @param dx -- change in x
   * @param dy -- change in y
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }
  
  /**
   * check if a given point is over the visibleThing image
   * 
   * @param x-- horizontal position of the point
   * @param y-- vertical position of the point
   * @return true only when point x,y is over image
   */
  public boolean isOver(int x, int y) {  
    if (x >= this.x && x <= (this.image.width + this.x) && y >= this.y
        && y <= (this.image.height + this.y)) {  // if (x,y) is in the bounds of the VisibleThing
      return true;
    }
    return false;
  }
  
  /**
   * check if a different image is overlapping this VisibleThing 
   * 
   * @param other-- another image
   * @return true only when other's image overlaps this one's
   */
  public boolean isOver(VisibleThing other) {
    // first image's top left coordinate
    int firstTopLeftX = this.x;  
    int firstTopLeftY = this.y;
    // first image's bottom right coordinate
    int firstBottomRightX = this.x + this.image.width;
    int firstBottomRightY = this.y + this.image.height;
    // second image's top left coordinate
    int secondTopLeftX = other.x;
    int secondTopLeftY = other.y;
    // second image's bottom right coordinate
    int secondBottomRightX = other.x + other.image.width;
    int secondBottomRightY = other.y + other.image.height;  
    
    // if one rectangle is on the left side of the other
    if (firstBottomRightX < secondTopLeftX || secondBottomRightX < firstTopLeftX) {
      return false; 
    }
    
    // if one rectangle is above the other
    if (firstBottomRightY < secondTopLeftY || secondBottomRightY < firstTopLeftY) {
      return false;
    }
    
    return true;
    
  }
}
