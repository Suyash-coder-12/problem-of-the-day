import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> queue = new LinkedList<>();
        // Visited array bounded by the 0 to 1000 constraint
        boolean[] visited = new boolean[1001];
        
        queue.offer(start);
        visited[start] = true;
        
        int operations = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all states at the current "level" of operations
            for (int i = 0; i < size; i++) {
                int x = queue.poll();
                
                // Try all numbers in nums with all 3 operations
                for (int num : nums) {
                    int[] nextStates = {x + num, x - num, x ^ num};
                    
                    for (int nx : nextStates) {
                        // If we hit the goal, return immediately
                        if (nx == goal) {
                            return operations + 1;
                        }
                        
                        // If the new state is within the valid operational range and unvisited
                        if (nx >= 0 && nx <= 1000 && !visited[nx]) {
                            visited[nx] = true;
                            queue.offer(nx);
                        }
                    }
                }
            }
            // Increment operation count after processing a full level
            operations++;
        }
        
        // If the queue empties and we haven't reached the goal
        return -1;
    }
}