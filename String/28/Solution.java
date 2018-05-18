class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        return haystack.indexOf(needle);
    }
}


class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        int[] pi = new int[needle.length()];  // KMP algorithm
        char[] nee = needle.toCharArray();
        pi[0] = 0;
        int q = 0;
        for (int i = 1; i < pi.length; i++) {
            while (q > 0 && nee[q] != nee[i])
                q = pi[q-1];
            if (nee[q] == nee[i])
                q++;
            pi[i] = q;
        }
        char[] hay = haystack.toCharArray();
        int k = 0;
        for (int i = 0; i < hay.length; i++) {
            while (k > 0 && nee[k] != hay[i])
                k = pi[k-1];
            if (nee[k] == hay[i])
                k++;
            if (k == nee.length)
                return i + 1 - k;
        }
        return -1;
    }
}


class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (haystack.length() < needle.length())
            return -1;
        int mod = 97, d = 128;      // Rabin Karp algorithm
        char[] hay = haystack.toCharArray(), nee = needle.toCharArray();
        int p = 0, t = 0, h = 1;
        for (int i = 1; i < nee.length; i++)
            h = (h * d) % mod;
        for (int i = 0; i < nee.length; i++) {
            p = (p * d + (nee[i]-'0')) % mod;
            t = (t * d + (hay[i]-'0')) % mod;
        }
        for (int i = 0; i <= hay.length - nee.length; i++) {
            if (p == t) {
                int j = 0;
                for (; j < nee.length; j++) 
                    if (nee[j] != hay[i+j])
                        break;
                if (j == nee.length)
                    return i;
            }
            if (i != hay.length - nee.length)
                t = (((((t - (hay[i]-'0')*h))*d+hay[i+nee.length]-'0')%mod)+mod)%mod;
        }
        return -1;
    }
}
