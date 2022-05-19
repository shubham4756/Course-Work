#include <bits/stdc++.h>
using namespace std;
int main(){
	cout<<"Enter Input Frame: ";
    string s,res="";
    cin>>s;
    //stuffing '-' special/flag char and ending delimiter = '$'
    int n=s.size();
    for(int i=0;i<n;i++){
        if(s[i]=='$' || s[i]=='-'){
            res+='-';
        }
        res+=s[i];
    }
    cout<<"before stuffing Frame length: "<<n<<endl;
    cout<<"After stuffing Frame length: "<<res.size()<<endl;
    cout<<"After stuffing Frame: "<<res<<endl;
    //unstuffing
    s="";
    n=res.size();
    for(int i=0;i<n;i++){
        if(res[i]=='-') i++;
        if(i<n)
            s+=res[i];
    }
    cout<<"After unstuffing Frame length: "<<s.size()<<endl;
    cout<<"After unstuffing Frame: "<<s<<endl;
    return 0;
}