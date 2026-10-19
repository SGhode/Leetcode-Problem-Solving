class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int countTarget = 0;
        int smallerThanTarget = 0;
        
        for (int num : nums) {
            if (num < target) {
                smallerThanTarget++;
            } else if (num = target) {
                countTarget++;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < countTarget; i++) {
            result.add(smallerThanTarget + i);
        }
        
        return result;
    }
}