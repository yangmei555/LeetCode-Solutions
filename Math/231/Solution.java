class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        return 1073741824 % n == 0;
    }
}


class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        return (n & (n-1)) == 0;
    }
}


class Solution {
    public boolean isPowerOfTwo(int n) {
        while (n != 0) {
            if ((n & 0x1) == 1)
                break;
            n >>= 1;
        }
        return n == 1;
    }
}
