import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        // Initialize with the sum of the first three elements
        int closestSum = nums[0] + nums[1] + nums[2];
        
        // Step 2: Iterate through the array
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // If we found the exact target, return immediately
                if (currentSum == target) {
                    return currentSum;
                }
                
                // Update closestSum if the current one is closer to the target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                // Move pointers based on the comparison with target
                if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return closestSum;
    }
}