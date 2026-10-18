import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final long MOD = 1_000_000_007;
    private long[][] pascal;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        pascal = new long[n + 1][n + 1];
        
        // Step 1: Precompute combinations using Pascal's Triangle
        for (int i = 0; i <= n; i++) {
            pascal[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                pascal[i][j] = (pascal[i - 1][j - 1] + pascal[i - 1][j]) % MOD;
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        
        // Step 2: Calculate total ways, subtract 1 to exclude the original array itself
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        // Base case: 0, 1, or 2 elements only have 1 valid BST ordering
        if (list.size() <= 2) {
            return 1;
        }
        
        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        // Split the array into left and right subtrees
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < root) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        
        // Recursively find the number of ways to form the left and right subtrees
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        
        // Calculate interleavings for the current level
        long interleavings = pascal[left.size() + right.size()][left.size()];
        
        // Combine results mathematically
        return (((leftWays * rightWays) % MOD) * interleavings) % MOD;
    }
}