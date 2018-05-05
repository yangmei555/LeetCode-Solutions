class Solution {
    public int findPeakElement(int[] nums) {
        if (nums.length < 2)
            return 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] > nums[i+1])
                    return i;
            } else if (i == nums.length-1) {
                if (nums[i] > nums[i-1])
                    return i;
            } else if (nums[i] > nums[i-1] && nums[i] > nums[i+1])
                return i;
        }
        return -1;
    }
}


class Solution {
    public int findPeakElement(int[] nums) {
        if (nums.length < 2)
            return 0;
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (left+right)/2;
            int pre = mid == 0 ? Integer.MIN_VALUE : nums[mid-1];
            int next = mid == nums.length-1 ? Integer.MIN_VALUE : nums[mid+1];
            if (mid == 0) {
                if (nums[mid] > next)
                    return mid;
                else
                    left = mid + 1;
            } else if (mid == nums.length-1) {
                if (nums[mid] > pre)
                    return mid;
                else
                    right = mid - 1;
            } else {
                if (nums[mid] > pre && nums[mid] > next)
                    return mid;
                else if (pre < nums[mid])
                    left = mid + 1;
                else if (pre > nums[mid])
                    right = mid - 1;
            }
        }
        return left;
    }
}


class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[mid+1])
                left = mid+1;
            else
                right = mid;
        }
        return left;
    }
}


class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if ((mid == 0 || nums[mid] > nums[mid-1]) && 
                (mid == nums.length-1 || nums[mid] > nums[mid+1]))
                return mid;
            else if (mid == 0 || nums[mid] > nums[mid-1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
