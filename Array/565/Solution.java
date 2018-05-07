class Solution {
    public int arrayNesting(int[] nums) {
        if (nums.length == 0)
            return 0;
        int res = 1, temp;
        for (int i = 0; i < nums.length; i++) {
            int change = 1;
            while (nums[i] != i) {
                change++;
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
            res = res > change ? res : change;
        }
        return res;
    }
}


class Solution {
    public int arrayNesting(int[] nums) {
        if (nums.length == 0)
            return 0;
        int res = 1, temp;
        for (int i = 0; i < nums.length; i++) {
            int change = 0, loop = i;
            while (nums[loop] != -1) {
                change++;
                temp = nums[loop];
                nums[loop] = -1;
                loop = temp;
            }
            res = res > change ? res : change;
        }
        return res;
    }
}
