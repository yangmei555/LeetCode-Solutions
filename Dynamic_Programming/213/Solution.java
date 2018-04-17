class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];
        int pre = 0, cur = 0, max = 0, temp = 0;
        for (int i = 0; i < len - 1; i++) {
            temp = cur;
            cur = nums[i] + pre > cur ? nums[i] + pre : cur;
            pre = temp;
        }
        max = cur;
        cur = 0;
        pre = 0;
        for (int i = len - 1; i > 0; i--) {
            temp = cur;
            cur = nums[i] + pre > cur ? nums[i] + pre : cur;
            pre = temp;
        }
        return max > cur ? max : cur;
    }
}
