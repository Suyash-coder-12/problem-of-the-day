class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        long baseCost = 0;
        int n = nums1.length;
        int target = nums2[n]; // The extra element we need to match
        
        int minDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            // 1. Add to the base cost for transforming nums1[i] to nums2[i]
            baseCost += Math.abs(nums1[i] - nums2[i]);
            
            // 2. Find the interval bounds for this index
            int L = Math.min(nums1[i], nums2[i]);
            int R = Math.max(nums1[i], nums2[i]);
            
            // 3. Calculate the distance from our target to this interval
            int dist = 0;
            if (target < L) {
                dist = L - target;
            } else if (target > R) {
                dist = target - R;
            }
            // If target is inside the interval [L, R], the distance is naturally 0
            
            // 4. Keep track of the minimum distance achievable
            minDist = Math.min(minDist, dist);
        }
        
        // Total cost = cost to match arrays + 1 append operation + cost to reach target
        return baseCost + 1 + minDist;
    }
}