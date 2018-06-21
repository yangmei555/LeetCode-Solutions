class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] stack = new int[nums.length], res = new int[nums.length];
        Arrays.fill(res, -1);
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (index != 0 && nums[stack[index-1]] < nums[i]) {
                res[stack[index-1]] = nums[i];
                index--;
            }
            stack[index++] = i;
        }
        for (int n : nums) {
            while (nums[stack[index-1]] < n) {
                res[stack[index-1]] = n;
                index--;
            }
            if (n == nums[stack[0]])
                break;
        }
        return res;
    }
}


class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] stack = new int[nums.length*2], res = new int[nums.length];
        Arrays.fill(res, -1);
        int index = nums.length;
        for (int i = 0; i < nums.length; i++)
            stack[i] = i;
        for (int i = 0; i < nums.length; i++) {
            while (index != 0 && nums[stack[index-1]] < nums[i]) {
                res[stack[index-1]] = nums[i];
                index--;
            }
            stack[index++] = i;
        }
        return res;
    }
}
