import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007;
        int[] bitCounts = new int[32];
        
        // Step 1: Count the frequency of set bits at each position across all numbers
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSum = 0;
        
        // Step 2 & 3: Construct the k largest possible numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            
            for (int bit = 0; bit < 31; bit++) {
                if (bitCounts[bit] > 0) {
                    currentNum |= (1L << bit); // Set the bit
                    bitCounts[bit]--;          // Consume one '1' from this position
                }
            }
            
            // Square the constructed number and add to total with modulo
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSum;
    }
}