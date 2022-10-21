public class Solution {
    public int paint(int A, int B, ArrayList<Integer> C) {
        int mod = 10000003;
        B = B%mod;
        int n = C.size();
        long lo = 0, hi =0;
        for(Integer val:C){
            lo = Math.max(lo,val);
            hi+=val;
        }
        if(A==n){
            return (int) ((lo%mod)*B)%mod;
        }
        long ans = 0;
        while(lo<=hi){
            long mid = lo + (hi-lo)/2;
            if(isValid(A,C,mid)){
                ans = mid;
                hi = mid-1;
            }
            else{
                lo = mid+1;
            }
        }
        ans = ((ans%mod)*B)%mod;
        // System.out.println(ans);
        return (int) ans;
    }

    boolean isValid(int A, ArrayList<Integer> C,long mid){
        int painters=1;
        long sum=0;
        for(int i=0;i<C.size();i++){
            sum+=C.get(i);
            if(sum>mid){
                painters++;
                sum = C.get(i);
            }
        }
        return painters<=A;
    }
}