class Solution {
    public int change(int amount, int[] coins) {
        if (coins.length == 0)
            return amount == 0 ? 1 : 0;
        // Arrays.sort(coins);    // not necessary to sort the coins 
        int[][] dp = new int[amount+1][coins.length];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) 
                dp[i][j] = (i < coins[j] ? 0 : dp[i-coins[j]][j]) + (j == 0 ? 0 : dp[i][j-1]);
            
        }
        return dp[amount][coins.length-1];
    }
}


class Solution {
    public int change(int amount, int[] coins) {
        if (coins.length == 0)
            return amount == 0 ? 1 : 0;
        // Arrays.sort(coins);    // not necessary to sort the coins 
        int[][] dp = new int[coins.length][amount+1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = (coins[i] > j ? 0 : dp[i][j-coins[i]]) + (i == 0 ? 0 : dp[i-1][j]);
            }
            
        }
        return dp[coins.length-1][amount];
    }
}


class Solution {
    public int change(int amount, int[] coins) {
        if (coins.length == 0)
            return amount == 0 ? 1 : 0;
        // Arrays.sort(coins);    // not necessary to sort the coins 
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) 
                dp[j] += dp[j-coins[i]];
        }
        return dp[amount];
    }
}
