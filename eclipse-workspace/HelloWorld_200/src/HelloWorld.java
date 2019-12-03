//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Hello World on Eclipse
// Files: HelloWorld.java
// Course: CS 200 Fall 2018
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

public class HelloWorld {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String name;
        System.out.print("Enter a string: ");   //asks for input
        if (scnr.hasNextLine()) {               //checks if there is input
            name = scnr.nextLine();             //records input
            System.out.println("Hello, " + name + "!");     //prints named input
        } else {
            System.out.println("Hello, world!");            //prints default input
        }


    }

}
