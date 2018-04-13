class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] table = new int[nums.length];
        table[0] = nums[0];
        table[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int n = 2; n < nums.length; n++) {
            table[n] = table[n-1] > table[n-2]+nums[n] ? table[n-1] : table[n-2]+nums[n];
        }
        return table[nums.length - 1];
    }
}


class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int pre = 0, cur = 0, temp = 0;
        for (int n : nums) {
            temp = cur;
            cur = cur > pre + n ? cur : pre + n;
            pre = temp;
        }
        return cur;
    }
}
