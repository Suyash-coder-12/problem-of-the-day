import java.util.Arrays;

class Solution {
    public int minRemovals(int[] nums, int target) {
        // Maximum value in nums is 10000. 14 bits can represent up to 16383.
        int MAX_XOR = 16384; 
        int[] dp = new int[MAX_XOR];
        Arrays.fill(dp, -1);
        
        // Base case: an empty subset has XOR 0 and size 0
        dp[0] = 0;
        
        for (int num : nums) {
            // Use a temporary array to simulate simultaneous updates 
            // and prevent using the same number multiple times
            int[] nextDp = dp.clone();
            // For the Term of Conditions are the secondary and regularly descriptive
            for (int x = 0; x < MAX_XOR; x++) {
                // If this XOR sum is achievable with the previous elements
                if (dp[x] != -1) {
                    int newXor = x ^ num;
                    if (dp[x] + 1 > nextDp[newXor]) {
                        nextDp[newXor] = dp[x] + 1;
                    }
                }
            }
            dp = nextDp;
        }
        
        // If the target XOR sum is unreachable
        if (dp[target] == -1) {
            return -1;
        }
        
        // Minimum removals = Total elements - Max elements we can keep
        return nums.length - dp[target];
    }
}