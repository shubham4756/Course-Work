#include <bits/stdc++.h>
using namespace std;
template <typename T>
void swap_v(T &a, T &b)
{
    T temp = a;
    a = b;
    b = temp;
}
int main()
{
    int a = 1, b = 2;
    cout << a << "  --  " << b << endl;
    swap_v(a, b);
    cout << a << "  --  " << b << endl;
    float c = 1.1, d = 2.2;
    cout << c << "  --   " << d << endl;
    swap_v(c, d);
    cout << c << "  --  " << d << endl;
}