class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        
        // Find the first occurrence
        result[0] = findBound(nums, target, true);
        
        // If the target doesn't exist, we can short-circuit
        if (result[0] == -1) {
            return result;
        }
        
        // Find the last occurrence
        result[1] = findBound(nums, target, false);
        
        return result;
    }
    
    private int findBound(int[] nums, int target, boolean isFirstBound) {
        int left = 0;
        int right = nums.length - 1;
        int bound = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                bound = mid; // Record the index
                
                if (isFirstBound) {
                    // Keep searching to the left for the first occurrence
                    right = mid - 1;
                } else {
                    // Keep searching to the right for the last occurrence
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return bound;
    }
}