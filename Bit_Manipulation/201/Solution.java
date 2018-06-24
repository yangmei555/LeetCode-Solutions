class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int res = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = 1 << i;
            if ((m & mask) != 0 && (n & mask) != 0)
                res += mask;
            if ((m & mask) == 0 && (n & mask) != 0)
                break;
        }
        return res;
    }
}


class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int res = 0, mask = 1;
        while (m != n && m != 0) {
            m >>= 1;
            n >>= 1;
            mask <<= 1;
        }
        return m * mask;
    }
}


class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        return m == n ? m : rangeBitwiseAnd(m/2, n/2) << 1;
    }
}


class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        while (m < n) 
            n &= (n-1);
        return n;
    }
}
