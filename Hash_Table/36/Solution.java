class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set[] rowset = new Set[9];
        Set[] colset = new Set[9];
        Set[] boxset = new Set[9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;
                if (rowset[i] == null)
                    rowset[i] = new HashSet();
                if (!rowset[i].add(c))
                    return false;
                if (colset[j] == null)
                    colset[j] = new HashSet();
                if (!colset[j].add(c))
                    return false;
                int boxindex = i / 3 * 3 + j / 3;
                if (boxset[boxindex] == null)
                    boxset[boxindex] = new HashSet();
                if (!boxset[boxindex].add(c))
                    return false;
            }
        }
        return true;
    }
}


class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowset = new boolean[9][9];
        boolean[][] colset = new boolean[9][9];
        boolean[][] boxset = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;
                if (rowset[i][c-'1'])
                    return false;
                rowset[i][c-'1'] = true;
                if (colset[j][c-'1'])
                    return false;
                colset[j][c-'1'] = true;
                int boxindex = i / 3 * 3 + j / 3;
                if (boxset[boxindex][c-'1'])
                    return false;
                boxset[boxindex][c-'1'] = true;
            }
        }
        return true;
    }
}
