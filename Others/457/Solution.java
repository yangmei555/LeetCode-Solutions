// O(n) space beacuse of the visited array 
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        if (nums.length < 2)
            return false;
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            int slow = i, fast = i;
            do {
                slow = helper(nums, slow);
                fast = helper(nums, helper(nums, fast));
                visited[slow] = true;
                visited[fast] = true;
            } while (slow != fast);

            if (slow == helper(nums, slow))
                continue;
            boolean flag = true;
            do {
                slow = helper(nums, slow);
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
        return (index + nums[index] + nums.length) % nums.length;
    }
}


// O(1) space, mark the visited positions with 0 
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        if (nums.length < 2)
            return false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                continue;
            int slow = i, fast = i;
            do {
                slow = helper(nums, slow);
                fast = helper(nums, helper(nums, fast));
            } while (slow != fast);

            if (slow == helper(nums, slow))
                continue;
            boolean flag = true;
            do {
                slow = helper(nums, slow);
                if (nums[slow] > 0 != nums[fast] > 0) {
                    flag = false;
                    break;
                }
            } while (slow != fast);
            
            if (flag) {
                return true;
            } else {
                slow = i;
                while (nums[slow] != 0) {
                    int next = helper(nums, slow);
                    nums[slow] = 0;
                    slow = next;
                }
            }
        }
        return false;
    }
    
    public int helper(int[] nums, int index) {
        return (index + nums[index] + nums.length) % nums.length;
    }
}
