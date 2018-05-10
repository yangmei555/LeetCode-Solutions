class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0)
            return 0;
        if (divisor == 1)
            return dividend;
        if (divisor == -1)
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        if (dividend == divisor)
            return 1;
        if (dividend + divisor == 0)
            return -1;
        if (divisor == 2 || divisor == -2)
            return divisor == 2 ? (dividend >> 1) : (-(dividend >> 1));
        boolean sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        int res = 0, ori = dividend;
        while ((ori > 0 && dividend >= 0) || (ori < 0 && dividend <= 0)) {
            if (res == Integer.MIN_VALUE || res == Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            dividend = sign ? dividend - divisor : dividend + divisor;
            res++;
        }
        res--;
        return sign ? res : -res;
    }
}


class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0)
            return 0;
        if (divisor == 1)
            return dividend;
        if (divisor == -1)
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        if (dividend == divisor)
            return 1;
        if (dividend + divisor == 0)
            return -1;
        if (divisor == 2 || divisor == -2)
            return divisor == 2 ? (dividend >> 1) : (-(dividend >> 1));
        if (divisor == Integer.MIN_VALUE)
            return 0;
        boolean sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        int res = 0, ori = dividend;
        divisor = divisor > 0 ? divisor : -divisor;
        if (dividend == Integer.MIN_VALUE) {
            dividend += divisor;
            res++;
        } 
        dividend = dividend > 0 ? dividend : -dividend;
        while (dividend >= divisor) {
            int base = 1, sub = divisor;
            while ((dividend >> 1) >= sub) {
                sub <<= 1;
                base <<= 1;
            }
            dividend -= sub;
            res += base;
        }
        return sign ? res : -res;
    }
}
