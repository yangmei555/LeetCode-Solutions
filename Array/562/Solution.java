class Solution {
    public int longestLine(int[][] M) {
        if (M.length == 0 || M[0].length == 0)
            return 0;
        int[][][] dp = new int[M.length][M[0].length][4];
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    dp[i][j][0] = j == 0 ? 1 : dp[i][j-1][0]+1;
                    dp[i][j][1] = i == 0 || j == 0 ? 1 : dp[i-1][j-1][1]+1;
                    dp[i][j][2] = i == 0 ? 1 : dp[i-1][j][2]+1;
                    dp[i][j][3] = i == 0 || j == M[0].length-1 ? 1 : dp[i-1][j+1][3]+1;
                    res = res > dp[i][j][0] ? res : dp[i][j][0];
                    res = res > dp[i][j][1] ? res : dp[i][j][1];
                    res = res > dp[i][j][2] ? res : dp[i][j][2];
                    res = res > dp[i][j][3] ? res : dp[i][j][3];
                }
            }
        }
        return res;
    }
}


class Solution {
    public int longestLine(int[][] M) {
        if (M.length == 0 || M[0].length == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            int count = 0;
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    count++;
                    res = res > count ? res : count;
                } else {
                    count = 0;
                }
            }
        }
        for (int i = 0; i < M[0].length; i++) {
            int count = 0;
            for (int j = 0; j < M.length; j++) {
                if (M[j][i] == 1) {
                    count++;
                    res = res > count ? res : count;
                } else {
                    count = 0;
                }
            }
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (i == 0 || j == 0) {
                    int r = i, c = j, dia = 0, ant = 0;
                    while (r < M.length && c < M[0].length) {
                        if (M[r][c] == 1) {
                            dia++;
                            res = res > dia ? res : dia;
                        } else {
                            dia = 0;
                        }
                        if (M[r][M[0].length-1-c] == 1) {
                            ant++;
                            res = res > ant ? res : ant;
                        } else {
                            ant = 0;
                        }
                        r++;
                        c++;
                    }
                }
            }
        }
        return res;
    }
}
