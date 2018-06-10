class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        helper(board, click[0], click[1]);
        return board;
    }
    
    public void helper(char[][] board, int r, int c) {
        int row = board.length, col = board[0].length;
        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] == 'B')
            return;
        int count = helper2(board, r, c);
        if (count != 0) {
            board[r][c] = (char)('0' + count);
            return;
        }
        board[r][c] = 'B';
        for (int i = r-1; i <= r+1; i++) {
            for (int j = c-1; j <= c+1; j++) {
                if (i == r && j == c)
                    continue;
                helper(board, i, j);
            }
        }
    }
    
    public int helper2(char[][] board, int r, int c) {
        int row = board.length, col = board[0].length;
        int res = 0;
        for (int i = r-1; i <= r+1; i++) {
            for (int j = c-1; j <= c+1; j++) {
                if (i == r && j == c)
                    continue;
                if (i < 0 || i >= row || j < 0 || j >= col)
                    continue;
                if (board[i][j] == 'M')
                    res++;
            }
        }
        return res;
    }
}


class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = board.length, col = board[0].length;
        int r = click[0], c = click[1];
        if (r < 0 || r >= row || c < 0 || c >= col)
            return board;
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        } else if (board[r][c] == 'B') {
            return board;
        }
        int count = helper(board, r, c);
        if (count != 0) {
            board[r][c] = (char)('0' + count);
            return board;
        }
        board[r][c] = 'B';
        for (int i = r-1; i <= r+1; i++) {
            for (int j = c-1; j <= c+1; j++) {
                if (i == r && j == c)
                    continue;
                click[0] = i;
                click[1] = j;
                updateBoard(board, click);
            }
        }
        return board;
    }
    
    public int helper(char[][] board, int r, int c) {
        int row = board.length, col = board[0].length;
        int res = 0;
        for (int i = r-1; i <= r+1; i++) {
            for (int j = c-1; j <= c+1; j++) {
                if (i == r && j == c)
                    continue;
                if (i < 0 || i >= row || j < 0 || j >= col)
                    continue;
                if (board[i][j] == 'M')
                    res++;
            }
        }
        return res;
    }
}


class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = board.length, col = board[0].length;
        int r = click[0], c = click[1];
        if (r < 0 || r >= row || c < 0 || c >= col)
            return board;
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        } else if (board[r][c] == 'B') {
            return board;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            if (board[xy[0]][xy[1]] == 'B')
                continue;
            int count = helper(board, xy[0], xy[1]);
            if (count != 0) {
                board[xy[0]][xy[1]] = (char)('0' + count);
            } else {
                board[xy[0]][xy[1]] = 'B';
                for (int i = xy[0]-1; i <= xy[0]+1; i++) {
                    for (int j = xy[1]-1; j <= xy[1]+1; j++) {
                        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != 'E')
                            continue;
                        queue.offer(new int[]{i, j});
                    }
                }
            }
        }
        return board;
    }
    
    public int helper(char[][] board, int r, int c) {
        int row = board.length, col = board[0].length;
        int res = 0;
        for (int i = r-1; i <= r+1; i++) {
            for (int j = c-1; j <= c+1; j++) {
                if (i == r && j == c)
                    continue;
                if (i < 0 || i >= row || j < 0 || j >= col)
                    continue;
                if (board[i][j] == 'M')
                    res++;
            }
        }
        return res;
    }
}
