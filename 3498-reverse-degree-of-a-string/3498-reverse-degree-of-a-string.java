class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Map 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedAlphabetPos = 26 - (s.charAt(i) - 'a');
            
            // 1-based position in the string
            int stringPos = i + 1; 
            
            totalDegree += reversedAlphabetPos * stringPos;
        }
        
        return totalDegree;
    }
}