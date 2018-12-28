class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0)
            return true;
        if (nums.length == 1)
            return false;
        int addup = 0;
        for (int n : nums)
            addup += n;
        if (addup % 2 == 1)
            return false;
        Arrays.sort(nums);
        HashSet<Integer> list = new HashSet<>();
        HashSet<Integer> set = new HashSet<>();
        int sum = addup / 2;
        list.add(nums.length-1);
        System.out.println(addup);
        return helper(nums, list, nums[nums.length-1], sum, set);
    }
    
    public boolean helper(int[] nums, HashSet<Integer> list, int cursum, int sum, 
                                                                    HashSet<Integer> set) {
        if (cursum == sum)
            return true;
        if (cursum > sum)
            return false;
        set.add(cursum);
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(i) && !set.contains(cursum + nums[i])) {
                list.add(i);
                if (helper(nums, list, cursum + nums[i], sum, set))
                    return true;
                list.remove(i);
            }
        }
        return false;
    }
}


class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0)
            return true;
        if (nums.length == 1)
            return false;
        int addup = 0;
        for (int n : nums)
            addup += n;
        if (addup % 2 == 1)
            return false;
        Arrays.sort(nums);
        int sum = addup / 2;
        return helper(nums, nums.length - 1 , sum);
    }
    
    public boolean helper(int[] nums, int index, int sum) {
        if (sum == 0)
            return true;
        if (sum < 0 || index < 0)
            return false;
        int k = index;
        while (k > 0 && nums[k] == nums[k-1])
            k--;
        return helper(nums, index - 1, sum - nums[index]) || helper(nums, k - 1, sum);
    }
}


class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0)
            return true;
        if (nums.length == 1)
            return false;
        int addup = 0;
        for (int n : nums)
            addup += n;
        if (addup % 2 == 1)
            return false;
        Arrays.sort(nums);
        int sum = addup / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j > 0; j--) {
                if (nums[i] <= j)
                    dp[j] = dp[j] || dp[j - nums[i]];
                if (j == sum && dp[j])
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum % 2 == 1)
            return false;
        sum /= 2;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= nums[i-1]; j--) {
                dp[j] |= dp[j-nums[i-1]];
            }
        }
        return dp[sum];
    }
}
