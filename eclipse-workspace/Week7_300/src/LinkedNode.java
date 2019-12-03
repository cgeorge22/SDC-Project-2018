
public class LinkedNode<T> {
  private T data;
  private LinkedNode<T> next;

  public static void main(String[] args) {
    LinkedNode<String> list = new LinkedNode<>("A", new LinkedNode<>("B", new LinkedNode<>("X",
        new LinkedNode<>("Q", new LinkedNode<>("T", new LinkedNode<>("C", null))))));

    // remove the node that contains "X"
    // find the relevant part of the list (node before the node with the X)
    LinkedNode<String> search = list;
    while (true) {
      if (search.next.data.equals("X")) {
        // found what we're looking for
        System.out.println("Found: " + search.data);
        break;
      }
      search = search.next;
    }
    // make change to that part
    search.next = search.next.next;
  }

  public LinkedNode(T data, LinkedNode<T> next) {
    super();
    this.data = data;
    this.next = next;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public LinkedNode<T> getNext() {
    return next;
  }

  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}
