class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // c == '*'
                minOpen--; // Treat '*' as ')'
                maxOpen++; // Treat '*' as '('
            }
            
            // Even with all '*' as '(', we have too many ')'
            if (maxOpen < 0) {
                return false;
            }
            
            // We can't have negative open parentheses; 
            // clamp to 0 (equivalent to treating a '*' as "" instead of ')')
            if (minOpen < 0) {
                minOpen = 0;
            }
        }
        
        // Valid if we can end up with 0 unmatched open parentheses
        return minOpen == 0;
    }
}