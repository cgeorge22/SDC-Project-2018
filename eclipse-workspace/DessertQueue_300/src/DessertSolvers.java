//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dessert Solvers
// Files: QueueTests.java, Guest.java, ServingQueue.java, DessertSolvers.java
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
 * solves various dessert serving problems
 * 
 * @author chrisgeorge
 *
 */
public class DessertSolvers {

  /**
   * method to find the last guest served, when the number of guests and guests skipped are variable
   * 
   * @param numberOfGuests at the table
   * @param guestsSkipped  how many guests to skip over (add to the end of the queue)
   * @return reference to last guest served
   * @throws IllegalArgumentException if numberOfGuests isn't positive, if guestsSkipped is negative
   * 
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {
    if (numberOfGuests <= 0) {
      throw new IllegalArgumentException("Error: numberOfGuests should be a positive number");
    }
    if (guestsSkipped < 0) {
      throw new IllegalArgumentException("Error: guestsSkipped cannot be negative");
    }

    Guest lastGuest = null; // should end up as the last guest served
    ServingQueue table = new ServingQueue(numberOfGuests);
    for (int i = 0; i < numberOfGuests; i++) { // fill queue with given number of guest objects
      table.add(new Guest());
    }
    while (!table.isEmpty()) { // loop should run until the last guest is served and removed
      lastGuest = table.remove(); // record served guest
      if (!table.isEmpty()) { // only call remove() when the queue is not empty to avoid throw
        for (int i = 0; i < guestsSkipped; i++) { // skip specified number of guests
          table.add(table.remove()); // add skipped guest to the end of the queue
        }
      }
    }
    return lastGuest;
  }

  /**
   * calculates who will be served dessert first when there are a variable number of courses
   * 
   * @param numberOfGuests: number of guests at the table
   * @param coursesServed: number of courses served
   * @return reference to the guest who will be served dessert (the last course) first
   * @throws IllegalArgumentException If either numberOfGuests or coursesServed is not positive
   */
  public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {
    Guest.resetNextGuestIndex();
    if (numberOfGuests <= 0) {
      throw new IllegalArgumentException("Error: numberOfGuests should be positive");
    }
    if (coursesServed <= 0) {
      throw new IllegalArgumentException("Error: coursesServed should be positive");
    }
    ServingQueue table = new ServingQueue(numberOfGuests);
    for (int i = 0; i < numberOfGuests; i++) { // fill queue with given number of guest objects
      table.add(new Guest());
    }
    Guest firstForDessert = null; // should end up as the first guest for dessert
    if (coursesServed == 1) { // special case: if there is only 1 course
      firstForDessert = table.peek(); // the first guest will get dessert first
    } else {
      while (coursesServed > 1) { // want to iterate until the second to last course
        // second table to store the next course order
        ServingQueue table2 = new ServingQueue(numberOfGuests);
        while (!table.isEmpty()) { // loop should run until the last guest is served and removed
          firstForDessert = table.peek(); // store first guest to be served, but don't remove
          table2.add(table.remove()); // empty first table, fill the next table in order
          if (!table.isEmpty()) { // only call remove() when the queue is not empty to avoid throw
            table.add(table.remove()); // add skipped guest to the end of the queue
          }
        }
        table = table2; // the next course table -> current course table
        for (int i = 0; i < numberOfGuests - 1; i++) {
          // shift order of queue so last guest to be served in the last course is the first to be
          // served in the next course
          table.add(table.remove());
        }
        coursesServed--; // decrement loop counter
      }
    }

    return firstForDessert;
  }


  public static void main(String[] args) {
    System.out.println(firstDessertVariableSkips(8, 3).toString());
    System.out.println(firstDessertVariableCourses(8, 3).toString());
  }
}
