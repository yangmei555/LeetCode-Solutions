class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int addup = 0;
        for (int n : nums)
            addup += n;
        Integer[][] memo = new Integer[nums.length][2*addup+1];
        return helper(nums, 0, 0, S, memo, addup);
    }
    
    public int helper(int[] nums, int index, int cur, int S, Integer[][] memo, int sum) {
        if (memo[index][cur+sum] != null)
            return memo[index][cur+sum];
        if (index == nums.length - 1) {
            memo[index][cur+sum] = (cur+nums[index]==S ? 1 : 0) + (cur-nums[index]==S ? 1 : 0);
            return memo[index][cur+sum];
        }
        memo[index][cur+sum] = helper(nums, index + 1, cur - nums[index], S, memo, sum) + 
                                helper(nums, index + 1, cur + nums[index], S, memo, sum);
        return memo[index][cur+sum];
    }
}


class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (S > sum || S < -sum)
            return 0;
        int[][] dp = new int[nums.length+1][sum*2+1];
        dp[0][sum] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= sum*2; j++) {
                if (j-nums[i-1] >= 0)
                    dp[i][j] += dp[i-1][j-nums[i-1]];
                if (j+nums[i-1] <= sum*2)
                    dp[i][j] += dp[i-1][j+nums[i-1]];
            }
        }
        return dp[nums.length][S+sum];
    }
}


class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (S > sum || S < -sum)
            return 0;
        int[] dp = new int[2 * sum + 1];
        int[] temp;
        dp[sum] = 1;
        for (int i = 0; i < nums.length; i++) {
            temp = new int[2*sum+1];
            for (int j = 0; j < dp.length; j++) {
                if (dp[j] != 0) {
                    temp[j+nums[i]] += dp[j];
                    temp[j-nums[i]] += dp[j];
                }
            }
            dp = temp;
        }
        return dp[S + sum];
    }
}


// sum(positive) - sum(negative) = S, so 2 * sum(positive) = S + sum(total)
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum < S || -sum > S || (sum + S) % 2 == 1)
            return 0;
        int tar = (sum + S) / 2;
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = tar; j >= nums[i]; j--)
                dp[j] += dp[j - nums[i]];
        }
        return dp[tar];
    }
}


// sum(positive) - sum(negative) = S, so sum(negative) = (sum(total) - S) / 2 
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum < S || -sum > S || (sum - S) % 2 == 1)
            return 0;
        int tar = (sum - S) / 2;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int n : nums) {
            Map<Integer, Integer> temp = new HashMap<>(map);
            for (int i : map.keySet()) {
                temp.put(i+n, map.get(i) + map.getOrDefault(i+n, 0));
            }
            map = temp;
        }
        return map.get(tar);
    }
}


// sum(positive) - sum(negative) = S, so sum(negative) = (sum(total) - S) / 2 
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum < S || -sum > S || (sum - S) % 2 == 1)
            return 0;
        int tar = (sum - S) / 2;
        int[] dp = new int[tar+1];
        dp[0] = 1;        
        for (int i = 0; i < nums.length; i++) {
            for (int j = tar; j >= nums[i]; j--) 
                dp[j] += dp[j-nums[i]];
        }
        return dp[tar];
    }
}


// 2 dimension dp version 
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum < S || -sum > S || (sum - S) % 2 == 1)
            return 0;
        int tar = (sum - S) / 2;
        int[][] dp = new int[nums.length+1][tar+1];
        dp[0][0] = 1;        
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= tar; j++) 
                dp[i][j] = (j >= nums[i-1] ? dp[i-1][j-nums[i-1]] : 0) + dp[i-1][j];
        }
        return dp[nums.length][tar];
    }
}
