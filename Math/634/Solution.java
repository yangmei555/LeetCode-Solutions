class Solution {
    public int findDerangement(int n) {
        long res = 0, sign = n % 2 == 0 ? 1 : -1, term = 1;
        int mod = 1000000007;
        for (int i = n; i >= 2; i--) {
            if (i != n)
                term = term * (i+1) % mod;
            res = (res + sign * term) % mod;
            sign *= -1;
        }
        return res >= 0 ? (int)res : (int)(res + mod);
    }
}


class Solution {
    public int findDerangement(int n) {
        if (n < 3)
            return n - 1;
        int mod = 1000000007;
        long a = 0, b = 1, temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = b;
            b = (i-1) * (a + b) % mod;
            a = temp;
        }
        return (int)b;
    }
}
