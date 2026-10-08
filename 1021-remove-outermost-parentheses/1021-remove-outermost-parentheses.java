class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // Only append if it's not the outermost opening bracket
                if (depth > 0) {
                    result.append(c);
                }
                depth++;
            } else { // c == ')'
                depth--;
                // Only append if it's not the outermost closing bracket
                if (depth > 0) {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}