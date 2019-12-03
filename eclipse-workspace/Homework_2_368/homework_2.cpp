/* ******************************************************
 * Name: Chris George
 * Wisc ID: crgeorge
 * OS: Mac
 * IDE (or text editor): Eclipse
 * Compiler: Mac OSX GCC
 * How long did the assignment take you to complete in minutes: 300-500
 * What other resources did you consult (copy and paste links below:
 */ // ******************************************************
#include <iostream>
#include <iomanip>
#include <string>
#include <fstream>

using namespace std;

const int MAX_CLASS_SIZE = 100;
const int MAX_NUMBER_OF_ASSIGNMENTS = 100;

// do not change these prototypes.  Add your code to the function definitions below
void Read_Grade_File(string names[MAX_CLASS_SIZE][2],
		int scores[MAX_CLASS_SIZE][MAX_NUMBER_OF_ASSIGNMENTS],
		int *number_of_students, int *number_of_assignments,
		const string input_filename);
void Format_Case_Of_Names(string names[MAX_CLASS_SIZE][2],
		const int number_of_students);
void Compute_Total_And_Percent(
		int scores[MAX_CLASS_SIZE][MAX_NUMBER_OF_ASSIGNMENTS], int total[],
		float percent[], int number_of_students, int number_of_assignments);
void Write_Formatted_Grades(string names[MAX_CLASS_SIZE][2], int total[],
		float percent[], const int number_of_students,
		const string output_filename);

// There is no need to change main. However you are encouraged to write code
// to write data to the console to check the correctness of each of your functions as
// as you work.
int main() {
	string input_filename = "unformatted_grades.txt";
	string output_filename = "formatted_grades.txt";
	string names[MAX_CLASS_SIZE][2];
	int scores[MAX_CLASS_SIZE][MAX_NUMBER_OF_ASSIGNMENTS];
	int number_of_students = 0;
	int number_of_assignments = 0;
	int total[MAX_CLASS_SIZE];
	float percent[MAX_CLASS_SIZE];

	Read_Grade_File(names, scores, &number_of_students, &number_of_assignments,
			input_filename);
	Format_Case_Of_Names(names, number_of_students);
	Compute_Total_And_Percent(scores, total, percent, number_of_students,
			number_of_assignments);
	Write_Formatted_Grades(names, total, percent, number_of_students,
			output_filename);

//	for(int k = 0; k < number_of_students ; k++){
//		for(int n = 0 ; n < 2 ; n+=2){
//			cout<<names[k][n]<< " " << names[k][n+1] << " ";
//			for(int p = 0 ; p < number_of_assignments ; p++){
//				cout<<scores[k][p]<< " ";
//			}
//			cout<<endl;
//		}
//	}
//	cout<<"123456789012345678901234567890"<<endl;
//	for(int k = 0; k < number_of_students ; k++){
//		for(int n = 0 ; n < 2 ; n+=2){
//			int x = names[k][n].length() + names[k][n+1].length();
//			cout << names[k][n+1] << ", " << names[k][n] << setw(20-x) << total[k] << " "<< fixed << setprecision(1) << setw(6) << percent[k];
//		}
//		cout<<endl;
//	}
	return 0;
}

// Add your code below to define these functions
// remember to add comments to each function to describe the
// 1) purpose, 2) input, and 3) output of the functions

// 1) purpose: opens the file for reading and processes the contents for number of students and assignments
//             puts students and scores into corresponding arrays, then closes file
// 2) input: file contents
// 3) output: number of students, number of assignments, student names array, student scores array
void Read_Grade_File(string names[MAX_CLASS_SIZE][2],
		int scores[MAX_CLASS_SIZE][MAX_NUMBER_OF_ASSIGNMENTS],
		int *number_of_students, int *number_of_assignments,
		const string input_filename) {
	ifstream infile;	   //creates input file stream object
	string ignoreText; //creates "garbage" string to ignore certain parts of the document
	infile.open(input_filename);   //attaches stream object to file
	infile >> ignoreText >> *number_of_students; //ignores first word, extracts number_of_students
	infile >> ignoreText >> *number_of_assignments >> ignoreText >> ignoreText
			>> ignoreText; //ignores next word, extracts number_of_assignments, ignores next 3 words
	for (int i = 0; i < *number_of_assignments; i++) {
		infile >> ignoreText;							//ignores every '10'
	}
	for (int i = 0; i < *number_of_students; i++) {
		infile >> ignoreText;			//ignores student ID
		infile >> names[i][0];			//stores first name in names array
		infile >> names[i][1];			//stores last name in names array
		for (int k = 0; k < *number_of_assignments; k++) {
			infile >> scores[i][k];     //stores every score in scores array
		}

	}
	infile.close();		//closes file when finished
}

