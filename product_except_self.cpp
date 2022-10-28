class Solution {
public:
    vector<int> productExceptSelf(vector<int>& v) {
        int n=v.size();
        vector<int>v1(n,1);
        int p=1;
        for(int i=0;i<n;i++){
            v1[i]=p;
            p*=v[i];
        }
        p=1;
        for(int i=n-1;i>=0;i--){
            v1[i]*=p;
            p*=v[i];
        }
        return v1;
        
    }
};