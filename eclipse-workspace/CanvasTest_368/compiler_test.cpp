#include <iostream>
using namespace std;

int main() {
	long unsigned int student_id_number;
	cout << "Please enter your 10 digit student id number: ";
	cin >> student_id_number;
	int x = abs(int(-student_id_number) % 500) << 1;
	cout << "Your code is: " << x << endl;

	enum COLOR { RED, ORANGE, BLUE, BLACK=7, GREEN, YELLOW, VIOLET=2, PINK, WHITE };
		int w = 11;
		cout << "RED = " << RED << endl;
		cout << "ORANGE = " << ORANGE << endl;
		cout<< "BLUE = " << BLUE << endl;
		cout<< "BLACK = " << BLACK << endl;
		cout<< "GREEN = " << GREEN << endl;
		cout<< "YELLOW = " << YELLOW << endl;
		cout << "VIOLET = " << VIOLET << endl;
		cout << "PINK = " << PINK << endl;
		cout << "WHITE = " << WHITE << endl;
		cout<<endl;

		cout<<RED<<endl;

		return 0;
}
