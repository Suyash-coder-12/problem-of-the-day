class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // minLenUpTo[i] stores the minimum length of a valid subarray ending at or before index i
        int[] minLenUpTo = new int[n];
        int ans = Integer.MAX_VALUE;
        int currentMin = Integer.MAX_VALUE;
        
        int left = 0;
        int sum = 0;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink the window from the left if the sum exceeds the target
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }
            
            if (sum == target) {
                int currLen = right - left + 1;
                
                // If a valid non-overlapping subarray exists before 'left', check their combined length
                if (left > 0 && minLenUpTo[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + minLenUpTo[left - 1]);
                }
                
                // Update the best minimum length found so far
                currentMin = Math.min(currentMin, currLen);
            }
            
            // Record the best minimum length up to the current right index
            minLenUpTo[right] = currentMin;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}