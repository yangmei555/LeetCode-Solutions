class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0)
            return 1;
        double[] dp = new double[K+W+1];
        for (int i = K; i < K+W && i <= N; i++)
            dp[i] = 1;
        dp[K-1] = (N - K + 1.0) / W;
        for (int i = K-2; i >= 0; i--)
            dp[i] = (dp[i+1] - dp[i+1+W]) / W + dp[i+1];
        return dp[0];
    }
}


class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K+W-1)
            return 1;
        double[] dp = new double[N+1];
        dp[0] = 1;
        double sum = 1, res = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = sum / W;
            if (i < K) 
                sum += dp[i];
            else 
                res += dp[i];
            if (i >= W)
                sum -= dp[i-W];
        }
        return res;
    }
}


class Solution {
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[N+1];
        dp[0] = 1;
        double res = 0, cum = 0;
        for (int i = 0; i <= N; i++) {
            if (i != 0)
                dp[i] = cum / W;
            if (i < K)
                cum += dp[i];
            if (i >= W)
                cum -= dp[i-W];
            if (i >= K)
                res += dp[i];
        }
        return res;
    }
} 
