package PepCodingLvL2Dp.CatalanNumbers;
import java.util.*;
import java.math.*;
public class NthCatalanNumber {

    class Solution
    {
        //Function to find the nth catalan number.
        public BigInteger findCatalan(int n)
        {
            //Your code here
        /*
        BigInteger[] dp = new BigInteger[n+1];
        dp[0]=new BigInteger("1");
        dp[1]=new BigInteger("1");
        for(int i=2;i<=n;i++){
            dp[i] = new BigInteger("0");
            for(int j=0;j<i;j++){
                dp[i]=dp[i].add(dp[j].multiply(dp[i-j-1]));
            }
        }
        return dp[n];
        */
            BigInteger res = new BigInteger("1");
            for(int i=0;i<n;i++){
                res=res.multiply(BigInteger.valueOf(2*n-i));
                res=res.divide(BigInteger.valueOf(i+1));
            }
            res = res.divide(BigInteger.valueOf(n+1));
            return res;
        }
    }
}
