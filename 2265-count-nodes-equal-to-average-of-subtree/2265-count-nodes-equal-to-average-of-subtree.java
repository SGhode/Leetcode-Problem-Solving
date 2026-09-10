/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchingNodesCount = 0;
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingNodesCount;
    }
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] leftSubtree = dfs(root.left);
        int[] rightSubtree = dfs(root.right);
        int currentSum = root.val + leftSubtree[0] + rightSubtree[0];
        int currentNodeCount = 1 + leftSubtree[1] + rightSubtree[1];
        int currentAverage = currentSum / currentNodeCount;
        if (root.val == currentAverage) {
            matchingNodesCount++;
        }
        return new int[]{currentSum, currentNodeCount};
    }
}