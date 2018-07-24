class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = Math.max(max * nums[i], nums[i]);
                min = Math.min(min * nums[i], nums[i]);
            } else {
                int temp = max;
                max = Math.max(min * nums[i], nums[i]);
                min = Math.min(temp * nums[i], nums[i]);
            }
            res = Math.max(res, max);
        }
        return res;
    }
}


class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = Integer.MIN_VALUE, max = 1, min = 1, temp = 0;
        for (int n : nums) {
            temp = max;
            max = (max * n > n ? max * n : n) > min * n ? (max * n > n ? max * n : n) : min * n;
            min = (min * n < n ? min * n : n) < temp * n ? (min * n < n ? min * n : n) : temp * n;
            res = max > res ? max : res;
        }
        return res;
    }
}
