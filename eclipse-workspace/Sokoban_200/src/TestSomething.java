
public class TestSomething {
    public static void main(String[] args) {
        char EMPTY_CHAR = ' '; // Empty character
        char BOX_CHAR = '='; // Box character
        char WALL_CHAR = '#'; // Wall character
        char WORKER_CHAR = '@'; // Worker character
        char GOAL_CHAR = '.'; // Worker character
        char BOX_GOAL_CHAR = '*'; // Box on a goal character
        char WORK_GOAL_CHAR = '+'; // Worker on a goal character
        char[][] board =
   {{EMPTY_CHAR, WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
   {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR},
   {WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR},
   {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR,
   WALL_CHAR},{EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR,
   WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR,
   EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
   {EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
   EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR,
   EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
   {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR,
   EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR,
   EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
   {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
   EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
   EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
   {WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR,
   WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR,
   WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR}

   };
        char[][]board2 = {{'x','x','x','x'},{'x','x','x'}, {'x','x'},{'x','x','x','x'}};
        int[]pos = new int[] {4,5};
        System.out.println(pos[0] + "," + pos[1]);
        System.out.println(board2.length);
        System.out.println(board2[1].length);
        for(int i = 0 ; i < board2.length ; i++) {
              if(board.length - 1 < pos[0] || pos[0] < 0) {
                  System.out.println("too large");
              }
              if(board2[i].length - 1 < pos[1] || pos[1] < 0) {
                  System.out.println("too large");
              }
          }
        
     }
  }
//        int[] testPos = {1, 0};
//        char[][] g1 = initBoard(2, MyLevels.LEVELS, MyLevels.GOALS, testPos);
//        for (int i = 0; i < g1.length; i++) {
//            for (int j = 0; j < g1[i].length; j++) {
//                System.out.print(g1[i][j]);
//            }
//            System.out.println();
//        }
//       
//    }
//
//    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
//        int maxColumns = 0;
//        for (int i = 0; i < levels[lvl].length; i++) {
//            if (maxColumns < levels[lvl][i].length) {
//                maxColumns = levels[lvl][i].length;
//            }
//        }
//        char[][] gameBoard = new char[levels[lvl].length][maxColumns];
//        for (int i = 0; i < levels[lvl].length; i++) {
//            for (int j = 0; j < levels[lvl][i].length; j++) {
//                gameBoard[i][j] = levels[lvl][i][j];
//            }
//        }
//        return gameBoard;
//    }
//}



// char EMPTY_CHAR = ' '; // Empty character
// char BOX_CHAR = '='; // Box character
// char WALL_CHAR = '#'; // Wall character
// char WORKER_CHAR = '@'; // Worker character
// char GOAL_CHAR = '.'; // Worker character
// char BOX_GOAL_CHAR = '*'; // Box on a goal character
// char WORK_GOAL_CHAR = '+'; // Worker on a goal character
// char[][] board =
// {{EMPTY_CHAR, WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
// {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR},
// {WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR},
// {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR,
// WALL_CHAR},
// {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR,
// WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR,
// EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
// {EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
// EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR,
// EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
// {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR,
// EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR,
// EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
// {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
// EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
// EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
// {WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR,
// WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR,
// WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR}
//
// };
// char[][]board2 = {{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
// Config.WALL_CHAR, Config.EMPTY_CHAR},
// {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
// Config.EMPTY_CHAR},
// {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
// Config.EMPTY_CHAR},
// {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR,
// Config.EMPTY_CHAR},
// {Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
// Config.WALL_CHAR},
// {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
// Config.EMPTY_CHAR}};
//
// for(int i = 0 ; i < board2.length ; i++) {
// for(int j = 0 ; j < board2[i].length; j++) {
// System.out.print(board2[i][j]);
// }
// System.out.println();
// }
// System.out.println();
// System.out.println();
// printBoard(board2);
// }
//
//
//
// public static void printBoard(char[][] board) {
// int i, j;
// for (i = 0; i < board[0].length + 2; i++) {
// System.out.print('#');
// }
// System.out.println();
// for (i = 0; i < board.length; i++) {
// System.out.print('#');
// for (j = 0; j < board[i].length; j++) {
// System.out.print(board[i][j]);
// }
// System.out.println('#');
// }
// for (i = 0; i < board[board.length - 1].length + 2; i++) {
// System.out.print('#');
// }
// }
// }


