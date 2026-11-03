class Solution {
    public int minMoves(int[] nums) {
        int maxVal = 0;
        int sum = 0;
        
        // Single pass to find the maximum element and the total sum
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
            sum += num;
        }
        
        // Total moves = (n * maxVal) - sum
        return (nums.length * maxVal) - sum;
    }
}