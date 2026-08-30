class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        
        // Edge case: if array has 1 or 2 elements, we just remove all of them
        if (n <= 2) {
            return n;
        }
        
        int minIndex = 0;
        int maxIndex = 0;
        
        // Find the indices of the minimum and maximum elements
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        // Identify which index comes first (left) and which comes second (right)
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);
        
        // Calculate the cost of the three strategies
        int removeBothFromFront = right + 1;
        int removeBothFromBack = n - left;
        int removeFromOppositeEnds = (left + 1) + (n - right);
        
        // Return the minimum of the three
        return Math.min(Math.min(removeBothFromFront, removeBothFromBack), removeFromOppositeEnds);
    }
}