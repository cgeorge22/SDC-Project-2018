
public interface ListADT<T> {
  // subset java.util.Colletion unordered collection
  public void add(T newObject);
  public boolean isEmpty();
  public int size();
  public boolean contains(T findObject);
  
  // subset java.util.List: ordered collection
  public void add(int index, T newObject);
  public void indexOf(T findObject);
  public T get(int index);
  public T remove(int index);
}
