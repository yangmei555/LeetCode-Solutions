class Solution {
    public int findComplement(int num) {
        if (num == 0)
            return 1;
        int res = 0, begin = 0;
        for (int i = 31; i >= 0; i--) {
            if ((num & (1 << i)) != 0) {
                begin = 1;
            } else if (begin == 1) {
                res |= (1 << i);
            }
        }
        return res;
    }
}


class Solution {
    public int findComplement(int num) {
        if (num == 0)
            return 1;
        int count = 0, n = num;
        while (num != 0) {
            count++;
            num >>= 1;
        }
        return ~n & (~((-1) << count));
    }
}
