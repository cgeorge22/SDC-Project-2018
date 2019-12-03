//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P2 BALST
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Description: a Node that holds a <K> Key and a <V> value. Points to other
// BSTNodes to form an AVL tree, implemented in BALST.java
// Classes that use this type: class BALST<K,V>
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


/**
 * class to implement a specific Node inside a Balanced Search Tree
 *
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
class BSTNode<K extends Comparable<K>, V> {

  K key;
  V value;
  BSTNode<K, V> left;
  BSTNode<K, V> right;
  int balanceFactor;
  int height;


  /**
   * constructor for a node with children
   * 
   * @param key
   * @param value
   * @param leftChild
   * @param rightChild
   */
  BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
    this.key = key;
    this.value = value;
    this.left = leftChild;
    this.right = rightChild;
    this.height = 0;
    this.balanceFactor = 0;
  }

  /**
   * constructor for a node with no children
   * 
   * @param key
   * @param value
   */
  BSTNode(K key, V value) {
    this(key, value, null, null);
  }

  /**
   * assigns a node's height after calling a method to compute the height
   */
  void setHeight() {
    height = computeHeight(this);
  }

  /**
   * recursive helper method for setHeight() that computes the height of the [sub]tree of a node
   * 
   * @param current -- node to compute the height for
   * @return height of node
   */
  int computeHeight(BSTNode current) {
    if (current == null) {
      return 0;
    }
    // recursively compute height of left and right subtrees
    int leftHeight = computeHeight(current.left);
    int rightHeight = computeHeight(current.right);

    // height = height of the larger subtree + 1 for root node
    return 1 + Math.max(leftHeight, rightHeight);
  }

  /**
   * assigns a node's balance factor after calling a method to compute the balance factor
   */
  void setBalanceFactor() {
    balanceFactor = computeBalanceFactor(this);
  }

  /**
   * helper method that calculates the balance factor for a specific node
   * 
   * @param node to compute the balance factor for
   * @return balance factor of the node
   */
  int computeBalanceFactor(BSTNode node) {
    if (node.left == null && node.right == null) { // no children
      return 0;
    } else if (node.left != null && node.right == null) { // 1 child (left)
      return node.left.height;
    } else if (node.left == null && node.right != null) { // 1 child (right)
      return 0 - node.right.height;
    } else { // two children
      return node.left.height - node.right.height;
    }
  }

}
