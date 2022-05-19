#include <bits/stdc++.h>
using namespace std;
template <class T>
class VCTR
{
private:
    T *arr;
    int size;

public:
    VCTR(int n)
    {
        size = n;
        arr = new T[size];
    }
    void set(int i, T val)
    {
        arr[i] = val;
    }
    T get(int i)
    {
        return arr[i];
    }
};
int main()
{
    int n;
    cout << "Enter the size of the vector: ";
    cin >> n;
    VCTR<int> v(n);
    int p;
    for (int i = 0; i < n; i++)
    {
        cout << "Enter the value of element " << i << ": ";
        cin >> p;
        v.set(i, p);
    }
    while (1)
    {
        cout << "1. Print vector" << endl;
        cout << "2. Modify element" << endl;
        cout << "3. Print certain element" << endl;
        cout << "4. Exit" << endl;
        int choice;
        cin >> choice;
        switch (choice)
        {
        case 1:
            for (int i = 0; i < n; i++)
            {
                cout << v.get(i) << " ";
            }
            cout << endl;
            break;
        case 2:
            int i, val;
            cout << "Enter the index of the element to be modified: ";
            cin >> i;
            cout << "Enter the new value: ";
            cin >> val;
            v.set(i, val);
            break;
        case 3:
            int index;
            cout << "Enter the index of the element to be printed: ";
            cin >> index;
            cout << v.get(index) << endl;
            break;
        case 4:
            return 0;
        default:
            cout << "Invalid choice" << endl;
        }
    }
    return 0;
}
