#include <bits/stdc++.h>
using namespace std;

class process {
public:
    int pid;
    int cpuTime;
    int arrivalTime;
};

void SJF(vector<process> &p) {
    int n, cur;
    int curr_time = 0, cur_cpu_time = INT_MAX;
    vector<process> ans;
    while (p.size() > 0) {
        n = p.size();
        cur_cpu_time = INT_MAX, cur = -1;
        for (int i = 0; i < n; i++) {
            if (p[i].arrivalTime <= curr_time && p[i].cpuTime < cur_cpu_time) {
                cur_cpu_time = p[i].cpuTime;
                cur = i;
            }
        }
        if (cur != -1) {
            ans.push_back(p[cur]);
            p.erase(p.begin() + cur);
            curr_time += cur_cpu_time;
        } else {
            curr_time++;
        }
    }
    cout << "PID\tARRIVAL_TIME\tCPU_TIME\tCOMP_TIME" << endl;
    int comp_time = 0;
    for (int i = 0; i < ans.size(); i++) {
        comp_time += ans[i].cpuTime;
        cout << ans[i].pid << '\t' << ans[i].arrivalTime << "\t\t" << ans[i].cpuTime << "\t\t" << comp_time << endl;
    }
}

int main() {
    int n;
    cout << "Enter the value of n : ";
    cin >> n;
    vector<process> init(n);
    cout << "PID\tARRIVAL_TIME\tCPU_TIME" << endl;
    for (int i = 0; i < n; i++) {
        init[i].pid = i + 1;
        init[i].cpuTime = rand() % (n * 3) + 1;
        init[i].arrivalTime = rand() % (n * 3) + 1;
        cout << init[i].pid << "\t" << init[i].arrivalTime << "\t\t" << init[i].cpuTime << endl;
    }
    SJF(init);
}