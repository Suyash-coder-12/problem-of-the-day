import java.util.*;

class Solution {
    private int[] head, to, next;
    private int edgeCount = 0;
    
    private int[] tin, tout, depth;
    private int[][] up;
    private int timer = 0;
    private int LOG = 17;
    private int[] bit;
    private int n;

    private void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        head = new int[n];
        Arrays.fill(head, -1);
        to = new int[2 * (n - 1)];
        next = new int[2 * (n - 1)];

        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][LOG];
        bit = new int[n + 2];

        // Iterative DFS to avoid StackOverflowError on deep trees
        iterativeDfs(0);

        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            rangeXor(tin[i], tout[i], mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            if (q.charAt(0) == 'u') {
                // Format: "update u c"
                int firstSpace = q.indexOf(' ');
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                int u = Integer.parseInt(q.substring(firstSpace + 1, secondSpace));
                char c = q.charAt(secondSpace + 1);

                if (chars[u] != c) {
                    int diffMask = (1 << (chars[u] - 'a')) ^ (1 << (c - 'a'));
                    chars[u] = c;
                    rangeXor(tin[u], tout[u], diffMask);
                }
            } else {
                // Format: "query u v"
                int firstSpace = q.indexOf(' ');
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                int u = Integer.parseInt(q.substring(firstSpace + 1, secondSpace));
                int v = Integer.parseInt(q.substring(secondSpace + 1));

                int lca = getLCA(u, v);
                int pathMask = pointQuery(tin[u]) ^ pointQuery(tin[v]) ^ (1 << (chars[lca] - 'a'));

                // True if at most 1 bit is set in pathMask
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private void iterativeDfs(int root) {
        int[] stack = new int[n];
        int[] edgePtr = new int[n];
        int top = 0;

        stack[0] = root;
        edgePtr[0] = head[root];
        tin[root] = ++timer;

        while (top >= 0) {
            int u = stack[top];
            int e = edgePtr[top];

            if (e != -1) {
                edgePtr[top] = next[e];
                int v = to[e];
                if (top == 0 || v != stack[top - 1]) {
                    depth[v] = depth[u] + 1;
                    up[v][0] = u;
                    for (int j = 1; j < LOG; j++) {
                        int prevAncestor = up[v][j - 1];
                        up[v][j] = up[prevAncestor][j - 1];
                    }
                    tin[v] = ++timer;
                    top++;
                    stack[top] = v;
                    edgePtr[top] = head[v];
                }
            } else {
                tout[u] = timer;
                top--;
            }
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) != 0) {
                u = up[u][j];
            }
        }
        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        return up[u][0];
    }

    private void updateBit(int idx, int val) {
        for (; idx <= n; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private void rangeXor(int l, int r, int val) {
        updateBit(l, val);
        updateBit(r + 1, val);
    }

    private int pointQuery(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res ^= bit[idx];
        }
        return res;
    }
}