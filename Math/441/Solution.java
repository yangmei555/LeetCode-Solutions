class Solution {
    public int arrangeCoins(int n) {
        if (n == 0 || n == 1)
            return n;
        long a = 1, b = n;
        while (a < b) {
            long mid = (a + b) / 2;
            if ((mid+1)*mid/2<=n && (mid+2)*(mid+1)/2>n)
                return (int)mid;
            else if ((mid+1)*mid/2>n)
                b = mid - 1;
            else
                a = mid + 1;
        }
        return (int)a;
    }
}


class Solution {
    public int arrangeCoins(int n) {
        if (n == 0 || n == 1)
            return n;
        long a = 1, b = n;
        while (a <= b) {
            long mid = (a + b) / 2;
            if ((mid+1)*mid/2 == n)
                return (int)mid;
            else if ((mid+1)*mid/2>n)
                b = mid - 1;
            else
                a = mid + 1;
        }
        return (int)b;
    }
}


class Solution {
    public int arrangeCoins(int n) {
        long left = 1, right = n;
        while (left < right) {
            long mid = (left + right) / 2;
            if ((1+mid)*mid/2 == n)
                return (int)mid;
            else if ((1+mid)*mid/2 > n)
                right = mid;
            else 
                left = mid + 1;
        }
        if ((1+left)*left/2 <= n)
            return (int)left;
        else
            return (int)left-1;
    }
}


class Solution {
    public int arrangeCoins(int n) {
        if (n == 0 || n == 1)
            return n;
        int i = 1;
        while (n >= 0) {
            n -= i;
            i++;
        }
        return i-2;
    }
}


class Solution {
    public int arrangeCoins(int n) {
        if (n == 0 || n == 1)
            return n;
        long t = (long)n;
        return (int)((Math.sqrt(1+8*t)-1)/2);
    }
}
