public class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int previous = 0, current = nums[0], len = nums.length, max = 0;
        while (true) {
            if (current >= len - 1)
                return true;
            if (current == previous)
                return false;
            for (int i = previous + 1; i <= current; i++) {
                max = Math.max(i + nums[i], max);
            }
            previous = current;
            current = max;
        }
    }
}
