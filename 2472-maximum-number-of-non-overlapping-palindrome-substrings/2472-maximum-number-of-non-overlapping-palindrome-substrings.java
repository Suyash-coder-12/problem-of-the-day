class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        
        // Step 1: Precompute all palindromes in O(N^2)
        for (int i = n - 1; i >= 0; i--) {
            isPal[i][i] = true; // A single character is always a palindrome
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // Check if it's a 2-character palindrome, or if the inner string is a palindrome
                    isPal[i][j] = (j - i == 1) ? true : isPal[i + 1][j - 1];
                }
            }
        }
        
        // Step 2: DP to find max non-overlapping palindromes
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // Default: skip the current character
            dp[i] = dp[i - 1];
            
            // Look backward for a valid palindrome of length >= k
            for (int j = i - k; j >= 0; j--) {
                if (isPal[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    
                    // Greedy Optimization: Since we scan j downwards, we hit the largest j first.
                    // Because dp array is non-decreasing, this yields the maximum dp[j] + 1 possible.
                    break;
                }
            }
        }
        
        return dp[n];
    }
}