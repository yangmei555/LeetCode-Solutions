class Solution {
    public boolean xorGame(int[] nums) {
        int x = 0;
        for (int n : nums)
            x ^= n;
        if (nums.length % 2 == 0)
            return true;
        else
            return x == 0;
    }
}
