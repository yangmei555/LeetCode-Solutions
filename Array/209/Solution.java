class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = 0, res = Integer.MAX_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            len++;
            while (sum >= s) {
                res = res < len ? res : len;
                sum -= nums[i-len+1];
                len--;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
