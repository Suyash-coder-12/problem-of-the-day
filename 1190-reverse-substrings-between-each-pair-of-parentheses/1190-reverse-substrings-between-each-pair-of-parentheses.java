import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Step 1: Precompute the matching parentheses
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stack.pop();
                // Create a two-way mapping between the opening and closing brackets
                pair[i] = j;
                pair[j] = i;
            }
        }
        
        // Step 2: Traverse the string using the wormhole technique
        StringBuilder result = new StringBuilder();
        int i = 0;
        int direction = 1; // 1 for forward, -1 for backward
        
        while (i < n) {
            char c = s.charAt(i);
            
            if (c == '(' || c == ')') {
                // Teleport to the matching parenthesis
                i = pair[i];
                // Reverse the direction of traversal
                direction = -direction;
            } else {
                result.append(c);
            }
            
            // Move to the next character in the current direction
            i += direction;
        }
        
        return result.toString();
    }
}