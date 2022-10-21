package PepCodingLvL2Dp.RecMem;

public class CherryPickup {

    /**
     * You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.
     *
     * 0 means the cell is empty, so you can pass through,
     * 1 means the cell contains a cherry that you can pick up and pass through, or
     * -1 means the cell contains a thorn that blocks your way.
     * Return the maximum number of cherries you can collect by following the rules below:
     *
     * Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells
     * (cells with value 0 or 1).
     * After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
     * When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
     * If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
     */

    class Solution {

    /*
        A greedy apporach that comes in mind could be
        1.To take the max cherry path starting from (0,0) to (m-1,n-1)
        2.After above now we take max cherry path starting from (m-1,n-1) to (0,0).
        We consider our answer to be (1) + (2)
        But this will give us the wrong answer.
        Consider the below grid
        1   1   1   0   0   0
        0   0   1   0   0   1
        1   0   1   0   0   0
        0   0   1   0   0   0
        0   0   1   1   1   1

        for 1.  our path would be RRRDDDDRRR giving the max cherry count to be = 10

        After this our grid becomes

        0   0   0   0   0   0
        0   0   0   0   0   1
        1   0   0   0   0   0
        0   0   0   0   0   0
        0   0   0   0   0   0

        Now, for 2.  we can only pickup one cherry max

        Our total answer coes up to be  = 10 + 1 = 11

        But actual answer would be 12

        correct path for 1 : RRRDRRRDDD -> cheery count = 6

        After this our grid becomes

        0   0   0   0   0   0
        0   0   0   0   0   0
        1   0   1   0   0   0
        0   0   1   0   0   0
        0   0   1   1   1   0

        correct path for 2 : LLLLUULLUU -> cherry count = 6

        Our total answer now = 6+6 = 12

        Reason of failing  :
        As we can see we can move only Right and Down for (0,0) to (m-1,n-1) and
        only Left and Up in case of (m-1,n-1) to (0,0)

        We cannot move in all four directions thus it restricts us in step 2 to pick up only
        one cherry to be maximum.
    */


        public int cherryPickup(int[][] grid) {
            int m = grid.length, n = grid[0].length;

            //For Backtracking solution
            /*
            maxCherry=0;
            rd(0,0,grid,0,m,n);
            return maxCherry;
            */

                //For O(n^4) DP solution
            /*
            dp1  = new Integer[m][n][m][n];
            int ans=solve1(0,0,0,0,grid,0,m,n);
            if(ans==Integer.MIN_VALUE) return 0;
            return ans;
            */

            //For O(N^3) Dp solution
            dp2  = new Integer[m][n][m];
            int ans=solve2(0,0,0,grid,0,m,n);
            if(ans==Integer.MIN_VALUE) return 0;
            return ans;
        }

        /*
          Backtracking Approach - (Brute force) gives TLE
          for each path from (0,0) to (m-1,n-1)
              for each path from (m-1,n-1) to (0,0)
                  ans = Max(cherriesPicked, ans);
      */
        int maxCherry;
        //DFS from (0,0) to (m-1,n-1)
        private void rd(int x, int y, int[][] g, int cpsf, int m, int n){
            if(x>=m||y>=n||g[x][y]==-1) return;
            if(x==m-1&&y==n-1){
                int cherries=g[x][y];
                g[x][y]=0;
                lu(x,y,g,cpsf+cherries);
                g[x][y]=cherries;
                return;
            }
            int cherries = g[x][y];
            g[x][y]=0;
            rd(x+1,y,g,cpsf+cherries,m,n);
            rd(x,y+1,g,cpsf+cherries,m,n);
            g[x][y]=cherries;
        }

        //DFS from (m-1,n-1) to (0,0)
        private void lu(int x, int y, int[][] g, int cpsf){
            if(x<0||y<0||g[x][y]==-1) return;
            if(x==0&&y==0){
                maxCherry=Math.max(maxCherry,cpsf);
                return;
            }
            int cherries = g[x][y];
            g[x][y]=0;
            lu(x-1,y,g,cpsf+cherries);
            lu(x,y-1,g,cpsf+cherries);
            g[x][y]=cherries;
        }


