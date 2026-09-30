class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];
        int depth = 0;
        
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                // Assign based on depth parity upon entering a level
                answer[i] = depth % 2;
            } else {
                // Assign at the same depth level before stepping out
                answer[i] = depth % 2;
                depth--;
            }
        }
        
        return answer;
    }
}