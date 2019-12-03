//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P3 Hash Table
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Description: Hash Table tests-- tests the correctness of the functons of the
// hashTable implementation
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test HashTable class implementation to ensure that required functionality works for all cases.
 */
public class BookHashTableTest {

  // Default name of books data file
  public static final String BOOKS = "books.csv";

  // Empty hash tables that can be used by tests
  static BookHashTable bookObject;
  static ArrayList<Book> bookTable;

  static final int INIT_CAPACITY = 2;
  static final double LOAD_FACTOR_THRESHOLD = 0.49;

  static Random RNG = new Random(0); // seeded to make results repeatable (deterministic)

  /** Create a large array of keys and matching values for use in any test */
  @BeforeAll
  public static void beforeClass() throws Exception {
    bookTable = BookParser.parse(BOOKS);
  }

  /** Initialize empty hash table to be used in each test */
  @BeforeEach
  public void setUp() throws Exception {
    // TODO: change HashTable for final solution
    bookObject = new BookHashTable(INIT_CAPACITY, LOAD_FACTOR_THRESHOLD);
  }

  /** Not much to do, just make sure that variables are reset */
  @AfterEach
  public void tearDown() throws Exception {
    bookObject = null;
  }

  private void insertMany(ArrayList<Book> bookTable)
      throws IllegalNullKeyException, DuplicateKeyException {
    for (int i = 0; i < bookTable.size(); i++) {
      bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
    }
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon initialization
   */
  @Test
  public void test000_collision_scheme() {
    if (bookObject == null)
      fail("Gg");
    int scheme = bookObject.getCollisionResolutionScheme();
    if (scheme < 1 || scheme > 9)
      fail("collision resolution must be indicated with 1-9");
  }


  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon initialization
   */
  @Test
  public void test000_IsEmpty() {
    // "size with 0 entries:"
    assertEquals(0, bookObject.numKeys());
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is not empty after adding one (key,book)
   * pair
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  public void test001_IsNotEmpty() throws IllegalNullKeyException, DuplicateKeyException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    String expected = "" + 1;
    // "size with one entry:"
    assertEquals(expected, "" + bookObject.numKeys());
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Test if the hash table will be resized after adding two
   * (key,book) pairs given the load factor is 0.49 and initial capacity to be 2.
   */
  @Test
  public void test002_Resize() throws IllegalNullKeyException, DuplicateKeyException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    int cap1 = bookObject.getCapacity();
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    int cap2 = bookObject.getCapacity();

    // "size with one entry:"
    assertTrue(cap2 > cap1 & cap1 == 2);
  }

  /**
   * checks to see if multiple insertions are properly stored
   * 
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   * @throws DuplicateKeyException
   */
  @Test
  public void test003_get_Multiple_Books()
      throws IllegalNullKeyException, KeyNotFoundException, DuplicateKeyException {
    insertMany(bookTable);
    for (int i = 0; i < 25; i++) {
      // bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
      if (!bookObject.get(bookTable.get(i).getKey()).equals(bookTable.get(i))) {
        fail("Get did not return proper value");
      }
    }
  }

  /**
   * inserts multiple books, then removes them all, checks if numkeys = 0
   * 
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   * @throws DuplicateKeyException
   */
  @Test
  public void test004_remove_Multiple_Books()
      throws IllegalNullKeyException, DuplicateKeyException {
    for (int i = 0; i < 25; i++) { // add the books
      bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
    }
    for (int i = 0; i < 25; i++) { // remove them
      bookObject.remove(bookTable.get(i).getKey());
    }
    if (bookObject.numKeys() != 0) {
      fail("after removing all, numkeys should be 0");
    }
  }

  /**
   * checks that numKeys is correctly updated upon successful insert
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  public void test005_insert_check_num_keys()
      throws IllegalNullKeyException, DuplicateKeyException {
    for (int i = 0; i < 25; i++) { // add the books
      bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
    }
    if (bookObject.numKeys() != 25) {
      fail("after adding 25 books, numkeys should be 25");
    }
  }

  /**
   * checks to make sure that both exceptions for insert() are thrown properly
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  public void test006_check_Insert_Exceptions()
      throws DuplicateKeyException, IllegalNullKeyException {
    Book testBook = bookTable.get(0);
    try { // test null key
      bookObject.insert(null, testBook);
      fail("should have thrown IllegalNullKeyException"); // should not be reached
    } catch (IllegalNullKeyException e) {
    }
    try { // test dup. key
      bookObject.insert(testBook.getKey(), testBook);
      bookObject.insert(testBook.getKey(), testBook); // duplicate
      fail("should have thrown DuplicateKeyException"); // should not be reached
    } catch (DuplicateKeyException e) {
    }
  }

  /**
   * checks if remove() properly throws exceptions for null key
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  public void test007_check_remove_Exceptions()
      throws DuplicateKeyException, IllegalNullKeyException {
    try {
      bookObject.remove(null); // null key
      fail("should have thrown IllegalNullKeyException"); // should not be reached
    } catch (IllegalNullKeyException e) {

    }
  }

  /**
   * checks that get() throws proper exceptions
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test008_check_get_exceptions()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    try { // test null key
      bookObject.get(null);
      fail("should have thrown IllegalNullKeyException"); // should not be reached
    } catch (IllegalNullKeyException e) {
    }
    try { // test nonexistent key
      bookObject.get(bookTable.get(25).getKey());
      fail("should have thrown KeyNotFoundException"); // should not be reached
    } catch (KeyNotFoundException e) {
    }
  }

}
