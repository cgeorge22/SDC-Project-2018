//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P4 Package Manager
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Due Date: 11/14/19
// Description: PackageManager class tests
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
 * class that tests the proper function of all the methods of the PackageManager class
 * 
 * @author chrisgeorge
 *
 */
public class PackageManagerTest {

  /**
   * packageManager object on which tests will be run
   */
  static PackageManager p;

  /**
   * .json file to parse into graph
   */
  public static final String VALID_FILE = "valid.json";

  /**
   * initializes the packageManager for each test
   */
  @BeforeEach
  public void setUp() {
    p = new PackageManager();
    try {
      p.constructGraph(VALID_FILE);
    } catch (Exception e) {
    }
  }

  /**
   * deletes the packageManager used during the test, so the next test will re create the
   * packageManager
   */
  @AfterEach
  public void tearDown() {
    p = null;
  }

  /**
   * tests that getAllPackages() works properly by checking the set created from a valid graph
   */
  @Test
  public void Test01_getAllPackages() {
    ArrayList<String> correct = new ArrayList(Arrays.asList("A", "B", "C", "D", "E"));
    ArrayList<String> allPackages = new ArrayList<String>();
    allPackages.addAll(p.getAllPackages());
    for (int i = 0; i < correct.size(); i++) {
      if (!correct.get(i).equals(allPackages.get(i))) {
        fail("all packages list not properly returned for a valid graph");
      }
    }

  }

  /**
   * tests that getInstallationOrder(pkg) works properly by verifying some installation orders on a
   * valid graph
   * 
   * @throws PackageNotFoundException
   * @throws CycleException
   */
  @Test
  public void Test02_getInstallationOrder() throws CycleException, PackageNotFoundException {
    ArrayList<String> forB = new ArrayList(Arrays.asList("C", "D", "B"));
    ArrayList<String> forD = new ArrayList(Arrays.asList("D"));
    ArrayList<String> forE = new ArrayList(Arrays.asList("C", "D", "B", "E"));

    ArrayList<String> testB = (ArrayList<String>) p.getInstallationOrder("B");
    if (!forB.equals(testB)) {
      fail("installation order incorrect");
    }
    ArrayList<String> testD = (ArrayList<String>) p.getInstallationOrder("D");
    if (!forD.equals(testD)) {
      fail("installation order incorrect");
    }
    ArrayList<String> testE = (ArrayList<String>) p.getInstallationOrder("E");
    if (!forE.equals(testE)) {
      fail("installation order incorrect");
    }
  }

  /**
   * tests that toInstall() works properly for a few nodes
   * 
   * @throws PackageNotFoundException
   * @throws CycleException
   */
  @Test
  public void Test03_toInstall() throws CycleException, PackageNotFoundException {
    ArrayList<String> toInstall = new ArrayList<String>();
    toInstall = (ArrayList<String>) p.toInstall("B", "C");
    ArrayList<String> correct = new ArrayList(Arrays.asList("D"));
    if (!toInstall.equals(correct)) {
      fail("toInstall not computed properly");
    }
  }

  /**
   * tests getInstallationOrderForAllPackages() given a valid graph
   * 
   * @throws CycleException
   */
  @Test
  public void Test04_getInstallationOrderForAll() throws CycleException {
    ArrayList<String> correct = new ArrayList(Arrays.asList("C", "D", "B", "A", "E"));
    ArrayList<String> test = (ArrayList<String>) p.getInstallationOrderForAllPackages();
    if (!test.equals(correct)) {
      fail("correct installation Order not outputted");
    }
  }

  /**
   * checks if getPackageWithMaxDependencies() works with valid graph
   * 
   * @throws CycleException
   */
  @Test
  public void Test05_getMaxDependencies() throws CycleException {
    String max = "E";
    if (!p.getPackageWithMaxDependencies().equals(max)) {
      fail("maxDependencyPackage not found correctly");
    }
  }

}
