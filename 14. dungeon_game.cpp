class Solution
{
public:
    int calculateMinimumHP(vector<vector<int>> &mat)
    {
        int n = mat.size();
        int m = mat[0].size();

        if (n == 1 && m == 1)
        {
            if (mat[0][0] >= 0)
                return 1;
            else
                return (abs(mat[0][0])) + 1;
        }

        vector<vector<int>> dp(n, vector<int>(m, 0));

        dp[n - 1][m - 1] = max(1 - mat[n - 1][m - 1], 1);

        for (int i = n - 2; i >= 0; --i)
            dp[i][m - 1] = max(dp[i + 1][m - 1] - mat[i][m - 1], 1);

        for (int i = m - 2; i >= 0; --i)
            dp[n - 1][i] = max(dp[n - 1][i + 1] - mat[n - 1][i], 1);

        for (int i = n - 2; i >= 0; --i)
        {
            for (int j = m - 2; j >= 0; --j)
            {
                dp[i][j] = max(min(dp[i + 1][j], dp[i][j + 1]) - mat[i][j], 1);
            }
        }

        return dp[0][0];
    }
};