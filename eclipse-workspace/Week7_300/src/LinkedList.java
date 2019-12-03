import java.util.Iterator;

public class LinkedList<T> implements ListADT<T>, Iterable<T> {

  private LinkedNode<T> head;

  public int size() {
    LinkedNode<T> countNext = head;
    int count = 0;
    while (countNext != null) {
      countNext = countNext.getNext();
      count++;
    }
    return count;
  }

  public boolean isEmpty() { // not a headed linked list
    return head == null; // head == null: list is empty
  }

  @Override
  public String toString() { // implements first to help with debugging
    String s = "[";
    LinkedNode<T> display = head;
    while (display != null) {
      s += display.getData().toString();
      if (display.getNext() != null)
        s += ", ";
      display = display.getNext();
    }
    return s + "]";
  }

  // What type of parameter should this method take as input?
  // A) LinkedNode<T> - we want to hide this implementation detail from this user of our class
  // B) T - all that the code creating and using LinkedList objects needs to know about
  public void add(T newObject) { // adds newObject to the end of the list
    if (isEmpty())
      head = new LinkedNode<>(newObject, null);
    else {
      LinkedNode<T> lastNode = head;
      while (lastNode.getNext() != null)
        lastNode = lastNode.getNext();
      lastNode.setNext(new LinkedNode<>(newObject, null));
    }
  }

  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();
    System.out.println(list.isEmpty());
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    System.out.println(list);
    System.out.println(list.size());
    
    LinkedList<Integer> numbers = new LinkedList<>();
    numbers.add(13);
    numbers.add(8);
    numbers.add(6);
    numbers.add(22);
    
    int sum = 0;
    Iterator<Integer> it = numbers.iterator();
    while(it.hasNext())
      sum+= it.next();
    System.out.println(sum);
    
    sum = 0;
    for(Integer i : numbers)
      sum += i;
    System.out.println(sum);
    
    sum = 0;
    for(int i = 0; i  < numbers.size(); i++)
      sum += numbers.get(i);
    System.out.println(sum);
  }
  
  @Override
  public Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return new LinkedListIterator(head);
  }

  @Override
  public boolean contains(T findObject) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void add(int index, T newObject) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void indexOf(T findObject) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public T get(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T remove(int index) {
    // TODO Auto-generated method stub
    return null;
  }

}
