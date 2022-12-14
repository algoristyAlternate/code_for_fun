package PepCodingLvL2Dp.CatalanNumbers;

public class IntersectingChordsInACircle_IB {
    public class Solution {
        public int chordCnt(int A) {
            int mod=1000000007;
            long dp[] = new long[A+1];
            dp[0]=dp[1]=1;
            for(int i=2;i<=A;i++){
                for(int j=0;j<i;j++){
                    dp[i]=((dp[i]%mod) + ((dp[j]%mod)*(dp[i-j-1]%mod))%mod)%mod;
                }
            }
            return (int)dp[A];
        }
    }
}
