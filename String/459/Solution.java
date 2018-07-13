class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0)
            return false;
        if (s.indexOf(s.charAt(s.length()-1)) == s.length() - 1)
            return false;
        StringBuilder rotate = new StringBuilder(s);
        String first;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.length() % (i+1) == 0) {
                rotate = new StringBuilder(s);
                first = s.substring(0, i+1);
                rotate.delete(0, i+1).append(first);
                // System.out.println(rotate);
                if (rotate.toString().equals(s))
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0)
            return false;
        StringBuilder rotate = new StringBuilder(s + s);
        rotate.deleteCharAt(0);
        rotate.deleteCharAt(rotate.length() - 1);
        return rotate.indexOf(s) != -1;
    }
}


class Solution {
    public boolean repeatedSubstringPattern(String s) {
        return (s+s).substring(1, s.length()*2-1).contains(s);
    }
}


class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0)
            return false;
        int len = s.length(), times = 0;
        for (int i = len / 2; i >=1; i--) {
            if (len % i == 0) {
                times = len / i;
                String sub = s.substring(0, i);
                int j = 1;
                for (; j < times; j++) {
                    if (!s.substring(j*i, (j+1)*i).equals(sub))
                        break;
                }
                if (j == times)
                    return true;
            }
        }
        return false;
    }
}


class Solution {            // KMP algorithm
    public boolean repeatedSubstringPattern(String s) {
        int index = s.length() / 2;
        int len = s.length();
        char[] ch = s.toCharArray();
        int[] pi = getPrefix(ch);
        for (; index > 0; index--) {
            if (len % index != 0)
                continue;
            if (match(ch, pi, index) == len / index) {
                return true;
            }
        }
        return false;
    }
    
    public int match(char[] s, int[] pi, int bound) {
        int res = 0;
        int q = 0;
        for (int i = 0; i < s.length; i++) {
            while (q != 0 && s[q] != s[i])
                q = pi[q-1];
            if (s[q] == s[i])
                q++;
            if (q == bound) {
                res++;
                q = 0;
            }
        }
        return res;
    }
    
    public int[] getPrefix(char[] s) {
        int[] pi = new int[s.length];
        int k = 0;
        pi[0] = 0;
        for (int i = 1; i < s.length; i++) {
            while (k > 0 && s[k] != s[i])
                k = pi[k-1];
            if (s[k] == s[i])
                k++;
            pi[i] = k;
        }
        return pi;
    }
}


class Solution {            // the correct way to use KMP!!!!!!!!!!
    public boolean repeatedSubstringPattern(String s) {
        char[] ch = s.toCharArray();
        int[] pi = new int[ch.length];
        pi[0] = 0;
        int matched = 0;
        for (int i = 1; i < pi.length; i++) {
            while (matched > 0 && ch[matched] != ch[i])
                matched = pi[matched-1];
            if (ch[matched] == ch[i])
                matched++;
            pi[i] = matched;
        }
        return pi[pi.length-1] != 0 && pi.length % (pi.length - pi[pi.length-1]) == 0;
    }
}


class Solution {               // Rabin Karp algorithm
    public boolean repeatedSubstringPattern(String s) {
        int index = s.length() / 2;
        int len = s.length();
        char[] ch = s.toCharArray();
        for (; index > 0; index--) {
            if (len % index != 0)
                continue;
            if (rabin_karp(ch, index) == len / index) {
                return true;
            }
        }
        return false;
    }
    
    public int rabin_karp(char[] s, int bound) {
        int mod = 97, d = 26, p = 0, t = 0, h = 1, res = 1;
        for (int i = 0; i < bound; i++) {
            if (i != 0)
                h = (h * d) % mod;
            p = (p * d + s[i] - 'a') % mod;
            t = (t * d + s[i + bound] - 'a') % mod;
        }
        for (int i = bound; i <= s.length - bound; i++) {
            if (p == t && i % bound == 0) {
                int j = 0;
                for (; j < bound; j++)
                    if (s[j] != s[i + j])
                        break;
                if (j == bound)
                    res++;
            }
            if (i != s.length - bound)
                t = (((t-(s[i]-'a')*h)*d+(s[i+bound]-'a'))%mod+mod)%mod;
        }
        return res;
    }
}
