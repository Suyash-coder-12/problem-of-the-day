import java.util.Arrays;

class Solution {
    
    class SegmentTree {
        int[] minVal;
        int[] maxVal;
        int[] lazy;
        
        public SegmentTree(int n) {
            minVal = new int[4 * n + 1];
            maxVal = new int[4 * n + 1];
            lazy = new int[4 * n + 1];
        }
        
        private void pushDown(int node) {
            if (lazy[node] != 0) {
                int left = 2 * node;
                int right = 2 * node + 1;
                
                minVal[left] += lazy[node];
                maxVal[left] += lazy[node];
                lazy[left] += lazy[node];
                
                minVal[right] += lazy[node];
                maxVal[right] += lazy[node];
                lazy[right] += lazy[node];
                
                lazy[node] = 0;
            }
        }
        
        private void pushUp(int node) {
            minVal[node] = Math.min(minVal[2 * node], minVal[2 * node + 1]);
            maxVal[node] = Math.max(maxVal[2 * node], maxVal[2 * node + 1]);
        }
        
        public void update(int node, int l, int r, int ql, int qr, int val) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                minVal[node] += val;
                maxVal[node] += val;
                lazy[node] += val;
                return;
            }
            pushDown(node);
            int mid = l + (r - l) / 2;
            update(2 * node, l, mid, ql, qr, val);
            update(2 * node + 1, mid + 1, r, ql, qr, val);
            pushUp(node);
        }
        
        public int queryFirstZero(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return -1;
            
            if (minVal[node] > 0 || maxVal[node] < 0) return -1;
            
            if (l == r) {
                return l;
            }
            
            pushDown(node);
            int mid = l + (r - l) / 2;
            
            int res = queryFirstZero(2 * node, l, mid, ql, qr);
            if (res != -1) return res;
            
            return queryFirstZero(2 * node + 1, mid + 1, r, ql, qr);
        }
    }
    
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        
        int[] prev = new int[100005];
        Arrays.fill(prev, -1);
        
        int maxLen = 0;
        
        for (int j = 0; j < n; j++) {
            int p = prev[nums[j]];
            int val = (nums[j] % 2 == 0) ? 1 : -1;
            
            st.update(1, 0, n - 1, p + 1, j, val);
            
            int firstZero = st.queryFirstZero(1, 0, n - 1, 0, j);
            if (firstZero != -1) {
                maxLen = Math.max(maxLen, j - firstZero + 1);
            }
            
            prev[nums[j]] = j;
        }
        
        return maxLen;
    }
}