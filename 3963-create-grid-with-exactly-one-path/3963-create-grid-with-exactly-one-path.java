class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        
        // Row 0: All free cells (path moves all the way to the right)
        StringBuilder row0 = new StringBuilder();
        for (int j = 0; j < n; j++) {
            row0.append('.');
        }
        grid[0] = row0.toString();
        
        // Rows 1 to m-1: All obstacles except the last column
        if (m > 1) {
            StringBuilder otherRow = new StringBuilder();
            for (int j = 0; j < n - 1; j++) {
                otherRow.append('#');
            }
            otherRow.append('.');
            
            String rowStr = otherRow.toString();
            for (int i = 1; i < m; i++) {
                grid[i] = rowStr;
            }
        }
        
        return grid;
    }
}