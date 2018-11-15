class TicTacToe {

    /** Initialize your data structure here. */
    int N;
    int[][] marks;
    public TicTacToe(int n) {
        N = n;
        marks = new int[2][2*N+2];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (++marks[player-1][row] == N)
            return player;
        if (++marks[player-1][N+col] == N)
            return player;
        if (row == col && ++marks[player-1][2*N] == N)
            return player;
        if (row + col == N-1 && ++marks[player-1][2*N+1] == N)
            return player;
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
