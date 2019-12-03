import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {
  
  private LinkedNode<T> current;
  
  public LinkedListIterator(LinkedNode<T> head) {
    current = head;
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public T next() {
    T temp = current.getData();
    current = current.getNext();
    return temp;
  }

}
