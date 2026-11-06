import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int logLen = logs.length;
        int qLen = queries.length;

        // Step 1: Sort logs by time in ascending order
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Pair each query with its original index and sort by query time
        int[][] sortedQueries = new int[qLen][2];
        for (int i = 0; i < qLen; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] serverCount = new int[n + 1];
        int[] ans = new int[qLen];
        int activeServers = 0;
        int left = 0;
        int right = 0;

        // Step 2: Slide the [q - x, q] window across sorted logs
        for (int i = 0; i < qLen; i++) {
            int qTime = sortedQueries[i][0];
            int origIdx = sortedQueries[i][1];
            int minTime = qTime - x;

            // Expand window to include logs with time <= qTime
            while (right < logLen && logs[right][1] <= qTime) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                right++;
            }

            // Shrink window to exclude logs with time < qTime - x
            while (left < logLen && logs[left][1] < minTime) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }

            // Servers with 0 requests = total servers - active servers in window
            ans[origIdx] = n - activeServers;
        }

        return ans;
    }
}