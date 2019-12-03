import java.util.Random;

public class Inheritance {

  public static void main(String[] args) {
    // ContainsRandom rng = new ContainsRandom();
    // ExtendsRandom rng = new ExtendsRandom();
    Random rng = new ExtendsRandom(); // upcast
    // System.out.println(rng.nextInt(6) + 1);
    if (Math.random() < 0.5) // dynamic dispatch/binding
      rng = new Random();

    System.out.println(rng.nextInt(2));

    if (rng instanceof ExtendsRandom)
      System.out.println(((ExtendsRandom) rng).nextName()); // downcast

    Parent pp = new Parent();
    Parent pc = new Child();
    Child cc = new Child();
    // Child cp = new Parent();
    pp.method();
    pc.method();
    cc.method();
    System.out.println(pp.x);
    System.out.println(pc.x);
    System.out.println(cc.x);


  }

}



class ContainsRandom {
  private Random rng;

  public ContainsRandom(long seed) {
    rng = new Random(seed);
  }

  public ContainsRandom() {
    rng = new Random();
  }

  public String nextName() {
    return (char) ('A' + rng.nextInt(26)) + "ary";
  }

  public int nextInt(int max) {
    return rng.nextInt(max) + 1;
  }
}


class ExtendsRandom extends Random {

  public ExtendsRandom() {
    //super(); // constructor of the parent class-- always first, implied if not written
  }

  public ExtendsRandom(long seed) {
    super(seed);
  }

  public String nextName() {
    return (char) ('A' + super.nextInt(26)) + "ary";
  }

  @Override
  public int nextInt(int max) { // confusion with nextInt calling itself
    return super.nextInt(max) + 1;
  }

  // @Override
  // public int next(int size) {
  // return 5;
  // }
}


class Parent {
  public int x = 3;

  public void method() {
    System.out.println("Parent.method()");
  }
}


class Child extends Parent {
  public int x = 4;

  @Override
  public void method() {
    System.out.println("Child.method()");
  }
}
