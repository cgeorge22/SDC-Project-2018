
public class Recursion {
  public static int mul(int a, int b) {
    if (a == 0 || b == 0) return 0;
    else return a + mul(a,--b);
  }
  public static int add(int a, int b) {
    if (b == 0) return a;
    else return 1 + add(a, --b);
  }
   public static void main(String[] args) {
     System.out.println(add(5,7));
     System.out.println(mul(5,7));

   }
  
}
