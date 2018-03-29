class Solution {
    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return 0;
        int first = nums[0] > nums[1] ? 0 : 1;
        int second = nums[0] > nums[1] ? 1 : 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > nums[first]) {
                second = first;
                first = i;
            } else if (nums[i] > nums[second]) {
                second = i;
            }
        }
        if (nums[first] >= 2 * nums[second])
            return first;
        else
            return -1;
    }
}
