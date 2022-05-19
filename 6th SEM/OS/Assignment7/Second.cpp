#include<bits/stdc++.h>
using namespace std;
const int maxDisk = 200;
int main() {
    int n;
    cout << "Enter number of Requests \n";
    cin >> n;

    int head;
    cout << "Enter a head \n";
    cin >> head;

    int direction;
    cout << "Enter Direction 0 for toward left 1 for toward right \n";
    cin >> direction;

    vector<int> left, right;
    if (direction == 0) left.push_back(0);
    else right.push_back(maxDisk - 1);

    cout << "Enter All Requests one by one \n";
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        if (x < head) left.push_back(x);
        else if (x > head) right.push_back(x);
    }

    sort(left.begin(), left.end());
    sort(right.begin(), right.end());

    vector<int> sequence;
    sequence.push_back(head);
    int seekCount = 0;
    for (int i = 0; i < 2; i++) {
        if (direction == 0) {  // left
            for (int i = left.size() - 1; i >= 0; i--) {
                sequence.push_back(left[i]);
                seekCount += abs(left[i] - head);
                head = left[i];
            }
        } else {   // right
            for (int i = 0; i < right.size(); i++) {
                sequence.push_back(right[i]);
                seekCount += abs(right[i] - head);
                head = right[i];
            }
        }
        direction ^= 1;
    }
    cout << "Total number of seek operations = " << seekCount << endl;

    cout << "Seek Sequence is \n";

    for (auto it:sequence)
        cout << it << endl;
    return 0;
}