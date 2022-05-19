#include <bits/stdc++.h>
using namespace std;
void reverseCase(istream &in, ostream &out)
{
    char c;
    while (in.get(c))
    {
        if (isupper(c))
        {
            c = tolower(c);
        }
        else
        {
            c = toupper(c);
        }
        out.put(c);
    }
}
int main()
{
    string a = "in.txt";
    string b = "out.txt";
    ifstream in(a);
    ofstream out(b);
    reverseCase(in, out);
}
