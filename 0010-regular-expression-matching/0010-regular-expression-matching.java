class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        // dp[i][j] means s.substring(0, i) matches p.substring(0, j)
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: empty string matches empty pattern
        dp[0][0] = true;
        
        // Base case: empty string can match patterns like "a*", "a*b*", etc.
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                
                // If characters match, or pattern has '.', carry over previous state
                if (pc == sc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                // If pattern has '*', we have two scenarios
                else if (pc == '*') {
                    // Scenario 1: '*' matches zero occurrences of the preceding element
                    dp[i][j] = dp[i][j - 2];
                    
                    // Scenario 2: '*' matches one or more occurrences of the preceding element
                    char prevPc = p.charAt(j - 2);
                    if (prevPc == sc || prevPc == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}