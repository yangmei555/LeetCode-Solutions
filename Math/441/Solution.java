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
