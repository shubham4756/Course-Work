#include <bits/stdc++.h>
using namespace std;

string encryption(string plainText, string key) {
    int n = plainText.length();
    int col = key.length();
    int row = ceil(n * 1.0 / col);
    char matrix[row][col];
    int p = 0;
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            matrix[i][j] = (p >= n) ? '_' : plainText[p++];
        }
    }
    map<int, vector<int>> map;
    for (int i = 0; i < key.length(); i++) {
        map[key[i]].emplace_back(i);
    }
    string cipherText = "";
    for (auto it : map) {
        for (auto j : it.second) {
            for (int i = 0; i < row; i++) {
                    cipherText += matrix[i][j];
            }
        }
    }
    return cipherText;
}

string decryption(string cipherText, string key) {
    int n = cipherText.length();
    int col = key.length();
    int row = ceil(n * 1.0 / col);
    vector< vector< char > > matrix(row, vector<char> (col,'_'));
    map<int, vector<int>> map;
    for (int i = 0; i < key.length(); i++) {
        map[key[i]].emplace_back(i);
    }
    int p = 0;
    for (auto it : map) {
        for (auto j : it.second) {
            for (int i = 0; i < row; i++) {
                matrix[i][j] = cipherText[p++];
            }
        }
    }
    string plainText = "";
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (matrix[i][j] == '_') {
                i = row;
                break;
            }
            plainText += matrix[i][j];
        }
    }
    return plainText;
}

string readFrom(string filename) {
    ifstream file;
    string input = "", result = "";
    file.open(filename);
    while (!file.eof()) {
        getline(file, input);
        result += input + "\n";
    }
    file.close();
    return result.substr(0, result.length() - 1);
}

void writeTo(string filename, string message) {
    ofstream file;
    file.open(filename);
    file << message;
    file.close();
}

int main() {
    string key = "";
    int ch = 0;
    while (true) {
        cout << "1. Encryption\n";
        cout << "2. Decryption\n";
        cin >> ch;
        if (ch == 1) {
            string plainText = readFrom("input.txt");
            cout << "Enter the key" << endl;
            cin >> key;
            string cipherText = encryption(plainText, key);
            writeTo("output1.txt", cipherText);
            cout << "plain Text :: \n";
            cout << plainText << "\n\n";
            cout << "cipher Text :: \n";
            cout << cipherText << "\n\n";
        } else if (ch == 2) {
            string cipherText = readFrom("output1.txt");
            cout << "Enter the key" << endl;
            cin >> key;
            string plainText = decryption(cipherText, key);
            writeTo("output2.txt", plainText);
            cout << "cipher Text :: \n";
            cout << cipherText << "\n\n";
            cout << "plain Text :: \n";
            cout << plainText << "\n\n";
        } else {
            break;
        }
    }
    return 0;
}