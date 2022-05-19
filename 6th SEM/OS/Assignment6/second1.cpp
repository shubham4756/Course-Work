#include<bits/stdc++.h>
using namespace std;

int main() {
    int n;
    cout << "Enter number of page\n";
    cin >> n;
    int pages[n];
    cout << "Enter the reference string\n";
    for (int i = 0; i < n; i++) {
        cin >> pages[i];
    }
    int capacity;
    cout << "Enter numer of frame\n";
    cin >> capacity;

    set<int> frame;
    map<int, int> indexes;
    int page_faults = 0;
    for (int i = 0; i < n; i++) {
        bool flag=false;
        if (frame.size() < capacity) {
            if (frame.find(pages[i]) == frame.end()) {
                frame.insert(pages[i]);
                page_faults++;
                flag= true;
            }
            indexes[pages[i]] = i;
        } else {
            if (frame.find(pages[i]) == frame.end()) {
                int lru = INT_MAX, val;
                for (auto it:frame) {
                    if (indexes[it] < lru) {
                        lru = indexes[it];
                        val = it;
                    }
                }
                frame.erase(val);
                indexes.erase(val);
                frame.insert(pages[i]);
                page_faults++;
                flag = true;
            }
            indexes[pages[i]] = i;
        }
        cout << "id = " << i << " page[i] = " << pages[i] << " --> ";
        for (auto it:frame)
            cout << it << "   ";
        if(flag) cout<<"  Miss\n";
        else cout<<"  Hit\n";
    }
    cout << "no of page faults = " << page_faults << endl;
    return 0;
}