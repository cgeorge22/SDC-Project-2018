/*
 * lecTwo.cpp
 *
 *  Created on: Sep 13, 2018
 *      Author: chrisgeorge
 */
#include <iostream>
using namespace std;

int feet_to_inches (int feet, int inches){
	int total = feet * 12 + inches;
	return total;
}

int main(){
	int feet, inches;
	cout<<"enter your height in feet and inches: ";
	cin>> feet >> inches ;
	cout << "your height is  "<<feet<<" and "<< "inches"<<endl;

	int total_inches = feet_to_inches(feet, inches);

	return 0;
}





