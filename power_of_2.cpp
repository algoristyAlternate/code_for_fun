class Solution {
public:
    bool isPowerOfTwo(int n) {
        if(n<=0) {
            return false;
        }    
        if(n>0){
            n=(n&(n-1));
            if(n==0) return true;
            else return false;
         }
        return true;
    }
};