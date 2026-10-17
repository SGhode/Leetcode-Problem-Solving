class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] sortedNums = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedNums[i] = nums[i];
        }
        Arrays.sort(sortedNums, (a, b) -> {
            int sumA = getDigitSum(a);
            int sumB = getDigitSum(b);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(a, b);
        });
        
        Map<Integer, Integer> targetPos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            targetPos.put(sortedNums[i], i);
        }
        
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || targetPos.get(nums[i]) == i) {
                continue;
            }
            int cycleSize = 0;
            int currentIdx = i;
            
            while (!visited[currentIdx]) {
                visited[currentIdx] = true;
                currentIdx = targetPos.get(nums[currentIdx]);
                cycleSize++;
            }
            if (cycleSize > 0) {
                totalSwaps += (cycleSize - 1);
            }
        }
        return totalSwaps;
    }
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}