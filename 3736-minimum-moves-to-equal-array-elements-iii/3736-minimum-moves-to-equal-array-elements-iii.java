class Solution {
    public int minMoves(int[] nums) {
        int maxVal = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        int moves = 0;
        for (int num : nums) {
            moves += (maxVal - num);
        }
        
        Gson: return moves;
    }
}