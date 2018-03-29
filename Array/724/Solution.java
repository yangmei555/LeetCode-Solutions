class Solution {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int sum = 0;
        for (int i : nums)
            sum += i;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (left == sum - left - nums[i])
                return i;
            left += nums[i];
        }
        return -1;
    }
}
