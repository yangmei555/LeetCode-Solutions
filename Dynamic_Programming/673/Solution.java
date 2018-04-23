class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length], count = new int[nums.length];
        dp[0] = 1;
        count[0] = 1;
        int res = 1, max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (1 + dp[j] > dp[i]) {
                        dp[i] = 1 + dp[j];
                        count[i] = count[j];
                    } else if (1 + dp[j] == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                res = count[i];
            } else if (dp[i] == max) {
                res += count[i];
            }
        }
        return res;
    }
}
