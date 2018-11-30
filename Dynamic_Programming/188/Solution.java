class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0)
            return 0;
        if (k >= prices.length/2)
            return helper(prices);
        int[][] dp = new int[k+1][prices.length];
        for (int i = 1; i <= k; i++) {
            int max = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                max = max > dp[i-1][j] - prices[j] ? max : dp[i-1][j] - prices[j];
                dp[i][j] = dp[i][j-1] > prices[j] + max ? dp[i][j-1] : prices[j] + max;
            }
        }
        return dp[k][prices.length-1];
    }
    
    public int helper(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0;
        for (int p : prices) {
            buy = buy > sell - p ? buy : sell - p;
            sell = sell > buy + p ? sell : buy + p;
        }
        return sell;
    }
}


class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0)
            return 0;
        if (k >= prices.length / 2)
            return helper(prices);
        int[] dp = new int[prices.length];
        for (int i = 1; i <= k; i++) {
            int max = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                max = max > dp[j] - prices[j] ? max : dp[j] - prices[j];
                dp[j] = dp[j-1] > prices[j] + max ? dp[j-1] : prices[j] + max;
            }
        }
        return dp[prices.length-1];
    }
    
    public int helper(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0, temp = 0;
        for (int p : prices) {
            temp = buy;
            buy = buy > sell - p ? buy : sell - p;
            sell = sell > temp + p ? sell : temp + p;
        }
        return sell;
    }
}


class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0)
            return 0;
        if (k >= prices.length/2)
            return helper(prices);
        int[][] dp = new int[prices.length][k+1];
        int[] record = new int[k+1];
        Arrays.fill(record, -prices[0]);
        for (int i = 1; i < prices.length; i++) {
            record[0] = record[0] > -prices[i] ? record[0] : -prices[i]; 
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i-1][j] > prices[i] + record[j-1] ? 
                            dp[i-1][j] : prices[i] + record[j-1];
                // it is the same as using dp[i-1][j] - prices[i-1] ... don't know why
                record[j] = record[j] > dp[i][j] - prices[i] ? record[j] : dp[i][j] - prices[i];
            }
        }
        return dp[prices.length-1][k];
    }
    
    public int helper(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0;
        for (int p : prices) {
            buy = buy > sell - p ? buy : sell - p;
            sell = sell > buy + p ? sell : buy + p;
        }
        return sell;
    }
}


class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0)
            return 0;
        if (k >= prices.length/2)
            return helper(prices);
        int[] dp = new int[k+1];
        int[] record = new int[k+1];
        Arrays.fill(record, -prices[0]);
        for (int i = 1; i < prices.length; i++) {
            record[0] = record[0] > -prices[i] ? record[0] : -prices[i]; 
            for (int j = 1; j <= k; j++) {
                dp[j] = dp[j] > prices[i] + record[j-1] ? dp[j] : prices[i] + record[j-1];
                record[j] = record[j] > dp[j] - prices[i] ? record[j] : dp[j] - prices[i];
            }
        }
        return dp[k];
    }
    
    public int helper(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0;
        for (int p : prices) {
            buy = buy > sell - p ? buy : sell - p;
            sell = sell > buy + p ? sell : buy + p;
        }
        return sell;
    }
}


// if do not deal with the special case (k >= prices.length/2) seperately, it will become very 
// very slow 
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0)
            return 0;
        if (k >= prices.length/2) {
            int res = 0;
            for (int i = 1; i < prices.length; i++)
                res += Math.max(0, prices[i] - prices[i-1]);
            return res;
        }
        int[][] dp = new int[k][2];
        for (int i = 0; i < k; i++)
            dp[i][0] = Integer.MIN_VALUE;
        for (int p : prices) {
            for (int i = k-1; i >= 0; i--) {
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + p);
                dp[i][0] = Math.max(dp[i][0], (i == 0 ? 0 : dp[i-1][1]) - p);
            }
        }
        return dp[k-1][1];
    }
}
