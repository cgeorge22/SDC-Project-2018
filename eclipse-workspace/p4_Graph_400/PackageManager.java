//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P4 Package Manager
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Due Date: 11/14/19
// Description: PackageManager Class
// Known Bugs: could not get cycle detection to work within the time frame.
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename: PackageManager.java Project: p4 Authors: Chris George
 * 
 * PackageManager is used to process json package dependency files and provide function that make
 * that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json file.
 * 
 * Package dependencies are important when building software, as you must install packages in an
 * order such that each package is installed after all of the packages that it depends on have been
 * installed.
 * 
 * For example: package A depends upon package B, then package B must be installed before package A.
 * 
 * This program will read package information and provide information about the packages that must
 * be installed before any given package can be installed. all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test classes.
 */
public class PackageManager {

  /**
   * main graph of all the packages and dependencies
   */
  private Graph graph;
  /**
   * reversal of the main graph, each package points to its predecessor packages
   */
  private Graph backwardGraph;

  /**
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    graph = new Graph();
    backwardGraph = new Graph();
  }

  /**
   * given a certain package, detect if the graph has a cycle for that node
   * 
   * @param pkg to detect cycle for
   * @return true if cycle found, false otherwise
   */
  private boolean detectCycle(String pkg) {
    ArrayList<String> visited = new ArrayList<String>();

    return true;
  }

