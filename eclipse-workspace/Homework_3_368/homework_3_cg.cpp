#include <iostream>
#include <map>
#include <vector>
#include <set>
#include <string>
#include <time.h>      // time

using namespace std;

// feel free to use this function if you wish
// purpose: generate random coordinates
// input: none
// output: a vector with 2 coordinates between 1,1 and 4,4
vector<int> Get_A_Random_Square() {
	return vector<int> { rand() % 4 + 1, rand() % 4 + 1 };
}

// feel free to use this function if you wish
// purpose: verifies if the coordinates of a square fall within the cave
// input: coordinates of a square
// output: true if the square is in the cave otherwise false
bool Is_Square_Valid(const vector<int> &square) {
	for (auto e : square)
		if (e < 1 || e > 4)
			return false;
	return true;
}

// don't change this function
// purpose: prints a map of the cave to the console
// input: the printable map a vector of strings
// output: none - console output of the cave diagram
void Print_Cave_Diagram(const vector<string> &cave_diagram) {
	for (auto s : cave_diagram)
		cout << s << endl;
}

// Implement these functions below
// Do not change the function prototpyes
void Initialize_Cave(map<vector<int>, set<string> > &cave);
void Print_Square(map<vector<int>, set<string> > &cave, const vector<int> &rc);
void Get_Cave_Diagram(map<vector<int>, set<string> > &cave,
		vector<string> &cave_diagram);

// sample main function
int main() {
	srand(time(NULL)); // seed the random generator

	// Declare the cave data structure to 'map' coordinates to a 'set' of strings describing the cell contents
	map<vector<int>, set<string> > cave;

	// Check this out! The cave data structure has a lot going on
	// Uncomment the block below to see a demo of how to use the cave map
	// use the [] to access the set contained in the map
	// insert puts the word "ladder" into the set
	// cave[rc] is the set of words at coordinates rc
	// cave[rc].begin() returns an iterator to the first element of the set
	// note your program will likely crash if the set is empty
	// I recommend checking for this.
	// use the * to dereference the pointer to get the first word stored in the set
//	vector<int> rc{1, 1}; // row column
//	cave[rc].insert("ladder");
//	cave[rc].insert("stench");
//	if (cave[rc].empty() == false){
//		for(auto iter : cave[rc])
//			cout<<iter<<endl;
//
//	}
	Initialize_Cave(cave);

	for (int r = 1; r <= 4; r++) {
		for (int c = 1; c <= 4; c++) {
			vector<int> rc { r, c };

			Print_Square(cave, rc);
		}
	}

	vector<string> cave_diagram;
	Get_Cave_Diagram(cave, cave_diagram);
	Print_Cave_Diagram(cave_diagram);

	return 0;
}

