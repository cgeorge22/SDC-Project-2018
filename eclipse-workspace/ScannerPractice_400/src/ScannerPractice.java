//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: Scanner Practice
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Description: A BMI calculator: user enters information and the program
// computes and stores, and outputs the BMI information
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerPractice {
  // A single instance of a Scanner connected to default input (keyboard)
  private static final Scanner STDIN = new Scanner(System.in);

  // args - unused
  public static void main(String[] args) {
    String anotherEntry;
    String name;
    double height;
    double weight;
    double bmi;

    printWelcome();
    try {
      File saveFile = new File("file_output.txt"); // create output file
      PrintWriter output = new PrintWriter(saveFile); // will write to output file
      do {
        System.out.println("Enter the person's first name");
        name = STDIN.next();
        System.out.println("Enter the height in inches");
        height = STDIN.nextDouble();
        System.out.println("Enter the weight in pounds");
        weight = STDIN.nextDouble();
        bmi = calcBMI(name, height, weight);
        output.println(name + ": " + bmi);
        System.out.println("Do you want to record another BMI? (Enter yes/no)");
        anotherEntry = STDIN.next();
      } while (anotherEntry.equals("yes")); // loops until user is done inputting data
      output.close(); // closes data file upon completion
    } catch (IOException e) {
      System.out.println("Error: could not write output to file");
    }
    System.out.println("Calculated BMIs are in file_output.txt");
  }

  // calculates, rounds, and outputs BMI to the console, returns bmi as a double
  private static double calcBMI(String name, double height, double weight) {
    double bmi;

    bmi = 703 * (weight / (Math.pow(height, 2))); // bmi formula
    bmi = Math.round(bmi * 100.0) / 100.0; // round to 2 decimal places
    System.out.println("BMI = " + bmi);

    return bmi;
  }

  // loads and prints the text in the welcome message file
  private static void printWelcome() {
    try {
      File loadFile = new File("WelcomeMessage.txt"); // file with the welcome message
      Scanner sc = new Scanner(loadFile); // scanner for the file
      while (sc.hasNextLine()) {
        System.out.println(sc.nextLine()); // prints the contents of the welcome file
      }
    } catch (IOException e) {
      System.out.println("Welcome message file not found");
    }
  }
}

