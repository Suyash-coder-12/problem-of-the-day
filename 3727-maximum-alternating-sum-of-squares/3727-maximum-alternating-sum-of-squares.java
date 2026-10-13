class Solution {
    public long maxAlternatingSum(int[] nums) {
        // Use counting sort since max absolute value is 40,000
        int[] count = new int[40001];
        for (int num : nums) {
            count[Math.abs(num)]++;
        }
        
        long score = 0;
        int subtractLeft = nums.length / 2;
        
        // Iterate from smallest absolute value to largest
        for (long i = 0; i <= 40000; i++) {
            if (count[(int)i] > 0) {
                int c = count[(int)i];
                
                // How many of these identical elements go into the subtraction group?
                int subs = Math.min(c, subtractLeft);
                // The rest go into the addition group
                int adds = c - subs;
                
                // i * i can be up to 1.6 billion, so compute it in long
                long square = i * i;
                
                score -= (long) subs * square;
                score += (long) adds * square;
                
                subtractLeft -= subs;
            }
        }
        
        return score;
    }
}