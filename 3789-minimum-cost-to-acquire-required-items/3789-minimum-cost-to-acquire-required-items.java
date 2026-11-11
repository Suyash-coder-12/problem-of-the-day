class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long common = Math.min(need1, need2);
        long rem1 = need1 - common;
        long rem2 = need2 - common;

        // Best unit price to satisfy both needs simultaneously
        long bestBoth = Math.min((long) cost1 + cost2, costBoth);

        // Best unit prices to satisfy remaining individual needs
        long best1 = Math.min(cost1, costBoth);
        long best2 = Math.min(cost2, costBoth);

        return common * bestBoth + rem1 * best1 + rem2 * best2;
    }
}