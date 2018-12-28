class Solution {
    public int mySqrt(int x) {
        int n = 1;
        while (n <= x / n)
            n++;
        return n-1;
    }
}


class Solution {
    public int mySqrt(int x) {
        int a = 1, b = x;
        while (a < b) {
            int k = (a + b) / 2;
            if (k <= x/k && (k+1) > x/(k+1))
                return k;
            if (k > x/k)
                b = k - 1;
            else 
                a = k + 1;
        }
        return x == 0 ? x : a;
    }
}


class Solution {
    public int mySqrt(int x) {
        if (x < 2)
            return x;
        long left = 0, right = x / 2;
        while (left < right) {
            long mid = (left + right) / 2;
            if (mid * mid == x)
                return (int)mid;
            else if (mid * mid < x)
                left = mid + 1;
            else 
                right = mid;
        }
        return left * left > x ? (int)(left - 1) : (int)left;
    }
}


class Solution {
    public int mySqrt(int x) {
        if (x < 2)
            return x;
        int left = 1, right = x / 2;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid == x / mid)
                return mid;
            else if (mid < x / mid)
                left = mid + 1;
            else 
                right = mid;
        }
        return left > x / left ? left - 1 : left;
    }
}


// most concise one 
class Solution {
    public int mySqrt(int x) {
        int left = 1, right = x;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid)
                right = mid;
            else
                left = mid + 1;
        }
        return left > x / left ? left - 1 : left;
    }
}
