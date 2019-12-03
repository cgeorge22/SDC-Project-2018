/*
 * lecCode.cpp
 *
 *  Created on: Nov 10, 2018
 *      Author: chrisgeorge
 */
#include <iostream>
#include <iomanip>
#include <vector>
#include <set>
#include <unordered_set>
#include <string>
#include <map>

using namespace std;

void set_example() {
	cout << "SET EXAMPLE" << endl;
	cout << "Initialize a set" << endl;
	set<string> avengers { "Thor", "Hulk", "Captain America", "IronMan","Black Widow" };
	for (auto e : avengers)
		cout << e << endl;
	cout << endl;

	//*
	cout << "add Vision" << endl;
	avengers.insert("Vision");

	// adding returns an iterator and boolean as a pair
	// grab the iterator by accessing the first element pair
	cout << "add Hawkeye" << endl;
	auto p = avengers.insert("Hawkeye").first;
	for (auto e : avengers)
		cout << e << endl;
	cout << endl;

	cout << "pointer to Hawkeye and the avenger after Hawkeye" << endl;
	cout << *p << endl;
	p++;
	cout << *p << endl << endl;
	//*/

	//*
	cout << "adding more avengers" << endl;
	vector<string> more_avengers { "Spiderman", "Ant-man", "Wasp","Black Panther" };

	avengers.insert(more_avengers.begin(), more_avengers.end());
	for (auto e : avengers)
		cout << e << endl;
	cout << endl;
	// */

	//*
	cout << "lower_bound / upper_bound example" << endl;
	for (set<string>::iterator it = avengers.lower_bound("Black Panther");
			it != avengers.upper_bound("Hulk"); it++)
		cout << *it << endl;
	cout << endl;
 //*/
}

void unordered_set_example() {
	cout << "UNORDERED SET EXAMPLE" << endl;
	cout << "Initialize unordered set" << endl;
	unordered_set<string> avengers { "Thor", "Hulk", "Captain America","IronMan", "Black Widow" };
	for (auto e : avengers)
		cout << e << endl;
	cout << endl;

	//*
	cout << "add Hawkeye" << endl;
	auto p = avengers.insert("Hawkeye").first;
	for (auto e : avengers)
		cout << e << endl;
	cout << endl;

	cout << "pointer to avenger after Hawkeye" << endl;
	cout << *p << endl;
	p++;
	cout << *p << endl << endl;
// */

	//*
	cout << "adding more avengers" << endl;
	vector<string> more_avengers { "Spiderman", "Ant-man", "Wasp","Black Panther" };

	avengers.insert(more_avengers.begin(), more_avengers.end());
	for (auto e : avengers)
		cout << e << endl;
	cout << endl;
	// */
}

void map_example() {
	map<string, float> fruit_to_price { pair<string, float>("apple", 1.9), pair<string, float>("pear", 2.99) };
	for (auto e : fruit_to_price)
		cout << left << setw(7) << e.first << fixed << setprecision(2)
				<< setw(4) << e.second << endl;
	cout << endl;

	//*
	cout << "Add grapes" << endl;
	fruit_to_price["grapes"] = 4.99;
	for (auto e : fruit_to_price)
		cout << left << setw(7) << e.first << fixed << setprecision(2)
				<< setw(4) << e.second << endl;
	cout << endl;
	// */
}

int main() {

set_example();
unordered_set_example();
map_example();

	return 0;
}




