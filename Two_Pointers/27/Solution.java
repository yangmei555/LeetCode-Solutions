class Solution {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val)
                nums[index++] = nums[i];
        }
        return index;
    }
}


class Solution {
    public int removeElement(int[] nums, int val) {
        int res = nums.length, index = 0;
        while (index < res) {
            if (nums[index] == val) {
                nums[index] = nums[res-1];
                res--;
            } else {
                index++;
            }
        }
        return res;
    }
}
