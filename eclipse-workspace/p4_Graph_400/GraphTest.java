//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P4 Package Manager
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Due Date: 11/14/19
// Description: Graph class tests
// Known Bugs: None
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * class that tests the proper function of all the methods of the Graph class
 * 
 * @author chrisgeorge
 *
 */
public class GraphTest {
  /**
   * Graph object to run tests on
   */
  static Graph g;

  /**
   * initializes the graph for each test
   */
  @BeforeEach
  public void setUp() {
    g = new Graph();
  }

  /**
   * deletes the graph used during the test, so the next test will re create the graph
   */
  @AfterEach
  public void tearDown() {
    g = null;
  }

  /**
   * tests the addVertex function by adding valid and invalid vertices and checking graph order
   */
  @Test
  public void Test01_addVertex() {
    g.addVertex(null);
    if (g.order() != 0) {
      fail("adding null improperly added a vertex");
    }
    g.addVertex("A");
    if (g.order() != 1) {
      fail("failed to add a vertex");
    }
    g.addVertex("A"); // duplicate
    if (g.order() != 1) {
      fail("failed to ignore a duplicate add ");
    }
    // try adding multiple vertices
    g.addVertex("B");
    g.addVertex("C");
    g.addVertex("D");
    g.addVertex("E");
    if (g.order() != 5) {
      fail("failed to update order after multiple adds");
    }
  }

  /**
   * tests removeVertex by adding and removing vertices and checking graph order
   */
  @Test
  public void Test02_removeVertex() {
    g.addVertex("A");
    g.removeVertex(null);
    if (g.order() != 1) {
      fail("did not ignore attempt to delete null node");
    }
    g.removeVertex("B"); // non existent
    if (g.order() != 1) {
      fail("did not ignore attempt to delete nonexistent node");
    }
    g.removeVertex("A");
    if (g.order() != 0) {
      fail("failed to delete 1 node");
    }
    // try adding multiple vertices, removing a few of them
    g.addVertex("A");
    g.addVertex("B");
    g.addVertex("C");
    g.addVertex("D");
    g.removeVertex("C");
    g.removeVertex("D");
    if (g.order() != 2) {
      fail("failed to delete 2 nodes");
    }
  }

  /**
   * tests addEdge() for existing vertices and new vertices by checking size and order afterwards
   */
  @Test
  public void Test03_addEdge() {
    // 2 new vertices
    g.addEdge("a", "b");
    if (g.order() != 2 && g.size() != 1) {
      fail("failed to correctly create an edge between 2 new vertices");
    }
    // 1 new vertex
    g.addVertex("c");
    g.addEdge("c", "d");
    if (g.order() != 4 && g.size() != 2) {
      fail("failed to correctly create an edge between 1 new and 1 existing vertex");
    }
    // 2 existing vertices
    g.addEdge("a", "c");
    if (g.order() != 4 && g.size() != 3) {
      fail("failed to correctly create an edge between 2 existing vertices");
    }
  }

  /**
   * tests removeEdge() by adding then removing edges, and checking size and order afterwards
   */
  @Test
  public void Test04_removeEdge() {
    // make a graph with some edges to remove later
    g.addEdge("a", "b");
    g.addEdge("c", "d");
    g.addEdge("a", "c");
    g.addEdge("b", "d");

    // 1 or 2 non existent vertex
    g.removeEdge("z", "b");
    g.removeEdge("y", "j");
    if (g.size() != 4) {
      fail("failed to ignore attempt to remove non-existent vertices");
    }
    // remove 1 edge
    g.removeEdge("a", "b");
    if (g.size() != 3) {
      fail("failed to remove an existing vertex");
    }
    // remove several edges
    g.removeEdge("c", "d");
    g.removeEdge("a", "c");
    g.removeEdge("b", "d");
    if (g.size() != 0) {
      fail("failed to remove multiple existing vertices");
    }
    if (g.order() != 4) {
      fail("removeEdge should not remove vertices");
    }
  }

  /**
   * tests getAllVertices() for empty graph and a graph with nodes
   */
  @Test
  public void Test05_getAllVertices() {
    ArrayList<String> vertices = new ArrayList<String>();
    // empty graph should output empty set
    ArrayList<String> empty = new ArrayList(Arrays.asList());
    vertices.addAll(g.getAllVertices());
    if (!vertices.equals(empty)) {
      fail("empty graph should produce an empty vertex list");
    }

    // graph with nodes should output the nodes
    g.addVertex("a");
    g.addVertex("b");
    g.addVertex("c");
    g.addVertex("d");
    ArrayList<String> correct = new ArrayList(Arrays.asList("a", "b", "c", "d"));
    vertices.addAll(g.getAllVertices());
    if (!vertices.equals(correct)) {
      fail("graph with some nodes did not correctly produce vertex list");
    }
  }

  /**
   * tests getAdjacentVerticesOf() for a node with no adjacent vertices and a node with adjacent
   * vertices
   */
  @Test
  public void Test06_getAdjacentVertices() {
    g.addEdge("a", "b");
    g.addEdge("a", "c");
    g.addEdge("a", "d");
    g.addVertex("e"); // no adj. vertices
    ArrayList<String> adj = new ArrayList<String>();

    // node with no adj. vertices should output empty list
    ArrayList<String> empty = new ArrayList(Arrays.asList());
    adj = (ArrayList<String>) g.getAdjacentVerticesOf("e");
    if (!adj.equals(empty)) {
      fail("a vertex with no adjacent vertices should yield an empty list of adjacent vertices");
    }
    // node with adj. vertices should output all of them
    ArrayList<String> correct = new ArrayList(Arrays.asList("b", "c", "d"));
    adj = (ArrayList<String>) g.getAdjacentVerticesOf("a");
    if (!adj.equals(correct)) {
      fail("a vertex with several adjacent vertices should yield list of all adjacent vertices");
    }
  }

}
