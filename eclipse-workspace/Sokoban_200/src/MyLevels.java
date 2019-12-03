public class MyLevels{
    public static final char [][][] LEVELS = {
        {   // 3x3, 1 goal:
            // {' ', '#', '#'},
            // {' ', '=', ' '},
            // {' ', ' ', '@'}
            
               {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
               {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
               {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}
        },
        {
            // 6x6 2 goals:
            // {' ', ' ', ' ', '#', '#', ' '},
            // {' ', '=', ' ', '#', ' ', ' '},
            // {'#', ' ', ' ', ' ', ' ', ' '},
            // {' ', ' ', ' ', '=', ' ', ' '},
            // {'@', ' ', ' ', ' ', '#', '#'}, 
            // {' ', '#', '#', ' ', ' ', ' '}
               
               {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR},
               {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
               {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
               {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
               {Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR}, 
               {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}
        },
        {   // 9x9 3 goals
            // {' ','@',' ',' ',' ',' ',' '}, 
            // {' ',' ',' ','=',' ','#',' '},
            // {'#','#',' ',' ',' ','#',' '},
            // {' ',' ',' ',' ',' ','#',' ','#'},
            // {' ',' ',' ',' ','#','#',' ','#',' ',' ',' ',' ','#',' ',' ',' ','#'},
            // {' ','=',' ','#',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ','#'},
            // {' ',' ',' ','#',' ','=',' ',' ','#','#','#',' ','#',' ',' ',' ',' '},
            // {' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' '},
            // {'#','#','#',' ',' ','#','#','#','#',' ',' ',' ','#','#','#',' ',' '}
            
               {Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}, 
               {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR},
               {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR},
               {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
               {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
               {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
               {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
               {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
               {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}
            
        }

    };
    public static final int[][] GOALS = { {0,0}, {0,5,1,5}, {6,16,7,16,8,16} };
    
 
}