class Solution {
public:
    bool searchMatrix(vector<vector<int>>& v, int t) {
        int row=v.size();
        int col=v[0].size();
        int i=row-1,j=0;
        while(i>=0 and j<col){
            if(v[i][j]==t) return true;
            else if(v[i][j]>t) i--;
            else j++;
        }
        return false;
    }
};