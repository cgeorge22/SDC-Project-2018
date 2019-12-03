/* ******************************************************
 * Name: Chris George
 * Wisc ID: crgeorge
 * OS: Mac
 * IDE (or text editor): Eclipse
 * Compiler: Mac OSX GCC
 * How long did the assignment take you to complete in minutes: 45
 * What other resources did you consult (copy and paste links below:
*/ // ******************************************************

// Include appropriate libraries to work with input/output and strings.
#include <string>
#include <iostream>

using namespace std;

// function declarations -- do not changes these
// you must complete the function definitions below
string Get_Name();
void Print_Welcome_Message(string name);

// do not change main
int main() {
	string name;
	name = Get_Name();
	Print_Welcome_Message(name);
}


// Get_Name asks the user to enter their name
// Note: "Blackbeard", "Anne Bonny", and
// "Captain Jack Sparrow" are all valid names
// The user may also choose not to enter a name.
// input: none
// output: a string containing the user entered name
// Note: do not change the function prototype on the next line
string Get_Name() {
	// declare a variable to hold the name
	string name = "Mike";

	// prompt the user to enter a name
	cout<<"Enter your name: ";		//prompts user input

	// read the name entered by the user
	getline(cin,name);		//reads multi-word user input
	if(name != ""){		//if there is user input add a space before it, for the print statement later
		name = " " + name;
	}
	cout<<endl;       //newline


	// return the name entered by the user
	    // i.e. replace the text "name" in the return
	    // statement below with the name supplied by the user
	return name;

}

// Prints the welcome message
// input: a string with the user's name
// output: writes the welcome message to the screen
// return value: none
void Print_Welcome_Message(string name) {
    // print the top row of stars
	for (int i = 0; i <= name.size()+34; i++){   //prints row of stars as long as the input + message
		cout<<"*";
	}
	cout<<endl;	//go to next line to start next row of output

	// print the second line
    cout<<"*";
    for (int i = 0; i<= (name.size()+32);i++){   //prints spaces as long as input + message - 2 stars
    	cout<<" ";
    }
    cout<<"*"<<endl;	//go to next line to start next row of output

	// print the welcome message
	cout<<"* Hello"<<name<<"!"<<" Welcome to CS 368 (C++)! *"<<endl; //prints the input + stars and message

	// print the fourth line
	cout<<"*";
	for (int i = 0; i<= (name.size()+32);i++){	//prints spaces as long as input + message - 2 stars
	    	cout<<" ";
	    }
	    cout<<"*"<<endl;	//go to next line to start next row of output
	// print the bottom row of stars
	for (int i = 0; i <= name.size()+34; i++){ 	//prints row of stars as long as the input + message
		cout<<"*";
		}
	cout<<endl;		//go to next line to start next row of output

	return;
}


/* Sample console text - the console should appear exactly as follows
Including white spaces
I use an automatic grading script to easily identify correct work.


Enter your name: Mike

****************************************
*                                      *
* Hello Mike! Welcome to CS 368 (C++)! *
*                                      *
****************************************


Enter your name: The Dread Pirate Roberts

************************************************************
*                                                          *
* Hello The Dread Pirate Roberts! Welcome to CS 368 (C++)! *
*                                                          *
************************************************************


(If the user doesn't enter a name)
Enter your name:

***********************************
*                                 *
* Hello! Welcome to CS 368 (C++)! *
*                                 *
***********************************


*/

