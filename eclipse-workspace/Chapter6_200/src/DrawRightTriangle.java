//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Draw Right Triangle
// Files: DrawRightTriangle.java
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

public class DrawRightTriangle {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        char userChar;
        int triangleHeight;
        System.out.print("Enter a character: ");
        userChar = scnr.next().charAt(0);

        System.out.print("Enter triangle height (1-10): ");
        triangleHeight = scnr.nextInt();
        if (triangleHeight < 1 || triangleHeight > 10) { // if user enters out of range height
            do { // runs at least once if bad height entered
                System.out.println("Please enter height between 1 and 10.");
                triangleHeight = scnr.nextInt();

            } while (triangleHeight < 1 || triangleHeight > 10); // until height is between 1-10
        }

        for (int i = 0; i <= triangleHeight; i++) { // nested for loop which prints out the triangle
            for (int j = 0; j < i; j++) {
                System.out.print(userChar + " ");
            }
            System.out.println();
        }



    }

}
