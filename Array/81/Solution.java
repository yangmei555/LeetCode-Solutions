class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0)
            return false;
        return helper(0, nums.length-1, nums, target);
    }
    
    public boolean helper(int left, int right, int[] nums, int target) {
        if (left > right)
            return false;
        int mid = (left + right) / 2;
        if (nums[mid] == target)
            return true;
        if (helper(left, mid-1, nums, target))
            return true;
        if (helper(mid+1, right, nums, target))
            return true;
        return false;
    }
}


class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0)
            return false;
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[left] < nums[mid]) {
                if ((mid>0 && target <= nums[mid-1]) && target >= nums[left])
                    right = mid-1;
                else
                    left = mid+1;
            } else if (nums[mid] < nums[right]) {
                if ((mid<nums.length-1 && target >= nums[mid+1]) && target <= nums[right])
                    left = mid+1;
                else
                    right = mid-1;
            } else if (nums[left] == nums[mid]) {
                left++;
            } else if (nums[mid] == nums[right]) {
                right--;
            }
        }
        return false;
    }
}


// seems very concise and easy to understand 
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0)
            return false;
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[left] < nums[mid] || nums[mid] > nums[right]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid-1;
                else 
                    left = mid+1;
            } else if (nums[mid] < nums[right] || nums[mid] < nums[left]) {
                if (target > nums[mid] && target <= nums[right]) 
                    left = mid+1;
                else 
                    right = mid-1;
            } else {
                // left++;
                right--;
            }
        }
        return false;
    }
}
