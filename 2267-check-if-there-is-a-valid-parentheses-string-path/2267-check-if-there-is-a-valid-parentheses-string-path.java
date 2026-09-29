class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 1. Pruning: Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        // 2. Pruning: Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // Maximum possible balance at any given time
        int maxBalance = (m + n - 1) / 2;
        
        // Memoization table: visited[row][col][balance]
        boolean[][][] visited = new boolean[m][n][maxBalance + 1];
        
        return dfs(grid, 0, 0, 0, visited, maxBalance);
    }
    
    private boolean dfs(char[][] grid, int i, int j, int balance, boolean[][][] visited, int maxBalance) {
        // Apply the current cell's character to the balance
        if (grid[i][j] == '(') {
            balance++;
        } else {
            balance--;
        }
        
        // Invalid path state if balance drops below 0 or exceeds the mathematical maximum
        if (balance < 0 || balance > maxBalance) {
            return false;
        }
        
        // Reached the destination, check if perfectly balanced
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return balance == 0;
        }
        
        // If we've already evaluated this exact state without success, skip it
        if (visited[i][j][balance]) {
            return false;
        }
        visited[i][j][balance] = true; // Mark as evaluated
        
        // Try moving down
        if (i + 1 < grid.length && dfs(grid, i + 1, j, balance, visited, maxBalance)) {
            return true;
        }
        
        // Try moving right
        if (j + 1 < grid[0].length && dfs(grid, i, j + 1, balance, visited, maxBalance)) {
            return true;
        }
        
        return false;
    }
}