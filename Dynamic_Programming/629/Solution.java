class Solution {
    public int kInversePairs(int n, int k) {
        if (k == 0 || k == n * (n-1) / 2)
            return 1;
        if (k > n * (n-1) / 2)
            return 0;
        int mod = 1000000007;
        int[][] dp = new int[n+1][k+1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else if (j <= i * (i-1) / 2) {
                    int sub = j >= i ? dp[i-1][j-i] : 0;
                    dp[i][j] = ((dp[i][j-1] + (dp[i-1][j] - sub)) % mod + mod) % mod;
                } else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return (dp[n][k] - dp[n][k-1] + mod) % mod;
    }
}


class Solution {
    public int kInversePairs(int n, int k) {
        if (k == 0 || k == n * (n-1) / 2)
            return 1;
        if (k > n * (n-1) / 2)
            return 0;
        int mod = 1000000007;
        int[] dp = new int[k+1], pre = new int[k+1];
        Arrays.fill(dp, 1);
        for (int i = 1; i <= n; i++) {
            System.arraycopy(dp, 0, pre, 0, dp.length);
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[j] = 1;
                } else if (j <= i * (i-1) / 2) {
                    int sub = j >= i ? pre[j-i] : 0;
                    dp[j] = (dp[j-1] + (dp[j] - sub + mod) % mod) % mod;
                } else {
                    dp[j] = dp[j-1];
                }
            }
        }
        return (dp[k] - dp[k-1] + mod) % mod;
    }
}


class Solution {
    public int kInversePairs(int n, int k) {
        if (k == 0 || k == n * (n-1) / 2)
            return 1;
        if (k > n * (n-1) / 2)
            return 0;
        int mod = 1000000007;
        int[] dp = new int[k+1], pre = new int[k+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            System.arraycopy(dp, 0, pre, 0, dp.length);
            for (int j = 0; j <= k && j <= i * (i-1) / 2; j++) {
                if (j == 0) {
                    dp[j] = 1;
                } else {
                    // dp[i-1][j]+dp[i-1][j-1]+d[i-1][j-2]+... is included in dp[i][j-1]
                    int sub = j >= i ? pre[j-i] : 0;
                    dp[j] = (dp[j-1] + (dp[j] - sub + mod) % mod) % mod;
                } 
            }
        }
        return dp[k];
    }
}
