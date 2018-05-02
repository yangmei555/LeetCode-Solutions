class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        return helper(0, nums.length-1, nums, target);
    }
    
    public int helper(int left, int right, int[] nums, int target) {
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        if (nums[mid] == target)
            return mid;
        int res = 0;
        if ((res = helper(left, mid-1, nums, target)) != -1)
            return res;
        if ((res = helper(mid+1, right, nums, target)) != -1)
            return res;
        return -1;
    }
}


class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[left] < nums[mid]) {
                if (target >= nums[left] && (mid > 0 && target <= nums[mid-1])) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {
                if ((mid < nums.length-1 && target >= nums[mid+1]) && target <= nums[right]) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }
}
