class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] counts = new long[k];
        
        for (int num : nums) {
            long[] nextCounts = new long[k];
            
            // The value of the current element modulo k
            int val = num % k;
            
            // 1. A subarray containing strictly just the current element
            nextCounts[val] += 1;
            
            // 2. Subarrays continuing from the previous index
            for (int rem = 0; rem < k; rem++) {
                if (counts[rem] > 0) {
                    int nextRem = (rem * val) % k;
                    nextCounts[nextRem] += counts[rem];
                }
            }
            
            // Update the global results and the DP state for the next iteration
            for (int i = 0; i < k; i++) {
                result[i] += nextCounts[i];
                counts[i] = nextCounts[i];
            }
        }
        
        return result;
    }
}