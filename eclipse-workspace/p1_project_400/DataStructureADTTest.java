//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P1 Test ADT
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Description: several Abstract Data Structure tests
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author chrisgeorge
 * 
 *         several tests for a DataStructureADT
 *
 * @param <T> data type of data structure nodes
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }


  /**
   * checks size on an empty DataStructure
   */
  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }


  /**
   * checks size after inserting 1 element
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("one", "a");
    if (dataStructureInstance.size() != 1)
      fail("data structure should have size=1 after inserting one item, but size ="
          + dataStructureInstance.size());
  }

  /**
   * checks size after inserting and removing 1 node
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.remove("one");
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  /**
   * checks that duplicate insertion is not allowed
   */
  @Test
  void test03_duplicate_exception_is_thrown() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    dataStructureInstance.insert("three", "c");
    try {
      dataStructureInstance.insert("two", "b");
      fail("data structure failed to throw RuntimeException for duplicate insertion");
    } catch (RuntimeException e) {
    }
  }

  /**
   * checks that remove returns false if the key to remove is not found
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    if (dataStructureInstance.remove("three") != false) {
      fail("data structure remove method did not return false for non-existing element");
    }
  }

  /**
   * checks if inserting and removing 1 node returns true
   */
  @Test
  void test05_insert_one_and_remove() {
    dataStructureInstance.insert("one", "a");
    if (dataStructureInstance.remove("one") != true || dataStructureInstance.size() != 0)
      fail("data structure failed to correctly remove one item");

  }

  /**
   * checks if size() works properly after inserting and removing several items
   */
  @Test
  void test06_insert_many_items_remove_check_size() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    dataStructureInstance.insert("three", "c");
    dataStructureInstance.insert("four", "d");
    dataStructureInstance.insert("five", "e");
    dataStructureInstance.insert("six", "f");
    dataStructureInstance.remove("four");
    dataStructureInstance.remove("two");
    dataStructureInstance.remove("six");
    if (dataStructureInstance.size() != 3)
      fail("data structure did not successfully compute size upon several insertions and removals");
  }

  /**
   * tests if duplicates are not allowed to be added when far apart from each other
   */
  @Test
  void test07_far_away_duplicates() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    dataStructureInstance.insert("three", "c");
    dataStructureInstance.insert("four", "d");
    dataStructureInstance.insert("five", "e");
    dataStructureInstance.insert("six", "f");
    try {
      dataStructureInstance.insert("one", "a");
      fail("data structure failed to throw RuntimeException for duplicate insertion");
    } catch (RuntimeException e) {
    }
  }

  /**
   * checks if able to re-add a removed key
   */
  @Test
  void test08_re_add_key() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.remove("one");
    try {
      dataStructureInstance.insert("one", "a");
    } catch (RuntimeException e) {
      fail("data structure incorrectly rejected an insert for a non-duplicate key");
    }
  }

  /**
   * tries to add and remove 500 elements, checking size after each operation
   */
  @Test
  void test09_add_and_remove_500() {
    for (int i = 0; i < 500; i++) {
      dataStructureInstance.insert(Integer.toString(i), Integer.toString(i * 2));
    }
    if (dataStructureInstance.size() != 500) {
      fail("data structure could not add 500 elements");
    }
    for (int i = 0; i < 500; i++) {
      dataStructureInstance.remove(Integer.toString(i));
    }
    if (dataStructureInstance.size() != 0) {
      fail("data structure could not remove 500 elements");
    }
  }

  /**
   * tries to get() a nonexistent value
   */
  @Test
  void test10_get_nonexistent() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    if (dataStructureInstance.get("three") != null) {
      fail("data structure incorrectly returned non-null value when getting non-existent key");
    }
  }

  /**
   * tries to get() a null value
   */
  @Test
  void test11_get_null_key() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    try {
      dataStructureInstance.get(null);
      fail("data structure should have thrown IllegalArgumentException when getting null key");
    } catch (IllegalArgumentException e) {
    }
  }

  /**
   * tries to find an existing value
   */
  @Test
  void test12_contains_found() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    dataStructureInstance.insert("three", "c");
    if (dataStructureInstance.contains("one") != true)
      fail("data structure did not successfully find an existing value");
  }

  /**
   * tries to find an non-existent value
   */
  @Test
  void test13_contains_nonexistent() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    dataStructureInstance.insert("three", "c");
    if (dataStructureInstance.contains("four") != false)
      fail("data structure did not successfully return false upon not finding a nonexistent value");
  }

  /**
   * tries to search an empty data structure
   */
  @Test
  void test14_contains_for_empty_structure() {
    if (dataStructureInstance.contains("one"))
      fail("empty data structure incorrectly responded to contains()");
  }

  /**
   * checks size after several inserts and removals (back and forth)
   */
  @Test
  void test15_several_inserts_removes_check_size() {
    dataStructureInstance.insert("one", "a");
    dataStructureInstance.insert("two", "b");
    dataStructureInstance.insert("three", "c");
    dataStructureInstance.insert("four", "d");
    dataStructureInstance.insert("five", "e");
    dataStructureInstance.insert("six", "f");
    dataStructureInstance.remove("three");
    dataStructureInstance.remove("one");
    dataStructureInstance.remove("five");
    dataStructureInstance.insert("five", "e");
    dataStructureInstance.remove("six");
    dataStructureInstance.insert("six", "f");
    if (dataStructureInstance.size() != 4) {
      fail("upon several insertions/removals did not return correct size");
    }
  }

  /**
   * tries to remove() from an empty data structure
   */
  @Test
  void test16_remove_from_empty_list() {
    if (dataStructureInstance.remove("one") != false) {
      fail("removing from empty list should return false");
    }
  }



}
