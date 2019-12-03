import java.util.Iterator;

public class LotsOfIntegers implements Iterator<Integer> {
  private Integer i = 0;

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Integer next() {
    Integer temp = i;
    i++;
    return temp;
  } 
  
  public static void main(String[] args) {
    Iterator<Integer> it = new LotsOfIntegers();
    for(int i = 0; i < 10; i++) {
      System.out.println(it.next());
    }
  }
  
}
