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


class Solution {
    public int numberOfPatterns(int m, int n) {
        boolean[] used = new boolean[10];
        used[0] = true;
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[7][9] = skip[9][7] = 8;
        skip[9][3] = skip[3][9] = 6;
        skip[4][6] = skip[6][4] = 5;
        skip[2][8] = skip[8][2] = 5;
        skip[1][9] = skip[9][1] = 5;
        skip[3][7] = skip[7][3] = 5;
        int res1 = 0, res2 = 0, res3 = 0;
        res1 = helper(1, 1, m, n, used, skip);
        res2 = helper(2, 1, m, n, used, skip);
        res3 = helper(5, 1, m, n, used, skip);        
        return res1 * 4 + res2 * 4 + res3;
    }
    
    public int helper(int last, int len, int m, int n, boolean[] used, int[][] skip) {
        if (used[last])
            return 0;
        int res = 0;
        if (len >= m && len <= n)
            res++;
        if (len == n)
            return res;
        used[last] = true;
        for (int i = 1; i < 10; i++) {
            if (!used[i] && used[skip[last][i]]) 
                res += helper(i, len+1, m, n, used, skip);
        }
        used[last] = false;
        return res;
    }
}


class Solution {
    int[][] dependency = new int[10][10];
    public int numberOfPatterns(int m, int n) {
        dependency[1][3] = 2;
        dependency[4][6] = 5;
        dependency[7][9] = 8;
        dependency[1][7] = 4;
        dependency[2][8] = 5;
        dependency[3][9] = 6;
        dependency[1][9] = 5;
        dependency[3][7] = 5;
        int res = 0;
        boolean[] used = new boolean[10];
        for (int i = m; i <= n; i++) {
            res += helper(1, i, used) * 4;
            res += helper(2, i, used) * 4;
            res += helper(5, i, used);
        }
        return res;
    }
    
    public int helper(int start, int len, boolean[] used) {
        if (len == 0)
            return 0;
        if (len == 1)
            return 1;
        used[start] = true;
        int res = 0;
        for (int i = 1; i < 10; i++) {
            if (!used[i]) {
                int dep = dependency[start][i] + dependency[i][start];
                if (dep == 0 || used[dep]) 
                    res += helper(i, len-1, used);
            }
        }
        used[start] = false;
        return res;
    }
}
