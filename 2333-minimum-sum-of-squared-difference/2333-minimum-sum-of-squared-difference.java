class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2; // Total allowed operations
        
        // Use an array to count the frequencies of each absolute difference
        // The max value in nums1 and nums2 is 10^5, so max difference is 10^5
        int[] count = new int[100001];
        long totalDiffSum = 0;
        
        // Calculate absolute differences and populate the buckets
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            totalDiffSum += diff;
        }
        
        // If we have enough operations to reduce all differences to 0
        if (k >= totalDiffSum) {
            return 0;
        }
        
        // Greedily reduce the largest differences
        for (int i = 100000; i > 0 && k > 0; i--) {
            if (count[i] > 0) {
                // How many elements can we actually reduce from 'i' to 'i - 1'?
                long reduceCount = Math.min((long) count[i], k);
                
                count[i] -= reduceCount;
                count[i - 1] += reduceCount;
                k -= reduceCount;
            }
        }
        
        // Calculate the final sum of squared differences
        long minSumSquare = 0;
        for (int i = 1; i <= 100000; i++) {
            if (count[i] > 0) {
                // Use long to prevent integer overflow during squaring and accumulation
                minSumSquare += (long) count[i] * i * i;
            }
        }
        
        return minSumSquare;
    }
}