import java.util.HashSet;
import java.util.Set;

class Solution {
    private static final long BASE1 = 1315423911L;
    private static final long BASE2 = 2654435761L;
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }

        // Fast O(n) check for length = 1 using direct-indexed frequency array
        int[] freq = new int[100001];
        for (int num : nums) {
            freq[num]++;
        }
        for (int num : nums) {
            if (freq[num] == 1) {
                return 1;
            }
        }

        // Binary search for the minimum unique subarray length in [2, n]
        int low = 2;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long h1 = 0, h2 = 0;
        long pow1 = 1, pow2 = 1;

        // Precompute BASE^(len - 1) % MOD
        for (int i = 0; i < len - 1; i++) {
            pow1 = (pow1 * BASE1) % MOD1;
            pow2 = (pow2 * BASE2) % MOD2;
        }

        // Compute initial hash for window [0 ... len - 1]
        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }

        int numWindows = n - len + 1;
        Set<Long> seen = new HashSet<>(numWindows * 2);
        Set<Long> duplicates = new HashSet<>();

        long firstKey = (h1 << 32) | (h2 & 0xFFFFFFFFL);
        seen.add(firstKey);

        // Slide the window from index len to n - 1
        for (int i = len; i < n; i++) {
            int outVal = nums[i - len];
            int inVal = nums[i];

            h1 = (h1 - (outVal * pow1) % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + inVal) % MOD1;

            h2 = (h2 - (outVal * pow2) % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + inVal) % MOD2;

            long key = (h1 << 32) | (h2 & 0xFFFFFFFFL);
            if (!seen.add(key)) {
                duplicates.add(key);
            }
        }

        return seen.size() > duplicates.size();
    }
}