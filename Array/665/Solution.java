class Solution {
    public boolean checkPossibility(int[] nums) {
        int len = nums.length;
        boolean dec = false;
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i-1]) {
                if (dec == true)
                    return false;
                if (i + 1 == len || nums[i+1] >= nums[i-1] || i - 2 < 0 || nums[i] >= nums[i-2])
                    dec = true;
                else 
                    return false;
            }
        }
        return true;
    }
}


class Solution {
    public boolean checkPossibility(int[] nums) {
        boolean changed = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                if (changed)
                    return false;
                if (i != 1 && nums[i-2] > nums[i]) 
                    nums[i] = nums[i-1];
                changed = true;
            }
        }
        return true;
    }
}
