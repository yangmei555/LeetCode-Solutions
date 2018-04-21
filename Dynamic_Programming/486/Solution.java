class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return helper(nums, 0, nums.length-1, 0, sum, 0);
    }
    
    public boolean helper(int[] nums, int l, int r, int cur, int sum, int turn) {
        if (l == r) {
            if (turn == 0)
                return cur + nums[l] >= sum - cur - nums[l];
            else
                return sum - cur > cur;
        }
        return !helper(nums, l+1, r, cur+(turn==0?nums[l]:0), sum, (turn+1)%2) || 
                !helper(nums, l, r-1, cur+(turn==0?nums[r]:0), sum, (turn+1)%2);
    }
}


class Solution {
    public boolean PredictTheWinner(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length];
        return helper(nums, 0, nums.length-1, memo) >= 0;
    }
    
    public int helper(int[] nums, int l, int r, Integer[][] memo) {
        if (l == r)
            return nums[l];
        if (memo[l][r] != null)
            return memo[l][r];
        int left = nums[l] - helper(nums, l+1, r, memo);
        int right = nums[r] - helper(nums, l, r-1, memo);
        memo[l][r] = left > right ? left : right;
        return memo[l][r];
    }
}


class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (i == j) {
                    dp[j] = nums[j];
                } else {
                    dp[j] = nums[j] - dp[j+1] > nums[i] - dp[j] ? nums[j] - dp[j+1] : nums[i] - dp[j];
                }
            }
        }
        return dp[0] >= 0;
    }
}
