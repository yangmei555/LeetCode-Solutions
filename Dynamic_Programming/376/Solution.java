class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int len = nums.length;
        int[] pos = new int[nums.length];
        int[] neg = new int[nums.length];
        pos[0] = 1;
        neg[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                pos[i] = neg[i-1] + 1;
                neg[i] = neg[i-1];
            } else if (nums[i] < nums[i-1]) {
                pos[i] = pos[i-1];
                neg[i] = pos[i-1] + 1;
            } else {
                pos[i] = pos[i-1];
                neg[i] = neg[i-1];
            }
        }
        return pos[len-1] > neg[len-1] ? pos[len-1] : neg[len-1];
    }
}


class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0 || nums.length == 1)
            return nums.length;
        int up = nums[0] < nums[1] ? 2 : 1, down = nums[0] > nums[1] ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i-1] < nums[i]) 
                up = down + 1;
            else if (nums[i-1] > nums[i]) 
                down = up + 1;
        }
        return up > down ? up : down;
    }
}


class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int sign = 0, newsign = 0, res = 1;
        for (int i = 1; i < nums.length; i++) {
            newsign = nums[i] - nums[i-1];
            if (sign * newsign < 0 || (sign == 0 && newsign != 0)) {
                res++;
                sign = nums[i] - nums[i-1];
            }
        }
        return res;
    }
}
