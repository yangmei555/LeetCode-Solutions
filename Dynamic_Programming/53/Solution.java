class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int local = 0;
        for (int n : nums) {
            local = Math.max(n, n + local);
            res = Math.max(res, local);
        }
        return res;
    }
}
