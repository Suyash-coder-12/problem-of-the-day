class Solution {
    public String intToRoman(int num) {
        // Define the values and their corresponding Roman symbols in descending order
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder roman = new StringBuilder();
        
        // Iterate through the values
        for (int i = 0; i < values.length && num > 0; i++) {
            // While the current value can be subtracted from num
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]); // Append the corresponding symbol
            }
        }
        
        return roman.toString();
    }
}