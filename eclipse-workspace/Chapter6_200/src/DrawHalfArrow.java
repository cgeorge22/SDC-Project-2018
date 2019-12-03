//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Draw a half arrow
// Files: DrawHalfArrow.java
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

public class DrawHalfArrow {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int arrowBaseHeight = 0;
        int arrowBaseWidth = 0;
        int arrowHeadWidth = 0;

        int i = 0;
        int j = 0;

        System.out.print("Enter arrow base height: ");
        arrowBaseHeight = scnr.nextInt();

        System.out.print("Enter arrow base width: ");
        arrowBaseWidth = scnr.nextInt();

        System.out.print("Enter arrow head width: ");
        arrowHeadWidth = scnr.nextInt();

        
        // make sure arrow head width is larger than base width
        while (arrowHeadWidth <= arrowBaseWidth) {                  //loops as long as arrow head is smaller than base
            System.out.print("Enter arrow head width: ");
            arrowHeadWidth = scnr.nextInt();
        }

        
        System.out.println();
        // draw arrow base  
        for (i = 0; i < arrowBaseHeight; i++) {                     //nested for loop to print base using dimensions
            for (j = 0; j < arrowBaseWidth; j++) {
                System.out.print("*");
            }
            System.out.println();
        }


        // draw arrow head
        for (i = arrowHeadWidth; i > 0; i--) { // nested for loop which prints out the arrowhead-- same as triangle but upside down
            for (j = i; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }



        return;
    }
}
