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
