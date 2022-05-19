#include <bits/stdc++.h>
using namespace std;
int main(){
	string a,b;
    cout<<"Input Stuffed String ";
    cin>>a;
	//unstuffing
	b=""; 
    int n=a.size()-5,zero=0,one=0;
	zero = 0,one = 0;
	for(int i = 5;i<n;i++) {
		if(a[i] == '0') {
			zero = 1; one = 0;
		} else if(zero != 0) {
				one++;
		}
		b+=a[i];
		if(zero == 1 && one == 5) {
			zero = 0; one = 0;
			i++;
		}
	}
	cout<<"After unstuffing Frame length: "<<b.size()<<endl;
	cout<<"After unstuffing Frame: "<<b<<endl;
    return 0;
}