  /**
   * Takes in a file path for a json file and builds the package dependency graph from it.
   * 
   * @param jsonFilepath the name of json data file with package dependency information
   * @throws FileNotFoundException if file path is incorrect
   * @throws IOException if the give file cannot be read
   * @throws ParseException if the given json cannot be parsed
   */
  public void constructGraph(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException {
    // parse the file that is passed in as the argument
    Object obj = new JSONParser().parse(new FileReader(jsonFilepath));

    // typecasting obj to JSONObject
    JSONObject jo = (JSONObject) obj;
    JSONArray packages = (JSONArray) jo.get("packages");

    for (int i = 0; i < packages.size(); i++) { // iterate through packages array
      JSONObject pkg = (JSONObject) packages.get(i); // cast each package to JSONOBject
      String packageName = (String) pkg.get("name");
      graph.addVertex(packageName); // new vertex for each package
      backwardGraph.addVertex(packageName);
      JSONArray pkgDependencies = (JSONArray) pkg.get("dependencies");
      for (int j = 0; j < pkgDependencies.size(); j++) {
        String dependency = (String) pkgDependencies.get(j); // cast each dependency to string
        graph.addEdge(dependency, packageName); // draw edge out from dependency
        backwardGraph.addEdge(packageName, dependency);
      }

    }
  }

  /**
   * Helper method to get all packages in the graph.
   * 
   * @return Set<String> of all the packages
   */
  public Set<String> getAllPackages() {
    return graph.getAllVertices();
  }

  /**
   * Given a package name, returns a list of packages in a valid installation order.
   * 
   * Valid installation order means that each package is listed before any packages that depend upon
   * that package.
   * 
   * @return List<String>, order in which the packages have to be installed
   * 
   * @throws CycleException if you encounter a cycle in the graph while finding the installation
   *         order for a particular package. Tip: Cycles in some other part of the graph that do not
   *         affect the installation order for the specified package, should not throw this
   *         exception.
   * 
   * @throws PackageNotFoundException if the package passed does not exist in the dependency graph.
   */
  public List<String> getInstallationOrder(String pkg)
      throws CycleException, PackageNotFoundException {
    ArrayList<String> v = new ArrayList<String>();
    v.addAll(backwardGraph.getAllVertices());
    if (!graph.getAllVertices().contains(pkg)) {
      throw new PackageNotFoundException();
    }
    // topological sort on backwardsGraph starting with pkg
    Stack<String> st = new Stack<String>();
    String c = "";
    ArrayList<String> visited = new ArrayList<String>();
    ArrayList<String> installOrder = new ArrayList<String>();
    // instead of all nodes with no predecessors, only add pkg
    visited.add(pkg);
    st.push(pkg);
    while (!st.isEmpty()) {
      c = st.peek();
      ArrayList<String> successors = new ArrayList<String>();
      successors.addAll(backwardGraph.getAdjacentVerticesOf(c)); // list of immediate successors
      boolean allVisited = true;
      for (int i = 0; i < successors.size(); i++) { // check if all successors have been visited
        if (!visited.contains(successors.get(i))) {
          allVisited = false;
        }
      }
      if (allVisited) { // when all successors of c are visited
        c = st.pop();
        installOrder.add(c);
      } else {
        boolean visitedSuccessor = true;
        int j = 0; // index counter
        int uvSuccessor = -1;
        while (visitedSuccessor) { // iterate until unvisited successor found
          if (!visited.contains(successors.get(j))) { // unvisited successor found
            uvSuccessor = j; // record index of unvisited successor
            visitedSuccessor = false; // exit loop
          }
          j++;
        }
        visited.add(successors.get(uvSuccessor)); // mark successor as visited
        st.push(successors.get(uvSuccessor));
      }
    }
    return installOrder;
  }

  /**
   * Given two packages - one to be installed and the other installed, return a List of the packages
   * that need to be newly installed.
   * 
   * For example, refer to shared_dependecies.json - toInstall("A","B") If package A needs to be
   * installed and packageB is already installed, return the list ["A", "C"] since D will have been
   * installed when B was previously installed.
   * 
   * @return List<String>, packages that need to be newly installed.
   * 
   * @throws CycleException if you encounter a cycle in the graph while finding the dependencies of
   *         the given packages. If there is a cycle in some other part of the graph that doesn't
   *         affect the parsing of these dependencies, cycle exception should not be thrown.
   * 
   * @throws PackageNotFoundException if any of the packages passed do not exist in the dependency
   *         graph.
   */
  public List<String> toInstall(String newPkg, String installedPkg)
      throws CycleException, PackageNotFoundException {
    ArrayList<String> alreadyInstalled;
    ArrayList<String> installForNew;

    if (!graph.getAllVertices().contains(newPkg)
        || !graph.getAllVertices().contains(installedPkg)) {
      throw new PackageNotFoundException();
    }

    // lists of dependencies for each package
    alreadyInstalled = (ArrayList<String>) getInstallationOrder(installedPkg);
    installForNew = (ArrayList<String>) getInstallationOrder(newPkg);

    // remove the already installed packages from the to-install list for the new package
    for (int i = 0; i < alreadyInstalled.size(); i++) {
      installForNew.remove(alreadyInstalled.get(i));
    }

    return installForNew;
  }

  /**
   * Return a valid global installation order of all the packages in the dependency graph.
   * 
   * assumes: no package has been installed and you are required to install all the packages
   * 
   * returns a valid installation order that will not violate any dependencies
   * 
   * @return List<String>, order in which all the packages have to be installed
   * @throws CycleException if you encounter a cycle in the graph
   */
  public List<String> getInstallationOrderForAllPackages() throws CycleException {
    int num = graph.order(); // number of vertices
    String c = "";
    Stack<String> st = new Stack<String>();
    String[] installOrder = new String[num + 1]; // array to create the order in
    ArrayList<String> orderList = new ArrayList<String>(); // arrayList to return
    ArrayList<String> visited = new ArrayList<String>(); // keep track of visited nodes
    ArrayList<String> allNodes = new ArrayList<String>();
    allNodes.addAll(graph.getAllVertices());

    for (int i = 0; i < allNodes.size(); i++) { // analyze all nodes in graph
      String curr = allNodes.get(i); // evaluate the package at the current node
      boolean noPredecessors = true;
      // search every node's adjacency list for the current node
      for (int j = 0; j < allNodes.size(); j++) {
        if (graph.getAdjacentVerticesOf(allNodes.get(j)).contains(curr)) {
          noPredecessors = false; // if node is found in any adjacency list, it has a predecessor
        }
      }
      if (noPredecessors) { // for each node with no predecessors
        visited.add(curr); // mark as visited
        st.push(curr);
      }
    }
    while (!st.isEmpty()) {
      c = st.peek();
      ArrayList<String> successors = new ArrayList<String>();
      successors.addAll(graph.getAdjacentVerticesOf(c)); // list of immediate successors
      boolean allVisited = true;
      for (int i = 0; i < successors.size(); i++) { // check if all successors have been visited
        if (!visited.contains(successors.get(i))) {
          allVisited = false;
        }
      }
      if (allVisited) { // when all successors of c are visited
        c = st.pop();
        installOrder[num] = c; // put c in the last available spot in the array
        num--; // decrement num for next-latest spot
      } else {
        boolean visitedSuccessor = true;
        int j = 0; // index counter
        int uvSuccessor = -1;
        while (visitedSuccessor) { // iterate until unvisited successor found
          if (!visited.contains(successors.get(j))) { // unvisited successor found
            uvSuccessor = j; // record index of unvisited successor
            visitedSuccessor = false; // exit loop
          }
          j++;
        }
        visited.add(successors.get(uvSuccessor)); // mark successor as visited
        st.push(successors.get(uvSuccessor));
      }
    }
    // convert the array to arrayList for return
    for (int k = 1; k < installOrder.length; k++) { // 0 index is empty, so ignore it
      orderList.add(installOrder[k]);
    }
    return orderList;
  }

  /**
   * Find and return the name of the package with the maximum number of dependencies.
   * 
   * Tip: it's not just the number of dependencies given in the json file. The number of
   * dependencies includes the dependencies of its dependencies. But, if a package is listed in
   * multiple places, it is only counted once.
   * 
   * Example: if A depends on B and C, and B depends on C, and C depends on D. Then, A has 3
   * dependencies - B,C and D.
   * 
   * @return String, name of the package with most dependencies.
   * @throws CycleException if you encounter a cycle in the graph
   */
  public String getPackageWithMaxDependencies() throws CycleException {
    ArrayList<String> vertices = new ArrayList<>();
    vertices.addAll(graph.getAllVertices());
    // Initialize variables to keep track of information
    int max = 0;
    int numDependencies = -1;
    String name = "";
    // calculate dependencies for every package
    for (int i = 0; i < vertices.size(); i++) {
      try {
        numDependencies = getInstallationOrder(vertices.get(i)).size();
        if (numDependencies > max) {
          max = numDependencies; // update max dependencies
          name = vertices.get(i); // package with max dependencies
        }
      } catch (PackageNotFoundException e) { // should never throw exception; all vertices in graph
      }
    }
    return name;
  }


  /**
   * main method-- not used
   * 
   * @param args command line arguments-- unused
   */
  public static void main(String[] args) {
    System.out.println("PackageManager.main()");
  }

}
