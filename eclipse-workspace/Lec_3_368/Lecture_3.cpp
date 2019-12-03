/*
 * Lecture_3.cpp
 *
 *  Created on: Sep 19, 2018
 *      Author: chrisgeorge
 */
#include <iostream>
#include <string>
#include <iomanip>
#include <fstream>
using namespace std;

int main(){

//		string name, sentence = " ";
//		while (cin>>name){
//			sentence += name; //string concatenation
//			}
//	cout<<"My name is "<<sentence<<endl;
//
//
//	ofstream outfile;
//	outfile.open("afile.txt");
//	outfile<<"some text"<< endl;
//	outfile.close();
//
//	int numStds;
//	ifstream infile;
//	infile.open("unformatted_grades.txt");
//	string text;
//	int num;
//    infile>>text>>num;
//	infile>>numStds;
//	cout<<numStds<<endl;
//	cout<<num<<endl;
//	infile.close();

	char c = 'Z';
	char d = 'z';
	char e = 'A';
	char f = 'a';

	char a = c;

	if(a>=65 && a<=90){
		cout<<char(a+32);
	}
//	else if(a>=97 && a<=122){
//		cout<<char(a-32);
//	}
	else{
		cout<<char(a);
	}
	a = d;
	if(a>=65 && a<=90){
			cout<<char(a+32);
		}
//		else if(a>=97 && a<=122){
//			cout<<char(a-32);
//		}
		else{
			cout<<char(a);
		}
	a = e;
	if(a>=65 && a<=90){
			cout<<char(a+32);
		}
		else if(a>=97 && a<=122){
			cout<<char(a-32);
		}
		else{
			cout<<char(a);
		}
	a = f;
	if(a>=65 && a<=90){
			cout<<char(a+32);
		}
		else if(a>=97 && a<=122){
			cout<<char(a-32);
		}
		else{
			cout<<char(a);
		}

	cout<<endl;
	cout<<endl;

	//cout<<c<<endl;
	//cout<<('C' - 'c')<<endl;

	string word = "hEllO";
	int n = word.length();
	char charArray[n];
	string formattedWord;

	strcpy(charArray, word.c_str());
	for (int i = 0; i<n; i++){
		cout << charArray[i] ;
	}
	cout<<endl;
	for(int i = 0 ; i < n; i++){
		char a = charArray[i];
		if(a>=65 && a<=90){
					a = char(a+32);
					charArray[i] = a;
					cout<<a;
				}
				else{
					cout<<a;
					charArray[i] = a;
				}

	}
	cout<<endl;

	for(int i = 0 ; i<n ; i++){
		cout<<charArray[i];
	}
	charArray[0] = char(charArray[0]-32);

	cout<<endl;

	for(int i = 0 ; i<n ; i++){
			cout<<charArray[i];
		}
	cout<<endl;
	formattedWord = string(charArray);

	cout<<formattedWord<<endl;

	return 0;

}

