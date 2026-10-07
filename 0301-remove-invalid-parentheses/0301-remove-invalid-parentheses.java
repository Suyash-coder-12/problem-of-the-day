import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        // Step 1: Pre-calculate the exact number of '(' and ')' to remove
        int leftRem = 0;
        int rightRem = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--; // Match found
                } else {
                    rightRem++; // Unmatched ')'
                }
            }
        }
        
        // Step 2: Use DFS/Backtracking to find all valid combinations
        Set<String> validExpressions = new HashSet<>();
        dfs(s, 0, leftRem, rightRem, 0, 0, new StringBuilder(), validExpressions);
        
        return new ArrayList<>(validExpressions);
    }
    
    private void dfs(String s, int index, int leftRem, int rightRem, int openCount, int closeCount, StringBuilder sb, Set<String> res) {
        // Pruning: The path is already invalid if there are more closing than opening parentheses
        if (closeCount > openCount) {
            return;
        }
        
        // Base Case: Reached the end of the string
        if (index == s.length()) {
            // If we successfully removed the exact required amount
            if (leftRem == 0 && rightRem == 0) {
                res.add(sb.toString());
            }
            return;
        }
        
        char c = s.charAt(index);
        int currentLength = sb.length(); // Save length for backtracking
        
        // --- Option 1: REMOVE the current character ---
        if (c == '(' && leftRem > 0) {
            dfs(s, index + 1, leftRem - 1, rightRem, openCount, closeCount, sb, res);
        } else if (c == ')' && rightRem > 0) {
            dfs(s, index + 1, leftRem, rightRem - 1, openCount, closeCount, sb, res);
        }
        
        // --- Option 2: KEEP the current character ---
        sb.append(c);
        
        if (c == '(') {
            dfs(s, index + 1, leftRem, rightRem, openCount + 1, closeCount, sb, res);
        } else if (c == ')') {
            dfs(s, index + 1, leftRem, rightRem, openCount, closeCount + 1, sb, res);
        } else {
            // It's a normal letter, just keep it and move on
            dfs(s, index + 1, leftRem, rightRem, openCount, closeCount, sb, res);
        }
        
        // Backtrack: Remove the appended character before returning to the parent call
        sb.setLength(currentLength);
    }
}