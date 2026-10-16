class Solution {
    private long[] sumTree;
    private long[] sqSumTree;
    private long[] lazy;
    private final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        // 1-indexed segment tree arrays (size 4 * N)
        sumTree = new long[4 * n + 1];
        sqSumTree = new long[4 * n + 1];
        lazy = new long[4 * n + 1];
        
        // Track the last seen index of each number (1-based index)
        int[] lastSeen = new int[100001];
        long totalResult = 0;

        for (int i = 1; i <= n; i++) {
            int val = nums[i - 1];
            int prev = lastSeen[val];
            
            // Add 1 to the range [prev + 1, i]
            update(1, 1, n, prev + 1, i, 1);
            
            // The root node (index 1) holds the sqSum of all subarrays ending at 'i'
            totalResult = (totalResult + sqSumTree[1]) % MOD;
            
            // Update the last seen index for this value
            lastSeen[val] = i;
        }

        return (int) totalResult;
    }

    private void apply(int node, int start, int end, long v) {
        long len = end - start + 1;
        // sqSum = sqSum + 2*v*sum + v^2*len
        sqSumTree[node] = (sqSumTree[node] + 2 * v * sumTree[node] % MOD + v * v % MOD * len % MOD) % MOD;
        // sum = sum + v*len
        sumTree[node] = (sumTree[node] + v * len % MOD) % MOD;
        // queue lazy update for children
        lazy[node] = (lazy[node] + v) % MOD;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            int mid = start + (end - start) / 2;
            apply(2 * node, start, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, end, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int start, int end, int l, int r, long v) {
        // Out of bounds
        if (start > r || end < l) {
            return;
        }
        
        // Fully covered range
        if (start >= l && end <= r) {
            apply(node, start, end, v);
            return;
        }
        
        // Partial overlap: push down pending lazy updates first
        pushDown(node, start, end);
        
        int mid = start + (end - start) / 2;
        update(2 * node, start, mid, l, r, v);
        update(2 * node + 1, mid + 1, end, l, r, v);
        
        // Recompute current node based on updated children
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
        sqSumTree[node] = (sqSumTree[2 * node] + sqSumTree[2 * node + 1]) % MOD;
    }
}