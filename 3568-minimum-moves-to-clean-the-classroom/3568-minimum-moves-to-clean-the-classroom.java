import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        int numLitter = 0;
        int[][] litterId = new int[m][n];
        
        // Parse the grid to find the start and index the litter
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                litterId[r][c] = -1;
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterId[r][c] = numLitter++;
                }
            }
        }
        
        // Edge case: no litter to clean
        if (numLitter == 0) return 0;
        
        // maxEnergy[r][c][mask] stores the maximum energy we've had at this state
        int[][][] maxEnergy = new int[m][n][1 << numLitter];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }
        
        // Queue stores arrays of [row, col, current_energy, collected_litter_mask]
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR, startC, energy, 0});
        maxEnergy[startR][startC][0] = energy;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        int moves = 0;
        int targetMask = (1 << numLitter) - 1;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                int e = curr[2];
                int mask = curr[3];
                
                // If we've collected all the litter, return the current move count
                if (mask == targetMask) {
                    return moves;
                }
                
                // If out of energy, we cannot make further moves from this cell 
                if (e == 0) {
                    continue;
                }
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        int ne = e - 1;
                        int nmask = mask;
                        char nch = classroom[nr].charAt(nc);
                        
                        // Pick up litter if present
                        if (nch == 'L') {
                            nmask |= (1 << litterId[nr][nc]);
                        } 
                        // Reset energy if on a reset pad
                        else if (nch == 'R') {
                            ne = energy;
                        }
                        
                        // Explore this neighbor only if it provides a strictly better energy state
                        if (ne > maxEnergy[nr][nc][nmask]) {
                            maxEnergy[nr][nc][nmask] = ne;
                            q.offer(new int[]{nr, nc, ne, nmask});
                        }
                    }
                }
            }
            moves++;
        }
        
        // Impossible to clean all litter
        return -1; 
    }
}