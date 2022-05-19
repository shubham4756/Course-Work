#include <bits/stdc++.h>
using namespace std;

int main() {
	set<string> root;
	while (true) {
		cout << "1. Create\n2. Display\n3. Delete\n4. Search\n5. Exit\n";
		int choice;
		cout << "Enter your choice: ";
		cin >> choice;
		string filename;
		switch (choice) {
		case 1:
			cout << "Enter the file name: ";
			cin >> filename;
			if (root.find(filename) != root.end())
				cout << "File already exists\n\n";
			else
				root.insert(filename);
				cout << "File created\n\n";
			break;
		case 2:
			cout << "Files present in the root directory are:\n";
			for (string file_name : root)
				cout << file_name << " ";
			cout << "\n\n";
			break;
		case 3:
			cout << "Enter the file name: ";
			cin >> filename;
			if (root.find(filename) != root.end()) {
				cout << "File successfully deleted\n\n";
				root.erase(filename);
			} else
				cout << "No such file exists\n\n";
			break;
		case 4:
			cout << "Enter file name: ";
			cin >> filename;
			if (root.find(filename) != root.end())
				cout << "File exists\n\n";
			else
				cout << "File doesn't exist\n\n";
			break;
		case 5:
			cout << "Good Bye!\n\n";
			return 0;
		default:
			cout << "Invalid choice\n\n";
			break;
		}
	}
	return 0;
}