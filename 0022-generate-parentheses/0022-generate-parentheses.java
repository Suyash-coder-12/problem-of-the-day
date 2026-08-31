import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }
    
    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // Base case: the string has reached the maximum valid length (2 * n)
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }
        
        // Rule 1: We can add an open parenthesis if we haven't used all 'n' of them
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
        
        // Rule 2: We can add a close parenthesis if it has a matching unclosed open parenthesis
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }
}