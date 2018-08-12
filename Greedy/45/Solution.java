class Solution {
    public int jump(int[] nums) {
        int res = 0, cur = 0, pre = -1;
        while (cur < nums.length - 1) {
            int max = 0;
            for (int i = pre + 1; i <= cur; i++)
                max = Math.max(max, i + nums[i]);
            pre = cur;
            cur = max;
            res++;
        }
        return res;
    }
}


class Solution {
    public int jump(int[] nums) {
        int res = 0, max = 0, start = 0;
        for (int i = 0; i < nums.length-1; i++) {
            max = Math.max(max, i + nums[i]);
            if (start == i) {
                res++;
                start = max;
            }
        }
        return res;
    }
}
