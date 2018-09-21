// notice that Double.MIN_VALUE is actually a positive number, 
// so we should use -Double.MAX_VALUE to represent the really minimal double 
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double left = Double.MAX_VALUE, right = -Double.MAX_VALUE;
        for (int n : nums) {
            left = Math.min(left, n);
            right = Math.max(right, n);
        }
        while (Math.abs(left - right) >= 1e-6) {
            double mid = (left + right) / 2;
            if (helper(nums, k, mid))
                left = mid;
            else
                right = mid;
        }
        return left;
    }
    
    public boolean helper(int[] nums, int k, double mid) {
        double total = 0;
        for (int i = 0; i < k; i++)
            total += nums[i] - mid;
        double max = -Double.MAX_VALUE, local = 0;
        for (int i = k-1; i < nums.length; i++) {
            max = Math.max(max, local + total);
            if (max >= 0)
                return true;
            if (i != nums.length-1)
                total += nums[i+1] - nums[i-k+1];
            local += nums[i-k+1] - mid;
            if (local < 0)
                local = 0;
        }
        return false;
    }
}


// basically same implementation, but this version is much faster, don't know why 
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int n : nums) {
            left = Math.min(left, n);
            right = Math.max(right, n);
        }
        while (right - left >= 1e-5) {
            double mid = (left + right) / 2;
            if (helper(nums, k, mid))
                left = mid;
            else
                right = mid;
        }
        return left;
    }
    
    public boolean helper(int[] nums, int k, double mid) {
        double total = 0, prefix = 0;
        for (int i = 0; i < k; i++)
            total += nums[i] - mid;
        if (total >= 0)
            return true;
        for (int i = k; i < nums.length; i++) {
            total += nums[i] - mid;
            prefix += nums[i-k] - mid;
            if (prefix < 0) {
                total -= prefix;
                prefix = 0;
            }
            if (total >= 0)
                return true;
        }
        return false;
    }
}


// using deque, convex hell, but do not fully understand yet 
// view the average as slopes 
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double[] sum = new double[nums.length+1];
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i-1] + nums[i-1];
        int[] deque = new int[nums.length+1];
        int left = 0, right = 0;
        double res = Integer.MIN_VALUE;
        for (int i = k-1; i < nums.length; i++) {
            int start = i - k;
            while (right - left >= 2 && helper(sum, deque[right-2], start) >= 
                                        helper(sum, deque[right-1], start))
                right--;
            deque[right++] = start;
            while (right - left >= 2 && helper(sum, deque[left], i) <= 
                                        helper(sum, deque[left+1], i))
                left++;
            res = Math.max(res, helper(sum, deque[left], i));
        }
        return res;
    }
    
    public double helper(double[] sum, int i1, int i2) {
        return (sum[i2+1] - sum[i1+1]) / (i2 - i1);
    }
}
