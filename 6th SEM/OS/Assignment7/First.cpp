#include<bits/stdc++.h>
using namespace std;
int main() {
    int n;
    cout << "Enter number of Requests \n";
    cin >> n;
    cout << "Enter a head \n";
    int head;
    cin >> head;
    list<int> req;
    cout << "Enter All Requests one by one \n";
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        req.push_back(x);
    }
    vector<int> sequence;
    sequence.push_back(head);
    int seekCount = 0;
    while (req.size() > 0) {
        auto index = req.begin();
        int minimum = INT_MAX;
        for (auto it = req.begin(); it != req.end(); it++) {
            if (abs(head - *it) < minimum) {
                index = it;
                minimum = abs(head - *it);
            }
        }
        seekCount += minimum;
        head = *index;
        sequence.push_back(*index);
        req.erase(index);
    }
    cout << "Total number of seek operations = " << seekCount << endl;

    cout << "Seek Sequence is \n";

    for (auto it:sequence) {
        cout << it << endl;
    }
    return 0;
}