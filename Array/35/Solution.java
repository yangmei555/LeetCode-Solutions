public class Solution {
    public int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length-1]){
            return nums.length;
        }
        int i = 0, j = nums.length - 1;
        while (i < j){
            if (nums[(i+j)/2] > target){
                j = (i+j)/2;
            } else if (nums[(i+j)/2] < target){
                i = (i+j)/2+1;
            } else {
                return (i+j)/2;
            }
        }
        return i;
    }
}
