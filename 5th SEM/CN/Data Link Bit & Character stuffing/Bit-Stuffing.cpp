#include <bits/stdc++.h>
using namespace std;
int main(){
	string a,b;
	cout<<"Enter Input Frame: ";
	cin>>a;
	//stuffing
	int n=a.size(),id1=0,zero=0,one=0;
	for(int i=0;i<n;i++){
		if(a[i] == '0') {
			zero = 1; one = 0;
		} else if(zero != 0) {
				one++;
		}
		b+=a[i];
		if(zero == 1 && one == 5) {
			zero = 0; one = 0;
			b+='0';
		}
	}
	cout<<"before stuffing Frame length: "<<n<<endl;
	cout<<"After stuffing Frame length: "<<b.size()<<endl;
	cout<<"After stuffing Frame: "<<b<<endl;
	//unstuffing
	a=""; n=b.size();
	zero = 0,one = 0;
	for(int i = 0;i<n;i++) {
		if(b[i] == '0') {
			zero = 1; one = 0;
		} else if(zero != 0) {
				one++;
		}
		a+=b[i];
		if(zero == 1 && one == 5) {
			zero = 0; one = 0;
			i++;
		}
	}
	cout<<"After unstuffing Frame length: "<<a.size()<<endl;
	cout<<"After unstuffing Frame: "<<a<<endl;
    return 0;
}