class Flower {
  protected int petals = 2;

  public int getPetals() {
    return petals - 1;
  }
}


class Daisy extends Flower {
  private int petals = 5;

  @Override
  public int getPetals() {
    // return petals + 1;
    return super.getPetals();
  }

  public int getPetals2() {
    return 2;
  }
}


public class MoreInheritanceAndInterfaces {

  public static void main(String[] args) {
    Flower fd = new Daisy(); // flower: compile time type; Daisy: runtime type .... upcast
    // compiler just makes sure that all methods called can be run by the runtime type (Flower)
    System.out.println(fd.getPetals());
    // System.out.println(fd.getPetals2()); // doesn't work-- method not in Flower
    if (fd instanceof Daisy) {
      Daisy dd = (Daisy) fd; // downcast
      System.out.println(dd.getPetals2());
    }
    String[] fruits = new String[] {"banana", "cherry", "apple", "dragonfrruit"};
    Integer[] ages = new Integer[] {19, 97, 55, 23, 42};
    System.out.println(findEarliest(fruits));
    System.out.println(findEarliest(ages));

    Singer[] animalChoir = new Singer[] {new Dog(), new Cat(), new Bird()};
    for (int i = 0; i < animalChoir.length; i++)
      animalChoir[i].sing();
  }

  public static Comparable findEarliest(Comparable[] array) {
    Comparable earliest = array[0];
    for (int i = 1; i < array.length; i++) {
      if (earliest.compareTo(array[i]) > 0) {
        earliest = array[i];
      }
    }
    return earliest;
  }
}

interface Singer {
  public void sing();
}


class Dog implements Singer {
  public void sing() {
    System.out.println("BARK");
  }
}


class Cat implements Singer {
  public void sing() {
    System.out.println("MEOW");
  }
}


class Bird implements Singer {
  public void sing() {
    System.out.println("TWEET");
  }
}


