import java.util.List;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int totalSum = 0;
        
        // Loop over every possible starting point
        for (int i = 0; i < n; i++) {
            // Because nums[i] <= 100, we can use a boolean array 
            // instead of a HashSet for blazing fast O(1) lookups
            boolean[] seen = new boolean[101];
            int distinctCount = 0;
            
            // Expand the subarray to the right
            for (int j = i; j < n; j++) {
                int val = nums.get(j);
                
                // If it's the first time we see this number in the current subarray
                if (!seen[val]) {
                    seen[val] = true;
                    distinctCount++;
                }
                
                // Add the square of the distinct count to our total
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}