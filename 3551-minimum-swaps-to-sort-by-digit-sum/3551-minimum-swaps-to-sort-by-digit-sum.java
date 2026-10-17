import java.util.Arrays;

class Solution {
    // Helper class to store the original state and sorting keys
    static class Element {
        int val;
        int originalIdx;
        int digitSum;

        Element(int val, int originalIdx, int digitSum) {
            this.val = val;
            this.originalIdx = originalIdx;
            this.digitSum = digitSum;
        }
    }

    // Helper method to compute the sum of digits
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Element[] arr = new Element[n];
        
        // 1. Populate the array with values, original indices, and digit sums
        for (int i = 0; i < n; i++) {
            arr[i] = new Element(nums[i], i, getDigitSum(nums[i]));
        }

        // 2. Sort according to the problem's custom logic
        Arrays.sort(arr, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }
            return Integer.compare(a.val, b.val);
        });

        // 3. Count minimum swaps using cycle decomposition
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;

        for (int i = 0; i < n; i++) {
            // Skip if already placed correctly or already visited in a previous cycle
            if (visited[i] || arr[i].originalIdx == i) {
                continue;
            }

            // Find the size of the current cycle
            int cycleSize = 0;
            int j = i;
            
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j].originalIdx; // Move to the index where this element belongs
                cycleSize++;
            }

            // A cycle of size K requires K - 1 swaps
            if (cycleSize > 1) {
                totalSwaps += (cycleSize - 1);
            }
        }

        return totalSwaps;
    }
}