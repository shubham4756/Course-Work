#include <bits/stdc++.h>
using namespace std;

string encryption(string plainText, int key) {
    int row = key;
    vector<vector<char>> matrix(row);
    bool down = false;
    int j = 0;
    for (char c : plainText) {
        if (j == 0 || j + 1 == row)
            down = !down;
        matrix[j].emplace_back(c);
        down ? j++ : j--;
    }
    string cipherText = "";
    for (auto vi : matrix) {
        for (auto c : vi) {
            cipherText += c;
        }
    }
    return cipherText;
}

string decryption(string cipherText, int key) {
    int row = key;
    int col = cipherText.length();
    vector<vector<char>> matrix(row, vector<char>(col, '_'));
    bool down = false;
    int j = 0;
    for (int i = 0; i < col; i++) {
        if (j == 0 || j + 1 == row)
            down = !down;
        matrix[j][i] = '*';
        down ? j++ : j--;
    }
    int k = 0;
    for (int i = 0; i < row; i++) {
        for (j = 0; j < col; j++) {
            if (matrix[i][j] == '*' && k < col) {
                matrix[i][j] = cipherText[k++];
            }
        }
    }
    j = 0;
    down = false;
    string plainText = "";
    for (int i = 0; i < col; i++) {
        if (j == 0 || j == row - 1)
            down = !down;
        plainText += matrix[j][i];
        down ? j++ : j--;
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
    int key = 0;
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
            writeTo("output3.txt", cipherText);
            cout << "plain Text :: \n";
            cout << plainText << "\n\n";
            cout << "cipher Text :: \n";
            cout << cipherText << "\n\n";
        } else if (ch == 2) {
            string cipherText = readFrom("output3.txt");
            cout << "Enter the key" << endl;
            cin >> key;
            string plainText = decryption(cipherText, key);
            writeTo("output4.txt", plainText);
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