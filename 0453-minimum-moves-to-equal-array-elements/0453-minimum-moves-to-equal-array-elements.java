class Solution {
    public int minMoves(int[] nums) {
        int minVal = nums[0];
        
        // Step 1: Find the minimum element in the array
        for (int num : nums) {
            if (num < minVal) {
                minVal = num;
            }
        }
        
        // Step 2: Sum the moves needed to reduce each element to minVal
        int moves = 0;
        for (int num : nums) {
            moves += num - minVal;
        }
        
        return moves;
    }
}