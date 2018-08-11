class Solution {
    
    int[] nums, copy;
    Random random;
    public Solution(int[] nums) {
        this.nums = nums;
        copy = nums.clone();
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        nums = copy.clone();
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(len - i) + i;
            int temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
