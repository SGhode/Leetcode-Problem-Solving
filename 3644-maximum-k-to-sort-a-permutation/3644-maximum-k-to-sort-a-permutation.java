class Solution {
    public int sortPermutation(int[] nums) {
        int maxK = -1;
        boolean isSorted = true;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                isSorted = false;
                maxK &= nums[i];
            }
        }
        return isSorted ? 0 : maxK;
    }
}