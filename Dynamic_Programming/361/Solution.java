class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int res = 0;
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    int m = i - 1, n = i + 1, kill = 0;
                    while (m >= 0 || n < row) {
                        if (m >= 0)
                            if (grid[m][j] == 'E')
                                kill++;
                            else if (grid[m][j] == 'W')
                                m = -1;
                        if (n < row)
                            if (grid[n][j] == 'E')
                                kill++;
                            else if (grid[n][j] == 'W')
                                n = row;
                        m--;
                        n++;
                    }
                    m = j - 1;
                    n = j + 1;
                    while (m >= 0 || n < col) {
                        if (m >= 0)
                            if (grid[i][m] == 'E')
                                kill++;
                            else if (grid[i][m] == 'W')
                                m = -1;
                        if (n < col)
                            if (grid[i][n] == 'E')
                                kill++;
                            else if (grid[i][n] == 'W')
                                n = col;
                        m--;
                        n++;
                    }
                    if (kill > res)
                        res = kill;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int res = 0;
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            int start = 0, num = 0;
            for (int j = 0; j <= col; j++) {
                if (j == col || grid[i][j] == 'W') {
                    for (int k = start; k < j; k++) {
                        if (grid[i][k] == '0')
                            grid[i][k] = (char)num;
                    }
                    num = 0;
                    start = j + 1;
                } else if (grid[i][j] == 'E') {
                    num++;
                }
            }
        }
        for (int j = 0; j < col; j++) {
            int start = 0, num = 0;
            for (int i = 0; i <= row; i++) {
                if (i == row || grid[i][j] == 'W') {
                    for (int k = start; k < i; k++) {
                        if (grid[k][j] != 'E' && grid[k][j] + num > res) 
                            res = grid[k][j] + num;
                    }
                    num = 0;
                    start = i + 1;
                } else if (grid[i][j] == 'E') {
                    num++;
                }
            }
        }
        return res;
    }
}
