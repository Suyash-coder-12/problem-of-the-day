class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    // Convert char '1'-'9' to integer 0-8
                    int val = board[r][c] - '1'; 
                    
                    // Calculate the 3x3 box index (0 to 8)
                    int boxIndex = (r / 3) * 3 + (c / 3);
                    
                    // If the number was already seen in the current row, col, or box
                    if (rows[r][val] || cols[c][val] || boxes[boxIndex][val]) {
                        return false; 
                    }
                    
                    // Mark as seen
                    rows[r][val] = true;
                    cols[c][val] = true;
                    boxes[boxIndex][val] = true;
                }
            }
        }
        
        return true;
    }
}