class Solution {
    public int movesToChessboard(int[][] board) {
        int N = board.length, count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1)
                    count++;
            }
        }
        if (count != N * N / 2 && count != (N * N + 1) / 2)
            return -1;
        int res = 0;
        int ret = helper(board, true);
        if (ret == -1)
            return -1;
        res += ret;
        ret = helper(board, false);
        if (ret == -1)
            return -1;
        res += ret;
        return res;
    }
    
    public int helper(int[][] board, boolean isRow) {
        int N = board.length;
        int x = -1, y = -1, countX = 0, countXEven = 0;
        for (int i = 0; i < N; i++) {
            int num = 0;
            for (int j = 0; j < N; j++) {
                if (board[isRow ? i : j][isRow ? j : i] == 1)
                    num |= (1 << j);
            }
            if (x == -1)
                x = num;
            else if (x != num && y == -1)
                y = num;
            if (x == num) {
                countX++;
                if (i % 2 == 0)
                    countXEven++;
            } else if (y != num) {
                return -1;
            }
        }
        if (countX != N / 2 && countX != (N + 1) / 2)
            return -1;
        int res = 0;
        if (N % 2 == 0) {
            res += Math.min(countXEven, countX - countXEven);
        } else {
            if (countX > N - countX)
                res += countX - countXEven;
            else
                res += countXEven;
        }
        return res;
    }
}
