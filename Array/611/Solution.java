class Solution {
    public int triangleNumber(int[] nums) {
        if (nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int res = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == 0)
                continue;
            int left = 0, right = i-1;
            while (left < right) {
                if (nums[right] == 0)
                    break;
                if (nums[left] + nums[right] > nums[i]) {
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}
