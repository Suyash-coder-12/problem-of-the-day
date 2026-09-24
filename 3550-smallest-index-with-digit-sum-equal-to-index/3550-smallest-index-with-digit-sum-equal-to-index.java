class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int digitSum = 0;
            int currentNum = nums[i];
            
            // Calculate the sum of the digits of nums[i]
            while (currentNum > 0) {
                digitSum += currentNum % 10;
                currentNum /= 10;
            }
            
            // If the digit sum matches the index, return the index
            if (digitSum == i) {
                return i;
            }
        }
        
        // No valid index found
        return -1;
    }
}