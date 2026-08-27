class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        
        // Count character frequencies in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Find how much of the target's prefix we can perfectly match
        int maxI = 0;
        int[] tempFreq = freq.clone();
        for (int i = 0; i < n; i++) {
            int c = target.charAt(i) - 'a';
            if (tempFreq[c] > 0) {
                tempFreq[c]--;
                maxI++;
            } else {
                break;
            }
        }

        // Iterate backwards from the maximum matched index down to 0
        int startI = Math.min(n - 1, maxI);
        for (int i = startI; i >= 0; i--) {
            
            // Rebuild the available characters pool after matching up to index i-1
            int[] available = freq.clone();
            for (int j = 0; j < i; j++) {
                available[target.charAt(j) - 'a']--;
            }

            // Look for the smallest available char strictly greater than target[i]
            int targetChar = target.charAt(i) - 'a';
            int nextChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (available[c] > 0) {
                    nextChar = c;
                    break;
                }
            }

            // If we found a valid character to diverge, build and return the answer
            if (nextChar != -1) {
                StringBuilder sb = new StringBuilder();
                
                // 1. Append the perfectly matched prefix
                sb.append(target.substring(0, i));
                
                // 2. Append the diverging character
                sb.append((char) (nextChar + 'a'));
                available[nextChar]--;

                // 3. Append all remaining characters in lexicographical (sorted) order
                for (int c = 0; c < 26; c++) {
                    while (available[c] > 0) {
                        sb.append((char) (c + 'a'));
                        available[c]--;
                    }
                }
                
                return sb.toString();
            }
        }

        return "";
    }
}