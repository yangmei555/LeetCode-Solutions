class Solution {
    int res = 0;
    public int numberOfPatterns(int m, int n) {
        boolean[][] v = new boolean[3][3];
        helper(v, m, n, 0, 0, 1);
        res *= 4;
        int pre = res;
        helper(v, m, n, 0, 1, 1);
        res += (res - pre) * 3;
        helper(v, m, n, 1, 1, 1);
        return res;
    }
    public void helper(boolean[][] v, int m, int n, int row, int col, int len) {
        if (v[row][col] == true || len > n)
            return;
        if (len >= m && len <= n)
            res++;
        if (len == n)
            return;
        v[row][col] = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int rd = i - row > 0 ? i - row : row - i;
                int cd = j - col > 0 ? j - col : col - j;
                int nr = 0, nc = 0;
                if (i == 0)
                    nr = i + rd / 2;
                else if (i == 1)
                    nr = i;
                else
                    nr = i - rd / 2;
                if (j == 0)
                    nc = j + cd / 2;
                else if (j == 1)
                    nc = j;
                else
                    nc = j - cd / 2;
                if (!(rd % 2 == 0 && cd % 2 == 0 && !v[nr][nc]))
                    helper(v, m, n, i, j, len + 1);
            }
        }
        v[row][col] = false;
    }
}


class Solution {
    int res = 0;
    public int numberOfPatterns(int m, int n) {
        int[][] skip = new int[9][9];
        skip[0][2] = skip[2][0] = 1;
        skip[0][6] = skip[6][0] = 3;
        skip[0][8] = skip[8][0] = 4;
        skip[2][6] = skip[6][2] = 4;
        skip[2][8] = skip[8][2] = 5;
        skip[6][8] = skip[8][6] = 7;
        skip[1][7] = skip[7][1] = 4;
        skip[3][5] = skip[5][3] = 4;
        boolean[] v = new boolean[9];
        helper(v, skip, m, n, 0, 1);
        res *= 4;
        int pre = res;
        helper(v, skip, m, n, 1, 1);
        res += (res - pre) * 3;
        helper(v, skip, m, n, 4, 1);
        return res;
    }
    public void helper(boolean[] v, int[][] skip, int m, int n, int index, int len) {
        if (v[index] == true || len > n)
            return;
        if (len >= m && len <= n)
            res++;
        if (len == n)
            return;
        v[index] = true;
        for (int i = 0; i < 9; i++) {
            if (v[i] == false && (skip[index][i] == 0 || v[skip[index][i]] == true))
                helper(v, skip, m, n, i, len + 1);
        }
        v[index] = false;
    }
}
