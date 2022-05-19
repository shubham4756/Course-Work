#include<bits/stdc++.h>
using namespace std;

int n,m,num=0; // n=number of row // m=number of col. // num=number of words required to arrange in matrix
vector< vector < char > > s;  // main char 2 D matrix
vector< pair < pair<int,char> , vector<string> > > v; // all words direction and given words
map< char , pair<int,int> > place;  // map for find index of current index
bool complete=false;  // boolen for checking Answer already got or not
vector< vector < char> > Ans;  //  Find Answer

void Solve(int id, vector< vector<char> > &res) {
    if (complete)   return;
    if (id == num) {
        bool check = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (res[i][j] == '-') {
                    check = false;
                }
            }
        }
        if (check) {
            complete = true;
            Ans = res;
        }
        return;
    }
    vector<string> words = v[id].second;
    for (string wrd:words) {
        char direction = v[id].first.second;
        int ele = v[id].first.first;
        int x = place[ele + '0'].first, y = place[ele + '0'].second;
        vector<vector<char> > tmp = res;
        int i = 0, sz = wrd.size();
        bool fix = true;
        if (direction == 'A') {
            while (y < m && i < sz && tmp[x][y] != '*') {
                if (tmp[x][y] == '-') {
                    tmp[x][y] = wrd[i];
                } else if (tmp[x][y] != wrd[i]) {
                    fix = false;
                    break;
                }
                i++;    y++;
            }
        } else {
            while (x < n && i < sz && tmp[x][y] != '*') {
                if (tmp[x][y] == '-') {
                    tmp[x][y] = wrd[i];
                } else if (tmp[x][y] != wrd[i]) {
                    fix = false;
                    break;
                }
                i++;    x++;
            }
        }
        if (fix && i >= sz && y <= m && x <= n) {
            Solve(id + 1, tmp);
        }
    }
}

int main() {
    cin >> n >> m;
    s = vector<vector<char> >(n, vector<char>(m));
    vector<vector<char> > res(n, vector<char>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> s[i][j];
            if (s[i][j] != '*' && s[i][j] != '-') {
                place[s[i][j]] = {i, j};
                s[i][j] = '-';
            }
            res[i][j] = s[i][j];
        }
    }
    num = place.size();
    for (int i = 0; i < num; i++) {
        int x;
        cin >> x;
        char c;
        cin >> c;
        vector<string> tp(5);
        for (int j = 0; j < 5; j++) {
            cin >> tp[j];
        }
        v.push_back({{x, c}, tp});
    }
    Solve(0, res);
    cout << endl;
    if (complete) {
        cout << "-------------\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cout << Ans[i][j];
            }
            cout << endl;
        }
        cout << "-------------\n";
    } else {
        cout << "Solution does not exist !! ";
    }
}


/*

Input :-
6 5
1-2-3
**-*-
*4-5-
6*7--
8----
-**-*

1 A
hoses laser sails sheet steer

2 V
hoses laser sails sheet steer

3 V
hoses laser sails sheet steer

4 A
heel hike keel knot line

5 V
heel hike keel knot line

6 V
aft ale eel lee tie

7 A
aft ale eel lee tie

8 A
hoses laser sails sheet steer

Output :-
-------------
hoses
**a*t
*hike
a*lee
laser
e**l*
-------------

*/