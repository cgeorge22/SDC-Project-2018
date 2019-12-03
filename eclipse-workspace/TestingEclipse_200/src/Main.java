import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    String x;
		//System.out.println("A foo walks into a bar... \n\n\n... and says \"Hello World!\"");
		System.out.print("enter input");
		System.out.println(sc.hasNextLine());
		x = sc.nextLine();
	    System.out.println(x.isEmpty());
		System.out.println(x);
	}

}
