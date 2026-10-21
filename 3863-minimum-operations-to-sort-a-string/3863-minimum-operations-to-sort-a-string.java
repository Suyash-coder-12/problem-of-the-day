class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Count frequencies to build the sorted version of the string in O(N) time
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                sb.append((char) (i + 'a'));
                count[i]--;
            }
        }
        String sortedS = sb.toString();
        
        // Case 0: Already sorted
        if (s.equals(sortedS)) {
            return 0;
        }
        
        // Case -1: Length 2 and not sorted
        if (n == 2) {
            return -1;
        }
        
        // Find the mismatch bounds
        int L = 0;
        while (L < n && s.charAt(L) == sortedS.charAt(L)) {
            L++;
        }
        
        int R = n - 1;
        while (R >= 0 && s.charAt(R) == sortedS.charAt(R)) {
            R--;
        }
        
        // Case 1: Mismatch interval is strictly smaller than the whole string
        if (R - L + 1 < n) {
            return 1;
        }
        
        // Identify the absolute minimum and maximum characters in the string
        char minChar = sortedS.charAt(0);
        char maxChar = sortedS.charAt(n - 1);
        
        // Case 3: The minimum is exclusively at the end AND the maximum is exclusively at the start
        if (s.indexOf(minChar) == n - 1 && s.lastIndexOf(maxChar) == 0) {
            return 3;
        }
        
        // Case 2: All other combinations can be sorted by prefix-then-suffix or suffix-then-prefix
        return 2;
    }
}