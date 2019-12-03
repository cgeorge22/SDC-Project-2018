import java.util.Scanner;

class Person {
  private int age;

  public Person(int age) {
    this.age = age;
  }

  public void setAgeOrIncreaseAgeByOneYear(int age) {
    try {
      setAge(age);
    } catch (IllegalArgumentException e) {
      setAge(this.age + 1);
    }
  }

  public void setAge(int age) {
    if (age < this.age) {
      throw new IllegalArgumentException("Person is getting younger");
    } else {
      this.age = age;
    }
  }

  public String toString() {
    return "age: " + age;
  }
}


public class Exception {
  private static Scanner in;

  public static void main(String[] args) {
    in = new Scanner(System.in);
    Person p1 = new Person(14);
    try {
      p1.setAgeOrIncreaseAgeByOneYear(8);
      System.out.println(p1.toString());
    } catch (IllegalArgumentException ex) {
      System.out.println("Doesn't work: " + ex.getMessage());
    }
    System.out.println(divideTwoNumbers());

  }



  public static int divideTwoNumbers() {
    System.out.print("Enter two numbers divide(#/#): ");
    String input = in.nextLine();
    String[] parts = input.split("/");
    int numerator = Integer.parseInt(parts[0]);
    int denominator = Integer.parseInt(parts[1]);
    int quotient = numerator / denominator;
    return quotient;
  }

}
