package PepCodingLvL2Dp.KadanesAlgo;

public class MaxSumSubarray {

    class Solution {
        /**
         * https://leetcode.com/problems/maximum-subarray
         *Kadane's Algorithm
         */
        //O(n) logic.
        public int maxSubArray(int[] nums) {
            //Current Sum
            int csum=nums[0];
            //Overall Max Sum
            int osum=nums[0];
            //startIndex, endIndex, currentStartIndex
            int si=0, ei=0, csi=0;
            int n=nums.length;
            //Each nums[i] have two choices.
            for(int i=1;i<n;i++){
                //Choice 1 : If csum is already negative then nums[i] won't decrease its value
                //by adding in the previous family rather he chose to be self-dependent.
                if(csum<0) {
                    csi=i;
                    csum=nums[i];
                }
                //Choice 2 : If csum is positive then nums[i] will join the family happily.
                else csum+=nums[i];
                if(csum>osum){
                    osum=csum;
                    si=csi;
                    ei=i;
                }
            }
            for(int i=si;i<=ei;i++) System.out.print(nums[i]+" ");
            System.out.println();
            return osum;
        }

        /**
         *O(nlogn)
         * Divide and Conquer Approach
         * https://www.youtube.com/watch?v=3GD-3UZGsVI
         */
        private int divAndConq(int[] nums, int left, int right){
            if(left==right) return nums[left];
            int mid = left + (right-left)/2;
            //left sum subarray
            int lss=divAndConq(nums,left,mid);
            //right sum subarray
            int rss=divAndConq(nums,mid+1,right);
            int lsum=Integer.MIN_VALUE,rsum=Integer.MIN_VALUE,csum=0;
            for(int i=mid;i>=left;i--){
                csum+=nums[i];
                lsum=Math.max(lsum,csum);
            }
            csum=0;
            for(int i=mid+1;i<=right;i++){
                csum+=nums[i];
                rsum=Math.max(rsum,csum);
            }
            //crossing sum subarray.
            int css = lsum+rsum;
            return Math.max(css,Math.max(lss,rss));
        }

    }

}
