package PepCodingLvL2Dp.GameTheory;

public class PredictTheWinner_OptimalStrategyForAGame {
    /**
     * PredictTheWinner
     * You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
     *
     * Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0.
     * At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1])
     * which reduces the size of the array by 1. The player adds the chosen number to their score. The game ends when there are
     * no more elements in the array.
     *
     * Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner,
     * and you should also return true. You may assume that both players are playing optimally.
     *
     * https://leetcode.com/problems/predict-the-winner/
     */

    /**
     * OptimalStrategyForAGame
     * You are given an array A of size N. The array contains integers and is of even length.
     * The elements of the array represent N coin of values V1, V2, ....Vn. You play against an opponent in an alternating way.
     *
     * In each turn, a player selects either the first or last coin from the row, removes it from the row permanently,
     * and receives the value of the coin.
     *
     * You need to determine the maximum possible amount of money you can win if you go first.
     * Note: Both the players are playing optimally.
     * Return type is long so keep in mind overflow must not happen.
     *
     * https://practice.geeksforgeeks.org/problems/optimal-strategy-for-a-game-1587115620/1
     */

    /*
         How to Approach ?
         We will calculate the maxScore which can be achieved by player 1 while player 2 plays optimally.
         Now the score of player 2 = total sum - maxScore
         we can see if (maxScore>=total sum - maxScore) player 1 wins.

         How to calculate maxScore ?

         Consider f(i,j) to be function returning the maxScore achieved in range [i,j]

                                                         player2 chooses      i+1         j
                                     player 1 chose i -> [i+1,j] - >  min( f(i+2,j), f(i+1,j-1) )
                                    /      nums[i]+        player 2 chooses such that the
         f(i,j) : max(two choices) /                      player 1 will get minimum score
                                   \       nums[j]+       from rest remaining.
                                    \  player 1 chose j -> [i,j-1] ->  min( f(i+1,j-1), f(i,j-2) )
                                                           player2 chooses      i          j-1
     */
    class Solution {
        // private Integer[][] dp;
        public boolean PredictTheWinner(int[] nums) {
            int n = nums.length;
            int sum=0;
            for(int val:nums) sum+=val;
            // dp=new Integer[n][n];
            // int maxScore = solve(nums,0,n-1);
            // return maxScore>=sum-maxScore;

            //Tabulation
            int dp[][] = new int[n][n];
            //GAPp strategy is used.
            for(int g=0;g<n;g++){
                for(int i=0,j=g;j<n;i++,j++){
                    if(g==0) dp[i][j]=nums[i];
                    else if(g==1){
                        dp[i][j] = Math.max(nums[i],nums[j])+dp[i+1][j-1];
                    }
                    else{
                        int l = nums[i] + Math.min(dp[i+1][j-1],dp[i+2][j]);
                        int r = nums[j] + Math.min(dp[i+1][j-1],dp[i][j-2]);
                        dp[i][j] = Math.max(l,r);
                    }
                }
            }
            return dp[0][n-1]>=sum-dp[0][n-1];
        }

        //Recursive + Memoization
    /*
    private int solve(int[] a, int i, int j){
        if(i>j) return 0;
        if(dp[i][j]!=null) return dp[i][j];
        int l = a[i] + Math.min(solve(a,i+1,j-1),solve(a,i+2,j));
        int r = a[j] + Math.min(solve(a,i+1,j-1),solve(a,i,j-2));
        return dp[i][j]=Math.max(l,r);
    }
    */
    }
}
