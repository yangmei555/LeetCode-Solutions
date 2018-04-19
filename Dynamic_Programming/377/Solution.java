class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] index = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] < i)
                    index[i] += index[i - nums[j]];
                else if (nums[j] == i)
                    index[i] += 1;
            }
        }
        return index[target];
    }
}


class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] index = new int[target + 1];
        Arrays.fill(index, -1);
        return helper(nums, target, index);
    }
    
    public int helper(int[] nums, int remain, int[] index) {
        if (remain == 0)
            return 1;
        if (remain < 0)
            return 0;
        if (index[remain] != -1)
            return index[remain];
        int res = 0;
        for (int n : nums) {
            res += helper(nums, remain - n, index);
        }
        index[remain] = res;
        return res;
    }
}
