//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P2 BALST
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Description: AVL Balanced Search Tree tests-- test cases that evaluate the
// the proper function of the implemented BALST
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * several test cases to evaluate the proper functioning of BALST.java as an implementatio of a
 * balanced search tree in AVL form
 * 
 */
public class BALSTTest {

  BALST<String, String> balst1;
  BALST<Integer, String> balst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    balst1 = createInstance();
    balst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    balst1 = null;
    balst2 = null;
  }

  protected BALST<String, String> createInstance() {
    return new BALST<String, String>();
  }

  protected BALST<Integer, String> createInstance2() {
    return new BALST<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testBALST_001_insert_sorted_order_simple() {
    try {
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      balst2.insert(30, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testBALST_002_insert_reversed_sorted_order_simple() {
    try {
      balst2.insert(30, "30");
      if (!balst2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfLeftChildOf(30).equals(20))
        fail("avl insert to left child of root does not work");

      balst2.insert(10, "10");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_003_insert_smallest_largest_middle_order_simple() {
    try {
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      balst2.insert(30, "30");
      if (!balst2.getKeyOfRightChildOf(10).equals(30))
        fail("avl insert to right child of root does not work");

      balst2.insert(20, "20");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_004_insert_largest_smallest_middle_order_simple() {
    try {
      balst2.insert(30, "30");
      if (!balst2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      balst2.insert(10, "10");
      if (!balst2.getKeyOfLeftChildOf(30).equals(10))
        fail("avl insert to left child of root does not work");

      balst2.insert(20, "20");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * checks that numKeys updates correctly upon several inserts and removes
   */
  @Test
  void testBALST_005_check_NumKeys_update() {
    try {
      // inserts-- numKeys should go up 1 every time
      balst2.insert(10, "10");
      if (balst2.numKeys() != 1) {
        fail("avl numkeys did not update correctly for insert 1 root node");
      }
      balst2.insert(20, "20");
      if (balst2.numKeys() != 2) {
        fail("avl numkeys did not update correctly for insert 2nd node");
      }
      balst2.insert(30, "30");
      if (balst2.numKeys() != 3) {
        fail("avl numkeys did not update correctly for insert 3rd node");
      }
      balst2.insert(50, "50");
      if (balst2.numKeys() != 4) {
        fail("avl numkeys did not update correctly for insert 4th node");
      }
      balst2.insert(40, "40");
      if (balst2.numKeys() != 5) {
        fail("avl numkeys did not update correctly for insert 5th node");
      }

      // remove keys -- should decrement numKeys by 1 each time
      balst2.remove(30);
      if (balst2.numKeys() != 4) {
        fail("avl numkeys did not upadate correctly for remove node ");
      }
      balst2.remove(20);
      if (balst2.numKeys() != 3) {
        fail("avl numkeys did not upadate correctly for remove node ");
      }
      balst2.remove(10);
      if (balst2.numKeys() != 2) {
        fail("avl numkeys did not upadate correctly for remove node ");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * insert several keys in a random order, check if the ending BST is balanced properly
   */
  @Test
  void testBALST_006_check_rebalancing_for_large_tree() {
    try {
      // insert nodes randomly
      balst2.insert(10, "10");
      balst2.insert(50, "50");
      balst2.insert(60, "60");
      balst2.insert(20, "20");
      balst2.insert(30, "30");
      balst2.insert(90, "90");
      balst2.insert(40, "40");
      balst2.insert(70, "70");

      // check if each node is in the right place
      if (!balst2.getKeyAtRoot().equals(50)) {
        fail("the tree was not balanced correctly after several inserts.");
      }
      if (!balst2.getKeyOfLeftChildOf(50).equals(20)) {
        fail("the tree was not balanced correctly after several inserts.");
      }
      if (!balst2.getKeyOfRightChildOf(50).equals(70)) {
        fail("the tree was not balanced correctly after several inserts.");
      }
      if (!balst2.getKeyOfLeftChildOf(20).equals(10)) {
        fail("the tree was not balanced correctly after several inserts.");
      }
      if (!balst2.getKeyOfRightChildOf(20).equals(30)) {
        fail("the tree was not balanced correctly after several inserts.");
      }
      if (!balst2.getKeyOfRightChildOf(30).equals(40)) {
        fail("the tree was not balanced correctly after several inserts.");
      }
      if (!balst2.getKeyOfLeftChildOf(70).equals(60)) {
        fail("the tree was not balanced correctly after several inserts.");
      }
      if (!balst2.getKeyOfRightChildOf(70).equals(90)) {
        fail("the tree was not balanced correctly after several inserts.");
      }

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * check that the height of a BST is correct after some inserts
   */
  @Test
  void testBALST_007_check_height() {
    try {
      balst2.insert(10, "10");
      if (balst2.getHeight() != 1) {
        fail("height was not updated properly upon insertion");
      }
      balst2.insert(20, "20");
      if (balst2.getHeight() != 2) {
        fail("height was not updated properly upon insertion");
      }
      balst2.insert(50, "50");
      if (balst2.getHeight() != 2) {
        fail("height was not updated properly upon insertion");
      }
      balst2.insert(30, "30");
      if (balst2.getHeight() != 3) {
        fail("height was not updated properly upon insertion");
      }
      balst2.insert(15, "15");
      if (balst2.getHeight() != 3) {
        fail("height was not updated properly upon insertion");
      }
      balst2.insert(40, "40");
      if (balst2.getHeight() != 3) {
        fail("height was not updated properly upon insertion");
      }
      balst2.insert(60, "60");
      if (balst2.getHeight() != 4) {
        fail("height was not updated properly upon insertion");
      }
      balst2.insert(5, "5");
      if (balst2.getHeight() != 4) {
        fail("height was not updated properly upon insertion");
      }
      balst2.insert(90, "90");
      if (balst2.getHeight() != 4) {
        fail("height was not updated properly upon insertion");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * check that each traversal method returns the proper order of keys
   */
  @Test
  void testBALST_008_checkTraversalOrders() {
    // define the correct order for each traversal for an insert order of (1,2,3,4,5)
    ArrayList<Integer> inOrderCorrect = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    ArrayList<Integer> preOrderCorrect = new ArrayList<>(List.of(2, 1, 4, 3, 5));
    ArrayList<Integer> postOrderCorrect = new ArrayList<>(List.of(1, 3, 5, 4, 2));
    ArrayList<Integer> levelOrderCorrect = new ArrayList<>(List.of(2, 1, 4, 3, 5));

    try {
      balst2.insert(1, "1");
      balst2.insert(2, "2");
      balst2.insert(3, "3");
      balst2.insert(4, "4");
      balst2.insert(5, "5");

      // check traversal methods with correct lists
      if (!balst2.getInOrderTraversal().equals(inOrderCorrect)) {
        fail("InOrder traversal not properly executed");
      }
      if (!balst2.getPreOrderTraversal().equals(preOrderCorrect)) {
        fail("preOrder traversal not properly executed");
      }
      if (!balst2.getPostOrderTraversal().equals(postOrderCorrect)) {
        fail("postOrder traversal not properly executed");
      }
      if (!balst2.getLevelOrderTraversal().equals(levelOrderCorrect)) {
        fail("levelOrder traversal not properly executed");
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }

  /**
   * check that get() returns the right values after several node inserts
   */
  @Test
  void testBALST_009_check_get_after_several_inserts() {
    try {
      // insert some nodes
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(30, "30");
      balst2.insert(40, "40");
      balst2.insert(50, "50");

      // check if each node's correct value is still returned
      if (!balst2.get(10).equals("10")) {
        fail("correct value not returned for key 10");
      }
      if (!balst2.get(20).equals("20")) {
        fail("correct value not returned for key 20");
      }
      if (!balst2.get(30).equals("30")) {
        fail("correct value not returned for key 30");
      }
      if (!balst2.get(40).equals("40")) {
        fail("correct value not returned for key 40");
      }
      if (!balst2.get(50).equals("50")) {
        fail("correct value not returned for key 50");
      }

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * check that insert() works properly (height, numKeys) after inserting items, deleting them all
   */
  @Test
  void testBALST_010_check_add_after_deleting_all() {
    try {
      // add some nodes
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(30, "30");
      balst2.insert(40, "40");
      balst2.insert(50, "50");

      // delete all of the nodes
      balst2.remove(10);
      balst2.remove(20);
      balst2.remove(30);
      balst2.remove(40);
      balst2.remove(50);

      // add some nodes back
      balst2.insert(10, "10");
      balst2.insert(20, "20");

      // check to see if the BST height and numKeys updated properly
      if (balst2.numKeys() != 2) {
        fail("numKeys was incorrect after adding 5 nodes, deleting all of them, adding 2 more");
      }
      if (balst2.getHeight() != 2) {
        fail("height was incorrect after adding 5 nodes, deleting all of them, adding 2 more");
      }

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

}
