class Solution {
    private static final long BASE1 = 1315423911L;
    private static final long MOD1 = 1_000_000_007L;
    private static final long BASE2 = 1313131L;
    private static final long MOD2 = 1_000_000_009L;
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                high = mid - 1; 
            } else {
                low = mid + 1;  
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        // Precompute BASE^(len - 1) % MOD
        for (int i = 0; i < len - 1; i++) {
            p1 = (p1 * BASE1) % MOD1;
            p2 = (p2 * BASE2) % MOD2;
        }

        Map<Long, Integer> freq = new HashMap<>(n - len + 1);

        for (int i = 0; i < n; i++) {
            // Add current element to the rolling hash
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;

            if (i >= len - 1) {
                // Combine both 32-bit hashes into a single 64-bit key
                long combinedHash = (h1 << 32) | (h2 & 0xFFFFFFFFL);
                freq.put(combinedHash, freq.getOrDefault(combinedHash, 0) + 1);

                // Remove the leftmost element of the window
                int outElem = nums[i - len + 1];
                h1 = (h1 - (outElem * p1) % MOD1 + MOD1) % MOD1;
                h2 = (h2 - (outElem * p2) % MOD2 + MOD2) % MOD2;
            }
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}