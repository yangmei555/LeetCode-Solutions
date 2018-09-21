// use deque to store the values of nums 
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0)
            return new int[0];
        int[] deque = new int[nums.length];
        int p1 = 0, p2 = 0;
        int[] res = new int[nums.length-k+1];
        for (int i = 0; i < nums.length; i++) {
            while (p1 != p2 && deque[p2-1] < nums[i])
                p2--;
            deque[p2++] = nums[i];
            if (i >= k-1) {
                res[i-k+1] = deque[p1];
                if (deque[p1] == nums[i-k+1])
                    p1++;
            }
        }
        return res;
    }
}


// use deque to store the indices of values in nums 
// line 34 can be : nums[deque[p2-1]] <= nums[i], but the above solution can not accept <= 
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0)
            return new int[0];
        int[] deque = new int[nums.length];
        int p1 = 0, p2 = 0;
        int[] res = new int[nums.length-k+1];
        for (int i = 0; i < nums.length; i++) {
            while (p1 != p2 && nums[deque[p2-1]] < nums[i])
                p2--;
            deque[p2++] = i;
            if (i >= k-1) {
                res[i-k+1] = nums[deque[p1]];
                if (deque[p1] == i-k+1)
                    p1++;
            }
        }
        return res;
    }
}
