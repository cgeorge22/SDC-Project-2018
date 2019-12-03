//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Project Name: P3 Hash Table
// Name: Chris George
// Email: crgeorge@wisc.edu
// Lecture Number: 001
// Description: Hash Table implementation-- stores Books in a Hash Table data
// structure
//
//
// identify your scheme as open addressing or bucket : I am using a bucket
// scheme, with arrayList of Key,Value pairs as the buckets
//
// explain your hashing algorithm here: I split the first 13 digits of the isbn
// string into 4 parts, weighted each part, then recombined into an int, then
// modded with table size to make an index. In the case where the isbn string
// was not the correct length, I weighted the characters individually
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;

/**
 * HashTable implementation that stores information of several books that uses:
 * 
 * @param <String> the book's key, an ISBN value for hashing
 * @param <Book> Book reference to store in the table
 */
public class BookHashTable implements HashTableADT<String, Book> {

  /**
   * Inner class to pair up a book and its key. The hashTable's arrayLists will store these pair
   * objects
   * 
   * @author chrisgeorge
   *
   */
  private class KeyBookPair {
    private Book book;
    private String key;

    /**
     * constructor: creates KeyBookPair object given the key and book
     * 
     * @param key - the book's ISBN used for hashing
     * @param book - Book object associated with key
     */
    public KeyBookPair(String key, Book book) {
      this.key = key;
      this.book = book;
    }

    /**
     * @return the key of the pair
     */
    public String getKey() {
      return key;
    }

    /**
     * @return the book reference of the pair
     */
    public Book getBook() {
      return book;
    }
  }

  /** The initial capacity that is used if none is specified user */
  static final int DEFAULT_CAPACITY = 101;

  /** The load factor that is used if none is specified by user */
  static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;

  private int tableSize;
  private int numKeys = 0;
  private ArrayList<KeyBookPair>[] bookTable;
  private double loadFactorThreshold;
  private double loadFactor;

