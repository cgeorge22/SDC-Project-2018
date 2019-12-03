import java.util.ArrayList;

public class Person {
  private String name;
  private int age;
  private static int population = 0;
  private boolean isTooCold; // = true


  public Person() {

  }
  
  public Person(String name, int age) {
    this.name = name;
    this.age = age;
    Person.population++;
  }

  public Person(String name) {
    this(name, 0);

  }

  public int getAgeYears() {
    return age;
  }
  

  public int getAgeMonths() {
    return age * 12;
  }

  public boolean isAdult() {
    return age >= 18;
  }
  
  public void celebrateBirthday() {
    this.age++;
  }
  
  public boolean changeThermostat() {
    boolean wasTooCold = isTooCold;
    isTooCold = !isTooCold;
    return wasTooCold;
  }
  
  public String toString() {
    return name + " is " + age + " years old.";
  }

  public static void main(String[] args) {



    // Person p1 = new Person("Ahmed", 24);
    // p1.name = "ahmed";
    // p1.age = 24;
    // Person p2 = new Person("Bianca", 26);
    // p2.name = "bianca";
    // p2.age = 26;
    // System.out.println(p1.toString());//name + " is " + p1.age + " years old.");
    // System.out.println(p2.toString());// + " is " + p2.age + " years old.");
  }

}
