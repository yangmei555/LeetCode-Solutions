class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0)
            return false;
        char[] ch = word.toCharArray();
        int row = board.length, col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (helper(board, ch, i, j, 0))
                    return true;
            }
        }
        return false;
    }
    
    public boolean helper(char[][] board, char[] ch, int r, int c, int index) {
        if (index == ch.length)
            return true;
        int row = board.length, col = board[0].length;
        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] != ch[index])
            return false;
        board[r][c] = 0;
        if (helper(board, ch, r+1, c, index+1)) 
            return true;
        if (helper(board, ch, r-1, c, index+1))
            return true;
        if (helper(board, ch, r, c+1, index+1))
            return true;
        if (helper(board, ch, r, c-1, index+1))
            return true;
        board[r][c] = ch[index];
        return false;
    }
}
