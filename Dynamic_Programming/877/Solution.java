class Solution {
    public boolean stoneGame(int[] piles) {
        int[][] memo = new int[piles.length][piles.length];
        return helper(piles, 0, piles.length-1, memo) > 0 ? true : false;
    }
    
    public int helper(int[] piles, int left, int right, int[][] memo) {
        if (left == right)
            return piles[left];
        if (memo[left][right] != 0)
            return memo[left][right];
        memo[left][right] = Math.max(piles[left] - helper(piles, left+1, right, memo), 
                                        piles[right] - helper(piles, left, right-1, memo));
        return memo[left][right];
    }
}


class Solution {
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        for (int len = 1; len <= piles.length; len++) {
            for (int i = 0; i + len - 1 < piles.length; i++) {
                int j = i + len - 1;
                if (i == j) {
                    dp[i][j] = piles[i];
                } else {
                    dp[i][j] = Math.max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1]);
                }
            }
        }
        return dp[0][piles.length-1] > 0;
    }
}


// 1 dimension dp and no need to use temp variables here 
class Solution {
    public boolean stoneGame(int[] piles) {
        int[] dp = new int[piles.length];
        // int temp1 = 0, temp2 = 0;
        for (int i = piles.length-1; i >= 0; i--) {
            for (int j = i; j < piles.length; j++) {
                // temp2 = dp[j];
                if (i == j) {
                    dp[j] = piles[j];
                } else {
                    dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j-1]);
                }
                // temp1 = dp[j];
            }
        }
        return dp[piles.length-1] > 0;
    }
}


// another way to write the 1 dimension dp 
class Solution {
    public boolean stoneGame(int[] piles) {
        int[] dp = new int[piles.length];
        for (int len = 1; len <= piles.length; len++) {
            for (int i = 0; i + len - 1 < piles.length; i++) {
                int j = i + len - 1;
                if (i == j) {
                    dp[i] = piles[i];
                } else {
                    dp[i] = Math.max(piles[i] - dp[i+1], piles[j] - dp[i]);
                }
            }
        }
        return dp[0] > 0;
    }
}
