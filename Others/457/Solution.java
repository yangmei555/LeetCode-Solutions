class Solution {
    public boolean circularArrayLoop(int[] nums) {
        if (nums.length < 2)
            return false;
        for (int i = 0; i < nums.length; i++) {
            int slow = i, fast = i;
            do {
                slow = helper(nums, slow);
                fast = helper(nums, helper(nums, fast));
            } while (slow != fast);

            if (slow == helper(nums, slow))
                continue;
            boolean flag = true;
            do {
                fast = helper(nums, fast);
                if (nums[slow] > 0 != nums[fast] > 0) {
                    flag = false;
                    break;
                }
            } while (slow != fast);
            
            if (flag)
                return true;
        }
        return false;
    }
    
    public int helper(int[] nums, int index) {
        return ((index + nums[index]) % nums.length + nums.length) % nums.length;
    }
}
