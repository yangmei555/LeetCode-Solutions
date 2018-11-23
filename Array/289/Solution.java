class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int live = 0;
                for (int k = 0; k < dir.length; k++) {
                    if (i+dir[k][0] >= 0 && i+dir[k][0]<board.length && j+dir[k][1]>=0 && 
                        j+dir[k][1]<board[0].length && board[i+dir[k][0]][j+dir[k][1]] > 0)
                        live++;
                }
                if (board[i][j] == 0 && live == 3) {
                    board[i][j] = -1;
                } else if (board[i][j] == 1) {
                    board[i][j] <<= live;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                } else if (board[i][j] > 0) {
                    if (board[i][j] == 4 || board[i][j] == 8)
                        board[i][j] = 1;
                    else
                        board[i][j] = 0;
                }
            }
        }
        return;
    }
}


class Solution {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int neighbour = getNeighbour(board, i, j);
                if (board[i][j] == 1)
                    board[i][j] = neighbour+1;
                else
                    board[i][j] = -neighbour-1;
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 3 || board[i][j] == 4 || board[i][j] == -4)
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
            }
        }
    }
    
    public int getNeighbour(int[][] board, int r, int c) {
        int res = 0;
        for (int i = r-1; i <= r+1; i++) {
            if (i < 0 || i == board.length)
                continue;
            for (int j = c-1; j <= c+1; j++) {
                if (j < 0 || j == board[i].length)
                    continue;
                if (i == r && j == c)
                    continue;
                if (board[i][j] > 0)
                    res++;
            }
        }
        return res;
    }
}