// add the player, ladder, wumpus, pits, gold, stench, breeze to the map
// input: cave data structure
// output:
void Initialize_Cave(map<vector<int>, set<string> > &cave) {
	// place the "ladder" in row 1 column 1
	vector<int> oneOne { 1, 1 };
	cave[oneOne].insert("ladder"); //puts "ladder" string into cell (1,1)
	// place the "player" at the same location as the ladder
	cave[oneOne].insert("player");
	// place the "wumpus" - can't be in the same square as the ladder
	vector<int> wumpusBox = Get_A_Random_Square();	//assign random coordinate
	while (wumpusBox[0] == 1 && wumpusBox[1] == 1) {
		wumpusBox = Get_A_Random_Square();	//if wumpus is in (1,1) pick another random coordinate
	}
	cave[wumpusBox].insert("wumpus");

	// place the 3 "pits" - can't be in the same square as the ladder, wumpus, or another pit
	vector<int> pit1Box = Get_A_Random_Square();
	while (pit1Box == wumpusBox || pit1Box == oneOne) {
		pit1Box = Get_A_Random_Square();
	}
	cave[pit1Box].insert("pit");

	vector<int> pit2Box = Get_A_Random_Square();
		//pit can't be in same box as any other pit or (1,1)
	while (pit2Box == pit1Box || pit2Box == wumpusBox || pit2Box == oneOne) {
		pit2Box = Get_A_Random_Square();
	}
	cave[pit2Box].insert("pit");

	vector<int> pit3Box = Get_A_Random_Square();
	while (pit3Box == pit2Box || pit3Box == pit1Box || pit3Box == wumpusBox
			|| pit3Box == oneOne) {
		pit2Box = Get_A_Random_Square();
	}
	cave[pit3Box].insert("pit");

	// place the "gold" - can't be in the same square as a pit or the ladder
	vector<int> goldBox = Get_A_Random_Square();
	while (goldBox == pit3Box || goldBox == pit2Box || goldBox == pit1Box
			|| goldBox == oneOne) { //gold can't be in any pit box or (1,1)
		goldBox = Get_A_Random_Square();
	}
	cave[goldBox].insert("gold");

	// place the "stench" squares to the left, right, up, and down from the wumpus
	vector<int> stenchBox1(2), stenchBox2(2), stenchBox3(2), stenchBox4(2);

	stenchBox1[0] = wumpusBox[0] - 1; //cell to the left of wumpus
	stenchBox1[1] = wumpusBox[1];
	if (Is_Square_Valid(stenchBox1)) { //makes sure cell is on the board
		cave[stenchBox1].insert("stench");
	}

	stenchBox2[0] = wumpusBox[0] + 1; //right
	stenchBox2[1] = wumpusBox[1];
	if (Is_Square_Valid(stenchBox2)) {
		cave[stenchBox2].insert("stench");
	}
	stenchBox3[0] = wumpusBox[0];
	stenchBox3[1] = wumpusBox[1] - 1; //up
	if (Is_Square_Valid(stenchBox3)) {
		cave[stenchBox3].insert("stench");
	}
	stenchBox4[0] = wumpusBox[0];
	stenchBox4[1] = wumpusBox[1] + 1; //down
	if (Is_Square_Valid(stenchBox4)) {
		cave[stenchBox4].insert("stench");
	}

	// place the "breeze" squares to the left, right, up, and down from the three pits
	vector<int> breezeBox1(2), breezeBox2(2), breezeBox3(2), breezeBox4(2),
			breezeBox5(2), breezeBox6(2), breezeBox7(2), breezeBox8(2),
			breezeBox9(2), breezeBox10(2), breezeBox11(2), breezeBox12(2);
	//first pit
	breezeBox1[0] = pit1Box[0] - 1; //left of pit
	breezeBox1[1] = pit1Box[1];
	if (Is_Square_Valid(breezeBox1)) {
		cave[breezeBox1].insert("breeze");
	}

	breezeBox2[0] = pit1Box[0] + 1; //right
	breezeBox2[1] = pit1Box[1];
	if (Is_Square_Valid(breezeBox2)) {
		cave[breezeBox2].insert("breeze");
	}

	breezeBox3[0] = pit1Box[0];
	breezeBox3[1] = pit1Box[1] - 1; //up
	if (Is_Square_Valid(breezeBox3)) {
		cave[breezeBox3].insert("breeze");
	}

	breezeBox4[0] = pit1Box[0];
	breezeBox4[1] = pit1Box[1] + 1; //down
	if (Is_Square_Valid(breezeBox4)) {
		cave[breezeBox4].insert("breeze");
	}

	//second pit
	breezeBox5[0] = pit2Box[0] - 1;
	breezeBox5[1] = pit2Box[1];
	if (Is_Square_Valid(breezeBox5) && breezeBox5 != breezeBox1
			&& breezeBox5 != breezeBox2 && breezeBox5 != breezeBox3
			&& breezeBox5 != breezeBox4) {  //makes sure on board, only 1 breeze per box
		cave[breezeBox5].insert("breeze");
	}

	breezeBox6[0] = pit2Box[0] + 1;
	breezeBox6[1] = pit2Box[1];
	if (Is_Square_Valid(breezeBox6) && breezeBox6 != breezeBox1
			&& breezeBox6 != breezeBox2 && breezeBox6 != breezeBox3
			&& breezeBox6 != breezeBox4) {
		cave[breezeBox6].insert("breeze");
	}

	breezeBox7[0] = pit2Box[0];
	breezeBox7[1] = pit2Box[1] - 1;
	if (Is_Square_Valid(breezeBox7) && breezeBox7 != breezeBox1
			&& breezeBox7 != breezeBox2 && breezeBox7 != breezeBox3
			&& breezeBox7 != breezeBox4) {
		cave[breezeBox7].insert("breeze");
	}

	breezeBox8[0] = pit2Box[0];
	breezeBox8[1] = pit2Box[1] + 1;
	if (Is_Square_Valid(breezeBox8) && breezeBox8 != breezeBox1
			&& breezeBox8 != breezeBox2 && breezeBox8 != breezeBox3
			&& breezeBox8 != breezeBox4) {
		cave[breezeBox8].insert("breeze");
	}

	//third pit
	breezeBox9[0] = pit3Box[0] - 1;
	breezeBox9[1] = pit3Box[1];
	if (Is_Square_Valid(breezeBox9) && breezeBox9 != breezeBox1
			&& breezeBox9 != breezeBox2 && breezeBox9 != breezeBox3
			&& breezeBox9 != breezeBox4 && breezeBox9 != breezeBox5
			&& breezeBox9 != breezeBox6 && breezeBox9 != breezeBox7
			&& breezeBox9 != breezeBox8) {
		cave[breezeBox9].insert("breeze");
	}

	breezeBox10[0] = pit3Box[0] + 1;
	breezeBox10[1] = pit3Box[1];
	if (Is_Square_Valid(breezeBox10) && breezeBox10 != breezeBox1
			&& breezeBox10 != breezeBox2 && breezeBox10 != breezeBox3
			&& breezeBox10 != breezeBox4 && breezeBox10 != breezeBox5
			&& breezeBox10 != breezeBox6 && breezeBox10 != breezeBox7
			&& breezeBox10 != breezeBox8) {
		cave[breezeBox10].insert("breeze");
	}

	breezeBox11[0] = pit3Box[0];
	breezeBox11[1] = pit3Box[1] - 1;
	if (Is_Square_Valid(breezeBox11) && breezeBox11 != breezeBox1
			&& breezeBox11 != breezeBox2 && breezeBox11 != breezeBox3
			&& breezeBox11 != breezeBox4 && breezeBox11 != breezeBox5
			&& breezeBox11 != breezeBox6 && breezeBox11 != breezeBox7
			&& breezeBox11 != breezeBox8) {
		cave[breezeBox11].insert("breeze");
	}

	breezeBox12[0] = pit3Box[0];
	breezeBox12[1] = pit3Box[1] + 1;
	if (Is_Square_Valid(breezeBox12) && breezeBox12 != breezeBox1
			&& breezeBox12 != breezeBox2 && breezeBox12 != breezeBox3
			&& breezeBox12 != breezeBox4 && breezeBox12 != breezeBox5
			&& breezeBox12 != breezeBox6 && breezeBox12 != breezeBox7
			&& breezeBox12 != breezeBox8) {
		cave[breezeBox12].insert("breeze");
	}

}

