class Solution {
    public int numMusicPlaylists(int n, int goal, int k) {
        int MOD = 1_000_000_007;
        long[][] dp = new long[goal + 1][n + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= goal; i++) {
            for (int j = 1; j <= n; j++) {
                // Scenario 1: The last song added is a new song
                long newSongChoices = dp[i - 1][j - 1] * (n - j + 1) % MOD;
                dp[i][j] = (dp[i][j] + newSongChoices) % MOD;
                
                // Scenario 2: The last song added is a repeated old song
                if (j > k) {
                    long oldSongChoices = dp[i - 1][j] * (j - k) % MOD;
                    dp[i][j] = (dp[i][j] + oldSongChoices) % MOD;
                }
            }
        }
        
        return (int) dp[goal][n];
    }
}