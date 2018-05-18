class Solution {
    public int repeatedStringMatch(String A, String B) {
        if (B == null || B.length() == 0)
            return 1;
        if (A == null || A.length() == 0)
            return -1;
        StringBuilder sb = new StringBuilder();
        int res = 0;
        while (true) {
            sb.append(A);
            res++;
            if (sb.length() >= B.length())
                break;
        }
        if (sb.toString().contains(B))
            return res;
        if (sb.append(A).toString().contains(B))
            return res + 1;
        
        return -1;
    }
}


class Solution {
    public int repeatedStringMatch(String A, String B) {
        if (A.indexOf(B) != -1)
            return 1;
        int times = (B.length()-1) / A.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= times; i++)
            sb.append(A);
        if (sb.toString().indexOf(B) != -1)
            return times+1;
        sb.append(A);
        if (sb.toString().indexOf(B) != -1)
            return times+2;
        return -1;
    }
}


class Solution {            // KMP algorithm
    public int repeatedStringMatch(String A, String B) {
        int[] pi = new int[B.length()];
        char[] b = B.toCharArray();
        pi[0] = 0;
        int k = 0;
        for (int q = 1; q < b.length; q++) {
            while (k > 0 && b[k] != b[q])
                k = pi[k-1];
            if (b[k] == b[q])
                k++;
            pi[q] = k;
        }
        char[] a = A.toCharArray();
        int q = 0;
        for (int i = 0; i < a.length+b.length; i++) {
            while (q > 0 && b[q] != a[i % a.length])
                q = pi[q-1];
            if (b[q] == a[i % a.length])
                q++;
            if (q == b.length) 
                return i/a.length+1;
        }
        return -1;
    }
}


class Solution {           // Rabin Karp algorithm
    public int repeatedStringMatch(String A, String B) {
        int mod = 97, d = 128, h = 1, p = 0, t = 0;
        char[] a = A.toCharArray(), b = B.toCharArray();
        for (int i = 0; i < b.length; i++) {
            if (i != 0)
                h = (h * d) % mod;
            p = (p * d + b[i]) % mod;
            t = (t * d + a[i%a.length]) % mod;
        }
        for (int i = 0; i <= a.length; i++) {
            if (p == t) {
                int j = 0;
                for (; j < b.length; j++)
                    if (b[j] != a[(i+j)%a.length])
                        break;
                if (j == b.length)
                    return (i+j-1)/a.length+1;
            }
            if (i != a.length)
                t = (((t-a[i]*h)*d+a[(i+b.length)%a.length])%mod+mod)%mod;
        }
        return -1;
    }
}
