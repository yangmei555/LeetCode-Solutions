public class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int max = 0;
        int i = 0;
        int location = 0;
        while (max < nums.length - 1){
            while (i <= location){
                max = Math.max(max, nums[i] + i);
                i++;
            }
            location = max;
            jumps++;
        }
        return jumps;
    }
}
