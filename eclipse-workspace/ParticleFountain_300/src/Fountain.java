//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Particle Fountain
// Files: fountain.java, P2ParticleFountain.jar
// Course: CS 300 Spring 2019
//
// Author: Chris George
// Email: crgeorge@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
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
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;

/**
 * The Fountain class holds the methods necessary to provide the user with a graphical experience
 * involving a stream of particles that resembles a water fountain, where the user can move the
 * stream of particles by clicking/dragging around the screen and take a screenshot of the display
 * 
 * @author chrisgeorge
 *
 */
public class Fountain {

  private static Random rand = new Random(); // random object that will be used in particle creation
  private static Particle[] particles; // creates Particle array; does not initialize it yet
  private static int positionX; // middle of the screen (left to right): 400
  private static int positionY; // middle of the screen (top to bottom): 300
  private static int startColor; // blue: Utility.color(23,141,235)
  private static int endColor; // lighter blue: Utility.color(23,200,255)

  /**
   * the main method runs the Application which displays the particle fountain using the rest of the
   * methods
   * 
   * @param args (unused)
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * runs the tests, giving feedback if necessary. Then initializes several variables to be used in
   * other methods
   */
  public static void setup() {
    if (!testUpdateParticle() || !testRemoveOldParticles()) { // calls the two test methods
      System.out.print("FAILED"); // feedback if either test fails
    }

    // these lines initialize several fields created earlier with given values
    particles = new Particle[800];
    positionX = 400;
    positionY = 300;
    startColor = Utility.color(23, 141, 235);
    endColor = Utility.color(23, 200, 255);
  }

  /**
   * refreshes the screen by drawing the background and calling methods to create, update, and
   * remove particles
   */
  public static void update() {
    Utility.background(Utility.color(235, 213, 186));
    createNewParticles(10);
    for (int i = 0; i < particles.length; i++) {
      if (particles[i] != null) {
        updateParticle(i);
      }
    }
    removeOldParticles(80);


  }

  /**
   * draws and fills circle particles, increments position according to velocity, increments
   * velocity according to gravity effect, and increments age
   * 
   * @param index the position of the given particle in the Particle array
   */
  private static void updateParticle(int index) {
    // sets fill color and transparency according to the particle's details
    Utility.fill(particles[index].getColor(), particles[index].getTransparency());
    // draws circle according to particle size and position
    Utility.circle(particles[index].getPositionX(), particles[index].getPositionY(),
        particles[index].getSize());

    // increments velocity of the particle by the accelertion float
    particles[index].setVelocityY(particles[index].getVelocityY() + 0.3f);
    // increments the x position by the x velocity
    particles[index]
        .setPositionX(particles[index].getPositionX() + particles[index].getVelocityX());
    // increments the y position by the y velocity
    particles[index]
        .setPositionY(particles[index].getPositionY() + particles[index].getVelocityY());
    // increments age of the particle
    particles[index].setAge(particles[index].getAge() + 1);
  }

  /**
   * creates a specified number of particles for the particle array, or until all entries in the
   * particle array are non-null. Upon creation, the particles are assigned random positions,
   * velocities, ages, colors, and transparencies within the specified ranges
   * 
   * @param numNewParticles max number of new particles the method should create
   */
  private static void createNewParticles(int numNewParticles) {
    int particlesCreated = 0; // counter for number of new particles created
    int particleIndex = 0; // counter for the index in the particle array

    // loop runs until either the specified number of particles are created or all positions in the
    // particle array are filled
    while (particlesCreated < numNewParticles || particleIndex == particles.length - 1) {
      if (particles[particleIndex] == null) { // creates new particle for empty entries in array
        particles[particleIndex] = new Particle(); // particle object created
        // sets the particle's x position to a random position within the specified range
        particles[particleIndex]
            .setPositionX(rand.nextFloat() * ((positionX + 3) - (positionX - 3)) + (positionX - 3));
        // sets the particle's y position to a random position within the specified range
        particles[particleIndex]
            .setPositionY(rand.nextFloat() * ((positionY + 3) - (positionY - 3)) + (positionY - 3));
        // sets the particle's size to a random size within the specified range
        particles[particleIndex].setSize(rand.nextFloat() * (7) + 4);
        // sets the particle's color to a random shade within the specified range
        particles[particleIndex]
            .setColor(Utility.lerpColor(startColor, endColor, rand.nextFloat()));
        // sets the particle's x velocity to a random velocity within the specified range
        particles[particleIndex].setVelocityX(rand.nextFloat() * (2) - 1);
        // sets the particle's y velocity to a random velocity within the specified range
        particles[particleIndex].setVelocityY(rand.nextFloat() * (5) - 10);
        // sets age to a random age within the specified rage
        particles[particleIndex].setAge(rand.nextInt(41));
        // sets transparency to a random transparency within the specified range
        particles[particleIndex].setTransparency(rand.nextInt(96) + 32);

        particlesCreated++; // increments after a new particle is created
      }
      particleIndex++; // increments the particle index before loop recycles
    }
  }

  /**
   * removes particles which are older than a specified age
   * 
   * @param maxAge the maximum particle age before removal
   */
  private static void removeOldParticles(int maxAge) {
    // iterates through the particles array
    for (int i = 0; i < particles.length; i++) {
      // check if there is a particle in the current position, then if it is older then the max age,
      // remove it
      if (particles[i] != null && particles[i].getAge() > maxAge) {
        particles[i] = null;
      }
    }
  }

  /**
   * upon a mouse click on the display, sets the coordinates of the fountain to the clicked position
   * 
   * @param xPos horizontal position of the mouse click
   * @param yPos vertical position of the mouse click
   */
  public static void mousePressed(int xPos, int yPos) {
    positionX = xPos;
    positionY = yPos;
  }

  /**
   * if the user presses the 'p' key, a screenshot of the display is taken
   * 
   * @param key pressed by the user
   */
  public static void keyPressed(char key) {
    if (key == 'p') {
      Utility.save("screenshot.png");
    }
  }

  /**
   * Creates a single particle at position (3,3) with velocity (-1,-2). Then checks whether calling
   * updateParticle() on this particle's index correctly results in changing its position to
   * (2,1.3).
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateParticle() {
    particles = new Particle[1];
    particles[0] = new Particle();
    particles[0].setPositionX(3);
    particles[0].setPositionY(3);
    particles[0].setVelocityX(-1);
    particles[0].setVelocityY(-2);

    updateParticle(0);

    if (particles[0].getPositionX() == 2f && particles[0].getPositionY() == 1.3f) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Calls removeOldParticles(6) on an array with three particles (two of which have ages over six
   * and another that does not). Then checks whether the old particles were removed and the young
   * particle was left alone.
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldParticles() {
    particles = new Particle[3];
    particles[0] = new Particle();
    particles[1] = new Particle();
    particles[2] = new Particle();
    particles[0].setAge(7);
    particles[1].setAge(8);
    particles[2].setAge(3);

    removeOldParticles(6);

    if (particles[0] == null && particles[1] == null && particles[2] != null) {
      return true;
    } else {
      return false;
    }
  }

}
