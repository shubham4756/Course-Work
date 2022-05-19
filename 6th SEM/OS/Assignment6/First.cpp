#include <bits/stdc++.h>
using namespace std;

vector< pair< int,int> > freeSpace;
vector < vector<int> > allocatedSpace;

void FirstFit(int id, int size) {
    bool found = false;
    int i = 0;
    for (pair<int, int> block: freeSpace) {
        if (block.second - block.first + 1 >= size) {
            // found
            found = true;
            int startAdd = block.first;
            int EndAdd = block.first + size - 1;
            allocatedSpace.push_back({startAdd, EndAdd, id});
            sort(allocatedSpace.begin(), allocatedSpace.end());
            if (EndAdd == block.second) {
                // remove the entry
                freeSpace.erase(freeSpace.begin() + i);
            } else {
                // Modify Enty
                freeSpace[i].first = freeSpace[i].first + size;
            }
            break;
        }
        i++;
    }
    if (!found) {
        cout << " Sorry No Space Available......" << endl;
    }
}

void BestFit(int id, int size) {
    bool found = false;
    int mnSize = INT_MAX;
    int index = -1;
    int startAdd, EndAdd;
    int i = 0;
    for (pair<int, int> block:freeSpace) {
        if (block.second - block.first + 1 >= size) {
            // found
            found = true;
            if (block.second - block.first + 1 < mnSize) {
                index = i;
                startAdd = block.first;
                EndAdd = block.first + size - 1;
                mnSize = block.second - block.first + 1;
            }
        }
        i++;
    }
    if (!found) {
        cout << " Sorry No Space Available......" << endl;
        return;
    }
    pair<int, int> block = freeSpace[index];
    allocatedSpace.push_back({startAdd, EndAdd, id});
    sort(allocatedSpace.begin(), allocatedSpace.end());
    if (EndAdd == block.second) {
        // remove the entry
        freeSpace.erase(freeSpace.begin() + index);
    } else {
        // Modify Enty
        freeSpace[index].first = freeSpace[index].first + size;
    }
}

void WorstFit(int id,int size) {
    bool found = false;
    int mxSize = INT_MIN;
    int index = -1;
    int startAdd, EndAdd;
    int i = 0;
    for (pair<int, int> block:freeSpace) {
        if (block.second - block.first + 1 >= size) {
            // found
            found = true;
            if (block.second - block.first + 1 > mxSize) {
                index = i;
                startAdd = block.first;
                EndAdd = block.first + size - 1;
                mxSize = block.second - block.first + 1;
            }
        }
        i++;
    }
    if (!found) {
        cout << " Sorry No Space Available......" << endl;
        return;
    }
    pair<int, int> block = freeSpace[index];
    allocatedSpace.push_back({startAdd, EndAdd, id});
    sort(allocatedSpace.begin(), allocatedSpace.end());
    if (EndAdd == block.second) {
        // remove the entry
        freeSpace.erase(freeSpace.begin() + index);
    } else {
        // Modify Enty
        freeSpace[index].first = freeSpace[index].first + size;
    }
}


void DeleteAllocatedSpace(int id) {
    int start = -1, end = -1;
    for (int i = 0; i < allocatedSpace.size(); i++) {
        if (allocatedSpace[i][2] == id) {
            start = allocatedSpace[i][0];
            end = allocatedSpace[i][1];
            allocatedSpace.erase(allocatedSpace.begin() + i);
            break;
        }
    }
    if (start != -1) {
        // Check for merging
        for (auto & i : freeSpace) {
            if (end + 1 == i.first) {
                i.first = start;
                return;
            }
            if (i.second + 1 == start) {
                i.second = end;
                return;
            }
        }
        // add Free Block
        freeSpace.emplace_back(start, end);
        sort(freeSpace.begin(), freeSpace.end());
    }
}

void display() {
    cout<<"Free Space list \n";
    for (pair<int, int> p:freeSpace)
        cout << "{" << p.first << "," << p.second << "}\n";

    cout<<" ---------------  \n";
    cout<<" allocated Space list \n";
    for(int i=0;i<allocatedSpace.size();i++)
        cout << "{ " << allocatedSpace[i][0] << "," << allocatedSpace[i][1] << "," << allocatedSpace[i][2] << " }\n";
}

int main() {
    freeSpace.push_back({0, 100});
    int id;
    while (true) {
        cout<< "Enter 1 to add new proces \n"
               "Enter 2 to remove one process \n"
               "Enter 3 to See memory allocation\n"
               "Enter 4 to exit\n";
        int type;
        cin >> type;
        if (type == 1) {
            cout << "Enter Process ID " << endl;
            cin >> id;
            bool flag = false;
            for (int i = 0; i < allocatedSpace.size(); i++) {
                if (allocatedSpace[i][2] == id) {
                    cout << "ID already Exists ..  \n";
                    flag = true;
                }
            }
            if (flag)
                continue;
            cout << "Enter Size of required space \n";
            int size;
            cin >> size;
            WorstFit(id, size);
        } else if (type == 2) {
            cout << "Enter ID " << endl;
            cin >> id;
            DeleteAllocatedSpace(id);
        } else if (type == 3) {
            display();
        } else if (type == 4) {
            break;
        } else {
            cout << "Enter valid choice !! ";
        }
    }
    display();
    return 0;
}