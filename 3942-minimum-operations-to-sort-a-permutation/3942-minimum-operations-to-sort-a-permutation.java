class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        int idx = -1;
        boolean isInc = true;
        boolean isDec = true;
        
        // Find the index of 0 and verify if it is circularly increasing or decreasing
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx = i;
            }
            
            int next = nums[(i + 1) % n];
            
            if (next != (nums[i] + 1) % n) {
                isInc = false;
            }
            if (next != (nums[i] - 1 + n) % n) {
                isDec = false;
            }
        }
        
        int minOps = Integer.MAX_VALUE;
        
        // Evaluate valid operations if circularly increasing
        if (isInc) {
            int ops1 = idx;
            int ops2 = 2 + (n - idx) % n;
            minOps = Math.min(minOps, Math.min(ops1, ops2));
        }
        
        // Evaluate valid operations if circularly decreasing
        if (isDec) {
            int ops1 = 1 + (n - 1 - idx) % n;
            int ops2 = 1 + (idx + 1) % n;
            minOps = Math.min(minOps, Math.min(ops1, ops2));
        }
        
        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }
}