class Solution {
    public boolean find132pattern(int[] nums) {
        int[] stack = new int[nums.length];
        int index = 0, min = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= min) {
                min = n;
            } else {
                while (index != 0 && stack[index-2] < n) {
                    if (n < stack[index-1])
                        return true;
                    index -= 2;   
                }
                stack[index++] = min;
                stack[index++] = n;
            }
        }
        return false;
    }
}


class Solution {
    public boolean find132pattern(int[] nums) {
        int[] stack = new int[nums.length];
        int index = 0, bar = Integer.MIN_VALUE;
        for (int i = nums.length-1; i >= 0; i--) {
            if (nums[i] < bar)
                return true;
            while (index > 0 && stack[index-1] < nums[i])
                bar = stack[--index];
            stack[index++] = nums[i];
        }
        return false;
    }
}
