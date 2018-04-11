class Solution {
    public int trailingZeroes(int n) {
        int loop = 5;
        int res = 0;
        while (n != 0) {
            res += n / loop;
            n /= 5;
        }
        return res;
    }
}
