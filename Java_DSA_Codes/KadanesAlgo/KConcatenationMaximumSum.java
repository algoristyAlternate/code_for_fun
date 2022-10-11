package PepCodingLvL2Dp.KadanesAlgo;

public class KConcatenationMaximumSum {
    /**
     * Given an integer array arr and an integer k, modify the array by repeating it k times.
     *
     * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
     *
     * Return the maximum sub-array sum in the modified array.
     *
     * V.V.Imp **** Note that the length of the sub-array can be 0 and its sum in that case is 0.
     *
     * As the answer can be very large, return the answer modulo 109 + 7.
     *
     * https://leetcode.com/problems/k-concatenation-maximum-sum/
     */

    /*
        Intuition:
        if(k==1)
            Only one copy is there so just apply Kadane's and return maxSum.
        else
            we need to apply Kadane's on first two copies and get maxSum
            if(k==2)
                simply return double Kadane's maxSum
            else
                Two possibilities for total sum of the array
                sum<=0
                    we will never consider the (k-2) copies because either they will contribute negative or zero.
                    Answer will lie in first two copies itself.
                    simply return double Kadane's maxSum
                sum>0
                    Now here the (k-2) copies can contribute there total sum
                    we will return double Kadane's maxSum + (k-2)*sum
     */

    class Solution {
        public int kConcatenationMaxSum(int[] a, int k) {
            int n = a.length;
            int mod=(int)1e9+7;
            if(k==1){
                //Single Kadane
                //***We need to start from 0 because empty subarray can also be the answer with maxSum as 0.
                int csum=0,osum=0;
                for (int val : a) {
                    if (csum < 0) csum = val;
                    //XXXXX Don't take mod here XXXXX****
                    else csum += val;
                    osum = Math.max(csum, osum);
                }
                return osum%mod;
            }
            else{
                //Double Kadane
                //***We need to start from 0 because empty subarray can also be the answer with maxSum as 0.
                int csum=0,osum=0;
                for(int j=1;j<=2;j++) {
                    for (int val : a) {
                        if (csum < 0) csum = val;
                        //XXXXX Don't take mod here XXXXX****
                        else csum += val;
                        osum = Math.max(csum, osum);
                    }
                }
                //*****Imp to do mod here*****
                if(k==2) return osum%mod;
                int sum=0;
                for(int val:a) sum=((sum%mod) +(val%mod))%mod;
                //*****Imp to do mod here*****
                if(sum<=0) return osum%mod;
                for(int i=0;i<k-2;i++) osum=((osum%mod) + (sum%mod))%mod;
                //*****Imp to do mod here*****
                return osum%mod;
            }
        }
    }
}
