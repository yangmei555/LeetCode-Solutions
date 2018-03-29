class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (i < k)
                sum += nums[i];
            else {
                if (i == k)
                    max = sum;
                sum = sum + nums[i] - nums[i - k];
                if (sum > max)
                    max = sum;
            }
        }
        return (double)(sum > max ? sum : max) / k;
    }
}
