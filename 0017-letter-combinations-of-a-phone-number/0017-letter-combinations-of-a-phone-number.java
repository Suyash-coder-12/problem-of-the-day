import java.util.ArrayList;
import java.util.List;

class Solution {
    // Array mapping digits 0-9 to their corresponding letters
    private static final String[] KEYPAD = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // Handle edge case for empty input
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // Base case: we have formed a complete combination
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        // Get the letters that the current digit maps to
        char digit = digits.charAt(index);
        String letters = KEYPAD[digit - '0'];
        
        // Loop through the letters and backtrack
        for (char letter : letters.toCharArray()) {
            current.append(letter); // Choose
            backtrack(result, current, digits, index + 1); // Explore
            current.deleteCharAt(current.length() - 1); // Un-choose (backtrack)
        }
    }
}