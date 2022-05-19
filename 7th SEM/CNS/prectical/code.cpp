#include <bits/stdc++.h>
#include <fstream>
using namespace std;

map<char, vector<char> > en;

char Next(char c) {
    int x = c - '0';
    x++;
    x%=10;
    return x + '0';
}

void generateKey(string key) {
    char c = '0', x1 = key[0], x2 = key[1];
    for(char ch='a';ch<='c';ch++) {
        en[ch].push_back(c);
        c = Next(c);
    }
    c = Next(c);
    for(char ch='d';ch<='g';ch++) {
        en[ch].push_back(c);
        c = Next(c);
    }
    c = Next(c);
    en['h'].push_back(c);
    c = Next(c);
    for(char ch='i';ch<='r';ch++) {
        en[ch].push_back(x1);
        en[ch].push_back(c);
        c = Next(c);
    }
    for(char ch='s';ch<='z';ch++) {
        en[ch].push_back(x2);
        en[ch].push_back(c);
        c = Next(c);
    }
    en[' '].push_back(x2);
    en[' '].push_back(c);
    c = Next(c);
    en['#'].push_back(x2);
    en['#'].push_back(c);
    for(pair<char,vector<char> > p:en) {
        cout<<p.first<<" ";
        for(char c:p.second)
            cout<<c<<" ";
        cout<<endl;
    }
}

string encrypt(string plain) {
    string ciphertext="";
    for(char c:plain) {
        if(c>='0' && c<='9') {
            for(char ch:en['#'])
                ciphertext+=ch;
            ciphertext+=c;
        }
        for(char ch:en[c])
            ciphertext+=ch;
    }
    return ciphertext;
}

char findChar1(char x1) {
    for(pair<char, vector<char> > p:en) {
        vector<char> v=p.second;
        if(v.size()==1 && v[0]==x1) {
            return p.first;
        }
    }
    return '@';
}

char findChar2(char x1,char x2) {
    for(pair<char, vector<char> > p:en) {
        vector<char> v=p.second;
        if(v.size()==2 && v[0]==x1 && v[1]==x2) {
            return p.first;
        }
    }
    return '@';
}
 
string decrypt(string s) {
    string res="";
    int n = s.size();
    for(int i=0;i<n;i++) {
        if(i+1<n) {
            if(s[i]=='8' && s[i+1]=='9') {
                res +=' ';
                i++;
                res += s[i+2];
                i++;
                continue;
            } else if(s[i]=='8' && s[i+1]=='8') {
                res += ' ';
                i++;
                continue;
            }
            char c = findChar2(s[i],s[i+1]);
            if(c=='@') {
                c = findChar1(s[i]);
            } else
                i++;
            res += c;
        } else {
            char c = findChar1(s[i]);
            res += c;
        }
    }
    return res;
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
    string plaintext,ciphertext,key;
    bool f=true;
    int choice;
    while(f) {
        cout << "1. Encrypt plain text" << endl;
        cout << "2. Decrypt plain text" << endl;
        cout << "3. generate key" << endl;
        cout << "4. exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;
        if(choice==1) {
            plaintext = readFrom("input.txt");
            cout<<"plain text: ";
            cout << plaintext << endl;  
            ciphertext = encrypt(plaintext);
            cout << "cipher Text: " << ciphertext << endl;
            writeTo("output1.txt", ciphertext);   
        } else if(choice==2) {
            ciphertext = readFrom("output1.txt");
            cout << "ciphet text: "<< ciphertext << endl;
            string plainText = decrypt(ciphertext);
            cout << "Decrypted Text: " << plainText << endl;
            writeTo("output2.txt", plainText);
        } else if(choice==3) {
            cout<<"Enter the key: ";
            cin>>key;
            generateKey(key);
        } else {
            break;
        }
    }
    return 0;
}