import java.util.*;

class Solution {
    private TreeSet<Integer> left;
    private TreeSet<Integer> right;
    private long sumLeft = 0;
    private long sumRight = 0;
    private int[] nums;
    private Comparator<Integer> comp;

    public long minOperations(int[] nums, int x, int k) {
        this.nums = nums;
        int n = nums.length;
        int m = n - x + 1;

        // Compare indices by their value in nums, breaking ties by index
        comp = (a, b) -> {
            if (nums[a] != nums[b]) {
                return Integer.compare(nums[a], nums[b]);
            }
            return Integer.compare(a, b);
        };

        left = new TreeSet<>(comp);
        right = new TreeSet<>(comp);

        long[] cost = new long[m];

        // Step 1: Compute equalization cost for every sliding window of size x
        for (int i = 0; i < n; i++) {
            add(i);
            if (i >= x) {
                remove(i - x);
            }
            if (i >= x - 1) {
                int medIdx = right.first();
                long median = nums[medIdx];
                int winStart = i - x + 1;
                cost[winStart] = sumRight - sumLeft - (x % 2 == 1 ? median : 0);
            }
        }

        // Step 2: DP to pick k non-overlapping windows (spaced at least x apart)
        long[] prevDp = new long[m];
        prevDp[0] = cost[0];
        for (int i = 1; i < m; i++) {
            prevDp[i] = Math.min(prevDp[i - 1], cost[i]);
        }

        for (int j = 2; j <= k; j++) {
            long[] currDp = new long[m];
            int startIdx = (j - 1) * x;

            for (int i = startIdx; i < m; i++) {
                long pick = prevDp[i - x] + cost[i];
                if (i == startIdx) {
                    currDp[i] = pick;
                } else {
                    currDp[i] = Math.min(currDp[i - 1], pick);
                }
            }
            prevDp = currDp;
        }

        return prevDp[m - 1];
    }

    private void add(int idx) {
        if (!right.isEmpty() && comp.compare(idx, right.first()) >= 0) {
            right.add(idx);
            sumRight += nums[idx];
        } else {
            left.add(idx);
            sumLeft += nums[idx];
        }
        rebalance();
    }

    private void remove(int idx) {
        if (left.remove(idx)) {
            sumLeft -= nums[idx];
        } else {
            right.remove(idx);
            sumRight -= nums[idx];
        }
        rebalance();
    }

    private void rebalance() {
        while (left.size() > right.size()) {
            int idx = left.pollLast();
            sumLeft -= nums[idx];
            right.add(idx);
            sumRight += nums[idx];
        }
        while (right.size() > left.size() + 1) {
            int idx = right.pollFirst();
            sumRight -= nums[idx];
            left.add(idx);
            sumLeft += nums[idx];
        }
    }
}