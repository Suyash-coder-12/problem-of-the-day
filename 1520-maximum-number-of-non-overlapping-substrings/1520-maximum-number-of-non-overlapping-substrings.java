import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        
        // Step 1: Record the first and last occurrences of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }
        
        List<int[]> intervals = new ArrayList<>();
        
        // Step 2: Find all valid intervals starting at each character's first occurrence
        for (int i = 0; i < 26; i++) {
            if (first[i] != -1) {
                int end = getRightBoundary(s, i, first, last);
                if (end != -1) {
                    intervals.add(new int[]{first[i], end});
                }
            }
        }
        
        // Step 3: Sort intervals by end time ascending, then start time descending (to favor shorter lengths)
        intervals.sort((a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(b[0], a[0]); 
            }
            return Integer.compare(a[1], b[1]);
        });
        
        // Step 4: Greedily pick non-overlapping intervals
        List<String> result = new ArrayList<>();
        int lastEnd = -1;
        
        for (int[] interval : intervals) {
            // If the interval strictly starts after the end of the last picked interval
            if (interval[0] > lastEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1];
            }
        }
        
        return result;
    }
    
    private int getRightBoundary(String s, int c, int[] first, int[] last) {
        int start = first[c];
        int end = last[c];
        
        // Dynamically expand the end boundary for any characters caught in the middle
        for (int i = start; i <= end; i++) {
            int charIdx = s.charAt(i) - 'a';
            
            // If a character's first occurrence is before our start, it invalidates this substring
            if (first[charIdx] < start) {
                return -1; 
            }
            end = Math.max(end, last[charIdx]);
        }
        
        return end;
    }
}