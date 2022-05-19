#include <bits/stdc++.h>
using namespace std;

const int NoPage = 4;
int main() {
    int n;
    cout << "Enter the Length of sequence\n";
    cin >> n;
    int a[n];
    cout << "Enter the sequence one by one\n";
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    set<int> pageFrame;
    int pageFault = 0;
    for (int i = 0; i < n; i++) {
        int x = a[i];
        bool flag = false;
        if (pageFrame.size() < NoPage) {
            if(pageFrame.find(x)==pageFrame.end()) {
                pageFrame.insert(x);
                pageFault++;
                flag = true;
            }
        } else if (pageFrame.find(x) == pageFrame.end()) {
            pageFrame.erase(a[i - 1]);
            pageFrame.insert(a[i]);
            pageFault++;
            flag = true;
        }
        cout << "Page Frame --> ";
        for (int it:pageFrame)
            cout << it << " ";

        if (flag)
            cout << "Page Fault";
        else
            cout << "No Page Fault";
        cout << endl;
    }
    cout<<"\n\ntotal Number of page Fault :- " << pageFault << endl;
    return 0;
}