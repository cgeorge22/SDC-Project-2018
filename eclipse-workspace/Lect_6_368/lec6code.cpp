/*
 * lec6code.cpp
 *
 *  Created on: Nov 10, 2018
 *      Author: chrisgeorge
 */
#include <iostream>
#include <iomanip>
#include <vector>
#include <algorithm>

using namespace std;

void Double_By_Reference(int &z) {
	z = z * 2;
}

int main() {

	cout << "REFERENCES" << endl;
// a lot like pointers but safer
	int x = 17;
	int& r = x;
	cout << "x=" << x << "  r=" << r << endl;
	x = 18;
	cout << "x=" << x << "  r=" << r << endl;
	r = 23;
	cout << "x=" << x << "  r=" << r << endl;
	// Call by reference
	Double_By_Reference(x);
	cout << "x=" << x << "  r=" << r << endl;

	cout << "\nCONTAINERS - VECTOR" << endl;
	vector<int> v1;  // declares an empty vector that will hold integers
	v1.push_back(2);
	v1.push_back(5);
	v1.push_back(7);
	cout << "v1 ";
	for (int i = 0; i < v1.size(); i++) {
		cout << v1[i] << " ";
	}
	cout << endl;

	vector<int> v2 { 2, 3, 4, 5, 6, 7 };
	cout << "v2 ";
	for (int i = 0; i < v2.size(); i++) {
		cout << v2[i] << " ";
	}
	cout << endl;

	vector<int> v3(5);  // this means declare a vector with 5 numbers in it
	vector<int> v4(5, -2); // initialize all five elements to -2
	cout << "v4 ";
	for (int i = 0; i < v4.size(); i++) {
		cout << v4[i] << " ";
	}
	cout << endl;

	// sequential containers
	// dynamically allocated - means we can change their size
	// use contiguous storage

	cout << "\nCOPY AND ASSIGNMENT WORK FOR VECTOR" << endl;
	vector<int> v5 = v3;
	vector<int> v6(v3);

	cout << "\nRANGE ITERATORS" << endl;
	vector<int> v7 { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	int sum = 0;
	cout << setw(9) << left << "Element" << setw(12) << "Running Total" << endl;
	for (auto e : v7) { // this version makes a copy of the elements in the vector
		sum += e;
		cout << right << setw(5) << e << setw(12) << right << sum << endl;
	}

	cout << "\nREFERENCES IN RANGE ITERATORS" << endl;
	cout << "original v7 ";
	for (auto e : v7)
		cout << setw(4) << e;
	cout << endl;
	for (auto &e : v7) { // this version uses references to the elements in the vector
		e *= 2;
	}
	cout << "doubled  v7 ";
	for (auto e : v7)
		cout << setw(4) << e;
	cout << endl;

	cout << "\nELEMENT ACCESS" << endl;
	cout << "v7[2] = " << v7[2] << endl;
	cout << "front " << v7.front() << endl;
	cout << "back " << v7.back() << endl;

	cout << "\nMORE ITERATORS" << endl;
	vector<int>::iterator b = v7.begin(); // begin returns a pointer to the first element
	cout << "iterator access with pointer arithmetic *(b+3) " << *(b + 3)
			<< endl;
	auto e = v7.end(); // end() actually points to one place past the end of v
	cout << "for loop with iterators";
	for (auto b = v7.begin(); b != v7.end(); b++)
		cout << setw(4) << (*b);
	cout << endl;

	cout << "\nOTHER VECTOR FUNCTIONS" << endl;
	vector<int> v8 { 23, 453, 2, 35, 54, 56, 34 };
	cout << "insert";
	v7.insert(v7.begin() + 2, v8.begin(), v8.end());
	for (auto n : v7) {
		cout << setw(4) << n;
	}
	cout << endl;
	cout << "erase ";
	v7.erase(v7.begin() + 2, v7.begin() + 5);
	for (auto n : v7) {
		cout << setw(4) << n;
	}
	cout << endl;
	cout << "v7.size() " << v7.size() << endl;
	cout << "is v7 empty? " << boolalpha << v7.empty() << endl;
	v7.clear();
	cout << "is v7 empty after clear? " << boolalpha << v7.empty() << endl;
	cout << "v7.size() " << v7.size() << endl;

	return 0;
}




