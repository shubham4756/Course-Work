#include <bits/stdc++.h>
using namespace std;
void copy(istream &in, ostream &out)
{
    char ch;
    while (in.get(ch))
    {
        out.put(ch);
    }
}
int main()
{
    string a = "in.txt";
    string b = "out.txt";
    ifstream in(a);
    ofstream out(b);
    copy(in, out);
}