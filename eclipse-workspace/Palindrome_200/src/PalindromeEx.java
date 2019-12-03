import java.util.Scanner;
public class PalindromeEx {

	public static void main(String[] args) {
		String userString = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a word"); 
		userString = sc.nextLine();
		System.out.println(isPalindrome(userString));

	}
public static boolean isPalindrome(String word) {
	word = word.toLowerCase();
	word = word.replaceAll(" ", "");
    for(int i = 0; i < word.length()/2; i++) {
    	if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
    		return false;
    	}
}     
    return true;

}
}
