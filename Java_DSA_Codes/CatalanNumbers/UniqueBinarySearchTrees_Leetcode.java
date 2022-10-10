package PepCodingLvL2Dp.CatalanNumbers;
import java.math.*;
public class UniqueBinarySearchTrees_Leetcode {

    class Solution {
        public int numTrees(int n) {
            //O(n) logic with formula 2nCn/(n+1) using BigInteger
        /*
        BigInteger res = new BigInteger("1");
        for(int i=0;i<n;i++){
            res=res.multiply(BigInteger.valueOf(2*n-i));
            res=res.divide(BigInteger.valueOf(i+1));
        }
        res = res.divide(BigInteger.valueOf(n+1));
        return Integer.parseInt(res.toString());
        */
            int dp[] = new int[n+1];
            dp[0]=1;
            dp[1]=1;
            for(int i=2;i<=n;i++){
                for(int j=0;j<i;j++){
                    dp[i]+=dp[j]*dp[i-1-j];
                }
            }
            return dp[n];
        }
    }
}
