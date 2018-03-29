class Solution {
    public int findLengthOfLCIS(int[] nums) {
       if (nums == null || nums.length == 0)
            return 0;
        int res = 1;
        int count = 1;
        for (int i = 1; i <= nums.length; i++) {
            if (i != nums.length && nums[i] > nums[i-1])
                count++;
            else {
                res = res > count ? res : count;
                count = 1;
            }
        }
        return res; 
    }
}
