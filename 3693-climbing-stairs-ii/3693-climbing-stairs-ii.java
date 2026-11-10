class Solution {
    public int climbStairs(int n, int[] costs) {
        final int INF = Integer.MAX_VALUE / 2;
        
        // dp1 = dp[i - 1], dp2 = dp[i - 2], dp3 = dp[i - 3]
        int dp1 = 0;   // Base case: dp[0] = 0
        int dp2 = INF;
        int dp3 = INF;

        for (int i = 1; i <= n; i++) {
            int bestPrev = Math.min(dp1 + 1, Math.min(dp2 + 4, dp3 + 9));
            int curr = costs[i - 1] + bestPrev;

            // Shift states forward for the next step
            dp3 = dp2;
            dp2 = dp1;
            dp1 = curr;
        }

        return dp1;
    }
}