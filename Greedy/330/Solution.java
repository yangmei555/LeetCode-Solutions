class Solution {
    public int minPatches(int[] nums, int n) {
        int res = 0, index = 0;
        long max = 0;     // [1, max] can already be expressed 
        while (max < n) {
            if (index == nums.length || max + 1 < nums[index]) {
                res++;
                max += max + 1;    // patch max+1 
            } else {
                max += nums[index++];
            }
        }
        return res;
    }
}
