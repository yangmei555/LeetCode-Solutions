class Solution {
    boolean[][] rows = new boolean[9][9];
    boolean[][] cols = new boolean[9][9];
    boolean[][] boxes = new boolean[9][9];
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    rows[i][board[i][j]-'1'] = true;
                    cols[j][board[i][j]-'1'] = true;
                    boxes[i/3*3+j/3][board[i][j]-'1'] = true;
                }
            }
        }
        helper(board, 0);
    }
    
    public boolean helper(char[][] board, int index) {
        if (index == 81)
            return true;
        int r = index / 9, c = index % 9;
        if (board[r][c] != '.')
            return helper(board, index+1);
        for (int i = 0; i < 9; i++) {
            if (!rows[r][i] && !cols[c][i] && !boxes[r/3*3+c/3][i]) {
                board[r][c] = (char)('1' + i);
                rows[r][i] = true;
                cols[c][i] = true;
                boxes[r/3*3+c/3][i] = true;
                if (helper(board, index+1))
                    return true;
                rows[r][i] = false;
                cols[c][i] = false;
                boxes[r/3*3+c/3][i] = false;
            }
        }
        board[r][c] = '.';
        return false;
    }
}