        //O(N^4) Optimal Approach - Dynamic Programming

        /*
            What??
            We state that going from (0,0) to (m-1,n-1) and coming back from (m-1,n-1) to (0,0)
            is equivalent to two persons starting simultaneously from (0,0) to (m-1,n-1).
        */

        /*
            How??
        */
        private Integer[][][][] dp1;
        private int solve1(int x1, int y1, int x2, int y2, int[][] g, int cpsf, int m, int n){
            if(x1>=m||x2>=m||y1>=n||y2>=n||g[x1][y1]==-1||g[x2][y2]==-1) return Integer.MIN_VALUE;
            if(x1==m-1&&y1==n-1) return g[x1][y1];
            //If both p1 and p2 reach (m-1,n-1)
            if(dp1[x1][y1][x2][y2]!=null) return dp1[x1][y1][x2][y2];
            int cherries=0;
            //If both p1 and p2 are at same position then we need to add the cherry only once.
            if(x1==x2&&y1==y2){
                cherries+=g[x1][y1];
            }
            //If p1 and p2 are at different positions then repective cherries can be added.
            else{
                cherries+=g[x1][y1]+g[x2][y2];
            }
            //4 possibilites for p1 and p2 from each point
            int dd=solve1(x1+1,y1,x2+1,y2,g,cpsf+cherries,m,n); //both moves down
            int dr=solve1(x1+1,y1,x2,y2+1,g,cpsf+cherries,m,n); //p1 moves down and p2 moves right
            int rr=solve1(x1,y1+1,x2,y2+1,g,cpsf+cherries,m,n); //both moves right
            int rd=solve1(x1,y1+1,x2+1,y2,g,cpsf+cherries,m,n); //p1 moves right and p2 moves down
            int max=Math.max(Math.max(dd,dr), Math.max(rr,rd));
            if(max==Integer.MIN_VALUE) return dp1[x1][y1][x2][y2]=max;
            return dp1[x1][y1][x2][y2]=cherries+=max;
        }


        /*
            Now can we further optimize this solution in terms of DP space
            Can we reduce it to O(N^3)
            Yes, we can.
            What and Why??
            As we can see p1 and p2 both makes 1 move simultaneously.
            We can see that x1+y1 = x2 + y2 always holds true.
            So we can get the 4th variable reduces i.e. y2
            y2 = x1+y1-x2
        */

        /*
            How O(n^3)
        */

        private Integer[][][] dp2;
        private int solve2(int x1, int y1, int x2, int[][] g, int cpsf, int m, int n){
            int y2 = x1+y1+-x2;
            if(x1>=m||x2>=m||y1>=n||y2>=n||g[x1][y1]==-1||g[x2][y2]==-1) return Integer.MIN_VALUE;
            if(x1==m-1&&y1==n-1) return g[x1][y1];
            //If both p1 and p2 reach (m-1,n-1)
            if(dp2[x1][y1][x2]!=null) return dp2[x1][y1][x2];
            int cherries=0;
            //If both p1 and p2 are at same position then we need to add the cherry only once.
            if(x1==x2&&y1==y2){
                cherries+=g[x1][y1];
            }
            //If p1 and p2 are at different positions then repective cherries can be added.
            else{
                cherries+=g[x1][y1]+g[x2][y2];
            }
            //4 possibilites for p1 and p2 from each point
            int dd=solve2(x1+1,y1,x2+1,g,cpsf+cherries,m,n); //both moves down
            int dr=solve2(x1+1,y1,x2,g,cpsf+cherries,m,n);   //p1 moves down and p2 moves right
            int rr=solve2(x1,y1+1,x2,g,cpsf+cherries,m,n);  //both moves right
            int rd=solve2(x1,y1+1,x2+1,g,cpsf+cherries,m,n); //p1 moves right and p2 moves down

            //We take maximum of 4 possiblities
            int max=Math.max(Math.max(dd,dr), Math.max(rr,rd));
            if(max==Integer.MIN_VALUE) return dp2[x1][y1][x2]=max;
            return dp2[x1][y1][x2]=cherries+=max;
        }
    }

}
