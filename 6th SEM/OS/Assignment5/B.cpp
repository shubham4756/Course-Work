#include <bits/stdc++.h>
using namespace std;

class process {
public:
    int pID;
    int cpuTime;
    int arrivalTime;
};

void SRTF_Algo(vector<process>  p) {
    int n, cur;
    int curr_time = 0, cur_cpu_time = INT_MAX;
    vector<pair<int, process> > ans;
    process noProcess;
    noProcess.pID = noProcess.cpuTime = noProcess.arrivalTime = -1;

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
            ans.push_back({curr_time, p[cur]});
            p[cur].cpuTime -= 1;
            if (p[cur].cpuTime == 0)
                p.erase(p.begin() + cur);
            curr_time++;
        } else {
            curr_time++;
            ans.push_back({curr_time, noProcess});
        }
    }
    cout << endl << "CPU_TIME\tPID\tARRIVAL_TIME\tCPU_TIME" << endl;
    cout << ans[0].first << "\t\t" << ans[0].second.pID <<"\t\t" << ans[0].second.cpuTime <<"\t\t" << ans[0].second.arrivalTime  << endl;
    for (int i = 1; i < ans.size(); i++) {
        if (ans[i - 1].second.pID != ans[i].second.pID)
            cout << ans[i].first << "\t\t" << ans[i].second.pID <<"\t\t" << ans[i].second.cpuTime <<"\t\t" << ans[i].second.arrivalTime << endl;
    }
    cout << "End -->-->--> " << ans[ans.size() - 1].first + 1 << endl;
}

int main() {
    int n;
    cout << "Enter the value of n : \n";
    cin >> n;
    vector<process> init(n);
    cout << "PID\tARRIVAL_TIME\tCPU_TIME" << endl;
    for (int i = 0; i < n; i++) {
        init[i].pID = i + 1;
        init[i].cpuTime = rand() % (n * 5) + 1;
        init[i].arrivalTime = rand() % (n * 5) + 1;
        cout << init[i].pID << '\t' << init[i].arrivalTime << "\t\t" << init[i].cpuTime << endl;
    }
    SRTF_Algo(init);
}