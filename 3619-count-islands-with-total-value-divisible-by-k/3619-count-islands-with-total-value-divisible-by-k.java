import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int validIslandsCount = 0;
        
        // 4-directional moves: Down, Up, Right, Left
        int[] dirX = {1, -1, 0, 0};
        int[] dirY = {0, 0, 1, -1};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we hit unvisited land, explore the whole island
                if (grid[i][j] > 0) {
                    long islandSum = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    
                    // Start BFS
                    queue.offer(new int[]{i, j});
                    islandSum += grid[i][j];
                    grid[i][j] = 0; // Mark as visited by sinking it
                    
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int r = curr[0];
                        int c = curr[1];
                        
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dirX[d];
                            int nc = c + dirY[d];
                            
                            // Check bounds and if the neighboring cell is land
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                islandSum += grid[nr][nc];
                                grid[nr][nc] = 0; // Sink to mark visited
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }
                    
                    // Check divisibility condition
                    if (islandSum % k == 0) {
                        validIslandsCount++;
                    }
                }
            }
        }
        
        return validIslandsCount;
    }
}