import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Step 1: Count frequency of each factor (1 <= factor_i <= n)
        int[] freq = new int[n + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Precompute how many items have a factor divisible by f
        int[] multipleCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] > 0) {
                for (int m = f; m <= n; m += f) {
                    multipleCount[f] += freq[m];
                }
            }
        }

        // Step 2: Sort items by price ascending
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remBudget = budget;
        long minPrice = items[0][1];
        long totalCopies = 0;

        // Step 3: Greedily buy bonus-yielding copies (worth 2 copies each) in order of lowest price
        for (int[] item : items) {
            long price = item[1];
            // Buying 2 copies of the cheapest item gives 2 copies for 2 * minPrice
            if (price >= 2 * minPrice || remBudget < price) {
                break;
            }

            long maxBonusCopies = multipleCount[item[0]] - 1;
            if (maxBonusCopies > 0) {
                long canBuy = Math.min(maxBonusCopies, remBudget / price);
                totalCopies += canBuy * 2;
                remBudget -= canBuy * price;
            }
        }

        // Step 4: Spend all remaining budget on the single cheapest item (1 copy each)
        totalCopies += remBudget / minPrice;

        return (int) totalCopies;
    }
}