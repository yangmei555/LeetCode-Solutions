class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums.length == 0 || k < 2)
            return 0;
        int prod = 1, left = 0, right = 0, res = 0;
        while (right < nums.length) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            res += right-left+1;
            right++;
        }
        return res;
    }
}


class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums.length == 0 || k < 2)
            return 0;
        int prod = 1, left = 0, right = 0, res = 0;
        while (right <= nums.length) {
            if (prod < k) {
                res += right-left;
                if (right != nums.length)
                    prod *= nums[right];
                right++;
            } else {
                while (prod >= k) {
                    prod /= nums[left];
                    left++;
                }
            }
        }
        return res;
    }
}
