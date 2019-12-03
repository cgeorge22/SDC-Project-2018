import java.util.Iterator;

public class TraceIterator implements Iterable<Integer>, Iterator<Integer> {
  private int a;
  private boolean b;
  public static void main(String[] args) {
    TraceIterator traceMe = new TraceIterator();
    for(Integer i : traceMe) System.out.print(i);
    for(Integer i : traceMe) System.out.print(i);
  }

  @Override
  public boolean hasNext() {
      return b = !b;
  }

  @Override
  public Integer next() {
    return ++a;
  }

  @Override
  public Iterator<Integer> iterator() {
    return this;
  }

}
