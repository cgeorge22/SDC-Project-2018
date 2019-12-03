//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P2 BALST
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Description: AVL Balanced Search Tree implementation-- creates a an AVL
// balanced search tree with BSTNodes of type <K,V>
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class to implement a BalanceSearchTree. AVL implementation was chosen.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

  private BSTNode<K, V> root;

  private int numKeys;

  public BALST() {}

  /**
   * Returns the key that is in the root node of this BST. If root is null, returns null.
   * 
   * @return key found at root node, or null
   */
  @Override
  public K getKeyAtRoot() {
    if (root == null) {
      return null;
    }
    return root.key;
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the key that is in the left child. If the left child of the found node is null, returns
   * null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> nodeToFind = searchForKey(key, root); // search the BST for the key, save node

    if (nodeToFind.left == null) {
      return null;
    } else {
      return nodeToFind.left.key;
    }
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the key that is in the right child. If the right child of the found node is null,
   * returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    BSTNode<K, V> nodeToFind = searchForKey(key, root); // search the BST for the key, save node

    if (nodeToFind.right == null) {
      return null;
    } else {
      return nodeToFind.right.key;
    }
  }

  /**
   * helper method that searches tree for a key and returns the node containing the key
   * 
   * @param key to search for
   * @param current node to search for the key
   * @return the node that contains the key
   * @throws KeyNotFoundException if no node containing the key is found
   */
  private BSTNode<K, V> searchForKey(K key, BSTNode<K, V> current) throws KeyNotFoundException {
    if (current != null) {
      if (key.equals(current.key)) {
        return current;
      } else if (key.compareTo(current.key) < 0) { // key is less than current node's key
        return searchForKey(key, current.left);
      } else { // key is greater than current node's key
        return searchForKey(key, current.right);
      }
    }
    throw new KeyNotFoundException(); // if null node reached (key not found)
  }

  /**
   * Returns the height of this BST. H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max( height(root.left),
   * height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key, has a height of one
   * (1). A BST with two keys, has a height of two (2). A BST with three keys, can be balanced with
   * a height of two(2) or it may be linear with a height of three (3) ... and so on for tree with
   * other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  @Override
  public int getHeight() {
    if (root == null) {
      return 0;
    }
    root.setHeight();
    return root.height;
  }

  /**
   * Returns the keys of the data structure in sorted order. In the case of binary search trees, the
   * visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  @Override
  public List<K> getInOrderTraversal() {
    return inOrderTraversalHelper(root);
  }

  /**
   * recursive helper method for getInOrderTraversal(), creates ArrayList of keys in order
   * 
   * @param current node being visited in the recursive call
   * @return arrayList of keys added so far
   */
  private ArrayList<K> inOrderTraversalHelper(BSTNode<K, V> current) {
    ArrayList<K> inOrderList = new ArrayList<K>();

    if (current == null) { // empty BST
      return inOrderList;
    }

    // recursively add the left child to the arrayList
    if (current.left != null) {
      inOrderList.addAll(inOrderTraversalHelper(current.left));
    }

    // visit current node
    inOrderList.add(current.key);

    // recursively add the right child to the arrayList
    if (current.right != null) {
      inOrderList.addAll(inOrderTraversalHelper(current.right));
    }

    return inOrderList;
  }


  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getPreOrderTraversal() {
    return preOrderTraversalHelper(root);
  }

  /**
   * recursive helper method for getPreOrderTraversal(), creates ArrayList of keys in preOrder
   * 
   * @param current node being visited in the recursive call
   * @return arrayList of keys added so far
   */
  private ArrayList<K> preOrderTraversalHelper(BSTNode<K, V> current) {
    ArrayList<K> preOrderList = new ArrayList<K>();

    if (current == null) { // empty BST
      return preOrderList;
    }

    // visit current node
    preOrderList.add(current.key);

    // recursively add the left child to the arrayList
    if (current.left != null) {
      preOrderList.addAll(preOrderTraversalHelper(current.left));
    }

    // recursively add the right child to the arrayList
    if (current.right != null) {
      preOrderList.addAll(preOrderTraversalHelper(current.right));
    }

    return preOrderList;
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    return postOrderTraversalHelper(root);
  }

  /**
   * recursive helper method for getPostOrderTraversal(), creates ArrayList of keys in postOrder
   * 
   * @param current node being visited in the recursive call
   * @return arrayList of keys added so far
   */
  private ArrayList<K> postOrderTraversalHelper(BSTNode<K, V> current) {
    ArrayList<K> postOrderList = new ArrayList<K>();

    if (current == null) { // empty BST
      return postOrderList;
    }

    // recursively add the left child to the arrayList
    if (current.left != null) {
      postOrderList.addAll(postOrderTraversalHelper(current.left));
    }

    // recursively add the right child to the arrayList
    if (current.right != null) {
      postOrderList.addAll(postOrderTraversalHelper(current.right));
    }

    // visit current node
    postOrderList.add(current.key);

    return postOrderList;
  }

  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down, and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    ArrayList<K> levelOrderList = new ArrayList<K>();

    int height = getHeight();
    for (int i = 0; i < height; i++) {
      levelOrderList.addAll(singleLevelTraversal(i, root));
    }
    return levelOrderList;
  }

  /**
   * recursively creates an ArrayList of the keys of Nodes on a single level in the BST
   * 
   * @param level to be traversed
   * @param current node visited
   * @return ArrayList of all the node keys in a level
   */
  private ArrayList<K> singleLevelTraversal(int level, BSTNode<K, V> current) {
    ArrayList<K> levelList = new ArrayList<K>();

    if (current == null) {
      return levelList;
    }
    if (level == 0) { // node to add is visited
      levelList.add(current.key);
    } else if (level > 0) { // need to traverse to the next Node for the list
      levelList.addAll(singleLevelTraversal(level - 1, current.left)); // nodes for left subtree
      levelList.addAll(singleLevelTraversal(level - 1, current.right)); // right subtree
    }

    return levelList;
  }

  /**
   * Add the key,value pair to the data structure and increase the number of keys. Do not increase
   * the number of keys in the structure if key,value pair is not added.
   * 
   * @param key of node to add to the BST
   * @param value of node to add to the BST
   * @throws IllegalNullKeyException If key is null
   * @throws DuplicateKeyException If key is already in data structure
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    root = insert(root, key, value);
    numKeys++;
  }

  /**
   * recursive helper method for insert(k,v)-- adds a new node to the BST
   * 
   * @param current node being visited in recursion
   * @param key to assign to new node
   * @param value to assign to new node
   * @return the root node for the BST on final frame
   * @throws DuplicateKeyException if the new key is already in the BST
   */
  private BSTNode<K, V> insert(BSTNode<K, V> current, K key, V value) throws DuplicateKeyException {
    if (current == null) { // reached spot to insert the node
      BSTNode<K, V> newNode = new BSTNode<K, V>(key, value);
      return newNode;
    }

    if (current.key.equals(key)) { // the key is already in the tree
      throw new DuplicateKeyException();
    } else if (key.compareTo(current.key) < 0) { // less than current: recursion-- left subtree
      current.left = insert(current.left, key, value);
      current.left.setHeight();
      current.left.setBalanceFactor();
    } else { // greater than current: recursion-- right subtree
      current.right = insert(current.right, key, value);
      current.right.setHeight();
      current.right.setBalanceFactor();
    }

    // update node attributes
    current.setHeight();
    current.setBalanceFactor();


    if (current.balanceFactor > 1) {
      if (current.left.balanceFactor > 0) { // descending "straight line"
        return rotateRight(current);
      } else if (current.left.balanceFactor < 0) { // "zig-zag" with max at root
        current.left = rotateLeft(current.left);
        return rotateRight(current);
      }
    }
    if (current.balanceFactor < -1) {
      if (current.right.balanceFactor < 0) { // ascending 'straight line"
        return rotateLeft(current);
      } else if (current.right.balanceFactor > 0) { // "zig-zag" with min at root
        current.right = rotateRight(current.right);
        return rotateLeft(current);
      }
    }
    return current;
  }

  /**
   * performs a left rotation on a given node in an AVL tree
   * 
   * @param node to perform left rotation on
   * @return the new root node after rotation
   */
  private BSTNode<K, V> rotateLeft(BSTNode<K, V> node) {
    BSTNode<K, V> newRoot = node.right;
    BSTNode<K, V> moveChild = newRoot.left;

    // rotate nodes
    newRoot.left = node;
    node.right = moveChild;

    // update heights and balance factors
    node.setHeight();
    node.setBalanceFactor();
    newRoot.setHeight();
    newRoot.setBalanceFactor();

    return newRoot;
  }

  /**
   * performs a right rotation on a given node in an AVL tree
   * 
   * @param node to perform right rotation on
   * @return the new root node after rotation
   */
  private BSTNode<K, V> rotateRight(BSTNode<K, V> node) {
    BSTNode<K, V> newRoot = node.left;
    BSTNode<K, V> moveChild = newRoot.right;

    // rotate nodes
    newRoot.right = node;
    node.left = moveChild;

    // update heights and balance factors
    node.setHeight();
    node.setBalanceFactor();
    newRoot.setHeight();
    newRoot.setBalanceFactor();

    return newRoot;
  }



  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys. If
   * key is not found, do not decrease the number of keys in the data structure.
   * 
   * @param key of node to delete from BST
   * @return true if key was deleted successfully
   * @throws IllegalNullKeyException If key is null
   * @throws KeyNotFoundException() If key is not found
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    root = remove(root, key); // will throw exception if key not found, execution won't not continue
    numKeys--;

    return true;
  }

  /**
   * recursive helper method for remove(), handles deletion of a specific node and returns it
   * 
   * @param current node being visited in recursion
   * @param key of node to delete
   * @return the new value of the removed node
   * @throws KeyNotFoundException if the key is not in the BST
   */
  private BSTNode<K, V> remove(BSTNode<K, V> current, K key) throws KeyNotFoundException {
    if (current == null) { // null node reached, so key was not found
      throw new KeyNotFoundException();
    }

    // search for key recursively
    if (key.compareTo(current.key) < 0) { // key is less than current key
      current.left = remove(current.left, key);
    } else if (key.compareTo(current.key) > 0) { // key is greater than current key
      current.right = remove(current.right, key);
    } else { // found node, now remove it
      // if node has no children, return null
      if (current.left == null && current.right == null) {
        return null;
      }
      // if the node has 1 child, return it
      else if (current.left == null || current.right == null) {
        if (current.left == null) {
          return current.right;
        } else {
          return current.left;
        }
      }
      // if node has 2 children, return the in-order predecessor, delete the in-order predecessor
      else {
        current.key = inOrderPredecessor(current).key; // assign key to in-order predecessor
        current.left = remove(current.left, current.key); // delete in-order predecessor recursively
        return current;
      }
    }
    return current;
  }

  /**
   * finds the in-order predecessor of a node with a given key
   * 
   * @param current node being visited recursively
   * @param key to search for
   * @return the Node which is the in-order predecessor of the node with the specified key
   */
  private BSTNode<K, V> inOrderPredecessor(BSTNode<K, V> node) {
    return findMax(node.left); // find the max value of the node's left subtree
  }

  /**
   * helper method to find the maximum of a [sub]tree
   * 
   * @param current node being visited in the tree
   * @return maximum node in the tree
   */
  private BSTNode<K, V> findMax(BSTNode<K, V> current) {
    while (current.right != null) {
      current = current.right;
    }

    return current;
  }

  /**
   * Returns the value associated with the specified key. Does not remove key or decrease number of
   * keys
   * 
   * @param key of Node to find and return value for
   * @return value associated with the given key
   * @throws IllegalNullKeyException If key is null
   * @throws KeyNotFoundException() If key is not found
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    return get(key, root);
  }

  /**
   * helper method for get() that returns the value of a searchKey for a BST
   * 
   * @param key to search for
   * @param current node being evaluated
   * @return value of the key being searched for
   * @throws KeyNotFoundException if the key is not in the BST
   */
  private V get(K key, BSTNode<K, V> current) throws KeyNotFoundException {
    if (current == null) {
      throw new KeyNotFoundException();
    }
    if (key.compareTo(current.key) < 0) { // search key is less than current key
      return get(key, current.left);
    } else if (key.compareTo(current.key) > 0) { // search key is greater than current key
      return get(key, current.right);
    } else { // search key equals current key (key found)
      return current.value;
    }
  }

  /**
   * searches BST for a key
   * 
   * @param key to find
   * @return true if the key is in the data structure, false if key is not null and is not present
   * @throws IllegalNullKeyException if the key is null
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    return contains(root, key);
  }

  /**
   * recursive helper method for contains()-- searches BST for a given key
   * 
   * @param current node being evaluated for key
   * @param key being searched for
   * @return true if key found, false if key not found
   */
  private boolean contains(BSTNode<K, V> current, K key) {
    if (current == null) {
      return false;
    }
    if (key.compareTo(current.key) < 0) { // search key is less than current key
      return contains(current.left, key);
    } else if (key.compareTo(current.key) > 0) { // search key is greater than current key
      return contains(current.right, key);
    } else { // search key equals current key (key found)
      return true;
    }
  }

  /**
   * @return the number of key,value pairs in the data structure
   */
  @Override
  public int numKeys() {
    return numKeys;
  }


  /**
   * print a visual representation of the tree
   */
  @Override
  public void print() {
    printTree(root, 0);
  }

  /**
   * recursive helper method for print()
   * 
   * @param current node being recursively visited
   * @param level being traversed
   */
  private void printTree(BSTNode<K, V> current, int level) {
    if (current == null) { // end of tree reached
      return;
    }
    printTree(current.right, level + 1); // recursively print right side
    if (level != 0) {
      for (int i = 0; i < level - 1; i++) {
        System.out.print("|\t"); // shift placement over to match level
      }
      System.out.println("|-------" + current.key);
    } else
      System.out.println(current.key); // root node
    printTree(current.left, level + 1); // recursively print left side
  }

}
