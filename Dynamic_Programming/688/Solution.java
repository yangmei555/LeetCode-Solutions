class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        Double[][][] memo = new Double[N][N][K+1];
        return helper(N, K, r, c, memo);
    }
    
    public double helper(int N, int K, int r, int c, Double[][][] memo) {
        if (r < 0 || r >= N || c < 0 || c >= N)
            return 0;
        if (K == 0)
            return 1;
        if (memo[r][c][K] != null)
            return memo[r][c][K];
        memo[r][c][K] = helper(N,K-1,r+1,c+2,memo) + helper(N,K-1,r+1,c-2,memo) + 
                        helper(N,K-1,r-1,c+2,memo) + helper(N,K-1,r-1,c-2,memo) + 
                        helper(N,K-1,r+2,c+1,memo) + helper(N,K-1,r+2,c-1,memo) + 
                        helper(N,K-1,r-2,c+1,memo) + helper(N,K-1,r-2,c-1,memo);
        memo[r][c][K] /= 8;
        return memo[r][c][K];
    }
}


class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        double[][] dp = new double[N][N], temp;
        int[][] dir = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, -1}, {-2, 1}};
        dp[r][c] = 1;
        for (int t = 0; t < K; t++) {
            temp = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dp[i][j] != 0) {
                        for (int m = 0; m < 8; m++) {
                            if (i+dir[m][0]>=0 && i+dir[m][0]<N && j+dir[m][1]>=0 && j+dir[m][1]<N)
                                temp[i+dir[m][0]][j+dir[m][1]] += dp[i][j] / 8;
                        }
                    }
                }
            }
            dp = temp;
        }
        double res = 0;
        for (double[] d : dp) {
            for (double dou : d)
                res += dou;
        }
        return res;
    }
}
