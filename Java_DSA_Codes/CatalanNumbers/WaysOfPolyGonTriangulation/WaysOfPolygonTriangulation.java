package PepCodingLvL2Dp.CatalanNumbers.WaysOfPolyGonTriangulation;

import java.util.*;

public class WaysOfPolygonTriangulation {

    /*
        1. You are given a number N, which represents the number of sides in a polygon.
        2. You have to find the total number of ways in which the given polygon can be triangulated.
     */

    public static int solution(int n){
        // write your code here
        //Very Important to see that Base case changes here
        //Humare polygon me minimum sides hogi 2 aur 3
        //for 2 :- 1 triangle possible of 0 size
        //for 3 :- 1 triangle possible itself;
        int[] dp = new int[n];
        dp[2]=dp[3]=1;
        //We will start from 4 sides.
        for(int i=4;i<=n-2;i++){
            for(int j=0;j<i;j++) dp[i]+=dp[j]*dp[i-j-1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(solution(n));
    }

}
