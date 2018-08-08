class Solution {
    public int reverse(int x) {
        if (x == 0 || x == Integer.MIN_VALUE)
            return 0;
        boolean pos = x > 0 ? true : false;
        x = x > 0 ? x : -x;
        int res = 0;
        while (x != 0) {
            if (res > Integer.MAX_VALUE / 10)
                return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return pos ? res : -res;
    }
}


class Solution {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (res * 10 / 10 != res)
                return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}
