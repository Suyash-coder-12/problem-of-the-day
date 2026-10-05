class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // Only add to the score when we hit an innermost "()" pair
                if (s.charAt(i - 1) == '(') {
                    score += (1 << depth); // Equivalent to 2^depth
                }
            }
        }
        
        return score;
    }
}