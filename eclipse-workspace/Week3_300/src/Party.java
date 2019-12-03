import java.util.ArrayList;

public class Party {
  public static void main(String[] args) {
    ArrayList<Person> people = new ArrayList<>();
    people.add(new Person("Ahmed", 24));
    people.add(new Person("Bianca", 26));
    people.add(new Person("Chen", 22));
    people.add(new Person("DeShawn", 28));
    Person p1 = new Person("Emma");
    
//    System.out.println(p1.changeThermostat() == p1.changeThermostat());
    
    while(p1.changeThermostat()) {
      if(p1.changeThermostat()) {
        System.out.println(p1.changeThermostat());
      }
      else {
        System.out.println(p1.changeThermostat());
      }
    }
    

//    System.out.println(people.get(2).getAgeYears());
//    System.out.println(people.get(3).isAdult());

    // System.out.print(Person.population);

  }
}
