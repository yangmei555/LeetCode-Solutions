class Solution {
    public int superPow(int a, int[] b) {
        int res = 1, temp = a % 1337;
        for (int i = b.length-1; i >= 0; i--) {
            res =  (res * helper(temp, b[i], 1337)) % 1337;
            temp = helper(temp, 10, 1337);
        }
        return res;
    }
    
    public int helper(int a, int b, int mod) {
        if (b == 0)
            return 1;
        if (b == 1)
            return a % mod;
        int ret = helper(a, b / 2, mod);
        if (b % 2 == 0) {
            return (ret * ret) % mod;
        } else {
            return (((ret * ret) % mod) * a) % mod; 
        }
    }
}


class Solution {
    public int superPow(int a, int[] b) {
        int mod = 1337, res = 1;
        a %= mod;
        for (int i = 0; i < b.length; i++) {
            res = (helper(res, 10, mod) * helper(a, b[i], mod)) % mod;
        }
        return res;
    }
    
    public int helper(int a, int n, int mod) {
        if (n == 0)
            return 1;
        int ret = helper(a, n/2, mod);
        int res = ret * ret % mod;
        if (n % 2 == 0)
            return res;
        else
            return res * a % mod;
    }
}


// use the Euler theorem! phi is the euler function value of mod 
// notice that, when a and mod is not coprime, and b != 0 && remain == 0, we have to set remain 
// to be phi, now remain % phi is still 0 
class Solution {
    public int superPow(int a, int[] b) {
        int mod = 1337;
        a %= mod;
        int phi = euler(mod);
        int remain = helper1(b, phi);
        if (remain == 0 && gcd(a, mod) != 1 && (b.length != 1 || b[0] != 0))
            remain += phi;
        return helper2(a, remain, mod);
    }
    
    public int euler(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                res = res / i * (i-1);
            }
        }
        if (n != 1)
            res = res / n * (n-1);
        return res;
    }
    
    public int helper1(int[] b, int mod) {
        int res = 0;
        for (int n : b) 
            res = (res * 10 + n) % mod;
        return res;
    }
    
    public int helper2(int a, int b, int mod) {
        if (b == 0)
            return 1;
        int ret = helper2(a, b/2, mod);
        int res = ret * ret % mod;
        if (b % 2 == 0)
            return res;
        else
            return res * a % mod;
    }
    
    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}


// same idea with above, but implement iteratively 
class Solution {
    public int superPow(int a, int[] b) {
        int mod = 1337;
        a %= mod;
        int phi = euler(mod);
        int remain = 0;
        for (int n : b)
            remain = (remain * 10 + n) % phi;
        if (remain == 0 && gcd(a, mod) != 1 && (b.length != 1 || b[0] != 0))
            remain += phi;
        int res = 1, cur = a;
        while (remain != 0) {
            if (remain % 2 == 1)
                res = res * cur % mod;
            cur = cur * cur % mod;
            remain >>= 1;
        }
        return res;
    }
    
    public int euler(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                res = res / i * (i-1);
            }
        }
        if (n != 1)
            res = res / n * (n-1);
        return res;
    }
    
    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
