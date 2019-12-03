//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P1 Test ADT
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Description: Abstract Data Structure Implementation, via linked list.stores
// a pair of values (key, value) together
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * @author chrisgeorge
 * 
 *         this class contains the functions for the LinkedList data structure
 *
 */
/**
 * @author chrisgeorge
 *
 */
public class DS_My implements DataStructureADT {

  /**
   * @author chrisgeorge
   * 
   *         inner class-- functions for an individual linked list node
   * 
   * @param <K> a Comparable type
   * @param <V> an Object type
   */
  class DataStructureNode<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private DataStructureNode next;

    /**
     * constructor-- creates the DataStructureNode, storing its given values
     * 
     * @param key -- first value in the pair
     * @param value -- second value in the pair
     */
    private DataStructureNode(K key, V value) {
      this.key = key;
      this.value = value;
    }

    /**
     * returns the Node's next DataStructureNode in the linked list
     * 
     * @return the next DataStructureNode
     */
    private DataStructureNode getNext() {
      return this.next;
    }

    /**
     * sets the Node's next DataStructureNode in the list
     * 
     * @param next DataStructureNode to set as next
     */
    private void setNext(DataStructureNode next) {
      this.next = next;
    }

    /**
     * returns the key entry of the node
     * 
     * @return key entry
     */
    private K getKey() {
      return this.key;
    }

    /**
     * returns the value entry of the node
     * 
     * @return the value entry
     */
    private V getValue() {
      return this.value;
    }

  }

  private DataStructureNode head;
  private int size;

  /**
   * constructor-- creates the ADT, setting size to 0 as default
   */
  public DS_My() {
    this.size = 0;
  }

  /**
   * Add the key,value pair to the data structure and increases size.
   *
   * @param k-- key to add
   * @param v-- value to add
   * @throws RuntimeException if duplicate key is given
   * @throws IllegalArgumentException if key is null
   */
  @Override
  public void insert(Comparable k, Object v) {
    DataStructureNode current;
    DataStructureNode previous;

    if (k == null) {
      throw new IllegalArgumentException("null key");
    } else if (this.head == null) { // if the linkedList is empty
      this.head = new DataStructureNode(k, v);
      this.size++;
    } else {
      current = this.head;
      previous = this.head;
      while (current != null) { // traverse through the LinkedList
        if (current.getKey().compareTo(k) == 0) { // duplicate key reached
          throw new RuntimeException("duplicate key");
        }
        previous = current; // increment place tracking values
        current = current.getNext();
      }
      previous.setNext(new DataStructureNode(k, v)); // end of list reached
      this.size++;
    }
  }


  /**
   * If key is found, Removes the key from the data structure and decreases size
   *
   * @param k -- key to remove
   * @throws IllegalArgumentException if k is null
   * 
   */
  @Override
  public boolean remove(Comparable k) {
    DataStructureNode current;
    DataStructureNode previous;

    if (k == null) {
      throw new IllegalArgumentException("null key");
    }
    current = this.head;
    previous = this.head;
    while (current != null) { // traverse the linkedList
      if (current.getKey().compareTo(k) == 0) { // key to remove found
        if (current.equals(previous)) { // key to remove is head
          this.head = current.getNext();
          this.size--;
        } else {
          previous.setNext(current.getNext());
          this.size--;
        }
        return true;
      }
      previous = current; // increment place keeping values
      current = current.getNext();
    }
    return false; // reached if the list is traversed without finding key
  }

  /**
   * searches for the key in the linkedList
   * 
   * @param k -- key to search
   * @return true if the key is in the data structure, false if key is null or not present
   * 
   */
  @Override
  public boolean contains(Comparable k) {
    DataStructureNode current = this.head;

    if (k == null) {
      return false;
    }
    while (current != null) { // traverse through list
      if (current.getKey().compareTo(k) == 0) { // match found
        return true;
      }
      current = current.getNext(); // increment place keeper
    }
    return false; // list traversed with no match
  }

  /**
   * Returns the value associated with the specified key
   * 
   * @param k-- key to get value for
   * @throws IllegalArgumentException if k is null
   * @return value associated with k, if exists
   */
  @Override
  public Object get(Comparable k) {
    DataStructureNode current = this.head;

    if (k == null) {
      throw new IllegalArgumentException("null key");
    }
    while (current != null) { // traverse through list
      if (current.getKey().compareTo(k) == 0) { // match found
        return current.getValue();
      }
      current = current.getNext(); // increment
    }
    return null; // not found
  }

  /**
   * return's the data structure's size
   * 
   * @return LinkedList's size
   */
  @Override
  public int size() {
    return this.size;
  }

}
