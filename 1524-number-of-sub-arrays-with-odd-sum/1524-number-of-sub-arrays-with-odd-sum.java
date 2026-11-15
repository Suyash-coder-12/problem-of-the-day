class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        int parity = 0;

        // Count how many running prefix sums have odd parity
        for (int num : arr) {
            parity ^= (num & 1);
            oddCount += parity;
        }

        // Including the initial empty prefix sum (0), there are arr.length + 1 total prefix sums
        long evenCount = arr.length + 1 - oddCount;

        return (int) ((oddCount * evenCount) % 1_000_000_007L);
    }
}