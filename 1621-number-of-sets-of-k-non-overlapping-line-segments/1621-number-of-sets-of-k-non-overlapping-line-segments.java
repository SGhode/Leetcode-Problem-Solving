class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        long[][] dp = new long[k + 1][2];
        long sum = 0;
        long[][] memo = new long[n][k + 1];
        return combination(n + k - 1, 2 * k, MOD);
    }

    private int combination(int n, int k, long mod) {
        if (k < 0 || k > n) return 0;
        long[] C = new long[k + 1];
        C[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                C[j] = (C[j] + C[j - 1]) % mod;
            }
        }
        return (int) C[k];
    }
}