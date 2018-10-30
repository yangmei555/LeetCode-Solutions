class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int N = flights.length, K = days[0].length;
        int[][] dp = new int[K+1][N];
        Arrays.fill(dp[0], -1);
        dp[0][0] = 0;
        int max = -1;
        for (int k = 1; k <= K; k++) {
            max = 0;
            for (int i = 0; i < N; i++) {
                dp[k][i] = -1;
                for (int j = 0; j < N; j++) {
                    if (dp[k-1][j] != -1 && (j == i || flights[i][j] == 1)) 
                        dp[k][i] = Math.max(dp[k][i], dp[k-1][j] + days[i][k-1]);
                }
                max = Math.max(max, dp[k][i]);
            }
        }
        return max;
    }
}


// optimize the space complexity of the above solution 
class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int N = flights.length, K = days[0].length;
        int[][] dp = new int[2][N];
        int[] cur = dp[0], pre = dp[1];
        Arrays.fill(pre, -1);
        pre[0] = 0;
        int max = -1;
        for (int k = 0; k < K; k++) {
            max = 0;
            for (int i = 0; i < N; i++) {
                cur[i] = -1;
                for (int j = 0; j < N; j++) {
                    if (pre[j] != -1 && (j == i || flights[j][i] == 1)) 
                        cur[i] = Math.max(cur[i], pre[j] + days[i][k]);
                }
                max = Math.max(max, cur[i]);
            }
            int[] temp = cur;
            cur = pre;
            pre = temp;
        }
        return max;
    }
}
