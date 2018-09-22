class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] index = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            index[i] = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i < c || index[i - c] == Integer.MAX_VALUE)
                    continue;
                if (1 + index[i - c] < index[i])
                    index[i] = 1 + index[i - c];
            }
        }
        return index[amount] == Integer.MAX_VALUE ? -1 : index[amount];
    }
}


// 2 dimension dp version 
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length+1][amount+1];
        for (int[] d : dp)
            Arrays.fill(d, Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                int cand1 = j >= coins[i-1] && dp[i][j-coins[i-1]] != Integer.MAX_VALUE ? 
                            dp[i][j-coins[i-1]] + 1 : Integer.MAX_VALUE;
                int cand2 = dp[i-1][j];
                dp[i][j] = Math.min(cand1, cand2);
            }
        }
        return dp[coins.length][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length][amount];
    }
}


// another 1 dimension version 
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = coins[i-1]; j <= amount; j++) {
                if (dp[j-coins[i-1]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[j-coins[i-1]] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
