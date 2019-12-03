
public class test {

	public static void main(String[] args) {
		int a = 4;
		someMethod(a);
		System.out.println(a);
		System.out.println(someMethod(a));
		
	}
	public static int someMethod(int a) {
		a = 5;
		return a;
	}

}
