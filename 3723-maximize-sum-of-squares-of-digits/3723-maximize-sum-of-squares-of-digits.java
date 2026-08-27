class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the required sum is strictly greater than what we can achieve 
        // with 'num' digits (all 9s), it's impossible.
        if (sum > 9L * num) {
            return "";
        }
        
        // Using a char array is much faster than appending to a StringBuilder
        char[] result = new char[num];
        
        for (int i = 0; i < num; i++) {
            // Take the maximum possible digit for the current position
            int digit = Math.min(9, sum);
            result[i] = (char) ('0' + digit);
            sum -= digit;
        }
        
        return new String(result);
    }
}