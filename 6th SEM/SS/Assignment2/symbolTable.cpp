#include <bits/stdc++.h>
using namespace std;

vector<string> simple_tokenizer(string s)
{
    vector<string> in;
    stringstream ss(s);
    string word;
    while (ss >> word)
    {
        in.push_back(word);
    }
    return in;
}

int main() {
    map<string, int> mnemonics;
    mnemonics["MOVER"] = 1;
    mnemonics["MOVEM"] = 1;
    mnemonics["ADD"] = 1;
    mnemonics["SUB"] = 1;
    mnemonics["BC"] = 1;
    mnemonics["MOVER"] = 1;
    mnemonics["STOP"] = 1;
    mnemonics["MULT"] = 1;
    mnemonics["DS"] = 1;
    mnemonics["DC"] = 1;
    mnemonics["START"] = 0;
    mnemonics["LTROG"] = 0;
    mnemonics["END"] = 0;

    int literal = 0;
    map<string, int> symbolTable;

    string line;
    ifstream input("input.asm");

    int add = 0;

    getline(input, line);
    vector<string> in = simple_tokenizer(line);
    add = stoi(in[1]);
    cout << "Starting Address " << add << endl;
    while (getline(input, line))
    {
        in = simple_tokenizer(line);

        if (in.back().substr(0, 1) == "=")
        {
            literal++;
        }

        if (in[0] == "LTROG")
        {
            // cout<<literal;
            add += literal;
            literal = 0;
        }
        else if (in[0] == "START" || in[0] == "END")
        {
            continue;
        }
        else if (in[0] == "ORIGIN")
        {
            add = stoi(in[1]);
        }
        else
        {
            if (mnemonics.find(in[0]) == mnemonics.end())
            {
                // then is the symbol at teh start of teh instruction
                symbolTable[in[0]] = add;
            }

            if (in[1] == "EQU")
            {
                symbolTable[in[0]] = symbolTable[in[2]];
            }
            else
            {
                add++;
            }
        }
    }

    for (const auto &i : symbolTable)
    {
        cout << i.first << " " << i.second << endl;
    }

    return 0;
}