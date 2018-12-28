class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n == -1) {
            return 1/x;
        } else {
            double ret = myPow(x, n / 2);
            if (n % 2 == 0) {
                return ret * ret;
            } else {
                return n > 0 ? x * ret * ret : ret * ret / x;
            }
        }
    }
}


class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        double ret = myPow(x, n/2);
        if (n % 2 == 0)
            return ret * ret;
        else if (n > 0)
            return x * ret * ret;
        else
            return ret * ret / x;
    }
}
