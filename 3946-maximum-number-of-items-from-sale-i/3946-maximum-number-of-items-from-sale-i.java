import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 1500;

        // Step 1: Count frequency of each factor and find the globally cheapest item
        int[] freq = new int[maxFactor + 1];
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            freq[item[0]]++;
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        // Precompute total multiples (including self) for each factor
        int[] multipleCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] > 0) {
                for (int m = f; m <= maxFactor; m += f) {
                    multipleCount[f] += freq[m];
                }
            }
        }

        // Step 2: 0-1 Knapsack for buying at most 1 copy of each distinct item
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            if (price > budget) {
                continue;
            }
            // Value = 1 purchased copy + (multipleCount[factor] - 1) free copies = multipleCount[factor]
            int value = multipleCount[items[i][0]];

            for (int c = budget; c >= price; c--) {
                if (dp[c - price] != -1) {
                    dp[c] = Math.max(dp[c], dp[c - price] + value);
                }
            }
        }

        // Step 3: Spend any remaining budget on the single cheapest item
        int maxTotal = 0;
        for (int c = 0; c <= budget; c++) {
            if (dp[c] != -1) {
                int extraCopies = (budget - c) / minPrice;
                maxTotal = Math.max(maxTotal, dp[c] + extraCopies);
            }
        }

        return maxTotal;
    }
}