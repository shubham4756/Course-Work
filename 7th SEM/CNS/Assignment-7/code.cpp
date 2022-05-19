#include <bits/stdc++.h>
using namespace std;

string prepare(int n, string keyword) {
    int m = keyword.length();
    string key = "";
    for (int i = 0; i < n; i++) {
        key += keyword[i % m];
    }
    return key;
}

string encryption(string plainText, string keyword) {
    string cipherText = "";
    int n = plainText.length();
    string key = prepare(n, keyword);
    cout << "Key: " << key << endl;
    for (int i = 0; i < n; i++) {
        cipherText += (((plainText[i] - 'A') + (key[i] - 'A')) % 26 + 'A');
    }
    return cipherText;
}

string decryption(string cipherText, string keyword) {
    string plainText = "";
    int n = cipherText.length();
    string key = prepare(n, keyword);
    cout << "Key: " << key << endl;
    for (int i = 0; i < n; i++) {
        plainText += ((cipherText[i] - key[i] + 26) % 26 + 'A');
    }
    return plainText;
}

int main() {
    bool run = true;
    while (run) {
        cout << "\nPress 1 for encryption , 2 for decryption and 3 for exit\n";
        int input;
        cin >> input;
        cout << "\n";
        switch (input) {
            case 1:
            {
                string plainText;
                cout << "Enter Plain Text: ";
                cin >> plainText;
                string keyword;
                cout << "Enter keyword: ";
                cin >> keyword;
                transform(plainText.begin(), plainText.end(), plainText.begin(), ::toupper);
                transform(keyword.begin(), keyword.end(), keyword.begin(), ::toupper);
                string cipherText = encryption(plainText, keyword);
                cout << "Cipher Text: " << cipherText << endl;
                break;
            }
            case 2:
            {
                string cipherText;
                cout << "Enter Cipher Text: ";
                cin >> cipherText;
                string keyword;
                cout << "Enter keyword: ";
                cin >> keyword;
                transform(cipherText.begin(), cipherText.end(), cipherText.begin(), ::toupper);
                transform(keyword.begin(), keyword.end(), keyword.begin(), ::toupper);
                string plain = decryption(cipherText, keyword);
                cout << "Plain Text : " << plain << endl;
                break;
            }
            case 3:
            {
                run = false;
                cout << "Thanks for running the program\n";
                break;
            }
            default:
            {
                cout << "Wrong input!!! Enter Again.\n";
            }
        }
    }
}

/*
Enter Plain Text: THISISPLAINTEXTWHICHISGOINGTOENCRYPT
Enter keyword: CIPHER
Key: CIPHERCIPHERCIPHERCIPHERCIPHERCIPHER
Cipher Text: VPXZMJRTPPRKGFIDLZEPXZKFKVVASVPKGFTK

Enter Cipher Text: VPXZMJRTPPRKGFIDLZEPXZKFKVVASVPKGFTK
Enter keyword: CIPHER
Key: CIPHERCIPHERCIPHERCIPHERCIPHERCIPHER
Plain Text : THISISPLAINTEXTWHICHISGOINGTOENCRYPT

*/