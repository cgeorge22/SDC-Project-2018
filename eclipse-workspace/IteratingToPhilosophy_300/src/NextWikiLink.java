//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Next Wiki Link
// Files: NextWikiLink.java, Generator.java, Finiteiterator.java, InfiniteIterator.java
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

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * follows the link in one Wikipedia page to the next for a certain number of iterations
 * 
 * @author chrisgeorge
 *
 */
public class NextWikiLink implements Function<String, String> {

  /**
   * returns the next page
   * 
   * @param t : previous wikipedia page
   * @return next wikipedia page or error message if it could not be accessed
   */
  @Override
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }

  /**
   * main method: asks for user input and performs iterations of wikipedia page follows
   * 
   * @param args-- command line args unused
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String topicName;
    int iterations;
    // Implement your own Wikipedia crawling application here.
    // 1. prompt the user to enter a topic name and number of iterations to follow
    System.out.print("Enter a wikipedia page topic: ");
    topicName = sc.nextLine().trim();
    System.out.print("Enter the number of pages you'd like to step through: ");
    iterations = sc.nextInt();
    // 2. prepend "/wiki/" to the user's input, and replace spaces with underscores
    topicName = "/wiki/" + topicName;
    topicName = topicName.replace(' ', '_');
    // 3. use a for-each loop to iterate through the number of links requested
    // generate a FiniteIterator of type string to iterate through links
    Generator<String> linkIteratorGen = new Generator<>(topicName, new NextWikiLink(), iterations);
    for (String s : linkIteratorGen) { // for each link
      System.out.println(s);
      // if any Exceptions happen,terminate the loop after printing one error message
      if (s.startsWith("FAILED ")) {
        break;
      }
    }
  }
}
