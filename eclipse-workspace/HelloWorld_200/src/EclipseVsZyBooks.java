import java.util.Scanner;

/**
 * The behavior of this code is different in Eclipse as compared to zyBooks.
 * Refactor the code so that it works in both zyBooks and Eclipse.
 * 
 * @author mrenault
 *
 */

public class EclipseVsZyBooks {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        int a = sc.nextInt();
        System.out.println("Enter a second integer: ");
        int b = sc.nextInt();
        if(a == b)
            System.out.println(a + " equals " + b);
        else if (a < b)
            System.out.println(a + " is less than " + b);
        else
            System.out.println(a + " is more than " + b);
    }
/*
    public static int readInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
*/
}