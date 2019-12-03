public class MyOuterClass {
  private int outer;

  private class MyInnerClass {
    private int inner;

    private MyInnerClass() {
      inner = 2;
    } // constructor

    private void setInner(int s) {
      inner = s;
    }
  } // MyInnerClass

  public MyOuterClass() {} // constructor

  public void method1() {
    MyInnerClass innerObject = new MyInnerClass(); // line 11
    innerObject.inner = 12; // line 12
    // innerObject.outer = 13; // line 13
    innerObject.setInner(14); // line 14
    // inner = 15; // line 15
    outer = 16; // line 16
  }

  public static void main(String[] args) {
    // MyInnerClass innerObject = new MyOuterClass(); // line 19
    MyOuterClass outerObject = new MyOuterClass(); // line 20
    outerObject.method1(); // line 21
  }

}
