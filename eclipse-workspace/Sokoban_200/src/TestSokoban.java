/**
 * This file contains testing methods for the Sokoban project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestSokoban {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when
     * you are ready for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();
        // Milestone 2
        testInitBoard();
        testCheckWin();
        testCalcDelta();
        testCheckDelta();
        // Milestone 3
        testTogglePos();
        testShiftBox();
        testDoMove();
        testProcessMove();
    }
    
    private static void testCheckLevel() {
        int numTests = 5;
        int passed = numTests;
        int res;
        //Test 1
        if((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
            System.out.println("FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
            passed--;
        }       
        //Test 2
          char[][][] lvl = new char[2][][];
        if((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
            System.out.println("FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
            passed--;
        }

        // Test 3
            int [][] goals = { {1,1,1}, {1,1,1}, {1,1,1}};
        if((res = Sokoban.checkLevel(1, Config.LEVELS, goals)) !=-2) {
            System.out.println("FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + res);
            passed--;
        }
        // Test 4
        char [][][] emptyLevel = {
        {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}
        },
        {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}
        }
     };
        if((res = Sokoban.checkLevel(1, emptyLevel, Config.GOALS)) !=-3) {
            System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned " + res);
            passed--;
        }
        // Test 5
        if((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) != 1) {
            System.out.println("FAILED: Sokoban.checkLevel for MyLevel 2. Expected 1, but value returned " + res);
            passed--;
        }
        
        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if(a == null || b == null)
            return false;
        if(a.length != b.length)
            return false;
        for(int i = 0; i < a.length; i++) {
            if(!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static void testInitBoard() {
        int numTests = 4;
        int passed = numTests;

        //Test 1
        int[] pTest1 = new int[2];
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);
        if(!Arrays.equals(pTest1, new int[]{4, 4})) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call " + Arrays.toString(pTest1));
            passed--;
        }
        char[][] bCompTest1 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!compBoards(bTest1, bCompTest1)){
            System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);            
            passed--;
        }
        //End of Test 1
        
        //Test 2
        char[][]bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest1);
        if(!Arrays.equals(pTest1, new int[]{7, 10})) {
            System.out.println("FAILED: Sokoban.initBoard Test 2. Expected initial position: {7, 10}, but value after call " + Arrays.toString(pTest1));
            passed--;
        }
        char[][] bCompTest2 = new char[][] { 
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR}
        };
            
        if(!compBoards(bTest2, bCompTest2)) {
            System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest2);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest2);            
            passed--;
        }
        
        
        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }
    
    private static void testCheckWin() {
        int numTests = 2;
        int passed = numTests;
        //Test 1
        char[][] winTest1 = new char[][] {{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!Sokoban.checkWin(winTest1)) {
            System.out.println("FAILED: Sokoban.checkWin Test 1. Expected result: true. Actual result: " + Sokoban.checkWin(winTest1));
            passed--;
        }
        //Test 2
        char[][] winTest2 = new char [][] { 
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR}
        };
        if(Sokoban.checkWin(winTest2)) {
            System.out.println("FAILED: Sokoban.checkWin Test 2. Expected result: false. Actual result: " + Sokoban.checkWin(winTest2));
            passed--;
        }
        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");
    }
    
    private static void testCalcDelta() {
        int numTests = 9;
        int passed = numTests;
        //test 1
        int[]testVector1 = {-1,0};
        if(!Arrays.equals(Sokoban.calcDelta("81"), testVector1)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 1. Expected result: {" + testVector1[0] + "," + testVector1[1]
                + "}. Actual result: {" + Sokoban.calcDelta("81")[0] + "," + Sokoban.calcDelta("81")[1] + "}" );
            passed--;
        }
        //test 1
        int[]testVector2 = {1,0};
        if(!Arrays.equals(Sokoban.calcDelta("21"), testVector2)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 2. Expected result: {" + testVector2[0] + "," + testVector2[1]
                + "}. Actual result: {" + Sokoban.calcDelta("21")[0] + "," + Sokoban.calcDelta("21")[1] + "}");
            passed--;
        }
        //test 3
        int[]testVector3 = {0,-1};
        if(!Arrays.equals(Sokoban.calcDelta("41"), testVector3)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 3. Expected result: {" + testVector3[0] + "," + testVector3[1]
                + "}. Actual result: {" + Sokoban.calcDelta("41")[0] + "," + Sokoban.calcDelta("41")[1] + "}");
            passed--;
        }
        //test 4
        int[]testVector4 = {0,1};
        if(!Arrays.equals(Sokoban.calcDelta("61"), testVector4)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 4. Expected result: {" + testVector4[0] + "," + testVector4[1]
                + "}. Actual result: {" + Sokoban.calcDelta("61")[0] + "," + Sokoban.calcDelta("61")[1] + "}");
            passed--;
        }
        //test 5
        int[]testVector5 = {0,0};
        if(!Arrays.equals(Sokoban.calcDelta("31"), testVector5)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 5. Expected result: {" + testVector5[0] + "," + testVector5[1]
                + "}. Actual result: {" + Sokoban.calcDelta("31")[0] + "," + Sokoban.calcDelta("31")[1] + "}");
            passed--;
        }
        //test 6
        int[]testVector6 = {-1,0};
        if(!Arrays.equals(Sokoban.calcDelta("8"), testVector6)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 6. Expected result: {" + testVector6[0] + "," + testVector6[1]
                + "}. Actual result: {" + Sokoban.calcDelta("8")[0] + "," + Sokoban.calcDelta("8")[1] + "}");
            passed--;
        }
        //test 7
        int[]testVector7 = {-400,0};
        if(!Arrays.equals(Sokoban.calcDelta("8400"), testVector7)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 7. Expected result: {" + testVector7[0] + "," + testVector7[1]
                + "}. Actual result: {" + Sokoban.calcDelta("8400")[0] + "," + Sokoban.calcDelta("8400")[1] + "}");
            passed--;
        }
        //test 8 
        int[]testVector8 = {0,-3};
        if(!Arrays.equals(Sokoban.calcDelta("43"), testVector8)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 8. Expected result: {" + testVector8[0] + "," + testVector8[1]
                + "}. Actual result: {" + Sokoban.calcDelta("43")[0] + "," + Sokoban.calcDelta("43")[1] + "}");
            passed--;
        }
      //test 9
        int[]testVector9 = {0,0};
        if(!Arrays.equals(Sokoban.calcDelta("z"), testVector9)) {
            System.out.println("FAILED: Sokoban.calcDelta Test 9. Expected result: {" + testVector9[0] + "," + testVector9[1]
                + "}. Actual result: {" + Sokoban.calcDelta("z")[0] + "," + Sokoban.calcDelta("z")[1] + "}");
            passed--;
        }
        
        System.out.println("testCalcDelta: Passed " + passed + " of " + numTests + " tests.");
    }
    
    private static void testCheckDelta() {
        int numTests = 4;
        int passed = numTests;
        int res;
        //Test 1
        char[][] testBoard = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}};
        int[] testDelta = new int [] {1,0}; 
        char [] validChars = new char [] {Config.BOX_CHAR, Config.BOX_GOAL_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR,
            Config.WALL_CHAR, Config.WORKER_CHAR};
        
        if((res = Sokoban.checkDelta(testBoard, null, testDelta, validChars )) != -1) {
            System.out.println("FAILED: Sokoban.checkDelta Test 1. Expected -1, but value returned " + res);
            passed--;
        }
      //Test 2
        int[]testPos = new int[] {20,0};                                          
        if((res = Sokoban.checkDelta(testBoard, testPos, testDelta, validChars )) != -1) {
            System.out.println("FAILED: Sokoban.checkDelta Test 2. Expected -1, but value returned " + res);
            passed--;
        }
      //Test 3
        char[][] testBoard2 = new char[][]{{Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}};
        int[]testPos3 = new int[] {0,0}; 
        int[]testDelta2 = new int[] {0,1};
        if((res = Sokoban.checkDelta(testBoard2, testPos3, testDelta2, validChars )) != -4) {
            System.out.println("FAILED: Sokoban.checkDelta Test 3. Expected -4, but value returned " + res);
            passed--;
        }
      //Test 4
        char[][] testBoard3 = new char[][]{{Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}};
        if((res = Sokoban.checkDelta(testBoard3, testPos3, testDelta2, validChars )) != -5) {
            System.out.println("FAILED: Sokoban.checkDelta Test 4. Expected -5, but value returned " + res);
            passed--;
        }
        
       
        
        
        System.out.println("testCheckDelta: Passed " + passed + " of " + numTests + " tests.");
    }
    
    private static void testTogglePos() {
        int numTests = 2;
        int passed = numTests;
        char[][] testBoard = new char[][] {{'x','x','x','x'},
                                           {'y','y','y','y'},
                                           {'z','z','z','z'}};
                                           
        
        //Test 1: change to opt1
        int[] test1pos = new int[] {0,1};
        Sokoban.togglePos(testBoard, test1pos, 'x', 'a', 'b');
        if(testBoard[0][1] != 'a') {
            System.out.println("FAILED: Sokoban.togglePos Test 1. Character at {0,1} expected: 'a', actual: " + testBoard[0][1]);
            passed--;
        }
        
        //Test 2: change to opt2
        int[] test2pos = new int[] {2,2};
        Sokoban.togglePos(testBoard, test2pos, 'x', 'a', 'b');
        if(testBoard[2][2] != 'b') {
            System.out.println("FAILED: Sokoban.togglePos Test 2. Character at {2,2} expected: 'b', actual: " + testBoard[2][2]);
            passed--;
        }
    
        System.out.println("testTogglePos: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testShiftBox() {
        int numTests = 2;
        int passed = numTests;
        
        int res;
        //Test 1: valid move
        int[]testPos1 = new int[] {1,4};
        int[] test1delta = new int[] {0,2};
        if((res = Sokoban.shiftBox(Config.LEVELS[1], testPos1, test1delta)) != 1) {
            System.out.println("FAILED: Sokoban.shiftBox Test 1. Expected 1, but value returned " + res);
            passed--;
        }
        //Test 2: invalid move
        int[]testPos2 = new int[] {2,6};
        int[] test2delta = new int[] {0,1};
        if((res = Sokoban.shiftBox(Config.LEVELS[1], testPos2, test2delta)) != -4) {
            System.out.println("FAILED: Sokoban.shiftBox Test 2. Expected -4, but value returned " + res);
            passed--;
        }
        System.out.println("testShiftBox: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testDoMove() {
        int numTests = 3;
        int passed = numTests;
        int res;
        char[][] testLevel = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                         {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                         {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                         {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                         {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR}};
        //Test 1: checkDelta <1 not -5
        int[]test1pos = new int[] {4,3};
        int[]test1delta = new int[] {0,2};
        if((res = Sokoban.doMove(testLevel, test1pos, test1delta)) != -4) {
            System.out.println("FAILED: Sokoban.doMove Test 1. Expected -4, but value returned " + res);
            passed--; 
        }
        //Test 2: checkDelta -5
        int[]test2pos = new int[] {4,3};
        int[]test2delta = new int[] {-3,0};
        if((res = Sokoban.doMove(testLevel, test2pos, test2delta)) != 0) {
            System.out.println("FAILED: Sokoban.doMove Test 2. Expected 0, but value returned " + res);
            passed--; 
        }
        //Test 3: checkDelta 1
        int[]test3pos = new int[] {4,3};
        int[]test3delta = new int[] {-1,0};
        if((res = Sokoban.doMove(testLevel, test3pos, test3delta)) != 1) {
            System.out.println("FAILED: Sokoban.doMove Test 3. Expected 1, but value returned " + res);
            passed--; 
        }
        
        System.out.println("testDoMove: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testProcessMove() {
        int numTests = 2;
        int passed = numTests;
        int res;
        char[][] testLevel = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                          {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR}};
        
        //Test 1: return < 1
        int[] testPos = new int[] {4,3};
        int[] test1Delta = new int[] {-5,0};
        char[][] testLevel1 = new char[5][5];
        for(int i = 0; i < testLevel.length; i++) {
            for(int j = 0; j < testLevel[i].length; j++) {
                testLevel1[i][j] = testLevel[i][j];
            }
        }
        if((res = Sokoban.processMove(testLevel1,testPos, test1Delta)) != 0) {
            System.out.println("FAILED: Sokoban.processMove Test 1. Expected 0, but value returned " + res);
            passed--;
        }
        //Test 2: return 1
        int[] testPos2 = new int[] {4,3};
        int[] test2Delta = new int[] {-2,0};
        char[][] testLevel2 = new char[5][5];
        for(int i = 0; i < testLevel.length; i++) {
            for(int j = 0; j < testLevel[i].length; j++) {
                testLevel2[i][j] = testLevel[i][j];
            }
        }
        if((res = Sokoban.processMove(testLevel2, testPos2, test2Delta)) != 1) {
            //System.out.print
            System.out.println("FAILED: Sokoban.processMove Test 2. Expected 1, but value returned " + res);
            passed--;
        }
        System.out.println("testProcessMove: Passed " + passed + " of " + numTests + " tests."); 
    }    

}

