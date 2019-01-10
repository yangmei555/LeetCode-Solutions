class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        if (left == nums.length || nums[left] != target)
            return new int[]{-1, -1};
        int _left = 0, _right = nums.length;
        while (_left < _right) {
            int mid = (_left + _right) / 2;
            if (nums[mid] >= target + 1)
                _right = mid;
            else
                _left = mid + 1;
        }
        return new int[]{left, _left - 1};
    }
}
