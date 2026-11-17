import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        // Precompute the proper divisors for each possible substring length up to n
        List<Integer>[] divs = new ArrayList[n + 1];
        for (int length = 2; length <= n; length++) {
            divs[length] = new ArrayList<>();
            for (int d = 1; d < length; d++) {
                if (length % d == 0) {
                    divs[length].add(d);
                }
            }
        }
        
        // Precompute the minimum changes to make each substring s[i...j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int L = j - i + 1;
                int minChanges = Integer.MAX_VALUE;
                
                // Check for all valid proper divisors of the substring's length
                for (int d : divs[L]) {
                    int currentChanges = 0;
                    
                    // For each group offset `r`
                    for (int r = 0; r < d; r++) {
                        int left = i + r;
                        int right = i + r + L - d;
                        
                        // Count character differences for the "palindrome" requirement
                        while (left < right) {
                            if (chars[left] != chars[right]) {
                                currentChanges++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    if (currentChanges < minChanges) {
                        minChanges = currentChanges;
                    }
                }
                cost[i][j] = minChanges;
            }
        }
        
        // dp[p][c] implies min cost for partitioning prefix of length `p` into `c` semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        
        // Initialize DP table with a large value to prevent overflow during addition
        int INF = Integer.MAX_VALUE / 2; 
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        
        // Fill DP table
        for (int c = 1; c <= k; c++) {
            // p minimum size is c * 2 because each semi-palindrome must be length >= 2
            for (int p = c * 2; p <= n; p++) {
                // m points to the end of the previous sequence.
                // Must leave at least length 2 for the last substring (p - m >= 2).
                for (int m = (c - 1) * 2; m <= p - 2; m++) {
                    if (dp[m][c - 1] != INF) {
                        int currentPartitionCost = dp[m][c - 1] + cost[m][p - 1];
                        if (currentPartitionCost < dp[p][c]) {
                            dp[p][c] = currentPartitionCost;
                        }
                    }
                }
            }
        }
        
        return dp[n][k];
    }
}