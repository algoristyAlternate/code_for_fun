package PepCodingLvL2Dp.KadanesAlgo;

public class MaximumDifferenceOfZerosAndOnesInBinaryString {

    /**
     * Given a binary string S consisting of 0s and 1s. The task is to find the maximum
     * difference of the number of 0s and the number of 1s (number of 0s – number of 1s)
     * in the substrings of a string.
     * Note: In the case of all 1s, the answer will be -1.
     */

    class Solution {

        /*
            ***IMP.
            Kadane's Algo applicaton  Maximum Sum-Subaarray
            How??
            Consider 0 as 1 and 1 as -1.
            Always keep this trick in mind.
        */
        int maxSubstring(String S) {
            // code here
            if(!S.contains("0")) return -1;
            char[] c = S.toCharArray();
            int csum, osum;
            csum=osum=c[0]=='0'?1:-1;
            int n = c.length;
            for(int i=1;i<n;i++){
                if(csum<0) csum=(c[i]=='0')?1:-1;
                else csum+=(c[i]=='0')?1:-1;
                osum=Math.max(osum,csum);
            }
            return osum;
        }
    }
}
