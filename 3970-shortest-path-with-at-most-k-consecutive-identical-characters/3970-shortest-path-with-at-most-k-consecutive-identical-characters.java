import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (n == 1) return 0;

        int m = edges.length;
        
        // Separate adjacency lists: 
        // sameHead for edges where labels[u] == labels[v]
        // diffHead for edges where labels[u] != labels[v]
        int[] sameHead = new int[n];
        int[] diffHead = new int[n];
        Arrays.fill(sameHead, -1);
        Arrays.fill(diffHead, -1);

        int[] to = new int[m];
        int[] weight = new int[m];
        int[] next = new int[m];
        char[] chars = labels.toCharArray();

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            to[i] = v;
            weight[i] = w;
            if (chars[u] == chars[v]) {
                next[i] = sameHead[u];
                sameHead[u] = i;
            } else {
                next[i] = diffHead[u];
                diffHead[u] = i;
            }
        }

        // minDist[u][c] stores the shortest distance to u ending with 'c' consecutive identical labels
        long[][] minDist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minDist[i], Long.MAX_VALUE);
        }

        // minCount[u] tracks the minimum consecutive count 'c' already expanded at node u
        int[] minCount = new int[n];
        Arrays.fill(minCount, k + 1);

        // PriorityQueue stores {distance, node, consecutive_count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        minDist[0][1] = 0;
        pq.offer(new long[]{0L, 0, 1});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int c = (int) curr[2];

            // Target reached! Since PQ orders by distance, the first time we pop n - 1 is optimal
            if (u == n - 1) {
                return (int) d;
            }

            // Dominance Pruning: if we already expanded u with a smaller/equal distance AND smaller/equal count, skip
            if (c >= minCount[u]) {
                continue;
            }

            boolean isFirstPop = (minCount[u] == k + 1);
            minCount[u] = c;

            // 1. Transitions to neighbors with DIFFERENT labels (resets count to 1)
            // Only need to do this the very FIRST time node u is popped!
            if (isFirstPop) {
                for (int e = diffHead[u]; e != -1; e = next[e]) {
                    int v = to[e];
                    long nd = d + weight[e];
                    if (nd < minDist[v][1] && 1 < minCount[v]) {
                        minDist[v][1] = nd;
                        pq.offer(new long[]{nd, v, 1});
                    }
                }
            }

            // 2. Transitions to neighbors with the SAME label (increments count to c + 1)
            if (c + 1 <= k) {
                int nc = c + 1;
                for (int e = sameHead[u]; e != -1; e = next[e]) {
                    int v = to[e];
                    long nd = d + weight[e];
                    if (nd < minDist[v][nc] && nc < minCount[v]) {
                        minDist[v][nc] = nd;
                        pq.offer(new long[]{nd, v, nc});
                    }
                }
            }
        }

        // Note: Maximum total distance is 50,000 * 10,000 = 5 * 10^8, which fits in a 32-bit signed int.
        // If LeetCode's driver expects long as the return type, change the method return type to `long`.
        return -1;
    }
}