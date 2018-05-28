class Solution {
    public int findMin(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > nums[i])
                return nums[i];
        }
        return nums[0];
    }
}
