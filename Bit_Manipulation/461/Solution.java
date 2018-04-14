class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int res = 0;
        while (xor != 0) {
            res++;
            xor = xor & (xor - 1);
        }
        return res;
    }
}
