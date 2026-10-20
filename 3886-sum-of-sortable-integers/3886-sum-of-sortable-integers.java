class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        // Step 1: Precalculate drops
        int[] drops = new int[n];
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                drops[i] = 1;
            }
        }

        // Step 2: Prefix sum of drops for O(1) range queries
        int[] pref_drops = new int[n];
        pref_drops[0] = 0;
        for (int i = 1; i < n; i++) {
            pref_drops[i] = pref_drops[i - 1] + drops[i - 1];
        }

        // Step 3: Track the exact index of the next drop for O(1) min/max fetching
        int[] next_drop = new int[n];
        next_drop[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            if (drops[i] == 1) {
                next_drop[i] = i;
            } else {
                next_drop[i] = next_drop[i + 1];
            }
        }

        int sum = 0;
        
        // Step 4: Check all possible divisors k
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;
            int prev_max = -1;

            // Check each contiguous block of size k
            for (int j = 0; j < n / k; j++) {
                int L = j * k;
                int R = (j + 1) * k - 1;

                // Number of drops strictly inside the current block
                int d = pref_drops[R] - pref_drops[L];

                // If more than 1 drop, or 1 drop but it fails the cyclic wrap condition
                if (d > 1 || (d == 1 && nums[R] > nums[L])) {
                    valid = false;
                    break;
                }

                int cur_min, cur_max;
                
                // If 0 drops, it's strictly non-decreasing
                if (d == 0) {
                    cur_min = nums[L];
                    cur_max = nums[R];
                } 
                // If 1 drop, max is AT the drop, min is directly AFTER the drop
                else {
                    int drop_idx = next_drop[L];
                    cur_max = nums[drop_idx];
                    cur_min = nums[drop_idx + 1];
                }

                // Global Ordering condition
                if (prev_max != -1 && prev_max > cur_min) {
                    valid = false;
                    break;
                }

                prev_max = cur_max;
            }

            if (valid) {
                sum += k;
            }
        }

        return sum;
    }
}