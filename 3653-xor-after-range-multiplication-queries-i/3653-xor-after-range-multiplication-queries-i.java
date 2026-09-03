class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int MOD = 1000000007;
        
        // Process each query directly
        for (int[] query : queries) {
            int li = query[0];
            int ri = query[1];
            int ki = query[2];
            int vi = query[3];
            
            // Jump by k steps and apply the multiplication
            for (int idx = li; idx <= ri; idx += ki) {
                // Use long to prevent overflow during multiplication
                nums[idx] = (int) (((long) nums[idx] * vi) % MOD);
            }
        }
        
        // Calculate the final XOR sum of the array
        int finalXor = 0;
        for (int num : nums) {
            finalXor ^= num;
        }
        
        return finalXor;
    }
}