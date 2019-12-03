#include <iostream>
#include <string>

using namespace std;

class PERSON {
public:
	string *name;
	PERSON() { name = new string(""); }
	PERSON(const string &input) {
		name = new string(input);
	}
	~PERSON() {
		delete name;
	}
	//*
	const PERSON & operator= (const PERSON &rhs) {
		if (this != &rhs) 
			*name=*rhs.name;
		return *this;
	}
	// */
	string GetName() const {
		return *name;
	}
	void SetName(const string &new_name) {
		*name = new_name;
	}
};

class INT_VALUE {
private:
	int value;
	static int objectCount;
	int ID;

public:
	INT_VALUE() {
		cout<<"Default constructor"<<endl;
		ID = objectCount++;
	}
	~INT_VALUE() {
		cout<<"destroying object "<<ID<<endl;
	}
	INT_VALUE(const INT_VALUE &rhs) {
		cout<<"Copy constructor"<<endl;
		ID = objectCount++;
		value=rhs.value;
	}
	/*
	INT_VALUE(int value) {
		cout<<"constructor with parameters without initializer list"<<endl;
		this->value = value;
		ID = objectCount++;
	}
	// */
	//* 
	INT_VALUE(int value)
		: value(value)
	{
		cout<<"constructor with parameters and initializer list"<<endl;
		ID = objectCount++;
	}
	// */
	const INT_VALUE & operator=(const INT_VALUE &rhs) {
		cout<<"assignment operator"<<endl;
		if (this != &rhs) value=rhs.value;
		return *this;
	}
	void SetValue(int input) { value=input; } // mutator
	int GetValue() const { return value; } // accessor
};
int INT_VALUE::objectCount = 0;



int main() {
	cout<<"Try switching commenting out the function PERSON::operator= to see the default version"<<endl;
	PERSON p1("mike");
	cout<< "  p1:  "<<p1.GetName()<<endl;
	PERSON p2;
	p2 = p1;
	cout<<"  p1:  "<< p2.GetName()<<endl;
	cout<<"Setting p2 to doescher"<<endl;
	p2.SetName("doescher");
	cout<<"  p1:  "<< p1.GetName()<<endl;
	cout<<"  p2:  "<< p2.GetName()<<endl;
	cout<<endl<<endl;


	INT_VALUE v1; // default
	v1.SetValue(5);
	cout<<"  v1.value = "<<v1.GetValue()<<endl;

	INT_VALUE v2(v1); // copy
	cout<<"  v2.value = "<<v2.GetValue()<<endl;
	INT_VALUE v3 = v1; // copy again
	cout<<"  v3.value = "<<v3.GetValue()<<endl;

	INT_VALUE v4; // default
	v4=v3; // assignment
	cout<<"  v4.value = "<<v4.GetValue()<<endl;

	INT_VALUE v5(7); // constructor
	cout<<"  v5.value = "<<v5.GetValue()<<endl;

	// CHECK THIS OUT - IT ACTUALLY WORKS!!!
	INT_VALUE v6 = 5; // same as INT_VALUE v6(5)
	cout<<"  v6.value = "<<v6.GetValue()<<endl;

	return 0;

	// destructors called when the next line is reached and all of the INT_VALUE objects go out of scope
}
