import java.util.Scanner;
import java.util.Random;

public class Sokoban {

    /**
     * Prompts the user for a value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt. 
     *
     * After prompting the user, the method will consume an entire
     * line of input while reading an int. If the value read is between min and max (inclusive), 
     * that value is returned.
     * Otherwise, "Invalid value." terminated by a new line is output to the console and the 
     * user is prompted again. 
     *
     * @param sc The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        int userInt = -99;
        do {
        System.out.print(prompt);       
        if(sc.hasNextInt()) {           //only reads input if the user inputs an integer
            userInt = sc.nextInt();    
        }
        sc.nextLine();              //consumes the entire line of input
        if (userInt >= min && userInt <= max) {     
            return userInt;                 //makes sure the input is in range
        }
        else{
            System.out.println("Invalid value.");   //if the input is not in range
        }
        }while(!(userInt >= min && userInt <= max));  //continue this until input is in range
        
       
        
       return -99;
    }

    /**
     * Prompts the user for a char value by displaying prompt.
     * Note: This method should not be a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If 
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        char userChar;      //will be assigned to userInput
        
        System.out.print(prompt);           //prints parameter prompt
        userChar = sc.next().charAt(0);     //reads first character of user input
        userChar = Character.toLowerCase(userChar); //makes input lowerCase if necessary
        
        if(userChar != ' '){
            return userChar;            //if the input is not whitespace, return it
        }
        else {
            return '\0';        //null character returned if input is whitespace
        }
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not be a new line to the output of prompt. 
     *
     * After prompting the user, the method will read an entire line of input, remove any leading and 
     * trailing whitespace, and return the input converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user, converted to lower case with leading and trailing
     *         whitespace removed.
     */    
    public static String promptString(Scanner sc, String prompt) {
        String userInput;
        
        System.out.print(prompt);
        userInput = sc.nextLine();          
        userInput = userInput.toLowerCase();    //makes the user input string all lower case
        userInput = userInput.trim();           //removes leading & trailing whitespace
        
        return userInput;
    }
    
    /**
     * Initializes the game board to a given level. You can assume that the level at lvl has been 
     * successfully verified by the checkLevel method and that pos is an array of length 2.
     *
     * 1 - The game board should be created row-by-row. 
     *     a - For each row, copy the values from the corresponding row in the 2-d array contained 
     *         at index lvl in levels.
     *     b - When the worker is located, it's position should be recorded in the pos parameter.
     * 2 - For each goal described in the array at index lvl of goals, convert the character at the 
     *     goal coordinate to:
     *     - Config.WORK_GOAL_CHAR if it contains the worker
     *     - Config.BOX_GOAL_CHAR if it contains a box
     *     - Config.GOAL_CHAR otherwise  
     * 
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @param pos The starting pos of the worker. A length 2 array, where index 0 is the row and 
     *            index 1 is the column. 
     * @return A two dimension array representing the initial configuration for the given level.
     */
    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
        char[][] gameBoard = new char[levels[lvl].length][]; //2D board
        
