//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Test Driver
// Files: TestDriver.java, EvenNumbers.java, Generator.java, Finiteiterator.java,
// InfiniteIterator.java
// Course: CS 300 Spring 2019
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

import java.util.function.Function;

/**
 * contains several tests for the classes in IteratingToPhilosophy
 * 
 * @author chrisgeorge
 *
 */
public class TestDriver {
  /**
   * tests if EvenNumbers is working properly
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testEvenNumbers() {
    EvenNumbers it = new EvenNumbers(44);
    if (it.next() != 44) {
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");
      return false;
    }
    if (it.next() != 46) {
      System.out.println(
          "The second call of EvenNumbers.next() " + "did not return the smallest even number, "
              + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out.println("EvenNumbers.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * tests if InfiniteIterator is working properly for Integer objects
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testPowersOfTwo() {
    InfiniteIterator<Integer> it = new InfiniteIterator<>(8, new NextPowerOfTwo());
    if (it.next() != 8) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;
    }
    if (it.next() != 16) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * tests if InfiniteIterator is working properly for String objects
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testAddExtraSmile() {
    InfiniteIterator<String> it = new InfiniteIterator<>("Hello", new AddExtraSmile());
    if (!it.next().equals("Hello")) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;
    }
    if (!it.next().contains(" :)")) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * tests if FiniteIterator is working properly
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testFiniteIterator() {
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());
    FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);
    String s = "";
    while (it.hasNext())
      s += " " + it.next();
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }

  /**
   * tests if Generator is working properly
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testGenerator() {
    // infinite -- uses same test as testPowersOfTwo(), but adds Generator
    Generator<Integer> infiniteGen = new Generator<>(8, new NextPowerOfTwo());
    InfiniteIterator<Integer> it = (InfiniteIterator<Integer>) infiniteGen.iterator();
    if (it.next() != 8) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;
    }
    if (it.next() != 16) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }

    // finite -- uses same test as testFiniteIterator(), but adds Generator
    Generator<Integer> finiteGen = new Generator<>(2, new NextPowerOfTwo(), 8);
    String s = "";
    for (Integer i : finiteGen) // add generated numbers to s using a for each loop
      s += " " + i;
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }

  /**
   * main method-- returns test results for all the test methods
   * 
   * @param args-- command line unused
   */
  public static void main(String[] args) {
    System.out.println(testEvenNumbers());
    System.out.println(testPowersOfTwo());
    System.out.println(testAddExtraSmile());
    System.out.println(testFiniteIterator());
    System.out.println(testGenerator());

  }

}


/**
 * helper class that returns next power of two of a number
 * 
 * @author dahl
 *
 */
class NextPowerOfTwo implements Function<Integer, Integer> {
  /**
   * computes the next power of 2
   * 
   * @return the next power of 2
   */
  @Override
  public Integer apply(Integer previous) {
    return 2 * previous;
  }
}


/**
 * helper class that adds a ":)" to the end of a string
 * 
 * @author dahl
 *
 */
class AddExtraSmile implements Function<String, String> {
  @Override
  public String apply(String t) {
    return t + " :)";
  }
}
