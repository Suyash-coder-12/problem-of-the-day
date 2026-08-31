class Solution {
    private boolean[][][] visited;
    private char[] s1, s2;
    
    public boolean possiblyEquals(String s1Str, String s2Str) {
        this.s1 = s1Str.toCharArray();
        this.s2 = s2Str.toCharArray();
        
        // diff ranges strictly between [-999, 999]. 
        // Adding 1000 guarantees a non-negative index up to 2000.
        visited = new boolean[s1.length + 1][s2.length + 1][2001];
        
        return dfs(0, 0, 0);
    }
    
    private boolean dfs(int i, int j, int diff) {
        // Base condition: both strings are fully processed and perfectly aligned
        if (i == s1.length && j == s2.length) {
            return diff == 0;
        }
        
        // If this state has already been evaluated and failed, skip
        if (visited[i][j][diff + 1000]) {
            return false;
        }
        visited[i][j][diff + 1000] = true;
        
        // Case 1: Process numeric wildcard characters in s1
        if (i < s1.length && Character.isDigit(s1[i])) {
            int val = 0;
            // Parse up to 3 consecutive digits
            for (int k = i; k < Math.min(i + 3, s1.length) && Character.isDigit(s1[k]); k++) {
                val = val * 10 + (s1[k] - '0');
                if (dfs(k + 1, j, diff + val)) {
                    return true;
                }
            }
        } 
        // Case 2: Process numeric wildcard characters in s2
        else if (j < s2.length && Character.isDigit(s2[j])) {
            int val = 0;
            for (int k = j; k < Math.min(j + 3, s2.length) && Character.isDigit(s2[k]); k++) {
                val = val * 10 + (s2[k] - '0');
                if (dfs(i, k + 1, diff - val)) {
                    return true;
                }
            }
        } 
        // Case 3: Process literal character matching
        else {
            // s1 is artificially longer, consume an s2 character wildcard
            if (diff > 0 && j < s2.length && Character.isLetter(s2[j])) {
                if (dfs(i, j + 1, diff - 1)) return true;
            } 
            // s2 is artificially longer, consume an s1 character wildcard
            else if (diff < 0 && i < s1.length && Character.isLetter(s1[i])) {
                if (dfs(i + 1, j, diff + 1)) return true;
            } 
            // Both are strictly aligned, literal characters must match perfectly
            else if (diff == 0 && i < s1.length && j < s2.length && s1[i] == s2[j]) {
                if (dfs(i + 1, j + 1, 0)) return true;
            }
        }
        
        return false;
    }
}