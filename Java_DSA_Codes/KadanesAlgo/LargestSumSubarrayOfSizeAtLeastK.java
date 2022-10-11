package PepCodingLvL2Dp.KadanesAlgo;

public class LargestSumSubarrayOfSizeAtLeastK {

    /**
     * Given an array and a number k, find the largest sum of the subarray containing at
     * least k numbers. It may be assumed that the size of array is at-least k.
     *
     * Excellent problem making use of Kadane's Algo.
     * https://practice.geeksforgeeks.org/problems/largest-sum-subarray-of-size-at-least-k3121/1
     */

    /**
     * Intuition :-
     * 1) We first compute maximum sum till every index and store it in an array maxSum[].
     * maxSum[i] give us the maxSum of subarray ending at i.
     * 2) After filling the array, we use the sliding window concept of size k. Keep track of sum of current k elements.
     * To compute sum of current window, remove first element of previous window and add current element. After getting the
     * sum of current window sum(l,r) where l-r+1 = k ,
     * a) we compare the current window sum with max.
     * b) we add the maxSum[l-1] , if it is greater than current max, then update it else not.
     */

    class Compute {

        public long maxSumWithK(long a[], long n, long k)
        {
            //Array storing the maxSum subarray value which ends at index i.
            long[] maxSum = new long[(int)n];
            //Using Kadane's csum variable which gives us the maxSum ending at each index i.
            long csum=a[0];
            maxSum[0]=a[0];
            for(int i=1;i<(int)n;i++){
                if(csum<0) csum=a[i];
                else csum+=a[i];
                maxSum[i]=csum;
            }
            //Sliding window of size k.
            int l=0,r=(int)k;
            //sum of window of size exactly k.
            long exactK=0;
            for(int i=l;i<r;i++){
                exactK+=a[i];
            }
            long ans=exactK;
            while(r<(int)n){
                exactK+=a[r]-a[l];
                //subarray size exactly equal to K
                ans=Math.max(ans,exactK);
                //subarray size more than K
                long moreThanK = exactK + maxSum[l];
                ans=Math.max(ans,moreThanK);
                l++;
                r++;
            }
            return ans;
        }
    }
}
