class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;  // Counts unmatched ')' that need a '(' before them
        int closeNeeded = 0; // Counts unmatched '(' that need a ')' after them
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                closeNeeded++;
            } else { // c == ')'
                if (closeNeeded > 0) {
                    // Match with a previous '('
                    closeNeeded--;
                } else {
                    // No '(' available to match; we must insert a '('
                    openNeeded++;
                }
            }
        }
        
        return openNeeded + closeNeeded;
    }
}