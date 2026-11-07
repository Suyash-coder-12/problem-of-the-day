import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        // Each server is stored as long[]{weight, index, freeTime}
        // freeServers orders by smallest weight, then smallest index
        PriorityQueue<long[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(a[1], b[1]);
        });

        // busyServers orders by earliest freeTime
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> Long.compare(a[2], b[2]));

        for (int i = 0; i < n; i++) {
            freeServers.offer(new long[]{servers[i], i, 0L});
        }

        int[] ans = new int[m];
        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            // Advance currentTime to at least the arrival second of task j
            currentTime = Math.max(currentTime, j);

            // If no server is currently available, jump currentTime to the earliest completion time
            if (freeServers.isEmpty()) {
                long[] earliest = busyServers.peek();
                currentTime = Math.max(currentTime, earliest[2]);
            }

            // Move all servers that become free at or before currentTime into freeServers
            while (!busyServers.isEmpty()) {
                long[] topBusy = busyServers.peek();
                if (topBusy[2] <= currentTime) {
                    freeServers.offer(busyServers.poll());
                } else {
                    break;
                }
            }

            // Assign task j to the highest-priority available server
            long[] assigned = freeServers.poll();
            ans[j] = (int) assigned[1];
            assigned[2] = currentTime + tasks[j];
            busyServers.offer(assigned);
        }

        return ans;
    }
}