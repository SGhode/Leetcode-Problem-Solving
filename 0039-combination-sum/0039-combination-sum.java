class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
                List<List<Integer>> result = new ArrayList<>();
        // 1. Sort to enable early pruning
        Arrays.sort(candidates); 
        // 2. Begin backtracking from index 0
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentList, 
                           int[] candidates, int remain, int start) {
        // Base case: target met
        if (remain == 0) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Pruning: if candidate exceeds the remaining sum, stop the loop
            if (candidates[i] > remain) {
                break; 
            }
            
            // Choose the candidate
            currentList.add(candidates[i]);
            
            // Explore: index stays 'i' because we can reuse the same element
            backtrack(result, currentList, candidates, remain - candidates[i], i);
            
            // Backtrack: unchoose the candidate
            currentList.remove(currentList.size() - 1);
        }
    }
}