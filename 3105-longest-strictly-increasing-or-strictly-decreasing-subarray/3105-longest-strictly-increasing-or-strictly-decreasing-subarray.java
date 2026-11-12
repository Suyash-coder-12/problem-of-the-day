class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int inc = 1;
        int dec = 1;
        int maxLen = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
                if (inc > maxLen) {
                    maxLen = inc;
                }
            } else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1;
                if (dec > maxLen) {
                    maxLen = dec;
                }
            } else {
                inc = 1;
                dec = 1;
            }
        }

        return maxLen;
    }
}