class Solution {
    public boolean canJump(int[] nums) {
        int last = -1, cur = 0;
        while (last != cur) {
            int max = 0;
            for (int i = last + 1; i <= cur; i++)
                max = max > i + nums[i] ? max : i + nums[i];
            last = cur;
            cur = max;
            if (cur >= nums.length-1)
                return true;
        }
        return false;
    }
}


class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max)
                return false;
            max = max > i + nums[i] ? max : i + nums[i];
        }
        return true;
    }
}
