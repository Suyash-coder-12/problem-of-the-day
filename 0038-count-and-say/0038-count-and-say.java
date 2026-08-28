class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        
        String current = "1";
        
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int count = 1;
            
            for (int j = 1; j < current.length(); j++) {
                // If the current character matches the previous one, increment the count
                if (current.charAt(j) == current.charAt(j - 1)) {
                    count++;
                } else {
                    // Otherwise, append the count and the previous character, then reset count
                    next.append(count).append(current.charAt(j - 1));
                    count = 1;
                }
            }
            
            // Append the final sequence group after the loop finishes
            next.append(count).append(current.charAt(current.length() - 1));
            
            // Update current for the next iteration
            current = next.toString();
        }
        
        return current;
    }
}