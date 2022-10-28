#include <bits/stdc++.h>
using namespace std;

/**
 * @brief   We are remapping the values to the ones which are not in blacklist. But this is not as straight as it sounds. 
 *          what exactly we are doing is, we partition the range [0,n-1] into two parts, one which includes all those 
 *          which are not in blacklist and the other including those which are in blacklist. Now, how do we do that....
 *          We are remapping them. See, the size of those which are not in blacklist will be n-size(blacklist). So, we 
 *          start from here, and if a current number is in first range and is in blacklist, we map it to the number in 
 *          second range to the number which is not alrready in blacklist.
 * 
 */

class Solution {
public:
    unordered_map <int,int> mp;
    int N;
    Solution(int n, vector<int>& black) {
        sort(black.begin(),black.end());
        int rem = n-(black.size());
        N = rem;
        int num = rem, i=0;
        while(i<(black.size()) and black[i]<rem){
            if(find(black.begin(),black.end(),num)!=black.end()) num++;
            else mp[black[i++]] = num++; 
        }
    }
    
    int pick() {
        int random = rand()%N;
        if(mp.find(random)!=mp.end()) return mp[random];
        return random;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(n, blacklist);
 * int param_1 = obj->pick();
 */