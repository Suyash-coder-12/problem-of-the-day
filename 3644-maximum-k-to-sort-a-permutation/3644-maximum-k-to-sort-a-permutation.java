class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1;
        boolean isSorted = true;
        
        // Find all out-of-place elements and accumulate their bitwise AND
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                isSorted = false;
                if (k == -1) {
                    k = nums[i];
                } else {
                    k &= nums[i];
                }
            }
        }
        
        // If the array is already sorted, the problem specifies returning 0
        return isSorted ? 0 : k;
    }
}