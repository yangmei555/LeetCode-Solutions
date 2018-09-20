class Solution {
    public int[][] candyCrush(int[][] board) {
        int row = board.length, col = board[0].length;
        boolean flag = false;
        for (int i = 0; i < row; i++) {
            int index = 0;
            while (index < col) {
                int start = index++;
                while (index < col && board[i][start] == board[i][index])
                    index++;
                if (board[i][start] != 0 && index - start >= 3) {
                    flag = true;
                    for (int k = start; k < index; k++)
                        board[i][k] = -board[i][k];
                }
            }
        }
        for (int j = 0; j < col; j++) {
            int index = 0;
            while (index < row) {
                int start = index++;
                while (index < row && (board[index][j] == board[start][j] || 
                                        board[index][j] + board[start][j] == 0))
                    index++;
                if (board[start][j] != 0 && index - start >= 3) {
                    flag = true;
                    for (int k = start; k < index; k++) {
                        if (board[k][j] > 0)
                            board[k][j] = -board[k][j];
                    }
                }
            }
        }
        if (!flag) {
            return board;
        } else {
            for (int j = 0; j < col; j++) {
                int index = row-1;
                for (int i = row-1; i >= 0; i--) {
                    if (board[i][j] > 0)
                        board[index--][j] = board[i][j];
                }
                while (index >= 0)
                    board[index--][j] = 0;
            }
            return candyCrush(board);
        }
    }
}
