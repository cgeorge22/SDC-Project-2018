/*
 * lec2.cpp
 *
 *  Created on: Sep 13, 2018
 *      Author: chrisgeorge
 */
#include <iostream>
using namespace std;
// converts height given in feet and inches to total inches
// input: feet - integer height in feet
// input: inches - integer height in inches
// output: integer total height in inches
// Note: this is a function declaration and definition in
// global scope (outside of a class)
int feet_to_inches(int feet, int inches) {
	int total = feet * 12 + inches;
	return total;
}
// a function declaration (prototype)
// functions must be declared before they are used.
// The actual definition of this function is after main
void blastoff(int x);
int main() {
	// Note: For lecture purposes I will write code in
	// blocks that that can be easily commented out so that
	// I can put all of the code used for the lecture in
	// one file and just run pieces of it during lecture.
	// for that I use the //*   **code**   // */  structure.
	// Just delete the first / of the //* and then the entire
	// block is commented.
	//*
	int feet, inches;
	cout << "enter you height in feet and inches: ";
	cin >> feet >> inches;
	cout << "your height is " << feet << " feet and " << inches << " inches" << endl;

	int total_inches = feet_to_inches(feet, inches);
	cout << " total inches " << total_inches << endl;
	// */// example function using while and for loops
	blastoff(10);
	return 0;
}
// Note: function definition - the declaration appears above
// main. Also in global scope - outside of a class
// purpose: counts down from x and prints out *s
// input: number to countdown from
// output: just prints some stars to the console
void blastoff(int x) {while (x >= 0) {for (int y = x; y > 0; y--) {cout << "*";}
cout << endl;x--;}}




