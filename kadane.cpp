#include<bits/stdc++.h>
using namespace std;

int main(){

    cout<<"Enter the number of Elements:- ";
    int n;cin>>n;

    vector<int> arr(n);

    for(int i=0;i<n;i++){
        cin>>arr[i];
    }

    int curr_max = arr[0],max_all = arr[0];

    for(int i=1;i<n;i++){
        curr_max = max(curr_max+arr[i],arr[i]);
        max_all = max(max_all,curr_max);
    }


    cout<<"Maximum Sum Subarray has a sum of :- ";
    cout<<max_all<<endl;
}