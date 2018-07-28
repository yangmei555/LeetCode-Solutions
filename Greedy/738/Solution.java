class Solution {
    public int monotoneIncreasingDigits(int N) {
        char[] ch = (N + "").toCharArray();
        char[] res = new char[ch.length];
        int index = ch.length-1;
        res[index] = ch[index];
        index--;
        while (index >= 0) {
            res[index] = ch[index];
            if (res[index] > res[index+1]) {
                res[index]--;
                res[index+1] = '9';
            }
            index--;
        }
        index++;
        while (index < res.length && res[index] != '9')
            index++;
        while (index < res.length) 
            res[index++] = '9';
        return Integer.valueOf(new String(res));
    }
}


class Solution {
    public int monotoneIncreasingDigits(int N) {
        int res = 0, base = 1, pre = 10;
        while (N != 0) {
            int digit = N % 10;
            if (digit > pre) {
                res = digit * base - 1;
                pre = digit - 1;
            } else {
                res = digit * base + res;
                pre = digit;
            }
            N /= 10;
            base *= 10;
        }
        return res;
    }
}
