#include <bits/stdc++.h>
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

    int page_faults = 0;
    set<int> frame;
    int hit = 0;
    for (int i = 0; i < n; i++) {
        bool flag=false;
        if (frame.find(pages[i])==frame.end()) {
            page_faults++;
            flag=true;
            if (frame.size() < capacity)
                frame.insert(pages[i]);
            else {
                int value = -1, farthest = i + 1;
                for (auto it:frame) {
                    int j;
                    for (j = i + 1; j < n; j++) {
                        if (it == pages[j]) {
                            if (j > farthest) {
                                farthest = j;
                                value = it;
                            }
                            break;
                        }
                    }
                    if (j == n) {
                        value = it;
                        break;
                    }
                }
                if (value == -1) {
                    frame.erase(frame.begin());
                } else {
                    frame.erase(value);
                }
                frame.insert(pages[i]);
            }
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