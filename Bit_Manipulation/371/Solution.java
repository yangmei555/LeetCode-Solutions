class Solution {
    public int getSum(int a, int b) {
        int res = 0, carry = 0;
        for (int i = 0; i < 32; i++) {
            int b1 = a & 1, b2 = b & 1;
            res |= (b1 ^ b2 ^ carry) << i;
            carry = (b1 & b2) | ((b1 ^ b2) & carry);
            a >>= 1;
            b >>= 1;
        }
        return res;
    }
}


class Solution {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }
}
