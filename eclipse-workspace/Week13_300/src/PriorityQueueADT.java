
public interface PriorityQueueADT<T extends Comparable<T>> {
  public void insert(T newObject);

  public T removeBest();

  public T peekBest();

  public boolean isEmpty();
  
  
}
