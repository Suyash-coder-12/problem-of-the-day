import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Stores available server IDs in sorted order for O(log k) ceiling/wrap-around queries
        TreeSet<Integer> freeServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            freeServers.add(i);
        }

        // Stores {endTime, serverId} ordered by earliest endTime
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] count = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currentTime = arrival[i];

            // 1. Free up all servers that have completed their tasks by currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int freedServerId = busyServers.poll()[1];
                freeServers.add(freedServerId);
            }

            // 2. Drop the request if all servers are currently busy
            if (freeServers.isEmpty()) {
                continue;
            }

            // 3. Find the first available server >= (i % k), or wrap around to the smallest available ID
            Integer assignedServer = freeServers.ceiling(i % k);
            if (assignedServer == null) {
                assignedServer = freeServers.first();
            }

            // 4. Assign the request and update server state
            freeServers.remove(assignedServer);
            busyServers.offer(new int[]{currentTime + load[i], assignedServer});

            count[assignedServer]++;
            if (count[assignedServer] > maxRequests) {
                maxRequests = count[assignedServer];
            }
        }

        // Collect all servers that handled the maximum number of requests
        List<Integer> busiest = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                busiest.add(i);
            }
        }

        return busiest;
    }
}