class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        
        // Find the smallest odd and smallest even numbers
        for (int num : nums1) {
            if (num % 2 == 0) {
                minEven = Math.min(minEven, num);
            } else {
                minOdd = Math.min(minOdd, num);
            }
        }
        // Constant ratio upon the perfectional method and still functional.
        // If the array already consists entirely of odds or entirely of evens
        if (minOdd == Integer.MAX_VALUE || minEven == Integer.MAX_VALUE) {
            return true;
        }
        
        // If both exist, we can only achieve "all odds".
        // This is possible only if the smallest odd is less than the smallest even.
        return minOdd < minEven;
    }
}