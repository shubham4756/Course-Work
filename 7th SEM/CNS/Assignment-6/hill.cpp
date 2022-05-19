#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> subMatrix(vector<vector<int>> mat, int p, int q) {

    int n = mat.size();

    vector<vector<int>> temp(n-1, vector<int>(n-1));

    int i = 0;
    for(int x = 0; x<n;x++)
        for (int y = 0;y<n;y++) 
            if(x!= p && y!=q) {
                temp[i/(n-1)][i%(n-1)] = mat[x][y];
                    i++;
            }

    return temp;
}

int det(vector<vector<int>> mat) {
    int ans = 0;
    
    int n = mat.size();

    if(n==1) {
        return mat[0][0];
    }

    int sign = 1;

    vector<vector<int>> temp;

    for(int i = 0;i<n;i++) {
        temp = subMatrix(mat, 0, i);

        ans += sign*mat[0][i]*det(temp);
        sign = -sign;
    }

    return ans;
}

vector<vector<int>> adj(vector<vector<int>> mat) {
    int n = mat.size();

    if(n==1) {
        return mat;
    }

    int sign = 1;
    vector<vector<int>> adj(n, vector<int>(n));

    for(int i = 0;i<n;i++)
        for(int j = 0;j<n;j++) {
            vector<vector<int>> temp = subMatrix(mat, i, j);
            adj[i][j] = sign*det(temp);
            sign = -sign;
        }

    return adj;
}

vector<int> multiply(vector<vector<int>> mat, vector<int> s) {
    int n = mat.size();
    vector<int> ans(n);

    for(int i = 0;i<n;i++) {
        int sum = 0;
        for(int j = 0;j<n;j++) {
            sum += mat[i][j]*s[j];
        }
        ans[i] = sum%26;
    }

    return ans;
}

vector<vector<int>> build(string key) {
    int n = key.size();

    int m = sqrt(n);
    vector<vector<int>> ans(m, vector<int>(m));

    for(int i = 0;i<n;i++) {
        ans[i/m][i%m] = (key[i] - 'a');
    }

    return ans;
}

string encrypt(string plainText, string key) {
    int n = plainText.length();
    int m = sqrt(key.length());

    string cipherText = "";

    if(n%m != 0) {
        int p = m - n%m;
        for(;p<m;p++) {
            plainText += 'z';
        }
    }

    n = plainText.length();

    vector<vector<int>> mat = build(key);

    for(int p = 0; p < n;p+=m) {
        vector<int> temp(m);

        for(int i = p;i<p+m; i++)
            temp[i-p] = plainText[i] - 'a';
        
        vector<int> t2 = multiply(mat, temp);

        for(int k : t2) {
            cipherText += (k + 'a');
        }

    }

    return cipherText;
}

string decrypt(string cipherText, string key, int inv) {
    string plainText = "";
    
    int n = cipherText.length();
    int m = sqrt(key.length());

    vector<vector<int>> mata = adj(build(key));

    vector<vector<int>> mat(m, vector<int>(m));

    for(int i= 0;i<m;i++) 
        for(int j=0;j<m;j++) {
            mata[i][j] = ((mata[i][j] %26) + 26)% 26;
            mata[i][j] = (mata[i][j]*inv)%26;
        }

    for(int i =0;i<m;i++) {
        for(int j=0;j<m;j++) {
            mat[i][j] = mata[j][i];
        }
    }

    for(int p = 0; p < n;p+=m) {
        vector<int> temp(m);

        for(int i = p;i< p +m; i++)
            temp[i-p] = cipherText[i] - 'a';
        
        vector<int> t2 = multiply(mat, temp);

        for(int k : t2) {
            plainText += (k + 'a');
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

    bool run = true;
    string key = "gybnqkurp";

    while (run) {
        cout << "1. encryption\n2. decryption\n3. enter key\n";
        int ch;
        cin >> ch;

        if(ch == 1) {

            string plainText = readFrom("input.txt");
            string cipherText = encrypt(plainText, key);

            cout<<"Plain Text : "<<plainText<<endl<<endl;

            cout<<"Cipher Text : "<<cipherText<<endl<<endl;

            writeTo("output1.txt", cipherText);


        } else if (ch == 2) {

            string cipherText = readFrom("output1.txt");

            vector<vector<int>> mat = build(key);

            int value = det(mat);

            if(__gcd(value, 26)!= 1) {
                cout<<"inverese of matrix doesnot exist\n";
                continue;
            }

            int inv = 0;
            value %= 26;

            for(int i = 0;i<26;i++) {
                if((i*value)%26 == 1) {
                    inv = i;
                    break;
                }
            }

            string plainText = decrypt(cipherText, key, inv);

            cout<<"Cipher Text : "<<cipherText<<endl<<endl;
            cout<<"Plain Text : "<<plainText<<endl<<endl;

            writeTo("output2.txt", plainText);


        } else if (ch == 3) {

            while(sqrt(key.length())*sqrt(key.length()) == key.length()) {
                cout<<"enter value of a\n";
                cin>>key;
            }
            
        } else {
            break;
        }
    }
    
    return 0;
}