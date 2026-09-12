class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 1. Place each number in its right place
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1]!= nums[i]) {
                // swap nums[i] with nums[nums[i]-1]
                int correctPos = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[correctPos];
                nums[correctPos] = temp;
            }
        }

        // 2. Find first place where index+1!= value
        for (int i = 0; i < n; i++) {
            if (nums[i]!= i + 1) {
                return i + 1;
            }
        }

        // 3. If all 1..n are present
        return n + 1;
    }
}