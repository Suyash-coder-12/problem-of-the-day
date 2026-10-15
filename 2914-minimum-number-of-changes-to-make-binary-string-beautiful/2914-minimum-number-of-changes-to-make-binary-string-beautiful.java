class Solution {
    public int minChanges(String s) {
        int changes = 0;
        
        // Iterate through the string in steps of 2
        for (int i = 0; i < s.length(); i += 2) {
            // Check if the current pair of characters mismatch
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++; // We need exactly 1 change to make them match
            }
        }
        
        return changes;
    }
}