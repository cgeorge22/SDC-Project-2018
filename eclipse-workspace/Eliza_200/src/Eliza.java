//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Eliza
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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that collects user input
 * and responds appropriately. Eliza is based off of a computer program written at MIT in the 1960's
 * by Joseph Weizenbaum. Eliza uses keyword matching to respond to users in a way that displays
 * interest in the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

    /**
     * This method does input and output with the user. It calls supporting methods to read and
     * write files and process each user input.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        String name = "";
        String userResponse = "";
        String compResponse = "";
        String fileName = "";
        String saveFileName = "";
        String therapistName = "Eliza"; // default therapist name
        char saveChar = 'n'; // default option is "no" for saving
        boolean foundQuit = false; // default -- no quit words
        boolean fileSaved = false; // default -- the file was not saved properly
        String[] preparedInput = null;
        ArrayList<ArrayList<String>> responseTable = null;
        ArrayList<String> dialog = new ArrayList<String>();
        // Milestone 2
        // create a scanner for reading user input and a random number
        // generator with Config.SEED as the seed
        Scanner sc = new Scanner(System.in);
        Random rand = new Random(Config.SEED);
        // Milestone 3
        // How the program starts depends on the command-line arguments.
        // Command-line arguments can be names of therapists for example:
        // Eliza Joe Laura
        // If no command-line arguments then the therapists name is Eliza
        // and this reads a file with that name and the Config.RESPONSE_FILE_EXTENSION.
        // Example filename: Eliza.rsp
        // If only one command-line argument, then read the responses from
        // the corresponding file with Config.RESPONSE_FILE_EXTENSION.
        // If there is more than one command-line argument then offer them
        // as a list of people to talk with. For the three therapists above the prompt is
        // "Would you like to speak with Eliza, Joe, or Laura?"
        // When a user types a name then read the responses from the file which
        // is the selected name and Config.RESPONSE_FILE_EXTENSION extension.
        // Whatever name the user types has the extension appended and
        // is read using loadResponseTable. If loadResponseTable can't load
        // the file then it will report an error.
        if (args.length == 1) {
            // the therapist's name is the only argument when there is one cmnd line argument
            therapistName = args[0];
        }
        // executes when the program is called with more than one argument
        else if (args.length > 1) {
            System.out.print("Would you like to speak with ");
            // loop prints all the therapist names in the specified format of the sentence
            for (int i = 0; i < args.length; i++) {
                if (i == args.length - 1) {
                    System.out.println("or " + args[i] + "?");
                } else {
                    System.out.print(args[i] + ", ");
                }
            }
            therapistName = sc.nextLine().trim(); // user decides therapist's name
        }
        // adds the extension to the therapists name to create the filename to look for
        fileName = therapistName + Config.RESPONSE_FILE_EXTENSION;
        // attempts to load up a response table with created filename
        responseTable = loadResponseTable(fileName);

        // Milestone 2
        // name prompt
        System.out.println("Hi I'm " + therapistName + ", what is your name?");
        // every "dialog.add()" statement adds the console output to an arraylist for possible
        // saving to text file later
        dialog.add("Hi I'm " + therapistName + ", what is your name?");
        name = sc.nextLine().trim();
        dialog.add(name);

        // Milestone 2
        // welcome prompt
        System.out.println("Nice to meet you " + name + ". Whats on your mind?");
        dialog.add("Nice to meet you " + name + ". Whats on your mind?");
        // Milestone 2
        // begin conversation loop
        while (!foundQuit) {
            // Milestone 2
            // obtain user input
            userResponse = sc.nextLine();
            dialog.add(userResponse);
            // Milestone 2
            // prepareInput
            preparedInput = prepareInput(userResponse);
            // Executes if quit word is found, which returns null in preparedInput
            if (preparedInput == null) {
                foundQuit = true;
                continue; // jumps to loop condition, which now breaks loop
            }
            // Milestone 3
            // if no quit words then prepareResponse
            // executes when the user doesn't enter quit word
            if (!foundQuit) {
                // responds to user input by printing output of prepareResponse
                compResponse = prepareResponse(preparedInput, rand, responseTable);
                System.out.println(compResponse);
                dialog.add(compResponse);
            }
            // Milestone 2
            // end loop if quit word
        }
        // Milestone 2
        // ending prompt
        System.out.println("Goodbye " + name + ".");
        dialog.add("Goodbye " + name + ".");

        // Milestone 3
        // Save all conversation (user and system responses) starting
        // with this program saying "Hi I'm..." and concludes with
        // "Goodbye <name>.".
        // Always prompt the user to see if they would like to save a
        // record of the conversation. If the user enters a y or Y as the
        // first non-whitespace character then prompt for filename and save,
        // otherwise don't save dialog. After successfully saving a dialog
        // print the message "Thanks again for talking! Our conversation is saved in: <filename>".
        // If saveDialog throws an IOException then catch it, print out the error:
        // "Unable to save conversation to: " <name of file>
        // Repeat the code prompting the user if they want to save the dialog.
        while (!fileSaved) { // block loops while file is not saved properly
            System.out.print("Would you like to save a record of our conversation (y/n): ");
            // only looks at first non whitespace character of user input
            saveChar = sc.next().trim().charAt(0);
            if (saveChar == 'Y' || saveChar == 'y') { // executes if user wants to save file
                System.out.print("Enter filename: ");
                saveFileName = sc.next();
                try {
                    saveDialog(dialog, saveFileName); // possible IOException here
                    fileSaved = true; // file is properly saved if this this line is reached
                    System.out.println(
                        "Thanks for talking! Our conversation is saved in: " + saveFileName);
                } catch (IOException expt) { // handles IOException thrown by saveDialog
                    System.out.println("Unable to save conversation to: " + saveFileName);
                }
            }
            // if the user enters string that starts with any other character than y or Y, assume
            // that the user does not want to save, so break out of the save file loop
            else {
                break;
            }
        }
    }

    /**
     * This method processes the user input, returning an ArrayList containing Strings, where each
     * String is a phrase from the user's input. This is done by removing leading and trailing
     * whitespace, making the user's input all lower case, then going through each character of the
     * user's input. When going through each character this keeps all digits, alphabetic characters
     * and ' (single quote). The characters ? ! , . signal the end of a phrase, and possibly the
     * beginning of the next phrase, but are not included in the result. All other characters such
     * as ( ) - " ] etc. should be replaced with a space. This method makes sure that every phrase
     * has some visible characters but no leading or trailing whitespace and only a single space
     * between words of a phrase. If userInput is null then return null, if no characters then
     * return a 0 length list, otherwise return a list of phrases. Empty phrases and phrases with
     * just invalid/whitespace characters should NOT be added to the list.
     * 
     * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i am", "a big fun
     * robot"
     * 
     * @param userInput text the user typed
     * @return the phrases from the user's input
     */
    public static ArrayList<String> separatePhrases(String userInput) {
        String inputSegment = ""; // empty string
        ArrayList<String> inputPhrases = new ArrayList<String>();

        // if the user enters nothing, this method returns nothing
        if (userInput == null) {
            return null;
        }

        // removes all leading and trailing whitespace in user input and converts to lower case
        userInput = userInput.trim();
        userInput = userInput.toLowerCase();

        // loop parses the user input text character by character
        for (int i = 0; i < userInput.length(); i++) {
            // what action to take for each character, depending on character
            switch (userInput.charAt(i)) {
                // all these characters end the current arrayList index entry
                case '?':
                case '!':
                case ',':
                case '.':
                    break;
                default:
                    // adds all letters, digits, and single quotes to the
                    // next arrayList index entry
                    if (Character.isLetterOrDigit(userInput.charAt(i))
                        || userInput.charAt(i) == '\'') {
                        inputSegment += userInput.charAt(i);
                    }
                    // every other non-whitespace character becomes a space in the
                    // arrayList index entry
                    else {
                        if (inputSegment != ""
                            && inputSegment.charAt(inputSegment.length() - 1) != ' ') {
                            inputSegment += ' ';
                        }
                    }
            }
            // executes block if the current character is a phrase ending character or
            // the last character
            if (userInput.charAt(i) == '?' || userInput.charAt(i) == '!'
                || userInput.charAt(i) == ',' || userInput.charAt(i) == '.'
                || i == userInput.length() - 1) {
                // block adds the current built phrase from the previous loop to the
                // array as long as it is not empty
                if (inputSegment != "" && inputSegment != " ") {
                    inputPhrases.add(inputSegment);
                    // reset the phrase string to empty to build the next phrase
                    inputSegment = "";
                }
            }
        }
        // print the method's return when debug mode activated
        if (Config.DEBUG) {
            System.out.println("DEBUG phrases: " + inputPhrases);
        }
        return inputPhrases;
    }

    /**
     * Checks whether any of the phrases in the parameter match a quit word from Config.QUIT_WORDS.
     * Note: complete phrases are matched, not individual words within a phrase.
     * 
     * @param phrases List of user phrases
     * @return true if any phrase matches a quit word, otherwise false
     */
    public static boolean foundQuitWord(ArrayList<String> phrases) {
        // loop searches the phrases ArrayList independently for each quit word
        for (int i = 0; i < Config.QUIT_WORDS.length; i++) {
            // if a quit word is found in the ArrayList, return true
            if (phrases.contains(Config.QUIT_WORDS[i])) {
                return true;
            }
        }
        // no quit words found if this line is reached
        return false;
    }

    /**
     * Iterates through the phrases of the user's input, finding the longest phrase to which to
     * respond. If two phrases are the same length, returns whichever has the lower index in the
     * list. If phrases parameter is null or size 0 then return "" (Update 11/15/18).
     * 
     * @param phrases List of user phrases
     * @return the selected phrase
     */
    public static String selectPhrase(ArrayList<String> phrases) {
        String longestPhrase = "";
        // if the arrayList passed in is null or empty, return an empty string
        if (phrases == null || phrases.size() == 0) {
            return "";
        } else {
            // iterates through entire phrases ArrayList, searching for longest phrase
            for (int i = 0; i < phrases.size(); i++) {
                if (phrases.get(i).length() > longestPhrase.length()) {
                    longestPhrase = phrases.get(i);
                }
            }
        }
        // print the method's return when debug mode activated
        if (Config.DEBUG) {
            System.out.println("DEBUG selected phrase: " + longestPhrase);
        }
        return longestPhrase;
    }

    /**
     * Looks for a replacement word for the word parameter and if found, returns the replacement
     * word. Otherwise if the word parameter is not found then the word parameter itself is
     * returned. The wordMap parameter contains rows of match and replacement strings. On a row, the
     * element at the 0 index is the word to match and if it matches return the string at index 1 in
     * the same row. Some example word maps that will be passed in are Config.INPUT_WORD_MAP and
     * Config.PRONOUN_MAP.
     * <p>
     * If word is null return null. If wordMap is null or wordMap length is 0 simply return word
     * parameter. For this implementation it is reasonable to assume that if wordMap length is >= 1
     * then the number of elements in each row is at least 2.
     * 
     * @param word The word to look for in the map
     * @param wordMap The map of words to look in
     * @return the replacement string if the word parameter is found in the wordMap otherwise the
     *         word parameter itself.
     */
    public static String replaceWord(String word, String[][] wordMap) {
        // executes if null passed in for word
        if (word == null) {
            return null;
        }
        // outputs word if wordMap is null or empty
        if (wordMap == null || wordMap.length == 0) {
            return word;
        } else {
            // loop iterates through every row of wordMap
            for (int i = 0; i < wordMap.length; i++) {
                // look for word in wordMap column 1
                if (word.equals(wordMap[i][0])) {
                    // changes word to the wordMap's pair for the matched word
                    word = wordMap[i][1];
                }
            }
            return word;
        }
    }

    /**
     * Concatenates the elements in words parameter into a string with a single space between each
     * array element. Does not change any of the strings in the words array. There are no leading or
     * trailing spaces in the returned string.
     * 
     * @param words a list of words
     * @return a string containing all the words with a space between each.
     */
    public static String assemblePhrase(String[] words) {
        String sentence = "";
        // executes if the words array is not null or empty
        if (words != null && words.length != 0) {
            // iterates through the entire words array
            for (int i = 0; i < words.length; i++) {
                // each entry in the words array is added to the originally empty sentence string,
                // along with a space after it
                sentence += words[i];
                sentence += " ";
            }
            // removes the last space in the sentence string, added by the loop
            sentence = sentence.trim();
        }
        return sentence;
    }

    /**
     * Replaces words in phrase parameter if matching words are found in the mapWord parameter. A
     * word at a time from phrase parameter is looked for in wordMap which may result in more than
     * one word. For example: i'm => i am Uses the replaceWord and assemblePhrase methods. Example
     * wordMaps are Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
     * parameter is returned. Note: there will Not be a case where a mapping will itself be a key to
     * another entry. In other words, only one pass through swapWords will ever be necessary.
     * 
     * @param phrase The given phrase which contains words to swap
     * @param wordMap Pairs of corresponding match & replacement words
     * @return The reassembled phrase
     */
    public static String swapWords(String phrase, String[][] wordMap) {
        String newPhrase = "";
        String[] phraseArray;
        // if the wordMap does not exist, return the original phrase
        if (wordMap == null) {
            return phrase;
        }
        // converts phrase string to an array, with one word in each index
        phraseArray = phrase.split(" ");
        // iterates through the phraseArray
        for (int i = 0; i < phraseArray.length; i++) {
            // converts each entry in the array to its replacement in the wordMap, if there is one
            phraseArray[i] = replaceWord(phraseArray[i], wordMap);
        }
        // converts the new phraseArray back to a string using assemblePhrase
        newPhrase = assemblePhrase(phraseArray);

        // print the method's return when debug mode activated
        if (Config.DEBUG) {
            System.out.println("DEBUG after input word swap: " + newPhrase);
        }
        return newPhrase;
    }

    /**
     * This prepares the user input. First, it separates input into phrases (using separatePhrases).
     * If a phrase is a quit word (foundQuitWord) then return null. Otherwise, select a phrase
     * (selectPhrase), swap input words (swapWords with Config.INPUT_WORD_MAP) and return an array
     * with each word its own element in the array.
     * 
     * @param input The input from the user
     * @return words from the selected phrase
     */
    public static String[] prepareInput(String input) {
        String selectedPhrase = "";
        String[] selectedArray;
        ArrayList<String> inputPhrases = new ArrayList<String>();
        // separates user input into an ArrayList using separatePhrases
        inputPhrases = separatePhrases(input);
        // exits method with null return if a quit word is found in the created ArrayList
        if (foundQuitWord(inputPhrases)) {
            return null;
        }
        // extracts the longest phrase in the ArrayList
        selectedPhrase = selectPhrase(inputPhrases);
        // swaps matching words in the longest phrase with corresponding entries in input word map
        selectedPhrase = swapWords(selectedPhrase, Config.INPUT_WORD_MAP);
        // separates the swapped longest phrase into an array with one word per index
        selectedArray = selectedPhrase.split(" ");

        return selectedArray;
    }

    /**
     * Reads a file that contains keywords and responses. A line contains either a list of keywords
     * or response, any blank lines are ignored. All leading and trailing whitespace on a line is
     * ignored. A keyword line begins with "keywords" with all the following tokens on the line, the
     * keywords. Each line that follows a keyword line that is not blank is a possible response for
     * the keywords. For example (the numbers are for our description purposes here and are not in
     * the file):
     * <p>
     * 1 keywords computer
     * <p>
     * 2 Do computers worry you?
     * <p>
     * 3 Why do you mention computers?
     * <p>
     * 4
     * <p>
     * 5 keywords i dreamed
     * <p>
     * 6 Really, <3>?
     * <p>
     * 7 Have you ever fantasized <3> while you were awake?
     * <p>
     * 8
     * <p>
     * 9 Have you ever dreamed <3> before?
     * <p>
     *
     * In line 1 is a single keyword "computer" followed by two possible responses on lines 2 and 3.
     * Line 4 and 8 are ignored since they are blank (contain only whitespace). Line 5 begins new
     * keywords that are the words "i" and "dreamed". This keyword list is followed by three
     * possible responses on lines 6, 7 and 9.
     * <p>
     * The keywords and associated responses are each stored in their own ArrayList. The response
     * table is an ArrayList of the keyword and responses lists. For every keywords list there is an
     * associated response list. They are added in pairs into the list that is returned. There will
     * always be an even number of items in the returned list.
     * <p>
     * Note that in the event an IOException occurs when trying to read the file then an error
     * message "Error reading <fileName>", where <fileName> is the parameter, is printed and a
     * non-null reference is returned, which may or may not have any elements in it.
     * 
     * @param fileName The name of the file to read
     * @return The response table
     */
    public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
        Scanner scnr = null;
        File inFile = null;
        String line = "";
        ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>();
        ArrayList<String> keywords = new ArrayList<String>();
        ArrayList<String> responses = new ArrayList<String>();
        String[] keywordsArray;
        try {
            inFile = new File(fileName); // IOException possibility
            scnr = new Scanner(inFile); // scanner object to parse the file
            while (scnr.hasNextLine()) { // loop block till the file has no more lines to read
                line = scnr.nextLine(); // parses file line-by-line
                // advances to the next line if the line is empty and not the end of file
                if (line.length() == 0 && scnr.hasNextLine()) {
                    line = scnr.nextLine();
                }
                // remove leading and trailing whitespace in line
                line = line.trim();
                // block executes if it is identified as a "keywords" line
                if (line.startsWith("keywords")) {
                    // create unique arrays for paired keywords and associated responses
                    keywords = new ArrayList<String>();
                    responses = new ArrayList<String>();
                    // a simple array consists of each individual word in the line
                    keywordsArray = line.split(" ");
                    // loops through the words in the line, ignoring the first word ("keywords")
                    for (int i = 1; i < keywordsArray.length; i++) {
                        // adds each word after "keywords" in line to the unique keywords ArrayList
                        keywords.add(keywordsArray[i]);
                    }
                    // adds previous ArrayLists of keywords responses to the response table
                    // in a pair, so there is never an odd number of elements in responseTable
                    responseTable.add(keywords);
                    responseTable.add(responses);
                } else {
                    // treat all other non-empty lines as responses, add them line-by-line
                    // to the responses array
                    if (line.length() != 0) {
                        responses.add(line);
                    }
                }
            }
        } catch (IOException excpt) { // handles IOException created by file read
            System.out.println("Error reading " + fileName);
            return responseTable;
        } finally { // close resources
            if (scnr != null) {
                scnr.close();
            }
        }
        return responseTable;
    }

    /**
     * Checks to see if the keywords match the sentence. In other words, checks to see that all the
     * words in the keyword list are in the sentence and in the same order. If all the keywords
     * match then this method returns an array with the unmatched words before, between and after
     * the keywords. If the keywords do not match then null is returned.
     * <p>
     * When the phrase contains elements before, between, and after the keywords, each set of the
     * three is returned in its own element
     * <p>
     * String[] keywords = {"i", "dreamed"};
     * <p>
     * String[] phrase = {"do", "you", "know", that", "i", "have", "dreamed", "of", "being", "an",
     * "astronaut"};
     * <p>
     * toReturn[0] = "do you know that"
     * <p>
     * toReturn[1] = "have"
     * <p>
     * toReturn[2] = "of being an astronaut"
     * <p>
     * In an example where there is a single keyword, the resulting List's first element will be the
     * the pre-sequence element and the second element will be everything after the keyword, in the
     * phrase
     * <p>
     * String[] keywords = {"always"};
     * <p>
     * String[] phrase = {"I", "always", "knew"};
     * <p>
     * toReturn[0] = "I"
     * <p>
     * toReturn[1] = "knew"
     * <p>
     * In an example where a keyword is not in the phrase in the correct order, null is returned.
     * String[] keywords = {"computer"};
     * <p>
     * String[] phrase = {"My","dog", "is", "lost"};
     * 
     * return null
     * 
     * @param keywords The words to match, in order, in the sentence.
     * @param phrase Each word in the sentence.
     * @return The unmatched words before, between and after the keywords or null if the keywords
     *         are not all matched in order in the phrase.
     */
    public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
        String unmatched = "";
        String[] toReturn;
        int keywordCounter = 0;
        // creates array with enough indexes for unmatched sections each phrase determined by
        // size of keyword arrayList
        toReturn = new String[keywords.size() + 1];
        Arrays.fill(toReturn, ""); // populates toReturn with empty strings

        // block executes if the keywords ArrayList is empty
        if (keywords.size() == 0) {
            // executes as long as phrases array exists
            if (phrase != null) {
                // iterates through every word in phrases array, adding contents of phrase,
                // separated by spaces to 'unmatched' string. no space after last element added
                for (int i = 0; i < phrase.length; i++) {
                    if (i == phrase.length - 1) {
                        unmatched += phrase[i];
                    } else {
                        unmatched += phrase[i] + " ";
                    }
                }
                // toReturn array only has 1 element, unmatched string assigned to it
                toReturn[0] = unmatched;
                return toReturn;
            }
        }
        // executes if there are keywords
        else {
            // iterates through every word in phrase array
            for (int i = 0; i < phrase.length; i++) {
                // executes if all elements in keywords list have been located
                if (keywordCounter == keywords.size()) {
                    // iterates through the remaining words in phrase
                    for (int j = i; j < phrase.length; j++) {
                        // adds remaining words and a space to the last spot in keywordCounter
                        toReturn[keywordCounter] += phrase[j] + " ";
                    }
                    break; // exits the toReturn assignment loop
                }
                // executes when the current word in the phrase array is a keyword
                if (phrase[i].equals(keywords.get(keywordCounter))) {
                    // records that keyword has been located in the phrase array
                    keywordCounter++;
                }
                // if the current word is not a keyword, and all keywords have not been located
                else {
                    // adds the current word and a space to the string in the designated spot in
                    // the return array
                    toReturn[keywordCounter] += phrase[i] + " ";
                }
            }
        }
        // return null there are no keywords or not all keywords have been matched
        if (keywordCounter == 0 || keywordCounter != keywords.size()) {
            return null;
        }
        // removes leading and trailing whitespace for each string in the toReturn array
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = toReturn[i].trim();
        }
        return toReturn;
    }

    /**
     * Selects a randomly generated response within the list of possible responses using the
     * provided random number generator where the number generated corresponds to the index of the
     * selected response. Use Random nextInt( responseList.size()) to generate the random number. If
     * responseList is null or 0 length then return null.
     * 
     * @param rand A random number generator.
     * @param responseList A list of responses to choose from.
     * @return A randomly selected response
     */
    public static String selectResponse(Random rand, ArrayList<String> responseList) {
        // if the responseList arrayList is null or empty, return null
        if (responseList == null || responseList.size() == 0) {
            return null;
        }
        // return a randomly selected response from the passed in responseList
        else {
            return responseList.get(rand.nextInt(responseList.size()));
        }
    }

    /**
     * This method takes processed user input and forms a response. This looks through the response
     * table in order checking to see if each keyword pattern matches the userWords. The first
     * matching keyword pattern found determines the list of responses to choose from. A keyword
     * pattern matches the userWords, if all the keywords are found, in order, but not necessarily
     * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See the
     * findKeyWordsInPhrase algorithm in the Eliza.pdf.
     * <p>
     * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned. Otherwise one of
     * possible responses for the matched keywords is selected with selectResponse method. The
     * response selected is checked for the replacement symbol <n> where n is 1 to the length of
     * unmatchedWords array returned by findKeyWordsInPhrase. For each replacement symbol the
     * corresponding unmatched words element (index 0 for <1>, 1 for <2> etc.) has its pronouns
     * swapped with swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol in
     * the response.
     * 
     * @param userWords using input after preparing.
     * @param rand A random number generator.
     * @param responseTable A table containing a list of keywords and response pairs.
     * @return The generated response
     */
    public static String prepareResponse(String[] userWords, Random rand,
        ArrayList<ArrayList<String>> responseTable) {
        String[] unmatchedWords = null;
        String response = "";
        String response1 = "";
        String response2 = "";
        String echo = "";
        int responseIndex = -1;
        int replaceIndex = -1;
        int replaceNum = -1;
        // Iterate through the response table.
        // The response table has paired rows. The first row is a list of key
        // words, the next a list of corresponding responses. The 3rd row another
        // list of keywords and 4th row the corresponding responses.
        for (int i = 0; i < responseTable.size() - 1; i += 2) {
            // checks to see if the current keywords match the user's words
            // using findKeyWordsInPhrase.
            unmatchedWords = findKeyWordsInPhrase(responseTable.get(i), userWords);
            if (unmatchedWords != null) {
                responseIndex = i;
                break;
            }
        }
        // if no keyword pattern was matched, return Config.NO_MATCH_RESPONSE
        if (unmatchedWords == null) {
            return Config.NO_MATCH_RESPONSE;
        }
        // else, select a response using the appropriate list of responses for the keywords
        else {
            response = selectResponse(rand, responseTable.get(responseIndex + 1));
            // Look for <1>, <2> etc in the chosen response. The number starts with 1 and
            // there won't be more than the number of elements in unmatchedWords returned by
            // findKeyWordsInPhrase. Note the number of elements in unmatchedWords will be
            // 1 more than the number of keywords.
            // For each <n> found, find the corresponding unmatchedWords phrase (n-1) and swap
            // its pronoun words (swapWords using Config.PRONOUN_MAP). Then use the
            // result to replace the <n> in the chosen response.
            while (response.contains("<")) {
                replaceIndex = response.indexOf('<');
                replaceNum = Character.getNumericValue(response.charAt(replaceIndex + 1));
                response1 = response.substring(0, replaceIndex);
                response2 = response.substring(replaceIndex + 3, response.length());
                echo = unmatchedWords[replaceNum - 1];
                // in the selected echo, swap pronouns
                echo = swapWords(echo, Config.PRONOUN_MAP);
                // inserts the new phrase with pronouns swapped, into the response
                response1 = response1 + echo;
                response = response1 + response2;
            }
        }
        return response;
    }

    /**
     * Creates a file with the given name, and fills that file line-by-line with the tracked
     * conversation. Every line ends with a newline. Throws an IOException if a writing error
     * occurs.
     * 
     * @param dialog the complete conversation
     * @param fileName The file in which to write the conversation
     * @throws IOException
     */
    public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {
        File outFile = null;
        PrintWriter output = null;

        outFile = new File(fileName); // possible IOException
        output = new PrintWriter(outFile);
        // iterates through every element of dialog ArrayList
        for (int i = 0; i < dialog.size(); i++) {
            // prints each element of dialog as a new line in the file
            output.println(dialog.get(i));
            output.flush(); // forces buffer to print to file
        }
        output.close(); // closes file when done writing

    }
}
