class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        int targetSum = totalSum - x;
        
        if (targetSum == 0) {
            return nums.length;
        }
        if (targetSum < 0) {
            return -1;
        }
        
        int maxWindowLength = -1;
        int currentWindowSum = 0;
        int left = 0;        
        for (int right = 0; right < nums.length; right++) {
            currentWindowSum += nums[right];
            while (currentWindowSum > targetSum && left <= right) {
                currentWindowSum -= nums[left];
                left++;
            }
            
            if (currentWindowSum == targetSum) {
                maxWindowLength = Math.max(maxWindowLength, right - left + 1);
            }
        }
        return maxWindowLength == -1 ? -1 : nums.length - maxWindowLength;
    }
}