import java.util.Scanner;

public class MadLib {
	public static void main(String[] args) {
      //prompt the user
      //pass the responses to the prepareStory method
      //print out the story returned from prepareStory
      Scanner scnr = new Scanner(System.in);
      int userNum;
      String userVerb1;
      String userVerb2;
      String userAdj;
      String userName;
      double userDec;
      String userPlace1;
      String userPlace2;
      
      System.out.println("Enter a whole number other than 0:");
      userNum = scnr.nextInt();
      System.out.println("Enter a verb:");
      userVerb1 = scnr.nextLine();
      System.out.println("Enter another verb:");
      userVerb2 = scnr.nextLine();
      System.out.println("Enter an adjective :");
      userAdj = scnr.nextLine();
      System.out.println("Enter a name:");
      userName = scnr.nextLine();
      System.out.println("Enter a two place decimal:");
      userDec = scnr.nextDouble();
      System.out.println("Enter a place:");
      userPlace1 = scnr.nextLine();
      System.out.println("Enter another place:");
      userPlace2 = scnr.next();
      System.out.println(prepareStory(userNum,userVerb1,userVerb2,userAdj,userName,userDec,userPlace1,userPlace2 ));
	}
	
	 /**
     * Returns a String that is a story containing the 8 arguments passed in.  Also, your story must include
     * the author's name (your name) within it using a string literal (don't pass as a parameter).Your name must match the name
     * within the File Header Comment.
     *  
     * There are no input or output statements within this method.  All input and 
     * output is done in the main method and the user must be prompted for all the arguments.
     * 
     * Note: Your method must have the name 'prepareStory', must have 8 parameters with the same data types shown and
     * in the same order.  You must change the parameter names to match values in your story. For example, you may want
     * to prompt for a verb, and store in a String parameter you name verb, or an adjective and store in a String param you
     * name adjective.
     * 
     * @param num1   int 1
     * @param str1   String 1
     * @param str2   String 2
     * @param str3   String 3
     * @param str4   String 4
     * @param dbl1   double 1
     * @param str5   String 5
     * @param str6   String 6
     * @return   A story containing the 8 arguments and includes the program author's name.
     */
    public static String prepareStory(int number, String verb1, String verb2, String adjective, String name, double decimal, String place1, String place2) {    
        String story = name + " had " + number + " brothers. Every day they would " + verb1 + " inside of the " + place1
        + " and after that they would " + verb2 + " at the " + place2 + ". No one liked them becuase they were so " + 
        adjective + " and every day they would spend " + decimal + 
        " at the store on hot pockets. This story was created by Chris George.";
        
        return story;
    }
}


