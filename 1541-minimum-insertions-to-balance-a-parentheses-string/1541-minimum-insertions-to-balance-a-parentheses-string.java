class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededClose = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If we need an odd number of ')', we are missing exactly one ')' 
                // for the previous '(' before starting this new one.
                if (neededClose % 2 != 0) {
                    insertions++;     // Insert the missing ')'
                    neededClose--;    // We just fulfilled that need
                }
                neededClose += 2;     // This new '(' needs two ')'
                
            } else { // c == ')'
                neededClose--;
                
                // If neededClose drops below 0, we have an unmatched ')'
                if (neededClose < 0) {
                    insertions++;     // Insert a missing '('
                    neededClose += 2; // A '(' requires two ')'. We just used one, so we still need 1.
                }
            }
        }
        
        // Add any trailing closing parentheses we still need
        return insertions + neededClose;
    }
}