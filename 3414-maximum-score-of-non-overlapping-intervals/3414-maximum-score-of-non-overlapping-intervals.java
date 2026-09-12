import java.util.Arrays;
import java.util.List;

class Solution {
    
    // Helper class to map intervals alongside their original index
    static class Interval implements Comparable<Interval> {
        int l, r, weight, id;
        
        public Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
        
        @Override
        public int compareTo(Interval other) {
            if (this.r != other.r) {
                return Integer.compare(this.r, other.r);
            }
            return Integer.compare(this.l, other.l);
        }
    }
    
    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> list = intervalsList.get(i);
            intervals[i] = new Interval(list.get(0), list.get(1), list.get(2), i);
        }
        
        // Sort intervals strictly by right endpoint 
        Arrays.sort(intervals);
        
        // dpWeight[i][k] stores the max weight using EXACTLY k intervals from the first i sorted intervals
        long[][] dpWeight = new long[n + 1][5];
        int[][][] dpIndices = new int[n + 1][5][];
        
        // Initialize DP tables
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dpWeight[i], -1); // -1 means unreachable configuration
            dpWeight[i][0] = 0;           // 0 intervals yields 0 weight
            dpIndices[i][0] = new int[0];
        }
        
        for (int i = 1; i <= n; i++) {
            Interval current = intervals[i - 1];
            
            // CORRECTED BINARY SEARCH: Find the last compatible interval ending before current begins
            int left = 1, right = i - 1;
            int prev = 0; // defaults to 0 if no intervals end before current starts
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                // interval[mid-1] corresponds to the 0-indexed array element
                if (intervals[mid - 1].r < current.l) {
                    prev = mid; // record valid bound
                    left = mid + 1; // explore further right for a closer bound
                } else {
                    right = mid - 1;
                }
            }
            
            // Try forming a subset of size 1 to 4
            for (int k = 1; k <= 4; k++) {
                // Option 1: Exclude the current interval
                long w1 = dpWeight[i - 1][k];
                int[] idx1 = dpIndices[i - 1][k];
                
                // Option 2: Include current interval (if a valid (k-1) subset exists prior)
                long w2 = -1;
                int[] idx2 = null;
                
                if (dpWeight[prev][k - 1] != -1) {
                    w2 = dpWeight[prev][k - 1] + current.weight;
                    idx2 = addIndex(dpIndices[prev][k - 1], current.id);
                }
                
                // Compare choices and record the best state
                if (isBetter(w2, idx2, w1, idx1)) {
                    dpWeight[i][k] = w2;
                    dpIndices[i][k] = idx2;
                } else {
                    dpWeight[i][k] = w1;
                    dpIndices[i][k] = idx1;
                }
            }
        }
        
        // Find the absolute best sequence tracking any valid k (1 to 4 intervals)
        long maxW = -1;
        int[] bestIdx = null;
        for (int k = 1; k <= 4; k++) {
            if (isBetter(dpWeight[n][k], dpIndices[n][k], maxW, bestIdx)) {
                maxW = dpWeight[n][k];
                bestIdx = dpIndices[n][k];
            }
        }
        
        return bestIdx == null ? new int[0] : bestIdx;
    }
    
    // Evaluates true if state 1 strictly outperforms state 2 based on Weight, then Lexicographical Order
    private boolean isBetter(long w1, int[] idx1, long w2, int[] idx2) {
        if (w1 != w2) {
            return w1 > w2;
        }
        if (idx1 == null) return false;
        if (idx2 == null) return true;
        
        for (int i = 0; i < Math.min(idx1.length, idx2.length); i++) {
            if (idx1[i] != idx2[i]) {
                return idx1[i] < idx2[i]; // Smaller original index is strictly better
            }
        }
        
        // Standard lexicographical definition: if elements match up to min length, the shorter array is smaller
        return idx1.length < idx2.length;
    }
    
    // Helper to merge the newly picked original index into a cloned array efficiently
    private int[] addIndex(int[] prev, int newIdx) {
        if (prev == null) return new int[]{newIdx};
        int[] res = new int[prev.length + 1];
        System.arraycopy(prev, 0, res, 0, prev.length);
        res[prev.length] = newIdx;
        Arrays.sort(res); // Max array size is 4, making this sorting operation exceptionally trivial O(1) time
        return res;
    }
}