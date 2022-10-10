package PepCodingLvL2Dp.CatalanNumbers;

import java.util.*;

public class CountOfValleysAndMountains {

    /*
            1. You are given a number n, representing the number of upstrokes / and number of downstrokes .
            2. You are required to find the number of valleys and mountains you can create using strokes.
            Note -> At no point should we go below the sea-level. (number of downstrokes should never be more
            than number of upstrokes).
     */

    /*
        calculation of nth catalan number.
     */

    /*
        Same logic for count of Balanced Parenthesis Brackets
        1. You are given a number n, representing the number of opening brackets ( and closing brackets )
        2. You are required to find the number of ways in which you can arrange the brackets if the closing brackets
        should never exceed opening brackets
        e.g.
        for 1, answer is 1 -> ()
        for 2, answer is 2 -> ()(), (())
        for 3, answer is 5 -> ()()(), () (()), (())(), (()()), ((()))
     */

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++) dp[i]+=dp[j]*dp[i-j-1];
        }
        System.out.println(dp[n]);
    }

}
