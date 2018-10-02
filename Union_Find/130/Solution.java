class Solution {
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        int row = board.length, col = board[0].length;
        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O')
                helper(board, 0, j);
            if (board[row-1][j] == 'O')
                helper(board, row-1, j);
        }
        for (int i = 1; i < row-1; i++) {
            if (board[i][0] == 'O')
                helper(board, i, 0);
            if (board[i][col-1] == 'O')
                helper(board, i, col-1);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'o')
                    board[i][j] = 'O';
            }
        }
        return;
    }
    
    public void helper(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O')
            return;
        board[r][c] = 'o';
        helper(board, r+1, c);
        helper(board, r-1, c);
        helper(board, r, c+1);
        helper(board, r, c-1);
    }
}


// the so-called union find  
class Solution {
    int[] parent, rank;
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        int row = board.length, col = board[0].length;
        parent = new int[row * col + 1];
        rank = new int[row * col + 1];
        for (int i = 0; i < parent.length; i++)
            makeSet(i);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == row-1 || j == 0 || j == col-1) {
                        union(i * col + j, row * col);
                    } else {
                        if (board[i-1][j] == 'O')
                            union(i * col + j, (i-1) * col + j);
                        if (board[i+1][j] == 'O')
                            union(i * col + j, (i+1) * col + j);
                        if (board[i][j-1] == 'O')
                            union(i * col + j, i * col + j-1);
                        if (board[i][j+1] == 'O')
                            union(i * col + j, i * col + j+1);
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O' && findSet(i * col + j) != findSet(row * col))
                    board[i][j] = 'X';
            }
        }
        return;
    }
    
    public void makeSet(int i) {
        parent[i] = i;
        rank[i] = 0;
    }
    
    // two ways of path compression 
    public int findSet(int i) {
        // if (parent[i] != i)
        //     parent[i] = findSet(parent[i]);
        // return parent[i];

        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
    
    public void union(int i, int j) {
        int x = findSet(i), y = findSet(j);
        if (x != y)
            link(x, y);
    }
    
    public void link(int x, int y) {
        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }
}
