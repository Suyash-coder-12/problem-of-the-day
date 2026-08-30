import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // Push the expected closing bracket for every opening bracket
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // If it's a closing bracket, check if it matches the expected one
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        
        // If the stack is empty, all brackets were matched correctly
        return stack.isEmpty();
    }
}