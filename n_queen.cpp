class Solution {
public:
    void helper(int col,vector<string>&v,vector<vector<string>>&ans,int n,vector<bool>&left,vector<bool>&ud,vector<bool>&ld){
        if(col==n){
            ans.push_back(v);
            return ;
        }
        int le,ldg,udg;
        for(int row=0;row<n;row++){
            le=row,ldg=row+col,udg=n-1+col-row;
            if(!left[le] && !ud[udg] && !ld[ldg]){
                v[row][col]='Q';
                left[le]=true;
                ud[udg]=true;
                ld[ldg]=true;
                helper(col+1,v,ans,n,left,ud,ld);
                v[row][col]='.';
                left[le]=false;
                ud[udg]=false;
                ld[ldg]=false;
            }
        }
    }
    
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>>ans;
        vector<string>v(n);
        string s(n,'.');
        for(int i=0;i<n;i++){
            v[i]=s;
        }
        vector<bool>left(n,false);
        vector<bool>ud(2*n-1,false);
        vector<bool>ld(2*n-1,false);
        helper(0,v,ans,n,left,ud,ld);
        return ans;
    }
};