// 1) purpose: changes case of student names to standard capitalization
// 2) input: array of student names
// 3) output: array of reformatted student names
void Format_Case_Of_Names(string names[MAX_CLASS_SIZE][2],
		const int number_of_students) {
	int n;
	char charArray[20]; //creates array with max of 20 characters, more than enough for each name
	string origWord, formattedWord;
	for (int i = 0; i < number_of_students; i++) {
		for (int j = 0; j < 2; j++) {
			origWord = names[i][j]; //assigns each entry in names array to origWord for manipulation
			n = origWord.length();		//gets length of name
			strcpy(charArray, origWord.c_str()); //converts name string to char array
			for (int i = 0; i < n; i++) {
				char a = charArray[i];//assigns each character in charArray to 'a' for case inspections
				if (a >= 65 && a <= 90) {
					a = char(a + 32);
					charArray[i] = a;//if the character is uppercase, convert it to lowercase

				} else {
					charArray[i] = a;//if it is lowercase already leave it alone
				}

			}
			charArray[0] = char(charArray[0] - 32); //makes first letter of name uppercase
			formattedWord = string(charArray);//converts fixed char array back to string type
			names[i][j] = formattedWord;//assigns entries in names array with the new properly formatted word
		}

	}
}

// 1) purpose: compute the sum of points earned per student and their percentage grade
// 2) input: array of raw student scores
// 3) output: array: sum of points and percentage for each student
void Compute_Total_And_Percent(
		int scores[MAX_CLASS_SIZE][MAX_NUMBER_OF_ASSIGNMENTS], int total[],
		float percent[], int number_of_students, int number_of_assignments) {
	int calculatedTotal = 0;
	int totalPoints = number_of_assignments * 10; //total points is the maximum # points students could score
	for (int i = 0; i < number_of_students; i++) {
		for (int j = 0; j < number_of_assignments; j++) {
			calculatedTotal += scores[i][j];//adds all of a student's scores together
		}
		total[i] = calculatedTotal;	//assigns the student's total score to specific entry in total scores array
		calculatedTotal = 0;//sets back to 0 to add up the next students score
	}
	for (int i = 0; i < number_of_students; i++) {
		percent[i] = ((float) total[i] / totalPoints) * 100; //assigns and calculates student's percentage
	}
}

// 1) purpose: create and write a formatted file that displays the formatted names, total scores, and percentages
//				closes file when finished
// 2) input: names array, total scores array, percentages array
// 3) output: a file containing the information, formatted to specifications
void Write_Formatted_Grades(string names[MAX_CLASS_SIZE][2], int total[],
		float percent[], const int number_of_students,
		const string output_filename) {
	ofstream outfile;		//creates output file stream
	outfile.open(output_filename);     //attaches file stream to file
	for (int k = 0; k < number_of_students; k++) {
		for (int n = 0; n < 2; n += 2) {
			int x = names[k][n].length() + names[k][n + 1].length(); //calculates total number of characters in name for proper formatting
			//prints the names (last, first), creates proper spacing depending on how long the name is, prints total points,
			//sets rounding to 1 decimal place, creates proper spacing between the two numbers, prints rounded percentage
			outfile << names[k][n + 1] << ", " << names[k][n] << setw(20 - x)
					<< total[k] << " " << fixed << setprecision(1) << setw(6)
					<< percent[k];
		}
		outfile << endl;		//newline after every student
	}
	outfile.close();         //closes file when done

}

