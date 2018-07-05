class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        for (int i = dungeon.length-1; i >= 0; i--) {
            for (int j = dungeon[0].length-1; j >= 0; j--) {
                if (i == dungeon.length-1 && j == dungeon[0].length-1) {
                    dp[i][j]  = dungeon[i][j] > 0 ? 0 : -dungeon[i][j];
                } else {
                    int cand1 = i == dungeon.length-1 ? Integer.MAX_VALUE : dp[i+1][j];
                    int cand2 = j == dungeon[0].length-1 ? Integer.MAX_VALUE : dp[i][j+1];
                    dp[i][j] = (cand1 < cand2 ? cand1 : cand2) - dungeon[i][j];
                    dp[i][j] = dp[i][j] < 0 ? 0 : dp[i][j];
                }
            }
        }
        return dp[0][0] + 1;
    }
}


class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int[] dp = new int[dungeon[0].length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[dungeon[0].length-1] = 0;
        for (int i = dungeon.length-1; i >= 0; i--) {
            for (int j = dungeon[0].length-1; j >= 0; j--) {
                dp[j] = (dp[j] < dp[j+1] ? dp[j] : dp[j+1]) - dungeon[i][j];
                dp[j] = dp[j] < 0 ? 0 : dp[j];
            }
        }
        return dp[0] + 1;
    }
}


class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (helper(dungeon, mid)) 
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
    
    public boolean helper(int[][] dungeon, int init) {
        int[][] dp = new int[dungeon.length+1][dungeon[0].length+1];
        for (int i = 0; i <= dungeon.length; i++)
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        dp[0][1] = init;
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[0].length; j++) {
                int cand = dp[i][j+1] > dp[i+1][j] ? dp[i][j+1] : dp[i+1][j];
                if (cand != Integer.MIN_VALUE) {
                    dp[i+1][j+1] = cand + dungeon[i][j];
                    if (dp[i+1][j+1] < 1)
                        dp[i+1][j+1] = Integer.MIN_VALUE;
                } 
            }
        }
        return dp[dungeon.length][dungeon[0].length] != Integer.MIN_VALUE;
    }
}


class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] memo = new int[dungeon.length][dungeon[0].length];
        return helper(dungeon, memo, 0, 0);
    }
    
    public int helper(int[][] dungeon, int[][] memo, int r, int c) {
        int row = memo.length, col = memo[0].length;
        if (r >= row || c >= col) {
            if (r == row-1 || c == col-1)
                return 1;
            else
                return Integer.MAX_VALUE;
        }
        if (memo[r][c] != 0)
            return memo[r][c];
        int cand1 = helper(dungeon, memo, r+1, c), cand2 = helper(dungeon, memo, r, c+1);
        memo[r][c] = (cand1 < cand2 ? cand1 : cand2) - dungeon[r][c];
        memo[r][c] = memo[r][c] < 1 ? 1 : memo[r][c];
        return memo[r][c];
    }
}
