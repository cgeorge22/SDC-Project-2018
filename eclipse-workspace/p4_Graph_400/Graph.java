//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P4 Package Manager
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Due Date: 11/14/19
// Description: Graph Class
// Known Bugs: None
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Filename: Graph.java Project: p4 Authors: Chris George
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
  /**
   * list of vertices in the graph
   */
  private ArrayList<String> vertexList;
  /**
   * list of adjacency lists, where index 0 of each list is the vertex that the rest depend on
   */
  private ArrayList<ArrayList<String>> adjList;

  /**
   * Default no-argument constructor
   */
  public Graph() {
    vertexList = new ArrayList<String>();
    adjList = new ArrayList<ArrayList<String>>();
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   * 
   * @param vertex the vertex to be added
   */
  public void addVertex(String vertex) {
    if (vertex == null) {
      return;
    }
    if (vertexList.contains(vertex)) {
      return;
    }
    vertexList.add(vertex);
    // create new adjacency list for the new vertex
    ArrayList<String> newVertexList = new ArrayList<String>();
    newVertexList.add(vertex);
    // add the newly created adjacency list to the list of adjacency lists
    adjList.add(newVertexList);
  }

  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   * 
   * @param vertex the vertex to be removed
   */
  public void removeVertex(String vertex) {
    if (vertex == null) {
      return;
    }
    if (!vertexList.contains(vertex)) {
      return;
    }

    vertexList.remove(vertex);
    // delete the whole adjacency list of vertex
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).get(0).equals(vertex)) {
        adjList.remove(i);
      }
    }
    for (int j = 0; j < adjList.size(); j++) {
      // remove vertex from adjacency lists of the other vertices
      adjList.get(j).remove(vertex);
    }


  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted)
   * 
   * If either vertex does not exist, VERTEX IS ADDED and then edge is created. No exception is
   * thrown.
   *
   * If the edge exists in the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge is not in the graph
   * 
   * @param vertex1 the first vertex (src)
   * @param vertex2 the second vertex (dst)
   */
  public void addEdge(String vertex1, String vertex2) {
    if (!vertexList.contains(vertex1)) {
      this.addVertex(vertex1);
    }
    if (!vertexList.contains(vertex2)) {
      this.addVertex(vertex2);
    }
    // find the adjacency list of vertex1, add vertex2 to it
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).get(0).equals(vertex1)) {
        adjList.get(i).add(vertex2);
      }
    }
  }

  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
   * 
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   */
  public void removeEdge(String vertex1, String vertex2) {
    if (!(vertexList.contains(vertex1)) || !(vertexList.contains(vertex2))) {
      return;
    }
    // find vertex1's adjacency list, remove vertex2 from it
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).get(0).equals(vertex1)) {
        adjList.get(i).remove(vertex2);
      }
    }
  }

  /**
   * Returns a Set that contains all the vertices
   * 
   * @return a Set<String> which contains all the vertices in the graph
   */
  public Set<String> getAllVertices() {
    Set<String> vertices = new HashSet<String>();

    // convert vertexList arrayList to a HashSet
    for (int i = 0; i < vertexList.size(); i++) {
      vertices.add(vertexList.get(i));
    }

    return vertices;
  }

  /**
   * Get all the neighbor (adjacent-dependencies) of a vertex
   * 
   * In terms of packages, this list contains the immediate dependencies of A and depending on your
   * graph structure, this could be either the predecessors or successors of A.
   * 
   * @param vertex the specified vertex
   * @return an List<String> of all the adjacent vertices for specified vertex
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    ArrayList<String> adjacent = new ArrayList<String>();

    // find the adjacency list of the vertex in the list of adjacency lists
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).get(0).equals(vertex)) { // adjacency list found
        adjacent = adjList.get(i);
        adjacent.remove(0); // remove the vertex, so the returned list is just the adjacent vertices
        return adjacent;
      }
    }
    return null; // only reached if the vertex's adjacency list is not found
  }

  /**
   * Returns the number of edges in this graph.
   * 
   * @return number of edges in the graph.
   */
  public int size() {
    int numEdges = 0;

    for (int i = 0; i < adjList.size(); i++) {
      numEdges += adjList.get(i).size() - 1; // subtract the signifier index from each adj. list
    }

    return numEdges;
  }

  /**
   * Returns the number of vertices in this graph.
   * 
   * @return number of vertices in graph.
   */
  public int order() {
    return vertexList.size();
  }
}
