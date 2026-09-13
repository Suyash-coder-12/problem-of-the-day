import java.util.ArrayList;
import java.util.List;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        
        // Step 1: Collect coordinates of all 1s
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    list1.add(new int[]{r, c});
                }
                if (img2[r][c] == 1) {
                    list2.add(new int[]{r, c});
                }
            }
        }
        
        // Step 2 & 3: Count frequencies of each translation vector
        // Offset by 'n' handles negative differences safely. Max difference is +/- (n-1).
        int[][] translationCounts = new int[2 * n][2 * n];
        int maxOverlap = 0;
        
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int dr = p2[0] - p1[0] + n;
                int dc = p2[1] - p1[1] + n;
                
                translationCounts[dr][dc]++;
                maxOverlap = Math.max(maxOverlap, translationCounts[dr][dc]);
            }
        }
        
        return maxOverlap;
    }
}