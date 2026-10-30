class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Step 1: Flood-fill all land (0s) connected to the outer boundaries
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && grid[i][j] == 0) {
                    dfs(grid, i, j, m, n);
                }
            }
        }
        
        // Step 2: Count and sink all remaining interior (closed) islands
        int closedCount = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    closedCount++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        
        return closedCount;
    }
    
    private void dfs(int[][] grid, int r, int c, int m, int n) {
        // Stop if out of bounds or if the cell is already water (1)
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 1) {
            return;
        }
        
        // Sink the land cell into water to mark it as visited
        grid[r][c] = 1;
        
        // Explore all 4 directions
        dfs(grid, r + 1, c, m, n);
        dfs(grid, r - 1, c, m, n);
        dfs(grid, r, c + 1, m, n);
        dfs(grid, r, c - 1, m, n);
    }
}