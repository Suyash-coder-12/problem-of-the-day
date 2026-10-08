class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Step 1: Precompute the minimum values from the right (suffix minimums)
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        
        // Step 2: Track the max from the left and evaluate the condition
        int prefixMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            
            // Check the instability score condition
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        // No valid index found
        return -1;
    }
}