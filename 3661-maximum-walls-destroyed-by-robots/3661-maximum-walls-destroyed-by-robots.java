import java.util.Arrays;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        
        // r[i][0] = position, r[i][1] = distance
        int[][] r = new int[n][2];
        for (int i = 0; i < n; i++) {
            r[i][0] = robots[i];
            r[i][1] = distance[i];
        }
        
        // Sort robots by their positions
        Arrays.sort(r, (a, b) -> Integer.compare(a[0], b[0]));
        // Sort walls for binary search
        Arrays.sort(walls);
        
        int baseWalls = 0;
        // Count walls that are exactly at any robot's position
        for (int i = 0; i < n; i++) {
            int pos = r[i][0];
            int idx = lowerBound(walls, pos);
            if (idx < walls.length && walls[idx] == pos) {
                baseWalls++;
            }
        }
        
        // DP Initialization
        // dp0: max walls destroyed up to the segment before Robot 0, assuming Robot 0 fires Left
        long dp0 = countWalls(walls, r[0][0] - r[0][1], r[0][0] - 1);
        // dp1: max walls destroyed up to the segment before Robot 0, assuming Robot 0 fires Right
        long dp1 = 0; 
        
        // Iterate through adjacent robots
        for (int i = 1; i < n; i++) {
            int prevX = r[i-1][0];
            int prevD = r[i-1][1];
            int currX = r[i][0];
            int currD = r[i][1];
            
            // Interval for Robot[i-1] firing Right
            int AR = prevX + 1;
            int BR = Math.min(prevX + prevD, currX - 1);
            int VR = countWalls(walls, AR, BR);
            
            // Interval for Robot[i] firing Left
            int AL = Math.max(currX - currD, prevX + 1);
            int BL = currX - 1;
            int VL = countWalls(walls, AL, BL);
            
            // Overlapping Check if Both shoot into the segment
            int VBOTH = 0;
            if (BR >= AL) { 
                VBOTH = countWalls(walls, AR, BL); 
            } else {
                VBOTH = VR + VL;
            }
            
            long nextDp0 = Math.max(dp0 + VL, dp1 + VBOTH);
            long nextDp1 = Math.max(dp0, dp1 + VR);
            
            dp0 = nextDp0;
            dp1 = nextDp1;
        }
        
        // Add the walls destroyed by the last robot firing right into the unbounded space
        int wallsAfterLast = countWalls(walls, r[n-1][0] + 1, r[n-1][0] + r[n-1][1]);
        long maxDestroyedInSegments = Math.max(dp0, dp1 + wallsAfterLast);
        
        return (int) (maxDestroyedInSegments + baseWalls);
    }
    
    private int countWalls(int[] walls, int A, int B) {
        if (A > B) return 0;
        int left = lowerBound(walls, A);
        int right = upperBound(walls, B);
        return right - left;
    }
    
    // Finds the first index where walls[idx] >= target
    private int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    // Finds the first index where walls[idx] > target
    private int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}