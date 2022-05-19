#include <bits/stdc++.h>
using namespace std;
void compress(ifstream &in, ofstream &out)
{
    char c;
    bool space = false;
    while (in.get(c))
    {
        if (c == ' ')
        {
            if (!space)
            {
                out.put(' ');
                space = true;
            }
        }
        else
        {
            out.put(c);
            space = false;
        }
    }
}
int main()
{
    string a = "in.txt";
    string b = "out.txt";
    ifstream in(a);
    ofstream out(b);
    compress(in, out);
}
