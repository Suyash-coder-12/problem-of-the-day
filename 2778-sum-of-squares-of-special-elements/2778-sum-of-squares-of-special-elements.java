class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Loop from 1 to n to represent the 1-based indices
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                // Fetch the element using 0-based indexing (i - 1)
                sum += nums[i - 1] * nums[i - 1];
            }
        }
        
        return sum;
    }
}