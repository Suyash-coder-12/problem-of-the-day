class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        
        // Monotonic deque stored in primitive arrays:
        // 'top' is the front of the window (near 'left')
        // 'bottom' is the back of the window (near 'right')
        int[] qVal = new int[n];
        int[] qCnt = new int[n];
        int bottom = 0;
        int top = -1;

        long totalSubarrays = 0;
        long cost = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            int val = nums[left];
            int count = 1;

            // Merge segments at the front whose maximum is <= nums[left]
            while (top >= bottom && qVal[top] <= val) {
                cost += (long) qCnt[top] * (val - qVal[top]);
                count += qCnt[top];
                top--;
            }

            // Push the new merged segment for nums[left]
            top++;
            qVal[top] = val;
            qCnt[top] = count;

            // Shrink the right boundary until total operations <= k
            while (cost > k) {
                cost -= (qVal[bottom] - nums[right]);
                qCnt[bottom]--;
                if (qCnt[bottom] == 0) {
                    bottom++;
                }
                right--;
            }

            // All subarrays starting at 'left' and ending in [left, right] are valid
            totalSubarrays += (right - left + 1);
        }

        return totalSubarrays;
    }
}