  /**
   * REQUIRED default no-arg constructor Uses default capacity and sets load factor threshold for
   * the newly created hash table.
   */
  public BookHashTable() {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_THRESHOLD);
  }

  /**
   * Creates an empty hash table with the specified capacity and load factor.
   * 
   * @param initialCapacity number of elements table should hold at start.
   * @param loadFactorThreshold the ratio of items/capacity that causes table to resize and rehash
   */
  public BookHashTable(int initialCapacity, double loadFactorThreshold) {
    this.tableSize = initialCapacity;
    this.loadFactorThreshold = loadFactorThreshold;
    bookTable = (ArrayList<KeyBookPair>[]) new ArrayList[tableSize];
  }

  /**
   * generates a hash index for a given ISBN number as a string, by dividing it into parts then
   * weighting and folding the parts. if the ISBN is not the normal length, it weights it according
   * to the number of characters it has
   * 
   * @param isbn - string representation of the book's ISBN number
   * @return hash table index for the book
   */
  private int hashIndex(String isbn) {
    int index = 0;
    int part1, part2, part3, part4;

    if (isbn.length() == 15) { // if the length is normal (15 characters)
      part1 = Integer.parseInt(isbn.substring(0, 3));
      part2 = Integer.parseInt(isbn.substring(4, 6));
      part3 = Integer.parseInt(isbn.substring(7, 9));
      part4 = Integer.parseInt(isbn.substring(10, 12));
      // weighting and folding
      index += part1 * Math.pow(11, 0);
      index += part2 * Math.pow(11, 1);
      index += part3 * Math.pow(11, 2);
      index += part4 * Math.pow(11, 3);
    } else {
      for (int i = 0; i < isbn.length(); i++) { // access each character of the isbn string
        int ascii = isbn.charAt(i); // convert character to ascii value
        index += (ascii * Math.pow(31, i)); // weight based on place
      }
    }
    return index % tableSize; // need to fit in table, so mod necessary
  }

  /**
   * resizes the book hash table and reassigns each book to a new index based on the new size
   * 
   */
  private void rehash() {
    int newHashIndex;
    this.tableSize = this.tableSize * 2 + 1; // resize
    ArrayList<KeyBookPair>[] newTable = new ArrayList[tableSize];
    // traverse through the old table, rehashing every item
    for (int i = 0; i < bookTable.length; i++) { // traverse array
      if (bookTable[i] != null) {
        for (int j = 0; j < bookTable[i].size(); j++) { // traverse each ArrayList
          newHashIndex = hashIndex(bookTable[i].get(j).getKey());
          if (newTable[newHashIndex] == null) { // initialize arrayList in empty index
            newTable[newHashIndex] = new ArrayList<KeyBookPair>();
          }
          newTable[newHashIndex].add(bookTable[i].get(j));
        }
      }
    }
    bookTable = newTable; // point the hashTable to the newly created/filled table
    tableSize = bookTable.length;
  }

  /**
   * puts a Book into the hashTable, using its ISBN key value. if the loadFactorThreshold is
   * reached, rehash.
   *
   * @param key - String representation of ISBN
   * @param value - Book object reference
   * @throws IllegalNullKeyException if the ISBN value is null
   * @throws DuplicateKeyException if the book is already in the hashTable
   */
  @Override
  public void insert(String key, Book value) throws IllegalNullKeyException, DuplicateKeyException {
    int hashIndex;
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    KeyBookPair toInsert = new KeyBookPair(key, value); // create new pair object to store
    hashIndex = hashIndex(toInsert.getKey());
    if (bookTable[hashIndex] == null) { // initializes arrayList at index if empty
      bookTable[hashIndex] = new ArrayList<KeyBookPair>();
    }
    // search the bookTable at hashIndex to make sure the book is not already in it
    for (int i = 0; i < bookTable[hashIndex].size(); i++) {
      if (bookTable[hashIndex].get(i).getKey().equals(key)) {
        throw new DuplicateKeyException();
      }
    }
    bookTable[hashIndex].add(toInsert);
    numKeys++;
    double lf = ((double) (numKeys / tableSize));
    this.loadFactor = lf;
    if (this.loadFactor >= this.loadFactorThreshold) { // resize necessary
      rehash();
    }
  }

  /**
   * find and removes a book from the book table, given its key. decreases numKeys
   * 
   * @return true if the book is successfully removed, false if the book is not found
   * @throws IllegalNullKeyException If key is null
   */
  @Override
  public boolean remove(String key) throws IllegalNullKeyException {
    int hashIndex;
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    hashIndex = hashIndex(key); // find table index for the key
    if (bookTable[hashIndex] != null) {
      for (int i = 0; i < bookTable[hashIndex].size(); i++) { // traverse the arrayList at index
        if (bookTable[hashIndex].get(i).getKey().equals(key)) {
          bookTable[hashIndex].remove(i);
          numKeys--;
          return true;
        }
      }
    }
    return false; // only reached if the book is not found
  }

  /**
   * returns the book in the hash table, when given the key
   * 
   * @return the Book associated with the specified key
   * @throws IllegalNullKeyException If key is null
   * @throw KeyNotFoundException()If key is not found
   */
  @Override
  public Book get(String key) throws IllegalNullKeyException, KeyNotFoundException {
    int hashIndex;
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    hashIndex = hashIndex(key); // find table index for the key
    if (bookTable[hashIndex] != null) {
      for (int i = 0; i < bookTable[hashIndex].size(); i++) { // traverse the arrayList at index
        if (bookTable[hashIndex].get(i).getKey().equals(key)) {
          return bookTable[hashIndex].get(i).getBook();
        }
      }
    }
    throw new KeyNotFoundException(); // will not be reached unless book is not found
  }

  /**
   * Returns the number of key,value pairs in the data structure
   * 
   * @return number of keys
   */
  @Override
  public int numKeys() {
    return numKeys;
  }

  /**
   * Returns the load factor for this hash table that determines when to increase the capacity of
   * this hash table
   * 
   * @return the load factor threshold
   */
  @Override
  public double getLoadFactorThreshold() {
    return loadFactorThreshold;
  }

  /**
   * @return the table size
   */
  @Override
  public int getCapacity() {
    return tableSize;
  }

  /**
   * Returns the collision resolution scheme used for this hash table. Implement this ADT with one
   * of the following collision resolution strategies and implement this method to return an integer
   * to indicate which strategy.
   * 
   * @return 4 : array of arrayLists
   */
  @Override
  public int getCollisionResolutionScheme() {
    return 4;
  }



}
