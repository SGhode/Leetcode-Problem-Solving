class Solution {
    private int[][][] nextR; // Stores target row after reflections
    private int[][][] nextC; // Stores target col after reflections
    private int m, n;

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        long MOD = 1_000_000_007;

        // Memoization tables for directions: 0 = right, 1 = down
        nextR = new int[m][n][2];
        nextC = new int[m][n][2];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nextR[i][j][0] = nextR[i][j][1] = -2;
                nextC[i][j][0] = nextC[i][j][1] = -2;
            }
        }

        long[][] dp = new long[m][n];
        
        // Base case: If destination itself isn't a mirror, there is 1 way out
        if (grid[m - 1][n - 1] == 0) {
            dp[m - 1][n - 1] = 1;
        }

        // DP transition from bottom-right back to top-left
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (r == m - 1 && c == n - 1) continue;
                if (grid[r][c] == 1) continue; 

                // Option 1: Attempt to move Right
                int nxtR_r = getNextRow(grid, r, c + 1, 0);
                int nxtC_r = getNextCol(grid, r, c + 1, 0);
                long waysR = (nxtR_r != -1) ? dp[nxtR_r][nxtC_r] : 0;

                // Option 2: Attempt to move Down
                int nxtR_d = getNextRow(grid, r + 1, c, 1);
                int nxtC_d = getNextCol(grid, r + 1, c, 1);
                long waysD = (nxtR_d != -1) ? dp[nxtR_d][nxtC_d] : 0;

                dp[r][c] = (waysR + waysD) % MOD;
            }
        }

        return (int) dp[0][0];
    }

    private int getNextRow(int[][] grid, int r, int c, int dir) {
        if (r < 0 || r >= m || c < 0 || c >= n) return -1;
        if (r == m - 1 && c == n - 1) return r;

        if (grid[r][c] == 1) {
            if (nextR[r][c][dir] != -2) return nextR[r][c][dir];
            
            int res = (dir == 0) ? getNextRow(grid, r + 1, c, 1) : getNextRow(grid, r, c + 1, 0);
            nextR[r][c][dir] = res;
            return res;
        }
        return r;
    }

    private int getNextCol(int[][] grid, int r, int c, int dir) {
        if (r < 0 || r >= m || c < 0 || c >= n) return -1;
        if (r == m - 1 && c == n - 1) return c;

        if (grid[r][c] == 1) {
            if (nextC[r][c][dir] != -2) return nextC[r][c][dir];
            
            int res = (dir == 0) ? getNextCol(grid, r + 1, c, 1) : getNextCol(grid, r, c + 1, 0);
            nextC[r][c][dir] = res;
            return res;
        }
        return c;
    }
}
