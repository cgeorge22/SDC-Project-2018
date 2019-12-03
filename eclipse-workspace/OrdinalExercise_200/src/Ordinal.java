import java.util.Scanner;
public class Ordinal {

	public static String toOrdinal(int i) {
		if(i % 100 >= 10 && i % 100 <= 19) {
			return i + "th";
		}
		else {
			switch(i % 10) {
			case 1:
				return i + "st";
			case 2:
				return i + "nd";
			case 3:
				return i + "rd";
			default:
				return i + "th";
			}
		}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int userNum;
		System.out.println("Enter the number to convert to ordinal form");
		userNum = sc.nextInt();
		System.out.println(toOrdinal(userNum));

	}

}
