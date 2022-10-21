/*
1st Approach that striked me is using priority queue. 
Basically, dijkstra kind of approach but it's very costly wrt time

class Solution {
public:
    
    bool isvalid(int i, int j, int n, int m){
        if(i < 0 or j < 0 or i >= n or j >= m)
            return false;
        
        return true;
    }
    
    int longestIncreasingPath(vector<vector<int>>& mat){
        int n = mat.size(), m = mat[0].size();
        
        vector<vector<int>> dp(n, vector<int> (m, 1));
        
        int dx[] = {-1, 0, 0, 1};
        int dy[] = {0, 1, -1, 0};
        
        priority_queue<pair<int, pair<int, int>>> pq;
        for(int i  = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                pq.push({1, {i, j}});
            }
        }
        
        int ans = 1;
        while(!pq.empty()){
            int dis = pq.top().first;
            int i = pq.top().second.first;
            int j = pq.top().second.second;
            
            ans = max(ans, dis);
            
            pq.pop();
            
            for(int k = 0; k < 4; k++){
                int x = i + dx[k], y = j + dy[k];
                if(isvalid(x, y, n, m)){
                    if(mat[x][y] > mat[i][j] and dp[x][y] < 1 + dis){
                        dp[x][y] = 1 + dis;
                        pq.push({dis + 1, {x, y}});
                    }
                }
            } 
        }
        
        return ans;
    }
};

*/

// Appraoch 2: Using DP

    int dx[] = {-1, 0, 0, 1};
    int dy[] = {0, 1, -1, 0};

class Solution{
    public:
    
    bool isvalid(int i, int j, int n, int m){
        if(i < 0 or j < 0 or i >= n or j >= m)
            return false;
        
        return true;
    }
    
    int rec(vector<vector<int>>& mat, int i, int j, int n, int m, int par, vector<vector<int>> &dp){
        if(!isvalid(i, j, n, m) or mat[i][j] <= par)
            return 0;
        
        if(dp[i][j] != -1){
            
            return dp[i][j];
        }
        
        for(int k = 0; k < 4; k++){
            int x = i + dx[k], y = j + dy[k];
            dp[i][j] = max(dp[i][j], 1 + rec(mat, x, y, n, m, mat[i][j], dp));
        }
        
        return dp[i][j];
    }
    
    int longestIncreasingPath(vector<vector<int>>& mat){
        int n = mat.size(), m = mat[0].size();
        
        vector<vector<int>> dp(n, vector<int> (m, -1));
        
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                ans = max(ans, rec(mat, i, j, n, m, -1, dp));
            }
        }
        
        return ans;
    }
    
};
