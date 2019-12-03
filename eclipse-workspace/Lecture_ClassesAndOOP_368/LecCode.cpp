/*
 * LecCode.cpp
 *
 *  Created on: Oct 17, 2018
 *      Author: chrisgeorge
 */

#include <iostream>
#include <string>

using namespace std;

class Point{

public:
	int x;
	int y;

	void PrintPoint() {
		cout<< "("<<x<<","<<y<<")"<<endl;
	}

};

int main(){
	Point p1;
	Point p2;

	p1.x = 4;
	p1.y = 4;
	p2.x = 9;
	p2.y = 9;
	p1.PrintPoint();
	p2.PrintPoint();
}
class Box{
public:

};

