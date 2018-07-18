class Solution {
    public int findIntegers(int num) {
        int n = num, cur = 2, pre = 1, temp = 0, bits = 0;
        int[] record = new int[32];  // record[i]: suitable numbers with length i
        record[bits] = 1;
        while (n != 0) {
            bits++;
            record[bits] = cur;
            temp = cur;
            cur += pre;
            pre = temp;
            n >>= 1;
        }
        int res = 0;
        while (bits > 1) {
            if ((num & (1 << (bits-1))) == 0) {
                bits--;
            } else if ((num & (1 << (bits-2))) == 0) {
                res += record[bits-1];
                bits -= 2;
            } else {
                res += record[bits];
                return res;
            }
        }
        if (bits == 1 && ((num & 1) == 1))
            res += 2;
        else
            res += 1;
        return res;
    }
}


class Solution {
    public int findIntegers(int num) {
        int res = 1, cur = 1, pre = 1, temp = 0;  // originally res is 1 means 'num' itself
        while (num != 0) {
            if ((num & 3) == 0b11)   // all previous possibilities are impossible
                res = 0;
            if ((num & 1) == 1)      // replace '1' with '0', append all its left with 
                res += cur;          // all possibilities of length xxxxxx ...
            temp = cur;
            cur += pre;
            pre = temp;
            num >>= 1;
        }
        return res;
    }
}