        for (int i = 0; i < levels[lvl].length; i++) {
            gameBoard[i] = new char[levels[lvl][i].length];
            //allots memory for each row of the 2D gameBoard
        }
        
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
              //copies the entries in the pre-made level into the new gameBoard
                gameBoard[i][j] = levels[lvl][i][j];            
            }
        }
        for(int i = 0 ; i < gameBoard.length ; i++) {
            for(int j = 0 ; j < gameBoard[i].length ; j++) {
             // goes through entries in gameBoard until worker char is found
                if(gameBoard[i][j] == Config.WORKER_CHAR) { 
                 // makes position vector = position of worker char
                    pos[0] = i;   
                    pos[1] = j;
                }
            }
        }
        for(int i = 0 ; i < goals[lvl].length ; i+=2) {
            if(gameBoard[goals[lvl][i]][goals[lvl][i+1]] == Config.WORKER_CHAR) {
                gameBoard[goals[lvl][i]][goals[lvl][i+1]] = Config.WORK_GOAL_CHAR; 
                //if a goal has a worker on it, makes it a worker goal char
            }
            else if(gameBoard[goals[lvl][i]][goals[lvl][i+1]] == Config.BOX_CHAR) {
                gameBoard[goals[lvl][i]][goals[lvl][i+1]] = Config.BOX_GOAL_CHAR; 
                //if a goal has a box on it, makes it a box goal char
            }
            else {
                gameBoard[goals[lvl][i]][goals[lvl][i+1]] = Config.GOAL_CHAR; 
                //if there is nothing on the goal spot, it is just a goal char
            }
        }
        return gameBoard;         //returns the copy of the pre-made level complete with goal chars
    }

    /**
     * Prints out the game board.
     * 
     * 1 - Since the game board does not contain the outer walls, print out a sequence of
     *     Config.WALL_CHAR with a length equal to that of the first row of board, plus the outer
     *     wall to the left and the right.
     * 2 - For each row in board, print out a Config.WALL_CHAR, followed by the contents
     *     of the row, followed by a Config.WALL_CHAR.
     * 3 - Finally, print out a sequence of Config.WALL_CHAR with a length equal to that 
     *     of the last row of board, plus the outer wall to the left and the right.
     *
     * Note: each row printed out should be terminated by a new line.
     *
     * @param board The board to print.
     */
    public static void printBoard(char[][] board) {
        int i, j;
        for(i = 0; i < board[0].length + 2 ; i++) {
            System.out.print(Config.WALL_CHAR);         //prints out top border wall
        }
        System.out.println();
        for(i = 0; i < board.length; i++) {
            System.out.print(Config.WALL_CHAR);          //side border wall
            for(j = 0; j < board[i].length ; j++) {     
                System.out.print(board[i][j]);          //prints out actual board row by row
            }
            System.out.println(Config.WALL_CHAR);       //side border wall
        }
        for(i = 0; i < board[board.length-1].length + 2 ; i++) {
            System.out.print(Config.WALL_CHAR);         //bottom border wall
        }
        System.out.println();
    }

    /**
     * Runs a given level through some basic sanity checks.
     *
     * This method performs the following tests (in order):
     * 1 - lvl >= 0
     * 2 - lvl is a valid index in levels, that the 2-d array at index lvl exists and that 
     *     it contains at least 1 row.
     * 3 - lvl is a valid index in goals, the 1-d array at index lvl exists and that it
     *     contains an even number of cells.
     * 4 - the number of boxes is more than 0.
     * 5 - the number of boxes equals the number of goals.
     * 6 - the coordinate of each goal is valid for the given lvl and does not
     *     correspond to a wall cell.
     * 7 - the number of workers is exactly 1.
     * 8 - check for duplicate goals.
     *
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, contaning the goals for the levels.
     * @return 1 if all tests pass.
     *         Otherwise if test:
     *          - Test 1 fails: 0
     *          - Test 2 fails: -1
     *          - Test 3 fails: -2
     *          - Test 4 fails: -3
     *          - Test 5 fails: -4
     *          - Test 6 fails: -5
     *          - Test 7 fails: -6
     *          - Test 8 fails: -7
     *  
     */
    public static int checkLevel(int lvl, char[][][] levels, int[][] goals)
    {
        //Test 1 checks that the level index is greater than or equal to 0
        if(!(lvl>=0)) {     
            return 0;
        }
        //Test 2 checks that lvl exists in levels, levels[lvl] is not null, 
                //and levels[lvl] has at least one row
        if((lvl >= levels.length) || levels[lvl] == null || (levels[lvl].length <= 1 )){ 
            return -1;
        }
        //Test 3 lvl exists in goals, goals[lvl] is not null, goals[lvl] is an even vector
        if((lvl >= goals.length) || goals[lvl] == null || ((goals[lvl].length %2) != 0)) {
            return -2;
        }
        //Test 4
        if (levels[lvl] == null) {  //makes sure levels[lvl] is not null
            return -3;
        }
        int numBoxes = 0;
        for(int n = 0; n < levels[lvl].length; n++){
            for (int m = 0; m < levels[lvl][n].length; m++) {
                if(levels[lvl][n][m] == Config.BOX_CHAR) {   //counts number of boxes in the board
                   numBoxes++; 
                }     
            }
        }
        if(numBoxes == 0) {  //returns error code if there are no boxes
            return -3;
        }
        //Test 5
        if (goals[lvl] == null) {
            return -4;
        }
        int numGoals = goals[lvl].length / 2;   //calculates the number of goals in a level
        if(!(numGoals == numBoxes)) {           //makes sure that # goals = # boxes
            return -4;
        }
        //Test 6 
        if (goals[lvl] == null) {
            return -5;
        }
        for(int n=0; n < (goals[lvl].length-1); n += 2) {
            if(levels[lvl][goals[lvl][n]][goals[lvl][n+1]] == Config.WALL_CHAR) { 
                //if a goal coordinate is a wall char, error code
                return -5;
            }  
        }
        //Test 7
        if (levels[lvl] == null) {
            return -6;
        }
        int numWorkers = 0;
        for(int n = 0; n < levels[lvl].length; n++){
            for (int m = 0; m < levels[lvl][n].length; m++) {
                if(levels[lvl][n][m] == Config.WORKER_CHAR) { //counts number of worker chars on board
                   numWorkers++; 
                }     
            }
        }
        if(numWorkers != 1) {  //makes sure there is only one worker char on board
            return -6;                      
        }

        //Test 8 
        //goes through goals array,every other element for pairs
        for(int i = 0; i < goals[lvl].length - 1; i += 2) { 
          //j is the first entry of the next coordinate pair
            for(int j = i + 2; j < goals[lvl].length - 1; j += 2) { 
                if(goals[lvl][i] == goals[lvl][j] && goals[lvl][i+1] == goals[lvl][j+1]) {  
                    //if the two coordinates are identical, test fails              
                    return -7;
                }
            }
        }
        
        return 1;
    }

    /**
     * This method builds an int array with 2 cells, representing a movement vector, based on the 
     * String parameter.
     *
     * The rules to create the length 2 int array are as follows:
     *   - The 1st character of the String represents the direction.
     *   - The remaining characters (if there are any) are interpreted as integer and represent the
     *     magnitude or the number of steps to take.
     *
     * The cell at index 0 represents movement in the rows. Hence, a negative value represents 
     * moving up the rows and a positive value represents moving down the rows.
     *
     * The cell at index 1 represents movement in the columns. Hence, a negative value represents 
     * moving left in the columns and a positive value represents moving right in the columns.
     *
     * If the first character of moveStr does not match on of Config.UP_CHAR, Config.DOWN_CHAR,
     * Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an array with 0 in both cells.
     *
     * If there are no characters after the first character of moveStr or the characters cannot be
     * interpreted as an int, set the magnitude of the movement to 1.
     *
     * Hint: Use Scanner to parse the magnitude.
     *
     * Some examples: 
     *   - If the parameter moveStr is "81": An array {-1, 0} would represent moving up by one 
     *     character.
     *   - If the parameter moveStr is "65": An array {0, 5} would represent moving right by 5
     *     characters.   
     *
     * @param moveStr The string to parse.
     * @return The calculated movement vector as a 2 cell int array.
     */
    public static int[] calcDelta(String moveStr) {
        int[] movementVector = new int[2]; //1D array that will give magnitude and direction
        int magnitude;      //how many rows or columns to cross
        char firstNum;      //direction characters
        char[] secondNumArr;    //character(s) that will signify magnitude
        
        if(!moveStr.equals("") && !(moveStr == null)) { //operates if there is a valid argument
            char[]moveStrArray = moveStr.toCharArray(); //converts user string into a char array
            firstNum = moveStrArray[0]; //the first element of the char array is direction
            
          //block executes of the direction vector is not recognized
            if(firstNum != Config.UP_CHAR && firstNum != Config.DOWN_CHAR && 
                firstNum != Config.LEFT_CHAR && firstNum!= Config.RIGHT_CHAR) {  
                movementVector[0] = 0;
                movementVector[1] = 0;
                return movementVector;
            }
            
            secondNumArr = new char[moveStrArray.length - 1]; //the rest of the char array
            if(secondNumArr.length == 0) { //if there was only one number in the input string
                magnitude = 1;
            }
            else {
                for(int i = 1 ; i < moveStrArray.length ; i++) {
                    secondNumArr[i-1] = moveStrArray[i]; //copies every element except for the
                                                         //first from the char Array
                    
                }
            
                magnitude = Integer.parseInt(new String(secondNumArr)); //converts array to integer
                

            }
            //executes if the first number corresponds to a valid direction char
            if(firstNum == Config.UP_CHAR || firstNum == Config.DOWN_CHAR || 
                firstNum == Config.LEFT_CHAR || firstNum == Config.RIGHT_CHAR) {
                switch(firstNum) {
                    case Config.UP_CHAR:
                        movementVector[0] = -1 * magnitude; //negative magn. to go 'up' in rows
                        movementVector[1] = 0;
                        break;
                    case Config.DOWN_CHAR:
                        movementVector[0] = 1 * magnitude; //go down in rows
                        movementVector[1] = 0;
                        break;
                    case Config.LEFT_CHAR:
                        movementVector[0] = 0;
                        movementVector[1] = -1 * magnitude;//negative magn. to go left in cols.
                        break;
                    case Config.RIGHT_CHAR:
                        movementVector[0] = 0;
                        movementVector[1] = 1 * magnitude;//pos. to go right in cols
                        break; 
                }
            }
            else {  //zero vector if first number of input is not recognized
                movementVector[0] = 0;
                movementVector[1] = 0;
            }
            
        }
        else { //zero vector if input is blank
            movementVector[0] = 0;
            movementVector[1] = 0;
        }
        return movementVector;
    }

    /**
     * This method checks that moving from one position to another position is a valid move.
     *
     * To validate the move, the method should (in order) check:
     *   1 - that pos is valid.
     *   2 - that the character at pos in board is in the valid array.
     *   3 - that the delta is valid.
     *   4 - that the new position is valid and not a wall character.
     *   5 - that the new position is not a box character 
     * For what makes each test invalid, see the return details below.
     *
     * @param board The current board.
     * @param pos The position to move from. A length 2 array, where index 0 is the row and 
     *            index 1 is the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @param valid A character array containing the valid characters for the cell at pos.
     * @return 1 if the move is valid.
     * Otherwise:
     *  -1 : if pos is null, not length 2, or not on the board.
     *  -2 : if the character at pos is not valid (not in the valid array).
     *  -3 : if delta is null or not length 2.
     *  -4 : if the new position is off the board or a wall character
     *  -5 : if the new position is a box character
     */
    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
        //Test if pos valid (not null, length 2)
        if(pos == null || pos.length != 2) {
            return -1;
        }
        
            //makes sure row entry pos is a row on the board
            if(pos[0] < 0 || board.length - 1 < pos[0]) {
                return -1;
            }
            //makes sure column entry pos is a column on the board
            if(pos[1] < 0  || board[pos[0]].length - 1 < pos[1]) {
                return -1;
            }
        
        
        //Test if character at pos is in valid array
        boolean posInArray = false;
        char posChar = board[pos[0]][pos[1]];
        
        for(int i = 0 ; i < valid.length ; i++) {
            if(posChar == valid[i]) {
                posInArray = true;  //goes through 'valid' array looking for the pos char
            }
        }
        if(!posInArray) {
            return -2;
        }
        
        //Test if delta is null or not length 2
        if(delta == null || delta.length != 2) {
            return -3;
        }
        
        //Test if new position is off board or a wall character
        int[] newPos = new int[2];
        //create new position vector
        newPos[0] = pos[0] + delta[0];  
        newPos[1] = pos[1] + delta[1];
        
            //makes sure row entry newPos is a row on the board
            if(newPos[0] < 0 || board.length - 1 < newPos[0] ) {
                return -4;
            }
          //makes sure row entry newPos is a row on the board
            if(newPos[1] < 0 || board[newPos[0]].length - 1 < newPos[1] ) {
                return -4;
            }            
        
        //determines whether newPos is a wall character
        if(board[newPos[0]][newPos[1]] == Config.WALL_CHAR) {
            return -4;
        }
        
        //Test if new position is a box character
        if(board[newPos[0]][newPos[1]] == Config.BOX_CHAR || 
            board[newPos[0]][newPos[1]] == Config.BOX_GOAL_CHAR) {
            return -5;
        }
        
        return 1;
    }

    /**
     * Changes a character on the board to one of two characters (opt1 or opt2), depending on the 
     * value of the cell.
     *
     * Check the cell at position pos. If the character is val, change it to opt1. Otherwise, change
     * it to opt2.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param val The value to check for in the board.
     * @param opt1 The character to change to if the value is val.
     * @param opt2 The character to change to if the value is not val.
     */
    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
        if(board[pos[0]][pos[1]] == val) { //if pos in the board is the character in question
            board[pos[0]][pos[1]] = opt1;  //changes the character to the opt1 char
        }
        else {
            board[pos[0]][pos[1]] = opt2; //otherwise changes to opt2 char
        }
    }   

    /**
     * Moves a box on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are
     *         2 characters that can represent a box.
     * Step 2: Use your togglePos method to correctly change the character at the new position to 
     *         the appropriate box character.
     * Step 3: Again use your togglePos method to correctly change the character at pos to the 
     *         the appropriate character without a box.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @return The return value of checkDelta if less than 1. Otherwise 1.
     */
    public static int shiftBox(char[][] board, int[] pos, int[] delta) {
      //the two characters that represent box
        char[] validBox = new char[] {Config.BOX_CHAR, Config.BOX_GOAL_CHAR};
        int[] newPos = new int[2]; //1D array that will hold box coordinate after shift
        int checkDeltaVal;          //return value of checkDelta
        
        checkDeltaVal = checkDelta(board,pos,delta, validBox);
        
        if(checkDeltaVal == 1) { //checkDelta passes
            //new position vector by adding delta to old pos vector
            newPos[0] = pos[0] + delta[0];
            newPos[1] = pos[1] + delta[1];
            //if the new pos is a goal, change it to a boxgoal, otherwise just a box 
            togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR);
            //if the old pos was a box goal, change it to a goal, otherwise just an empty
            togglePos(board, pos, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);
        }
        else {
            return checkDeltaVal;
        }
        return checkDeltaVal;
    }

    /**
     * Processes a move of the worker step-by-step.
     *
     * Go through the delta step-by-step, calling doMove for each step. 
     * That is, if the delta is {0, -3}, your method should call doMove three times with an argument of
     * {0, -1} for the delta parameter of doMove. Or, if the delta is {6, 0}, it would call the doMove
     * six times with an argument of {1, 0} for the delta parameter of the doMove method. 
     *
     * During the processing of the move, if ever a call to doMove returns a value less than 1, your 
     * method should stop processing and return that value.
     *
     * Note: You can assume that one of the cells of delta will be 0. 
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column. 
     * @return If both of the cells of delta are 0, return 0.
     *         If the call to doMove returns a value less than 1, return that value.
     *         Otherwise, return 1.
     */    
    public static int processMove(char[][] board, int[] pos, int[] delta) {
        int numberOfSteps;  //magnitude of move; number of times the 'unit' step will repeat
        int doMoveVal;      //return value of doMove
        //unit vectors for each direction that will be repeatedly added
        int[] horizontalPos = new int[] {0,1};
        int[] horizontalNeg = new int[] {0,-1};
        int[] verticalPos = new int[] {1,0};
        int[] verticalNeg = new int[] {-1,0};
        
        if(delta[0] == 0 && delta[1] == 0) { //if delta is 0 vector
            return 0;
        }
        
        //if-else makes nonzero entry in delta = to numberOfSteps
        if(delta[0] == 0) {    
            numberOfSteps = Math.abs(delta[1]); 
        }
        else{
            numberOfSteps = Math.abs(delta[0]);
        }
        
        //will run the "unit" delta as many times as the actual delta requires
        for(int i = 0; i < numberOfSteps; i++) {
           if(delta[0] == 0) {              //block executes if delta is a horizontal shift
               if(delta[1] < 0) {           //if second entry in delta is negative
                   //doMove with unit vector
                   doMoveVal = doMove(board, pos, horizontalNeg);
                   if(doMoveVal < 1) {  //if error made by doMove
                       return doMoveVal;
                   }
               }
               else if(delta[1] > 0) {      //if second entry in delta is positive
                 //doMove with unit vector
                   doMoveVal = doMove(board, pos, horizontalPos);
                   if(doMoveVal < 1) {  //if error made by doMove
                       return doMoveVal;
                   }
               }
           }
           else {                           //block executes if delta is a vertical shift
               if(delta[0] < 0) {           //if first entry in delta is negative
                 //doMove with unit vector
                   doMoveVal = doMove(board, pos, verticalNeg);
                   if(doMoveVal < 1) {  //if error made by doMove
                       return doMoveVal;
                   }
               }
               else if(delta[0] > 0) {      //if first entry in delta is positive
                 //doMove with unit vector
                   doMoveVal = doMove(board, pos, verticalPos);
                   if(doMoveVal < 1) {  //if error made by doMove
                       return doMoveVal;
                   }
               }
           }
        }
        return 1;
    }

    /**
     * Moves the worker on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are
     *         2 characters that can represent the worker.
     * Step 2: If checkDelta returns -5, use your shiftBox method to move the box by delta before
     *         moving the worker.
     * Step 3: Use your togglePos method to correctly change the character at the new position to 
     *         the appropriate worker character.
     * Step 4: Again use your togglePos method to correctly change the character at pos to the 
     *         the appropriate character without a worker.
     * Step 5: Update the position of the worker in pos.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @return If checkDelta returns a value less than 1 that is not -5, return that value. 
     *         If checkDelta returns -5 and shiftBox returns a value less than 0, return 0.
     *         Otherwise, return 1.
     */    
    public static int doMove(char[][] board, int[] pos, int[] delta) {
        //the two characters that represent worker
        char[] validBox = new char[] {Config.WORKER_CHAR, Config.WORK_GOAL_CHAR};
        int[] newPos = new int[2];  //will hold position of worker after move
        int[] boxPos = new int[2];  //position of box if worker is in contact
        int checkDeltaVal;          //return value of checkDelta
        int shiftBoxVal;            //return value of shiftBox
        
        //assesses validity of particular worker move
        checkDeltaVal = checkDelta(board,pos,delta, validBox);
        
        if(checkDeltaVal < 1 && checkDeltaVal != -5) { //not landing on box
            return checkDeltaVal;
        }
        if(checkDeltaVal == -5) {       //landing on box
            //calculate position of the box char to move it
            boxPos[0] = pos[0] + delta[0];  
            boxPos[1] = pos[1] + delta[1];
            shiftBoxVal = shiftBox(board,boxPos,delta); //worker is 'pushing' box
            if(shiftBoxVal < 0) {
                return 0;  //if box movement causes an error
            }
        }
        //new worker position after move
        newPos[0] = pos[0] + delta[0];
        newPos[1] = pos[1] + delta[1];
        
        //changes the characters in old and new worker positions 
        togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);
        togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);
        
        //updates position vector to new position
        pos[0] = newPos[0];
        pos[1] = newPos[1];
        
        
        return 1;
    }

    /**
     * Checks all the cells in board and ensures that there are no goals that are not covered by
     * boxes.
     *
     * @param board The current board.
     * @return true if all the goals are covered by boxes. Otherwise, false.
     */
    public static boolean checkWin(char[][] board) { 
        //this block checks to see if there are any uncovered goal chars
        //if there are, checkwin is false
        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                if(board[i][j] == Config.GOAL_CHAR || board[i][j] == Config.WORK_GOAL_CHAR) {
                    return false;
                }
            }
        }
 
            return true;
    }

    /**
     * This is the main method for the Sokoban game. It consists of the main game and play again
     * loops with calls to the various supporting methods. The details of the main method for each 
     * milestone can be found in the BP1 - Sokoban write-up on the CS 200 webpage:
     * https://cs200-www.cs.wisc.edu/wp/programs/
     *
     * For all milestones, you will need to create a Scanner object to read from System.in that you
     * will pass to the helper methods.
     *
     * For milestone 3, you will need to create a Random object using Config.SEED as the seed. This
     * object should be create at the beginning of the method, outside of any loops.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random(Config.SEED);
        char playAgain = ' ';       //initial value of user entered char to play again or not
        int maxLvl = Config.LEVELS.length-1;    //maxLevel is last index of Levels array
        int lvl;    //index in Levels array (current level)
        int totalMoves; //counter for valid moves performed
        int processMoveRes;    //value returned by processMove
        int numMoves = 0;      //adds on to totalMoves counter 
        int[] origWorkPos = new int[2]; //holds position of worker char at beginning of game
        int[] defaultPos = new int[] {0,0}; //exists just to call initBoard
        char[][] board;     //will be the 2D gameBoard
        String resp;        //user input
        
        System.out.println("Welcome to Sokoban!");
        do {
           totalMoves = 0; //resets totalMoves for new game
           //user picks level 
           lvl = promptInt(scnr, "Choose a level between 0 and " + maxLvl + ": ", -1 , maxLvl);
           if(lvl == -1) {
             //random number between 0 and index of maxLevel
               lvl = rand.nextInt(Config.LEVELS.length); 
           }
           
           //block executes if checkLevel passes
           if(checkLevel(lvl, Config.LEVELS, Config.GOALS) == 1){
               //makes a copy of the pre-made level and fills it out with Goal chars for the game
               board = initBoard(lvl, Config.LEVELS, Config.GOALS, defaultPos);
               
               //finds the original position of the worker char
               for(int i = 0 ; i < board.length ; i++) {
                   for(int j = 0 ; j < board[i].length ; j++) {
                       if(board[i][j] == Config.WORKER_CHAR) {
                           origWorkPos[0] = i;
                           origWorkPos[1] = j;
                       }
                   }
               }
               System.out.println("Sokoban Level " + lvl);
               
               do{
                   printBoard(board);
                   resp = promptString(scnr, ": ");
                   
                   //if no input, go through loop again
                   if(resp == null || resp.equals("")) { 
                       continue;
                   }
                   //exit loop if quit char detected
                   if(resp.equals(String.valueOf(Config.QUIT_CHAR))){
                       break;
                   }
                   
                   //calculate movement vector based on input
                   calcDelta(resp);
                   //else if calculates numMoves based on magnitude of nonzero component of delta
                   if(calcDelta(resp)[0] == 0) {
                       numMoves = Math.abs(calcDelta(resp)[1]);
                   }
                   else if(calcDelta(resp)[1]== 0) {
                       numMoves = Math.abs(calcDelta(resp)[0]);
                   }
                   //this block executes if the movement vector is nonzero
                   if(calcDelta(resp)[0] != 0 || calcDelta(resp)[1] !=0) {
                       //attempts the move
                       processMoveRes = processMove(board, origWorkPos,calcDelta(resp));
                       if(processMoveRes == 1) {  //adds to totalMoves if the move is valid
                           totalMoves += numMoves;
                           
                       }
                   }
                   
               }while(!checkWin(board));
               
               if(checkWin(board)) { //runs checkWin to see if the user covered goals
                   System.out.println("Congratulations! You won in " + totalMoves + " moves!");
                   printBoard(board);
               }
           }
           else { //this block executes if checkLevel fails
               System.out.println("Error loading level!");
               
               //prints statements customized to type of error checkLevel returned
               switch(checkLevel(lvl,Config.LEVELS,Config.GOALS)) {
                   case 0:
                       System.out.println("Level " + lvl + " must be 0 or greater!");
                       break;
                   case -1:
                       System.out.println("Error with Config.LEVELS");
                       break;
                   case -2:
                       System.out.println("Error with Config.GOALS");
                       break;
                   case -3:
                       System.out.println("Level " + lvl + " does not contain any boxes.");
                       break;
                   case -4:
                       System.out.println("Level " + lvl + " does not have the same number of boxes as goals.");
                       break;
                   case -5:
                       System.out.println("Level " + lvl + " has a goal location that is a wall.");
                       break;
                   case -6:
                       System.out.println("Level " + lvl + " has 0 or more than 1 worker(s).");
                       break;
                   case -7:
                       System.out.println("Level " + lvl + "contains duplicate goals.");
                       break;
                   default:
                       System.out.println("Unknown Error");       
               }
           }
         
           playAgain = promptChar(scnr, "Play again? (y/n) ");
           
        }while(playAgain == 'y'); //run game loop until 'n' is entered by user 
        System.out.println("Thanks for playing!");
        
    }
}
