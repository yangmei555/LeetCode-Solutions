class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i+2 < nums.length; i++) {
            int x = nums[i] - target;
            if (x + nums[i+1] + nums[i+2] >= 0)
                break;
            int left = i + 1, right = nums.length-1;
            while (left < right) {
                if (nums[left] + nums[right] + x < 0) {
                    res += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
