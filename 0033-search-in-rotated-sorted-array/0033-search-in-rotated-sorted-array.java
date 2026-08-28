class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check if the left half is perfectly sorted
            if (nums[left] <= nums[mid]) {
                // Check if the target is within this sorted left boundary
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // Target is in the left half
                } else {
                    left = mid + 1;  // Target is in the right half
                }
            } 
            // Otherwise, the right half must be perfectly sorted
            else {
                // Check if the target is within this sorted right boundary
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;  // Target is in the right half
                } else {
                    right = mid - 1; // Target is in the left half
                }
            }
        }
        
        return -1; // Target not found
    }
}