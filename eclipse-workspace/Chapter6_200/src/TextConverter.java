//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Text Converter
// Files: TextConverter.java
// Course: CS200 Fall 2018
//
// Author: Chris George
// Email: crgeorge@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: name of your pair programming partner
// Partner Email: email address of your programming partner
// Lecturer's Name: name of your partner's lecturer
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

public class TextConverter {
    /**
     * 1337 - convert the string to leet-speak: Replace each L or l with a 1 (numeral one) Replace
     * each E or e with a 3 (numeral three) Replace each T or t with a 7 (numeral seven) Replace
     * each O or o with a 0 (numeral zero) Replace each S or s with a $ (dollar sign)
     * 
     * @param current Original string
     * @return string converted to leet-speak.
     */
    public static String action1337(String current) {
        current = current.replace('L', '1'); // all of this switches the specified characters
        current = current.replace('l', '1');
        current = current.replace('E', '3');
        current = current.replace('e', '3');
        current = current.replace('T', '7');
        current = current.replace('t', '7');
        current = current.replace('O', '0');
        current = current.replace('o', '0');
        current = current.replace('S', '$');
        current = current.replace('s', '$');
        return current; // value is the new 1337 string
    }

    /**
     * tests action1337 method with various cases to ensure it is working correctly.
     */
    public static void testAction1337() {
        boolean error = false;

        String input1 = "LEeTs";
        String expected1 = "1337$";
        String result1 = action1337(input1);
        if (!result1.equals(expected1)) {
            error = true;
            System.out.println("1) testAction1337 with input " + input1 + ", expected: " + expected1
                + " but result:" + result1);
        }
        String input2 = "lLlL"; // same switch-to character
        String expected2 = "1111";
        String result2 = action1337(input2);
        if (!result2.equals(expected2)) {
            error = true;
            System.out.println("2) testActionReverse with input " + input2 + ", expected: "
                + expected2 + " but result:" + result2);
        }

        String input3 = "333"; // no changes required
        String expected3 = "333";
        String result3 = action1337(input3);
        if (!result3.equals(expected3)) {
            error = true;
            System.out.println("2) testActionReverse with input " + input3 + ", expected: "
                + expected3 + " but result:" + result3);
        }


        // FIX ME
        // add at least 2 other test cases. What edge cases can you think of?


        if (error) {
            System.out.println("testAction1337 failed");
        } else {
            System.out.println("testAction1337 passed");
        }
    }



    /**
     * This reverses the order of characters in the current string.
     * 
     * @param current Original string to be reversed.
     * @return The string in reversed order.
     */
    public static String actionReverse(String current) {
        String newString = "";
        for (int i = current.length() - 1; i >= 0; i--) { // creates a new string, reversing the old
                                                          // one by character index
            newString += current.charAt(i);
        }

        return newString; // value is new reversed string
    }

    /**
     * tests actionReverse method with various cases to ensure it is working correctly.
     */
    public static void testActionReverse() {
        boolean error = false;

        String input1 = "Abc";
        String expected1 = "cbA";
        String result1 = actionReverse(input1);
        if (!result1.equals(expected1)) {
            error = true;
            System.out.println("1) testActionReverse with input " + input1 + ", expected: "
                + expected1 + " but result:" + result1);
        }
        String input2 = "hI My NaMe iS ChriS"; // lots of spaces and capitalization
        String expected2 = "SirhC Si eMaN yM Ih";
        String result2 = actionReverse(input2);
        if (!result2.equals(expected2)) {
            error = true;
            System.out.println("2) testActionReverse with input " + input2 + ", expected: "
                + expected2 + " but result:" + result2);
        }

        String input3 = "12345"; // all numbers
        String expected3 = "54321";
        String result3 = actionReverse(input3);
        if (!result3.equals(expected3)) {
            error = true;
            System.out.println("2) testActionReverse with input " + input3 + ", expected: "
                + expected3 + " but result:" + result3);
        }



        // FIX ME
        // add at least 2 other test cases. What edge cases can you think of?


        if (error) {
            System.out.println("testActionReverse failed");
        } else {
            System.out.println("testActionReverse passed");
        }
    }

    /**
     * Provides the main menu for the text converter and calls methods based on menu options chosen.
     * 
     * @param args
     */
    public static void main(String[] args) {
        String userString;
        String userChoice;
        Scanner scnr = new Scanner(System.in);
        // testAction1337(); // uncomment to run the tests
        // testActionReverse(); // uncomment to run the tests
        System.out.println("Welcome to the Text Converter.");
        System.out.println("Available Actions:");
        System.out.println("  1337) convert to 1337-speak");
        System.out.println("  rev) reverse the string");
        System.out.println("  quit) exit the program");
        System.out.println();
        System.out.print("Please enter a string: ");
        userString = scnr.nextLine();
        do {
            System.out.print("Action (1337, rev, quit): ");
            userChoice = scnr.nextLine();
            if (userChoice.equals("1337")) {
                userString = action1337(userString); // assigns userString as the new string created
                                                     // by the method
            } else if (userChoice.equals("rev")) {
                userString = actionReverse(userString); // assigns userString as the new string
                                                        // created by the method
            } else if (userChoice.equals("quit")) {
                break; // exits loop if 'quit' is entered
            } else {
                System.out.println("Unrecognized action.");
            }
            System.out.println(userString); // prints the converted string at before going through
                                            // the loop again
        } while (!userChoice.equals("quit"));
        System.out.println("See you next time!");
    }
}
