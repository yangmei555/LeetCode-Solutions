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


class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];
        int res = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i-k];
            res = res > sum ? res : sum;
        }
        return res / (k + .0);
    }
}
