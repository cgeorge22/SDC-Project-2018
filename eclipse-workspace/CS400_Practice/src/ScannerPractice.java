import java.util.Scanner;
// learn how Scanner instances that are connected to the keyboard work.
public class ScannerPractice {
    // A single instance of a Scanner connected to default input (keyboard)
    private static final Scanner STDIN = new Scanner(System.in);

    // args - unused
    public static void main(String [] args) {
        String sample = "hello world\n i am here\n 1.34 43 good-bye"; 
        // try different methods of the Scanner STDIN
        while ( STDIN.hasNextInt() ) {
          System.out.println(STDIN.hasNextInt());
            // put each result on a new output line and within brackets
            System.out.println("[" + STDIN.nextInt() + "]");
        }
    }
}