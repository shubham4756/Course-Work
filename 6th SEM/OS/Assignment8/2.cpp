#include <bits/stdc++.h>
using namespace std;

int main(){
	map <string,set <string> > root;
	while(true){
		cout<<"1. Create Directory\n2. Create File\n3. Delete File\n4. Search File\n5. Display\n6. Exit\n";
		int choice;
		cin>>choice;
		string dir_name,file_name;
		switch(choice){
		    case 1:
		    	cout<< "Enter directory name: ";
		    	cin>>dir_name;
		    	if(root.find(dir_name)==root.end()){
		    	    root[dir_name]={};
		    	    cout<<"Directory Successfully Created\n\n";
		    	} else {
		    		cout<<"Directory already exists! Overwrite existing directory(Y/N)? All your files will be deleted\n";
		    		char ch;
		    		cin>>ch;
		    		if(ch=='Y' or ch=='y'){
		    			root[dir_name]={};
		    			cout<<"Successfully Overwritten\n\n";
		    		} else if(ch=='N' or ch=='n')
		    		    cout<< "Operation Discarded\n\n";
		    		else
		    			cout<<"Invalid Choice\n\n";
		    	}	
		    	break;
		    case 2:
		    	cout<<"Enter directory name: ";
		    	cin>>dir_name;

		    	if(root.find(dir_name)==root.end())
		    	    cout<<"No such directory exists\n\n";
		    	else {
		    		cout<< "Enter file name: ";
		    		cin>>file_name;
		    		if(root[dir_name].find(file_name)==root[dir_name].end()){
		    		    root[dir_name].insert(file_name);
		    		    cout<<"File Successfully Created\n\n";
		    		} else
		    		    cout<< "File already exists in the directory\n\n";
		    	}	

		    	break;
		    case 3:
		    	cout<<"Enter directory name: ";
		    	cin>>dir_name;

		    	if(root.find(dir_name)==root.end())
		    	    cout<<"No such directory exists\n\n";
		    	else {
		    	    cout<<"Enter file name: ";
		    	    cin>>file_name;
		    	    if(root[dir_name].find(file_name)==root[dir_name].end())
		    	        cout<< "No such file exists\n\n";
		    	    else {
		    	        root[dir_name].erase(file_name);
		    	        cout<<"File Successfully Deleted\n\n";
		    		}	
		    	}	
		    	break;
		    case 4:
		    	cout<< "Enter directory name: ";
		    	cin>>dir_name;

		    	if(root.find(dir_name)==root.end())
		    	    cout<<"No such directory exists\n\n";
		    	else {
		    		cout<< "Enter file name: ";
		    		cin>>file_name;
		    		if(root[dir_name].find(file_name)==root[dir_name].end())
		    			cout<< "File not found\n\n";
		    		else
		    			cout<< "File found\n\n";
		    	}	
		    	break;
		    case 5:
		    	for(auto p:root){
		    	    cout<< "Files in directory "<<p.first<<" are: ";
		    	    for(string f_name:p.second)
		    	        cout<<f_name<<" ";	
		    	    if(p.second.empty()) 
						cout<<"No files yet";
		    	    cout<<"\n";
		    	}	
		    	cout<<"\n";
		    	break;
		    case 6:
		    	cout<<"Good Bye!\n\n";
		    	return 0;
		    default:
		    	cout<<"Invalid Choice\n\n";
		    	break;
		}	
	}	
    return 0;
}	