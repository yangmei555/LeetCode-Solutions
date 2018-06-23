class Solution {
    public int singleNumber(int[] nums) {
        int res = 0, mask = 1;
        for (int bit = 0; bit < 32; bit++) {
            int ones = 0;
            for (int n : nums) {
                if ((n & mask) != 0)
                    ones++;
            }
            if (ones % 3 != 0)
                res |= mask;
            mask <<= 1;
        }
        return res;
    }
}


// use KarNot map to get the expression
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int n : nums) {
            int temp = b & n | a & ~n;
            b = ~a & ~b & n | b & ~n;
            a = temp;
        }
        return b;
    }
}


class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int n : nums) {
            a = (a ^ n) & (~b);
            b = (b ^ n) & (~a);
        }
        return a;
    }
}


class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0, mask = 0;
        for (int n : nums) {
            a ^= (b & n);
            b ^= n;
            mask = ~(a & b);
            a &= mask;
            b &= mask;
        }
        return b;
    }
}
