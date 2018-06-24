class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int n : nums)
            xor ^= n;
        int pos = 0;
        // while ((xor & (1 << pos)) == 0)
        //     pos++;
        // int mask = 1 << pos;
        int mask = xor & (-xor);
        int a = 0, b = 0;
        for (int n : nums) {
            if ((n & mask) == 0)
                a ^= n;
            else
                b ^= n;
        }
        return new int[]{a, b};
    }
}
