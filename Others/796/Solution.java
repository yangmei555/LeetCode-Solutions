class Solution {              // KMP
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length())
            return false;
        if (A.length() == 0)
            return true;
        char[] ch1 = A.toCharArray(), ch2 = (B + B).toCharArray();
        int[] pi = helper(ch1);
        int q = 0;
        for (int i = 0; i < ch2.length; i++) {
            while (q > 0 && ch1[q] != ch2[i])
                q = pi[q-1];
            if (ch1[q] == ch2[i])
                q++;
            if (q == ch1.length)
                return true;
        }
        return false;
    }
    
    public int[] helper(char[] ch) {
        int[] res = new int[ch.length];
        res[0] = 0;
        int k = 0;
        for (int i = 1; i < ch.length; i++) {
            while (k > 0 && ch[k] != ch[i])
                k = res[k-1];
            if (ch[k] == ch[i])
                k++;
            res[i] = k;
        }
        return res;
    }
}


class Solution {
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length())
            return false;
        return (A+A).contains(B);
    }
}


class Solution {                 // Rolling Hash
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length())
            return false;
        char[] ch1 = A.toCharArray(), ch2 = (B+B).toCharArray();
        int prime = 97, p = 0, t = 0, h = 1;
        for (int i = 1; i < ch1.length; i++)
            h = h * 26 % prime;
        for (int i = 0; i < ch1.length; i++) 
            p = (p * 26 + ch1[i] - 'a') % prime;
        for (int i = 0; i < ch1.length; i++)
            t = (t * 26 + ch2[i] - 'a') % prime;
        for (int i = 0; i <= ch2.length - ch1.length; i++) {
            if (p == t) {
                boolean err = false;
                for (int j = 0; j < ch1.length; j++) {
                    if (ch1[j] != ch2[i + j]) {
                        err = true;
                        break;
                    }
                }
                if (!err)
                    return true;
            }
            if (i != ch2.length - ch1.length)
                t = (((t - h * (ch2[i] - 'a')) * 26 + ch2[i+ch1.length]) % prime + prime) % prime;
        }
        return false;
    }
}
