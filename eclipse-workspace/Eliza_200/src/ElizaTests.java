//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: ElizaTests
// Files: Eliza.java, Config.java
// Course: CS 200 Fall 2018
//
// Author: Chris George
// Email: crgeorge@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: name of your pair programming partner
// Partner Email: email address of your programming partner
// Lecturer's Name: name of your partner's lecturer
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza class as they are developed.
 * These methods are private since they are only intended for use within this class.
 * 
 * @author Jim Williams
 * @author Chris George
 *
 */
public class ElizaTests {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1: Process User Input
        // M1: main nothing to do
        testSeparatePhrases();
        testFoundQuitWord();
        testSelectPhrase();
        testReplaceWord();
        testAssemblePhrase();

        // Milestone 2:
        // M2: implement parts of main as described in main method comments
        testSwapWords();
        testPrepareInput();
        testLoadResponseTable();

        // Milestone 3:
        // main: implement the rest of main as described in the main method comments
        testFindKeyWordsInPhrase();
        testSelectResponse();
        testInputAndResponse();
        testSaveDialog();
    }

    /**
     * This runs some tests on the separatePhrases method. For all these cases, tests if ArrayList
     * size is correct and contents are correct 1. Tests a "normal" input to be split into multiple
     * parts 2. Long input, only two parts, single quote used 3. All caps input, one word 4. no
     * changes required 5. removes question mark, one part 6. multiple parts, multiple ending
     * characters
     */
    private static void testSeparatePhrases() {
        boolean error = false;

        // 1.
        ArrayList<String> phrases = Eliza.separatePhrases("No.  I'm talking  to my dog! Bye.");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("no");
        expected.add("i'm talking to my dog");
        expected.add("bye");

        if (phrases.size() != expected.size()) {
            error = true;
            System.out.println("testSeparatePhrases 1: expected " + expected.size() + " but found "
                + phrases.size() + " phrases.");
        }

        // 2.
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(phrases.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases 1: phrases not the same");
                System.out.println("  " + expected.get(i));
                System.out.println("  " + phrases.get(i));
            }
        }

        // Additional test suggestions
        // "What? This isn't the 4th messy-sentence!" should result in ?
        // tests the suggested input string in separatePhrases
        ArrayList<String> phrases2 =
            Eliza.separatePhrases("What? This isn't the 4th messy-sentence!");
        ArrayList<String> expected2 = new ArrayList<>();
        // add expected results to the expected2 ArrayList
        expected2.add("what");
        expected2.add("this isn't the 4th messy sentence");

        // block executes if separatePhrases output has unexpected size
        if (phrases2.size() != expected2.size()) {
            error = true;
            // notifies programmer of unexpected size difference
            System.out.println("testSeparatePhrases 2: expected " + expected2.size() + " but found "
                + phrases2.size() + " phrases.");
        }

        // iterates through elements of expected arraylist
        for (int i = 0; i < expected2.size(); i++) {
            // executes if any element of expected arraylist does not match the
            // result of separatePhrases
            if (!expected2.get(i).equals(phrases2.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases 2: phrases not the same");
                System.out.println("  " + expected2.get(i));
                System.out.println("  " + phrases2.get(i));
            }
        }
        // "NO" should result in?
        // tests the suggested input string in separatePhrases
        ArrayList<String> phrases3 = Eliza.separatePhrases("NO");
        ArrayList<String> expected3 = new ArrayList<>();
        // add expected results to the expected3 ArrayList
        expected3.add("no");

        // block executes if separatePhrases output has unexpected size
        if (phrases3.size() != expected3.size()) {
            // notifies programmer of unexpected size difference
            error = true;
            System.out.println("testSeparatePhrases 3: expected " + expected3.size() + " but found "
                + phrases3.size() + " phrases.");
        }

        // iterates through elements of expected arraylist
        for (int i = 0; i < expected3.size(); i++) {
            // executes if any element of expected arraylist does not match the
            // result of separatePhrases
            if (!expected3.get(i).equals(phrases3.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases 3: phrases not the same");
                System.out.println("  " + expected3.get(i));
                System.out.println("  " + phrases3.get(i));
            }
        }
        // "this tab" should result in?
        // tests the suggested input string in separatePhrases
        ArrayList<String> phrases4 = Eliza.separatePhrases("this tab");
        ArrayList<String> expected4 = new ArrayList<>();
        // add expected results to the expected4 ArrayList
        expected4.add("this tab");

        // block executes if separatePhrases output has unexpected size
        if (phrases4.size() != expected4.size()) {
            // notifies programmer of unexpected size difference
            error = true;
            System.out.println("testSeparatePhrases 4: expected " + expected4.size() + " but found "
                + phrases4.size() + " phrases.");
        }

        // iterates through elements of expected arraylist
        for (int i = 0; i < expected4.size(); i++) {
            // executes if any element of expected arraylist does not match the
            // result of separatePhrases
            if (!expected4.get(i).equals(phrases4.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases 4: phrases not the same");
                System.out.println("  " + expected4.get(i));
                System.out.println("  " + phrases4.get(i));
            }
        }

        // "What?" should result in?
        // tests the suggested input string in separatePhrases
        ArrayList<String> phrases5 = Eliza.separatePhrases("What?");
        ArrayList<String> expected5 = new ArrayList<>();
        // add expected results to the expected5 ArrayList
        expected5.add("what");

        // block executes if separatePhrases output has unexpected size
        if (phrases5.size() != expected5.size()) {
            // notifies programmer of unexpected size difference
            error = true;
            System.out.println("testSeparatePhrases 5: expected " + expected5.size() + " but found "
                + phrases5.size() + " phrases.");
        }

        // iterates through elements of expected arraylist
        for (int i = 0; i < expected5.size(); i++) {
            // executes if any element of expected arraylist does not match the
            // result of separatePhrases
            if (!expected5.get(i).equals(phrases5.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases 5: phrases not the same");
                System.out.println("  " + expected5.get(i));
                System.out.println("  " + phrases5.get(i));
            }
        }
        // "Thank you, but no, I have to go. Goodbye!!!" should result in?
        // tests the suggested input string in separatePhrases
        ArrayList<String> phrases6 =
            Eliza.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
        ArrayList<String> expected6 = new ArrayList<>();
        // add expected results to the expected6 ArrayList
        expected6.add("thank you");
        expected6.add("but no");
        expected6.add("i have to go");
        expected6.add("goodbye");

        // block executes if separatePhrases output has unexpected size
        if (phrases6.size() != expected6.size()) {
            // notifies programmer of unexpected size difference
            error = true;
            System.out.println("testSeparatePhrases 6: expected " + expected6.size() + " but found "
                + phrases6.size() + " phrases.");
        }

        // iterates through elements of expected arraylist
        for (int i = 0; i < expected6.size(); i++) {
            // executes if any element of expected arraylist does not match the
            // result of separatePhrases
            if (!expected6.get(i).equals(phrases6.get(i))) {
                error = true;
                System.out.println("testSeparatePhrases 6: phrases not the same");
                System.out.println("  " + expected6.get(i));
                System.out.println("  " + phrases6.get(i));
            }
        }

        if (error) {
            System.out.println("testSeparatePhrases failed");
        } else {
            System.out.println("testSeparatePhrases passed");
        }
    }

    /**
     * This runs some tests on the foundQuitWord method. 1. "normal" quit action: a phrase in the
     * arraylist is a quit word 2. quit word is part of a phrase in the arrayList 3. the only phrase
     * is a quit word 4. no quit words
     */
    // should QUIT_WORDS be embedded within foundQuitWord? no
    private static void testFoundQuitWord() {
        boolean error = false;

        // 1.
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("thank you for talking");
        phrases.add("bye");

        boolean quit = Eliza.foundQuitWord(phrases);
        if (!quit) {
            error = true;
            System.out.println("testFoundQuitWord 1: failed");
        }

        // additional test suggestions:
        // 2. should foundQuitWord return true if bye is a part of a phrase rather than
        // separate? no
        ArrayList<String> phrases2 = new ArrayList<>();
        // add phrases to ArrayList for testing
        phrases2.add("thank you for talking");
        phrases2.add("bye for now"); // "bye" does not stand alone

        quit = Eliza.foundQuitWord(phrases2);
        // quit should not be true because 'bye' is part of a phrase
        if (quit) {
            error = true;
            System.out.println("testFoundQuitWord 2: failed");
        }

        // 3. should foundQuitWord return true if goodbye is the first or only phrase? yes
        ArrayList<String> phrases3 = new ArrayList<>();
        // the only phrase is a quit word
        phrases3.add("goodbye");

        quit = Eliza.foundQuitWord(phrases3);
        // quit should be true, because quit word is found
        if (!quit) {
            error = true;
            System.out.println("testFoundQuitWord 3: failed");
        }

        // 4. should foundQuitWord return true if there are no quit words in the phrases
        // list. no
        ArrayList<String> phrases4 = new ArrayList<>();
        // no quit words added to arrayList
        phrases4.add("thank you for talking");
        phrases4.add("hello");
        phrases4.add("xxxxx");

        quit = Eliza.foundQuitWord(phrases4);
        // quit should not be true, because no quit words found
        if (quit) {
            error = true;
            System.out.println("testFoundQuitWord 3: failed");
        }

        if (error) {
            System.err.println("testFoundQuitWord failed");
        } else {
            System.out.println("testFoundQuitWord passed");
        }
    }

    /**
     * This runs some tests on the selectPhrase method. 1. tests "normal" scenario-- picks longest
     * phrase 2. same length phrases: tests to see if phrase chosen is the lower index 3. tests if
     * return "" if list is empty
     */
    private static void testSelectPhrase() {
        boolean error = false;

        // 1 choose the longest
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("no");
        phrases.add("sometimes I remember being on a boat");
        phrases.add("not often");
        String choice = Eliza.selectPhrase(phrases);
        if (!choice.equals("sometimes I remember being on a boat")) {
            error = true;
            System.out.println("testSelectPhrase 1: failed");
        }

        // additional test suggestions:
        // 2 What should happen when there are 2 choices of the same length?
        ArrayList<String> phrases2 = new ArrayList<>();
        // two choices of the same length are added
        phrases2.add("no");
        phrases2.add("to");
        choice = Eliza.selectPhrase(phrases2);
        // executes if selectPhrase incorrectly chooses the higher index
        if (!choice.equals("no")) {
            error = true;
            System.out.println("testSelectPhrase 2: failed");
        }
        // 3 What should happen if an empty list is passed to selectPhrase?
        ArrayList<String> phrases3 = new ArrayList<>(); // no elements in arrayList
        choice = Eliza.selectPhrase(phrases3);
        // executes if selectPhrase does not return an empty string
        if (!choice.equals("")) {
            error = true;
            System.out.println("testSelectPhrase 3: failed");
        }

        if (error) {
            System.err.println("testSelectPhrase failed");
        } else {
            System.out.println("testSelectPhrase passed");
        }
    }

    /**
     * This runs some tests on the assemblePhrase method. 1. passes in a list of phrases with spaces
     * inside them 2. passes in an empty phrase list 3. passes in a list of phrases with no spaces
     * inside them
     */
    private static void testAssemblePhrase() {
        boolean error = false;

        // 1.
        String[] words = {"This", "is a", "sentence"};
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "This is a sentence";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
        }

        // additional test suggestions:
        // 2. what should happen if an array with no strings in it is passed in?
        String[] words2 = {};
        String sentence2 = Eliza.assemblePhrase(words2);
        String expectedSentence2 = ""; // empty string should come out of assemblePhrase

        // executes if assemblePhrase does not return an empty string
        if (!sentence2.equals(expectedSentence2)) {
            error = true;
            System.out.println("testAssembleSentence 2 failed '" + sentence2 + "'");
        }
        // 3. what should happen if just a list of words, with no internal spaces, is
        // passed in?
        String[] words3 = {"This", "is", "sentence"}; // array with no internal spaces
        String sentence3 = Eliza.assemblePhrase(words3);
        // assemblePhrase should properly concatenate the words with spaces
        String expectedSentence3 = "This is sentence";

        // executes if assemblePhrase does not return the expected sentence
        if (!sentence3.equals(expectedSentence3)) {
            error = true;
            System.out.println("testAssembleSentence 3 failed '" + sentence3 + "'");
        }

        if (error) {
            System.err.println("testAssemblePhrase failed");
        } else {
            System.out.println("testAssemblePhrase passed");
        }
    }

    /**
     * This runs some tests on the findKeyWordsInPhrase method. 1. tests 1 keyword, where the
     * keyword is the last word in the phrase 2. 1 keyword, where the keyword is the first word in
     * the phrase 3. 1 keyword, where the keyword is in the middle in the phrase 4. 2 keywords,
     * separated by a non-keyword 5. keywords not found in the correct order, returning null 6. 2
     * keywords, both adjacent to each other in the beginning of phrase
     */
    private static void testFindKeyWordsInPhrase() {
        boolean error = false;

        {// block so each test has its own variable scope.
         // 1.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"are", "you", "a", "computer"};

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("are you a")
                || !matches[1].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 1 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 2.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"computer", "are", "you"};

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("")
                || !matches[1].equals("are you")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 2 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 3.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"does", "that", "computer", "on", "your", "desk", "work"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("does that")
                || !matches[1].equals("on your desk work")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 3 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 4.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] phrase = {"why", "don't", "you", "like", "me"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 3 || !matches[0].equals("why don't")
                || !matches[1].equals("like") || !matches[2].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 4 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 5.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] sentence = {"me", "don't", "like", "you"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 5 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        // additional tests?
        {
            // 6. tests 2 keywords adjacent to each other in the beginning of the array
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] sentence = {"you", "me", "like", "you"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            // executes if the result of findKeyWordsInPhrase is null, not length 3, or
            // does not contain the proper elements
            if (matches == null || matches.length != 3 || !matches[0].equals("")
                || !matches[1].equals("") || !matches[2].equals("like you")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 6 failed.");
                // prints out the incorrect result of findKeyWordsInPhrase
                System.out.println(Arrays.toString(matches));
            }
        }

        if (error) {
            System.err.println("testFindKeyWordsInPhrase failed");
        } else {
            System.out.println("testFindKeyWordsInPhrase passed");
        }
    }

    /**
     * This runs some tests on the saveDialog method. 1. checks if a one line file is properly
     * written 2. checks if a multiple line file is properly written
     */
    private static void testSaveDialog() {
        boolean error = false;
        final String TEST_FILENAME = "testing.txt";
        { // 1.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            try {
                Eliza.saveDialog(list, TEST_FILENAME);
                String readFromFile = readFile(TEST_FILENAME);
                if (!readFromFile.equals(list.get(0) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile(TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        { // 2. test multiple lines of output written to the file.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a line.");
            list.add("this is another line.");
            try {
                Eliza.saveDialog(list, TEST_FILENAME);
                String readFromFile = readFile(TEST_FILENAME);
                String expected = list.get(0) + "\n" + list.get(1) + "\n";
                if (!(expected.equals(readFromFile))) {
                    error = true;
                    System.out.println("testSaveDialog 2 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile(TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        // additional tests?

        if (error) {
            System.err.println("testSaveDialog failed");
        } else {
            System.out.println("testSaveDialog passed");
        }
    }

    /**
     * Supporting method for testSaveDialog
     * 
     * @param fileName name of the file to read
     * @return the contents of the file
     */
    private static String readFile(String fileName) {
        StringBuffer buf = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }
            return buf.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Supporting method for testSaveDialog.
     * 
     * @param filename file to be removed.
     */
    private static void removeFile(String filename) {
        File file = new File(filename);
        try {
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This runs some tests on the replaceWord method. 1. tests replaceWord using input_word_map 2.
     * tests replaceWord using pronoun_map 3. tests that replaceWord returns the original word if
     * the word map is empty
     */
    private static void testReplaceWord() {
        boolean error = false;

        { // 1.
            String word = "machine";
            String expected = "computer";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // 2.
            String word = "yourself";
            String expected = "myself";
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        // additional suggestions:
        // Do these tests meet all the requirements described in the replaceWord header comment,
        // such as handling a null wordMap?
        {
            // 3. // tests handling a null wordMap
            String[][] emptyMap = null;
            String word = "x";
            // replaceWord should just return the original word if wordMap is null
            String expected = "x";
            String result = Eliza.replaceWord(word, emptyMap);
            // executes if the replaceWord returns anything other than the original word
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 3 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        if (error) {
            System.err.println("testReplaceWord failed");
        } else {
            System.out.println("testReplaceWord passed");
        }
    }

    /**
     * This runs some tests on the swapWords method. 1. switches all words, with Input_word_map, in
     * 3 words, out 4 words 2. test 1 word to switch, with pronoun_map; in: 2 words, out 3 words 3.
     * switches 1 word, with pronoun_map; in: 3, out 3 4. no words switched
     */
    private static void testSwapWords() {
        boolean error = false;

        { // 1.
            String someWords = "i'm cant recollect";
            String expected = "i am cannot remember";
            String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 2.
            String someWords = "i'm happy";
            String expected = "you are happy";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 3.
            String someWords = "about my dog";
            String expected = "about your dog";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 4. tests when no words should be swapped
            String someWords = "abc def ghi";
            String expected = "abc def ghi"; // same as original; no swaps
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            // executes if swapWords results in something other than the expected output
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 4 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }


        if (error) {
            System.err.println("testSwapWords failed");
        } else {
            System.out.println("testSwapWords passed");
        }
    }

    /**
     * This runs some tests on the selectResponse method. 1. calls selectResponse, fails if none of
     * the entries in strList are returned 2. sets seed, calls selectResponse 1000 times to test if
     * the the random generator is used properly 3. when only one string is in the provided list,
     * that string returns every time 4. null list -> null return
     */
    private static void testSelectResponse() {
        boolean error = false;

        { // 1.
          // is one of the 3 strings chosen?
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");
            strList.add("happy");
            strList.add("cat");
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
                error = true;
                System.out.println("testSelectResponse 1 failed.");
            }
        }

        { // 2.
          // if called 1000 times, are the choices approximately random?
            Random randGen = new Random(765);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("this");
            strList.add("is");
            strList.add("a");
            strList.add("list");
            strList.add("to");
            strList.add("choose");
            strList.add("from");
            int[] actualCount = new int[strList.size()];
            int[] expectedCount = new int[] {156, 146, 142, 138, 160, 130, 128};
            for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
                String choice = Eliza.selectResponse(randGen, strList);
                for (int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
                    if (choice.equals(strList.get(wordIndex))) {
                        actualCount[wordIndex]++;
                    }
                }
            }
            // since we set a seed on the random number generator we should know the expected
            // outcome
            for (int countIndex = 0; countIndex < actualCount.length; countIndex++) {
                if (actualCount[countIndex] != expectedCount[countIndex]) {
                    error = true;
                    System.out.println("testSelectResponse 2 failed.");
                    System.out.println("  expectedCount: " + Arrays.toString(expectedCount));
                    System.out.println("  actualCount: " + Arrays.toString(actualCount));
                }
            }

        }

        // additional test suggestions:
        // What should happen when a list a single string is provided?
        { // 3.
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The"); // a single string in the arrayList
            String choice = Eliza.selectResponse(randGen, strList);

            // executes if the result of selectResponse is not the only given string
            if (!(choice.equals("The"))) {
                error = true;
                System.out.println("testSelectResponse 3 failed.");
            }
        }
        // What should happen when null is passed to selectResponse?
        { // 4.
            Random randGen = new Random(434);
            ArrayList<String> strList = null;
            String choice = Eliza.selectResponse(randGen, strList);

            // executes of selectResponse does not return null
            if (choice != null) {
                error = true;
                System.out.println("testSelectResponse 4 failed.");
            }
        }


        if (error) {
            System.err.println("testSelectResponse failed");
        } else {
            System.out.println("testSelectResponse passed");
        }
    }

    /**
     * This runs some tests on the prepareInput method. 1. tests quit word -> null 2. splits a
     * sample phrase into individual words 3. splits sample phrase, swaps one word also
     */
    private static void testPrepareInput() {
        boolean error = false;

        { // 1.
            String input = "bye";
            String[] result = null;
            result = Eliza.prepareInput("bye");
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 1: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }

        // additional test suggestions
        // "I said goodbye" should result in "i", "said", "goodbye"
        { // 2.
            String input = "I said goodbye";
            // prepareInput should separate input into individual words
            String[] result = {"i", "said", "goodbye"};
            String[] res = Eliza.prepareInput("I said goodbye");
            // block executes if prepareInput does not return the expected result
            if (!Arrays.equals(res, result)) {
                ;
                error = true;
                System.out.println("testPrepareInput 2: failed");
                // notifies programmer of the input and incorrect result
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
        // "I can't do that" should result in "i", "cannot", "do", "that"
        { // 3.
            String input = "I can't do that";
            // prepare input swaps words when necessary, separates words to array
            String[] result = {"i", "cannot", "do", "that"};
            String[] res = Eliza.prepareInput("I can't do that");
            // block executes if prepareInput does not return the expected result
            if (!Arrays.equals(res, result)) {
                ;
                error = true;
                // notifies programmer of the input and incorrect result
                System.out.println("testPrepareInput 3: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }

        if (error) {
            System.err.println("testPrepareInput failed");
        } else {
            System.out.println("testPrepareInput passed");
        }
    }

    /**
     * This runs some tests on the loadResponseTable method. 1. tests if the 1st row of the
     * responseTable arrayList is correctly read 2. tests if the number of rows in responseTable is
     * even (should always be even because keyword/response pairs added
     */
    private static void testLoadResponseTable() {
        boolean error = false;

        { // 1.
            ArrayList<String> expected1stRow = new ArrayList<String>();
            expected1stRow.add("computer");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (!table.get(0).equals(expected1stRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " + table.get(0));
            }

            // 2.
            if (table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println(
                    "  expected an even number of elements in table but found: " + table.size());
            }
        }

        // additional test suggestions
        // Are the right number of keywords and responses read in for a keyword/response pair?
        // Are blank lines (containing no spaces or only whitespace) ignored?
        // Are the last rows of the file read in?
        // Are the right number of rows read in?

        if (error) {
            System.err.println("testLoadResponseTable failed");
        } else {
            System.out.println("testLoadResponseTable passed");
        }
    }

    /**
     * Supporting method for testInputAndResponse. Returns 1 if the test passed and 0 if the test
     * failed.
     */
    private static int checkResponse(String input, String expectedResponse, Random rand,
        ArrayList<ArrayList<String>> table) {

        String[] words = Eliza.prepareInput(input);
        if (words == null) {
            if (expectedResponse == null) {
                return 1;
            } else {
                System.out.println("testInputLines  checkResponse error");
                System.out.println("  input='" + input + "'");
                System.out.println("  response=null");
                System.out.println("  expected='" + expectedResponse + "'");
                return 0;
            }
        }

        String response = Eliza.prepareResponse(words, rand, table);
        if (!response.equals(expectedResponse)) {
            System.out.println("testPrepareResponse  checkResponse error");
            System.out.println("  input='" + input + "'");
            System.out.println("  response='" + response + "'");
            System.out.println("  expected='" + expectedResponse + "'");
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Runs tests on a bunch of example sentences using a Random number generator with a seed.
     */
    private static void testInputAndResponse() {
        int numPassed = 0;
        // since we are using a seed in the random number generator, the responses should be fixed
        // for the provided response table.
        Random randGen = new Random(923);
        ArrayList<ArrayList<String>> responseTable =
            Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

        numPassed += checkResponse("I like computers.",
            "What do you think machines have to do with your problem?", randGen, responseTable);
        numPassed += checkResponse("What is your name?",
            "I don't care about names -- please continue.", randGen, responseTable);
        numPassed += checkResponse("You remind me of a grumpy uncle.",
            "What other connections do you see?", randGen, responseTable);
        numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?",
            randGen, responseTable);
        numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?",
            randGen, responseTable);
        numPassed += checkResponse("I wish to believe you.",
            "Why do you think you wish to believe me?", randGen, responseTable);
        numPassed += checkResponse("I dreamed of going into space.",
            "Have you ever dreamed of going into space before?", randGen, responseTable);
        numPassed += checkResponse("I apologize for boring you.",
            "Why do you think you apologize for boring me?", randGen, responseTable);
        numPassed += checkResponse("Because it seems the polite thing to do.",
            "Does that reason seem to explain anything else?", randGen, responseTable);
        numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
        numPassed += checkResponse("I remember being in 1st grade.",
            "What is the connection between me and being in 1st grade?", randGen, responseTable);
        numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.",
            "Why do you recollect being on a boat just now?", randGen, responseTable);
        numPassed += checkResponse("My family liked to play cards.",
            "Tell me more about your family.", randGen, responseTable);
        numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen,
            responseTable);
        numPassed += checkResponse("Are you a Packers fan?",
            "Perhaps I am a packers fan in your fantasies.", randGen, responseTable);
        numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?",
            randGen, responseTable);
        numPassed += checkResponse("I cannot explain this.",
            "Do you really want to be able to explain this?", randGen, responseTable);
        numPassed += checkResponse("You seem to have a different perspective than many.",
            "You're not really talking about me -- are you?", randGen, responseTable);
        numPassed += checkResponse("I'm talking to my dog.",
            "How long have you been talking to your dog?", randGen, responseTable);
        numPassed += checkResponse("goodbye", null, randGen, responseTable);
        numPassed +=
            checkResponse("", "I'm not sure I understand you fully.", randGen, responseTable);
        int expected = 21;
        if (numPassed == expected) {
            System.out.println("testInputAndResponse passed ");
        } else {
            System.err.println("testInputAndResponse failed " + (expected - numPassed));
        }
    }

}
