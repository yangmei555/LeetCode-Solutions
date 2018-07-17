class Solution {
    public int checkRecord(int n) {
        int mod = 1000000007;
        int[] total = new int[n];
        total[0] = 2;
        int p = 1, l = 1, ll = 0, temp1 = 0, temp2 = 0;
        for (int i = 1; i < n; i++) {
            temp1 = p;
            temp2 = l;
            p = total[i-1];
            l = temp1;
            ll = temp2;
            total[i] = ((p + l) % mod + ll) % mod;
        }
        int res = 0;
        for (int i = 0; i < n; i++) 
            res = (res + (int)((long)(i == 0 ? 1 : total[i-1]) * 
                                (long)(i == n-1 ? 1 : total[n-i-2]) % mod)) % mod;
        return (res + total[n-1]) % mod;
    }
}


class Solution {
    public int checkRecord(int n) {
        int mod = 1000000007;
        int p = 1, l = 1, ll = 0;
        int ap = 1, al = 1, all = 0, aa = 1; // at most contains one "A", ended with P, L, LL, A
        int temp1 = 0, temp2 = 0, temp3 = 0;
        for (int i = 1; i < n; i++) {
            temp1 = p;
            temp2 = ap;
            temp3 = aa;
            aa = p = ((p + l) % mod + ll) % mod;
            ll = l;
            l = temp1;
            ap = (((ap + al) % mod + all) % mod + temp3) % mod;
            all = al;
            al = (temp2 + temp3) % mod;
        }
        return (((ap + al) % mod + all) % mod + aa) % mod;
    }
}


class Solution {
    public int checkRecord(int n) {
        int mod = 1000000007;
        int[][][] res = new int[n+1][2][3];
        res[0] = new int[][]{{1, 1, 1}, {1, 1, 1}};
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    int sum = res[i-1][j][2];
                    if (j != 0)
                        sum = (sum + res[i-1][j-1][2]) % mod;
                    if (k != 0)
                        sum = (sum + res[i-1][j][k-1]) % mod;
                    res[i][j][k] = sum;
                }
            }
        }
        return res[n][1][2];
    }
}


class Solution {
    int mod = 1000000007;
    public int checkRecord(int n) {
        long[][] coef = new long[][]{{0, 0, 1, 0, 0, 0}, 
                                     {1, 0, 1, 0, 0, 0}, 
                                     {0, 1, 1, 0, 0, 0}, 
                                     {0, 0, 1, 0, 0, 1}, 
                                     {0, 0, 1, 1, 0, 1}, 
                                     {0, 0, 1, 0, 1, 1}};
        return (int)pow(coef, n+1)[5][2];
    }
    
    public long[][] pow(long[][] m, int n) {
        if (n == 1)
            return m;
        if ((n & 1) == 0)
            return pow(mul(m, m), n/2);
        else
            return mul(pow(mul(m, m), n/2), m);
    }
    
    public long[][] mul(long[][] a, long[][] b) {
        long[][] res = new long[a.length][a.length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                for (int k = 0; k < res.length; k++)
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % mod;
            }
        }
        return res;
    }
}
