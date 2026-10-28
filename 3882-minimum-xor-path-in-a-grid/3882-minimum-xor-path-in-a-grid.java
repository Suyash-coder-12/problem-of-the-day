class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] dp = new boolean[n][1024];
        
        int startVal = grid[0][0];
        dp[0][startVal] = true;
        
        for (int j = 1; j < n; j++) {
            int val = grid[0][j];
            for (int x = 0; x < 1024; x++) {
                if (dp[j - 1][x]) {
                    dp[j][x ^ val] = true;
                }
            }
        }
        
        for (int i = 1; i < m; i++) {
            boolean[][] nextDp = new boolean[n][1024];
            
            int firstVal = grid[i][0];
            for (int x = 0; x < 1024; x++) {
                if (dp[0][x]) {
                    nextDp[0][x ^ firstVal] = true;
                }
            }
            
            for (int j = 1; j < n; j++) {
                int val = grid[i][j];
                for (int x = 0; x < 1024; x++) {
                    if (dp[j][x] || nextDp[j - 1][x]) {
                        nextDp[j][x ^ val] = true;
                    }
                }
            }
            
            dp = nextDp;
        }
        
        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }
        
        return 0;
    }
}