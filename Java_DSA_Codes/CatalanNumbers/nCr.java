package PepCodingLvL2Dp.CatalanNumbers;

public class nCr {
    class Solution{
        private int mod;
        private Integer[][] dp;
        int nCr(int n, int r)
        {
            // code here
            mod=1000000007;
            // dp=new Integer[n+1][r+1];
            // return nCrHelper(n,r);
            if(r>n) return 0;
            if(n-r<r)r=n-r;
            long res = 1;
            for(int i=0;i<r;i++){
                res=((res%mod)*((n-i)%mod))%mod;
                //Modular Inversion.
                long b = pow(i+1,mod-2)%mod;
                res=((res%mod)*(b%mod))%mod;
            }
            return (int)res;
        }

        /*
            
         */

        //Binary Exponentiation.
        private long pow(long a, long b){
            if(b==1) return a;
            if(b==0) return 1;
            long x = pow(a,b/2)%mod;
            x=((x%mod)*(x%mod))%mod;
            if((b&1)==0) return x%mod;
            else return ((a%mod)*(x%mod))%mod;
        }

        private int nCrHelper(int n, int r){
            if(r==0||r==n) return 1;
            if(r>n) return 0;
            if(dp[n][r]!=null) return dp[n][r];
            return dp[n][r]=(nCrHelper(n-1,r)%mod+nCrHelper(n-1,r-1)%mod)%mod;
        }
    }
}
