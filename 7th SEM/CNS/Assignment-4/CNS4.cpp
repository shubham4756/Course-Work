#include <bits/stdc++.h>
#include <fstream>

using namespace std;

void reverse(char str[], int length)
{
    int start = 0;
    int end = length -1;
    while (start < end)
    {
        swap(*(str+start), *(str+end));
        start++;
        end--;
    }
}

// Implementation of itoa()
char* itoa(int num, char* str, int base)
{
    int i = 0;
    bool isNegative = false;
    if (num == 0){
        str[i++] = '0';
        str[i] = '\0';
        return str;
    }
    if (num < 0 && base == 10){
        isNegative = true;
        num = -num;
    }
    while (num != 0){
        int rem = num % base;
        str[i++] = (rem > 9)? (rem-10) + 'a' : rem + '0';
        num = num/base;
    }
    if (isNegative)
        str[i++] = '-';
    str[i] = '\0';
    reverse(str, i);

    return str;
}

int hexa2Dec(char hexVal[])
{
    int len = strlen(hexVal);
    int base = 1;
    int dec_val = 0;

    for (int i = len - 1; i >= 0; i--)
    {
        if (hexVal[i] >= '0' && hexVal[i] <= '9')
        {
            dec_val += (hexVal[i] - 48) * base;
            base = base * 16;
        }

        else if (hexVal[i] >= 'A' && hexVal[i] <= 'F')
        {
            dec_val += (hexVal[i] - 55) * base;
            base = base * 16;
        }
    }

    return dec_val;
}

string RandomKey(int n)
{
    char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
                       'H', 'I', 'J', 'K', 'L', 'M', 'N',
                       'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                       'V', 'W', 'X', 'Y', 'Z'};

    string reskey = "";
    for (int i = 0; i < n; i++)
        reskey = reskey + alphabet[rand() % 26];

    return reskey;
}

string encryption(string message, string key)
{
    string cipher = "";
    char buffer[8];

    cout << "Message letters in binary:- \n";
    for (int i = 0; i < message.length(); i++)
    {
        itoa(message[i], buffer, 2);
        string bin = string(8 - strlen(buffer), '0') + buffer;
        cout << bin << " ";
    }
    cout << endl
         << endl;

    cout << "key letters in binary:- \n";
    for (int i = 0; i < key.length(); i++)
    {
        itoa(key[i], buffer, 2);
        string bin = string(8 - strlen(buffer), '0') + buffer;
        cout << bin << " ";
    }
    cout << endl
         << endl;

    cout << "Cipher text after xor:- \n";
    for (int i = 0; i < message.length(); i++)
    {
        int x = message[i] ^ key[i];
        itoa(x, buffer, 2);
        string bin = string(8 - strlen(buffer), '0') + buffer;
        cout << bin << " ";
        itoa(x, buffer, 16);
        if (strlen(buffer) == 1)
            cipher += '0';
        cipher += buffer;
        cipher += " ";
    }
    cout << endl
         << endl;

    transform(cipher.begin(), cipher.end(), cipher.begin(), ::toupper);
    return cipher;
}
string decryption(string cipher, string key)
{

    string plain = "";
    int l = 0;
    char temp[2];
    char binary[8];

    cout << "key letters in binary:- \n";
    for (int i = 0; i < key.length(); i++)
    {
        itoa(key[i], binary, 2);
        string bin = string(8 - strlen(binary), '0') + binary;
        cout << bin << " ";
    }
    cout << endl
         << endl;

    cout << "Cipher text in binary:-\n";
    for (int i = 0; i < key.length(); i++)
    {
        temp[0] = cipher[l];
        temp[1] = cipher[l + 1];
        int num = hexa2Dec(temp);
        itoa(num, binary, 2);
        string bin = string(8 - strlen(binary), '0') + binary;
        cout << bin << " ";
        plain += (num ^ key[i]);
        l += 3;
    }
    cout << endl
         << endl;

    return plain;
}

int main()
{
    bool run = true;
    string key;
    while (run)
    {
        cout << "----------------------------------------------------------\n";
        cout << "Press 1 for encryption , 2 for decryption and 3 for exit\n";
        int input;
        cin >> input;
        cout << "----------------------------------------------------------\n";
        switch (input)
        {
        case 1:
        {
            string data = "", line;
            ifstream infile;
            infile.open("input4.txt");
            while (!infile.eof())
            {
                getline(infile, line);
                data += line;
            }
            infile.close();
            cout << "String: " << data << endl;
            key = RandomKey(data.length());
            cout << "Randomly Generated key is : " << key << endl;
            cout << "----------------------------------------------------------\n";
            string cipher = encryption(data, key);
            cout << "Cipher Text(in hex) : " << cipher << endl;
            ofstream outfile;
            outfile.open("output4.txt");
            outfile << cipher;
            outfile.close();
            break;
        }
        case 2:
        {
            string data = "", line;
            ifstream infile;
            infile.open("output4.txt");
            while (!infile.eof())
            {
                getline(infile, line);
                data += line;
            }
            infile.close();
            cout << "String: " << data << endl;
            cout << "Randomly Generated key is : " << key << endl;
            cout << "----------------------------------------------------------\n";
            string plain = decryption(data, key);
            cout << "Plain Text : " << plain << endl;
            ofstream outfile;
            outfile.open("input4.txt");
            outfile << plain;
            outfile.close();

            break;
        }
        case 3:
        {
            run = false;
            cout << "Thanks\n";
            cout << "----------------------------------------------------------\n";
            break;
        }
        default:
        {
            cout << "----------------------------------------------------------\n";
            cout << "Wrong input!!! Enter Again.\n";
        }
        }
    }
}
