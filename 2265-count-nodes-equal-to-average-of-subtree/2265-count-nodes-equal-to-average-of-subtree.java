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

    // Returns an array: [sum_of_subtree, number_of_nodes_in_subtree]
    private int[] dfs(TreeNode node) {
        // Base case: empty node contributes 0 to sum and 0 to count
        if (node == null) {
            return new int[]{0, 0};
        }

        // Traverse left and right children
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Calculate sum and count for the current subtree
        int currentSum = left[0] + right[0] + node.val;
        int currentCount = left[1] + right[1] + 1;

        // Check if the current node's value equals the average of its subtree
        if (node.val == currentSum / currentCount) {
            matchingNodesCount++;
        }

        // Return the accumulated sum and count to the parent node
        return new int[]{currentSum, currentCount};
    }
}