class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return nums.length*(nums.length+1)/2 - sum;
    }
}


class Solution {
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] >= 0 ? nums[i] : -nums[i]-1;
            if (index == nums.length)
                continue;
            nums[index] = -nums[index]-1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0)
                return i;
        }
        return nums.length;
    }
}


class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++)
            res ^= i ^ nums[i];
        return res ^ nums.length;
    }
}
