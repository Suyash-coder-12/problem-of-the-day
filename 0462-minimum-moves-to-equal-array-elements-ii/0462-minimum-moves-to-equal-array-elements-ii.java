import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length - 1;
        int moves = 0;
        
        // Step 2: Sum the differences between symmetric outer pairs
        while (left < right) {
            moves += nums[right] - nums[left];
            left++;
            right--;
        }
        
        return moves;
    }
}