#include <bits/stdc++.h>
using namespace std;

int main() {
    cout << "Enter the number of model in tables: \n";
    map<string, pair<int, int> > mp;
    int n;
    cin >> n;
    while (n--) {
        string name;
        cout << "Enter the model name: ";
        cin >> name;
        int total_unit, cost;
        cout << "Enter the total number of units sold: ";
        cin >> total_unit;
        cout << "Enter the cost of model: ";
        cin >> cost;
        mp[name] = {total_unit, cost};
    }
    while (true) {
        cout << "1 Enter a model name\n2 Exit\n";
        int choice;
        cin >> choice;
        if (choice == 1) {
            cout << "Enter model name: ";
            string name;
            cin >> name;
            if (mp.find(name) == mp.end()) {
                cout << "Model not found !!\n";
            } else {
                pair<int, int> p = mp[name];
                cout << "total units sold: " << p.first << endl;
                cout << "cost of model: " << p.second << endl;
                cout << "total cost: " << p.first * p.second << endl;
            }
        } else {
            break;
        }
    }

    cout<<" model    total unit    cost\n"; 
    for(pair<string ,pair<int,int> > p:mp) {
        cout<<p.first<<"        "<<p.second.first<<"         "<<p.second.second<<endl;
    }

    return 0;
}