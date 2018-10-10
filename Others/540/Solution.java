// the array does not need to be sorted 
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length / 2;
        while (left != right) {
            int mid = (left + right) / 2;
            if (nums[2*mid] != nums[2*mid+1])
                right = mid;
            else
                left = mid + 1;
        }
        return nums[2*left];
    }
}


// O(n) solution 
class Solution {
    public int singleNonDuplicate(int[] nums) {
        for (int i = 0; i < nums.length-2; i += 2) {
            if (nums[i] != nums[i+1])
                return nums[i];
        }
        return nums[nums.length-1];
    }
}
