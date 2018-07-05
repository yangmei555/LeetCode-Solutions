class Solution {
    public int maxCoins(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[][] dp = new int[nums.length][nums.length];
        for (int len = 1; len <= nums.length; len++) {
            for (int i = 0; i+len-1 < nums.length; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    int burst = nums[k] * (i == 0 ? 1 : nums[i-1]) * 
                                            (j == nums.length-1 ? 1 : nums[j+1]);
                    burst += (k == i ? 0 : dp[i][k-1]) + (k == j ? 0 : dp[k+1][j]);
                    dp[i][j] = dp[i][j] > burst ? dp[i][j] : burst;
                }
            }
        }
        return dp[0][nums.length-1];
    }
}


class Solution {
    public int maxCoins(int[] nums) {
        int[] nums_ = new int[nums.length+2];
        nums_[0] = nums_[nums_.length-1] = 1;
        for (int i = 0; i < nums.length; i++)
            nums_[i+1] = nums[i];
        int[][] memo = new int[nums_.length][nums_.length];
        return helper(nums_, 0, nums_.length-1, memo);
    }
    
    public int helper(int[] nums, int start, int end, int[][] memo) {
        if (memo[start][end] != 0)
            return memo[start][end];
        if (start + 2 > end)
            return 0;
        int res = 0;
        for (int i = start + 1; i < end; i++) {
            int cand = helper(nums, start, i, memo) + helper(nums, i, end, memo) + 
                        nums[start] * nums[end] * nums[i];
            res = res > cand ? res : cand;
        }
        return memo[start][end] = res;
    }
}