// print the contents of the square
// input: cave map, coordinates of square
// output: contents of the square
void Print_Square(map<vector<int>, set<string> > &cave, const vector<int> &rc) {
	cout << "This part of the cave contains" << endl;
	if (cave[rc].empty() == true) {		// if the particular cell has no contents
		cout << "    " << "nothing" << endl; //4 spaces
	} else {
		for (auto iter : cave[rc]) {
			cout << "    " << iter << endl;	//iterates through the set, printing contents
		}
	}
	cout << endl;
}

// build a vector of strings where each string in the vector represents one row of the cave output
// input: cave map
// output: printout of cells and their contents
void Get_Cave_Diagram(map<vector<int>, set<string> > &cave,
		vector<string> &cave_diagram) {
	int cell_rows = 5;
	int cell_columns = 11;
	int total_rows = cell_rows * 4 + 1;
	int total_columns = cell_columns * 4 + 1;

	// fill in with vertical cell divisions
	for (int r = 0; r < total_rows; r++) {
		string row(total_columns, ' ');
		for (int c = 0; c < total_columns; c += cell_columns) {
			row[c] = '|';
		}
		cave_diagram.push_back(row);
	}

	// udpate horizontal rows with '-'
	for (int i = 0; i < total_rows; i += cell_rows) {
		cave_diagram[i] = string(total_columns, '-');
	}

	// update cell corners with '+'
	for (int r = 0; r < total_rows; r += cell_rows) {
		for (int c = 0; c < total_columns; c += cell_columns) {
			cave_diagram[r][c] = '+';
		}
	}

	// replace the part of the string with the cell contents
	int counter;

	vector<int> rc { 1, 1 };
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;	// so that the row will advance
		string element;
		element = iter;		//iterate through set, putting element in row
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter][j + 2] = element[j]; //two spaces, enters individual characters
		}
	}
	rc = {1,2};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			//add cell_columns to advance to next cell to the right
			cave_diagram[counter][j + cell_columns + 2] = element[j];
		}
	}
	rc = {1,3};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			//add multiple of cell_columns to advance further right
			cave_diagram[counter][j + (2 * cell_columns) + 2] = element[j];
		}
	}
	rc = {1,4};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter][j + (3 * cell_columns) + 2] = element[j];
		}
	}
	rc = {2,1};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			//add cell_rows to advance down column
			cave_diagram[counter + cell_rows][j + 2] = element[j];
		}
	}
	rc = {2,2};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + cell_rows][j + cell_columns + 2] =
					element[j];
		}
	}
	rc = {2,3};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + cell_rows][j + (2 * cell_columns) + 2] =
					element[j];
		}
	}
	rc = {2,4};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + cell_rows][j + (3 * cell_columns) + 2] =
					element[j];
		}
	}
	rc = {3,1};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			//add multiple of cell_row to advance further down
			cave_diagram[counter + (2 * cell_rows)][j + 2] = element[j];
		}
	}
	rc = {3,2};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + (2 * cell_rows)][j + cell_columns + 2] =
					element[j];
		}
	}
	rc = {3,3};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + (2 * cell_rows)][j + (2 * cell_columns) + 2] =
					element[j];
		}
	}
	rc = {3,4};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + (2 * cell_rows)][j + (3 * cell_columns) + 2] =
					element[j];
		}
	}
	rc = {4,1};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + (3 * cell_rows)][j + 2] = element[j];
		}
	}
	rc = {4,2};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + (3 * cell_rows)][j + cell_columns + 2] =
					element[j];
		}
	}
	rc = {4,3};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + (3 * cell_rows)][j + (2 * cell_columns) + 2] =
					element[j];
		}
	}
	rc = {4,4};
	counter = 0;
	for (auto iter : cave[rc]) {
		counter = counter + 1;
		string element;
		element = iter;
		for (int j = 0; j < element.length(); j++) {
			cave_diagram[counter + (3 * cell_rows)][j + (3 * cell_columns) + 2] =
					element[j];
		}
	}
}

