class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        if (nums.length == 0)
            return res;
        int left = 0, right = nums.length-1, lsum = 1, rsum = 1;
        while (left < nums.length) {
            if (left <= right)
                res[left] = lsum;
            else
                res[left] *= lsum;
            if (left < right)
                res[right] = rsum;
            else
                res[right] *= rsum;
            lsum *= nums[left];
            rsum *= nums[right];
            left++;
            right--;
        }
        return res;
    }
}
