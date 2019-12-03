#include <iostream>
#include <string>

using namespace std;

class POINT {
public:
	int x;
	int y;

public:
	// Constructor
	POINT() { x = 1; y = 3; }

	// Constructor that takes parameters
	//	POINT(int x_input, int y_input) {
	//		x=x_input; y = y_input;
	//	}

	// Constructor with parameters with an initialization list
	// use this to assign const values
	// it's also faster
	POINT(int x_input, int y_input): x(x_input), y(y_input) {}

	// copy constructor
	POINT(const POINT &p) {
		x=p.x; y = p.y;
	}

	void Print_Point() {
		cout << "(" << x << "," << y << ")" << endl;
	}
};

// derived class example
class UNIT_POINT: public POINT {
public:
	void Set_Unit_Point(int x_input, int y_input) {
		x=x_input; y = y_input;
	}
};


class BOX {
public:
	POINT min_corner;
	POINT max_corner;

	void Set_Corners(POINT p1, POINT p2) {
		min_corner.x = p1.x;
		min_corner.y = p1.y;
		max_corner.x = p2.x;
		max_corner.y = p2.y;
	}

	// member functions can be defined outside of the class
	// but we need the function declaration here
	pair<int, int> Edge_Lengths();
};

// defining the function outside of the class
// :: is the scope resolution operator
pair<int, int> BOX::Edge_Lengths() {
	pair<int, int> edge_lengths;
	edge_lengths.first=max_corner.x-min_corner.x;
	edge_lengths.second=max_corner.y-min_corner.y;
	return edge_lengths;
}

int main() {
	POINT p1;
	POINT p2;

	p1.x = 4;
	p1.y = 4;
	p2.x = 9;
	p2.y = 9;
	p1.Print_Point();
	p2.Print_Point();

	BOX b;
	b.Set_Corners(p1, p2);
	b.min_corner.Print_Point();

	UNIT_POINT up;
	up.Set_Unit_Point(1, 1);
	up.Print_Point();

	POINT p3;
	p3.Print_Point();

	POINT p4(5, 8);
	p4.Print_Point();

	POINT p5(p4);
	p5.Print_Point();

	p5.x = 23456;
	p4.Print_Point();
	p5.Print_Point();

	return 0;
}