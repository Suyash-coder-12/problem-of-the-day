class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 2; // Any subarray of length 2 satisfies a * b == lcm(a, b) * gcd(a, b)

        for (int i = 0; i < n; i++) {
            long prod = nums[i];
            long g = nums[i];
            long l = nums[i];

            for (int j = i + 1; j < n; j++) {
                prod *= nums[j];
                g = gcd(g, nums[j]);
                l = (l * nums[j]) / gcd(l, nums[j]);

                // Since nums[k] <= 10, lcm * gcd <= 2520 * 10 = 25200
                if (prod > 25200) {
                    break;
                }

                if (prod == l * g) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}