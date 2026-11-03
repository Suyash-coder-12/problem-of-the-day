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
    public TreeNode createBinaryTree(int[][] descriptions) {
        // Direct-indexed storage for node values in range [1, 100000]
        TreeNode[] nodes = new TreeNode[100001];
        boolean[] hasParent = new boolean[100001];

        // Step 1: Create nodes, wire parent-child pointers, and mark children
        for (int[] desc : descriptions) {
            int pVal = desc[0];
            int cVal = desc[1];
            int isLeft = desc[2];

            if (nodes[pVal] == null) {
                nodes[pVal] = new TreeNode(pVal);
            }
            if (nodes[cVal] == null) {
                nodes[cVal] = new TreeNode(cVal);
            }

            if (isLeft == 1) {
                nodes[pVal].left = nodes[cVal];
            } else {
                nodes[pVal].right = nodes[cVal];
            }

            hasParent[cVal] = true;
        }

        // Step 2: The root is the unique parent node that never appeared as a child
        for (int[] desc : descriptions) {
            int pVal = desc[0];
            if (!hasParent[pVal]) {
                return nodes[pVal];
            }
        }

        return null;
    }
}