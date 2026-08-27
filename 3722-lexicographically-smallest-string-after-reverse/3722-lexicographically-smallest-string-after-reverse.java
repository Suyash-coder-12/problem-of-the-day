class Solution {
    public String lexSmallest(String s) {
        String minStr = null;
        int n = s.length();
        
        for (int k = 1; k <= n; k++) {
            // Option 1: Reverse the first k characters
            StringBuilder prefix = new StringBuilder(s.substring(0, k));
            prefix.reverse();
            prefix.append(s.substring(k));
            String firstKReversed = prefix.toString();
            
            if (minStr == null || firstKReversed.compareTo(minStr) < 0) {
                minStr = firstKReversed;
            }
            
            // Option 2: Reverse the last k characters
            StringBuilder suffix = new StringBuilder(s.substring(n - k));
            suffix.reverse();
            String lastKReversed = s.substring(0, n - k) + suffix.toString();
            
            if (lastKReversed.compareTo(minStr) < 0) {
                minStr = lastKReversed;
            }
        }
        
        return minStr;
    }
}