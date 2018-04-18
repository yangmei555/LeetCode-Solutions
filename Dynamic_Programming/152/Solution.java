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
