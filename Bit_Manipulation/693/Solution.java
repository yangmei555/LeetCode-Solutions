class Solution {
    public boolean hasAlternatingBits(int n) {
        return ((n ^ n << 1) & ((n ^ n << 1) + 1)) == 0 || ((n ^ n >> 1) & ((n ^ n >> 1) + 1)) == 0;
    }
}


class Solution {
    public boolean hasAlternatingBits(int n) {
        return ((n ^ n >> 1) & ((n ^ n >> 1) + 1)) == 0;
    }
}


class Solution {
    public boolean hasAlternatingBits(int n) {
        int pre = 0;
        while (n != 0) {
            pre = n & 1;
            n >>>= 1;
            if (pre == (n & 1))
                return false;
        }
        return true;
    }
}
