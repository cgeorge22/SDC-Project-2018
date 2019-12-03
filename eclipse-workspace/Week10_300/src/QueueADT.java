
public interface QueueADT <T> {
  public void enqueue(T newData);
  public T dequeue();
  public T peek();
  public boolean isEmpty();
}
