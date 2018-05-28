public class Solution {
    public int findMin(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1])
                return nums[i];
        }
        return res;
    }
}


class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length;
        int n = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            int before = mid == 0 ? n-1 : mid-1;
            int after = mid == n-1 ? 0 : mid+1;
            if (nums[mid] < nums[before] && nums[mid] < nums[after])
                return nums[mid];
            else if (nums[mid] > nums[0] && nums[mid] > nums[n-1])
                left = mid+1;
            else 
                right = mid;
        }
        return nums[left];
    }
}
