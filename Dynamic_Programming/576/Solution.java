class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        Integer[][][] index = new Integer[m][n][N + 1];
        return helper(m, n, N, i, j, index);
    }
    
    public int helper(int m, int n, int N, int i, int j, Integer[][][] index) {
        int mod = 1000000007;
        if (N < 0)
            return 0;
        if (i < 0 || i >= m || j < 0 || j >= n)
            return 1;
        if (index[i][j][N] != null)
            return index[i][j][N];
        index[i][j][N] = ((helper(m, n, N-1, i-1, j, index) + 
                            helper(m, n, N-1, i+1, j, index))%mod + 
                            (helper(m, n, N-1, i, j-1, index) + 
                            helper(m, n, N-1, i, j+1, index))%mod)%mod;
        return index[i][j][N];
    }
}


class Solution {
    public int findPaths(int m, int n, int N, int ii, int jj) {
        int[][] dp = new int[m][n], temp;
        int res = 0, mod = 1000000007;
        dp[ii][jj] = 1;
        for (int t = 0; t < N; t++) {
            temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] != 0) {
                        if (i == 0)
                            res = (res + dp[i][j]) % mod;
                        else
                            temp[i-1][j] = (temp[i-1][j] + dp[i][j])%mod;
                        if (i == m-1)
                            res = (res + dp[i][j]) % mod;
                        else
                            temp[i+1][j] = (temp[i+1][j] + dp[i][j])%mod;
                        if (j == 0)
                            res = (res + dp[i][j]) % mod;
                        else
                            temp[i][j-1] = (temp[i][j-1] + dp[i][j])%mod;
                        if (j == n-1)
                            res = (res + dp[i][j]) % mod;
                        else
                            temp[i][j+1] = (temp[i][j+1] + dp[i][j])%mod;
                    }
                }
            }
            dp = temp;
        }
        return res;
    }
}


class Solution {
    public int findPaths(int m, int n, int N, int ii, int jj) {
        int[][] dp = new int[m][n], temp;
        int res = 0, mod = 1000000007;
        dp[ii][jj] = 1;
        for (int t = 0; t < N; t++) {
            temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0)
                        res = (res + dp[i][j]) % mod;
                    if (i == m-1)
                        res = (res + dp[i][j]) % mod;
                    if (j == 0)
                        res = (res + dp[i][j]) % mod;
                    if (j == n-1)
                        res = (res + dp[i][j]) % mod;
                    temp[i][j] = (((i>0?dp[i-1][j]:0)+(i<m-1?dp[i+1][j]:0))%mod+
                                    ((j>0?dp[i][j-1]:0)+(j<n-1?dp[i][j+1]:0))%mod)%mod;
                }
            }
            dp = temp;
        }
        return res;
    }
}
