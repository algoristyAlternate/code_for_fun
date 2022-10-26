class Solution {
public:
    bool isValid(int r,int c,vector<vector<int>>&v,int old_c,vector<vector<bool>>&vis){
        if(r<0 ||c<0 || r>=v.size() ||c>=v[0].size()){
            return false;
        }
        else if(vis[r][c] || v[r][c]!=old_c ){
            return false;
        }
        return true;
    }
    
    void bfs(vector<vector<int>>&v,int old_c,int new_c,int r,int c,vector<vector<bool>>&vis){
        int n=v.size();
        int m=v[0].size();
        
        vector<int>dx={-1,0,1,0};
        vector<int>dy={0,1,0,-1};
        queue<pair<int,int>>q;
        q.push({r,c});
        while(q.size()){
            pair<int,int>p=q.front();
            q.pop();
            r=p.first;
            c=p.second;
            v[r][c]=new_c;
            vis[r][c]=true;
            for(int i=0;i<4;i++){
                if(isValid(r+dx[i],c+dy[i],v,old_c,vis)){
                    q.push({r+dx[i],c+dy[i]});
                }
            }
        }
    }
    
    vector<vector<int>> floodFill(vector<vector<int>>&v, int r, int c, int new_c) {
        int old_c=v[r][c];
        int n=v.size();
        int m=v[0].size();
        vector<vector<bool>>vis(n,vector<bool>(m,false));
        bfs(v,old_c,new_c,r,c,vis);
        return v;
    }
};