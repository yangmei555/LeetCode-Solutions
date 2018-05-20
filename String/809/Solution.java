class Solution {
    public int expressiveWords(String S, String[] words) {
        char[] s = S.toCharArray();
        int res = 0;
        for (String word : words) {
            if (word.length() > s.length)
                continue;
            else if (word.length() == s.length) {
                res += word.equals(S) ? 1 : 0;
                continue;
            }
            char[] w = word.toCharArray();
            int i = 0, j = 0;
            while (i < s.length && j < w.length) {
                if (s[i] != w[j])
                    break;
                int cont1 = 1, cont2 = 1;
                while (i < s.length-1 && s[i] == s[i+1]) {
                    i++;
                    cont1++;
                }
                while (j < w.length-1 && w[j] == w[j+1]) {
                    j++;
                    cont2++;
                }
                if (cont1 < cont2 || (cont1 == 2 && cont2 == 1))
                    break;
                i++;
                j++;
            }
            if (i != s.length || j != w.length)
                continue;
            else
                res++;
        }
        return res;
    }
}


class Solution {
    public int expressiveWords(String S, String[] words) {
        char[] s = S.toCharArray();
        int res = 0;
        for (String word : words) {
            char[] w = word.toCharArray();
            int i = 0, j = 0;
            while (i < s.length) {
                if (j < w.length && s[i] == w[j]) {
                    i++;
                    j++;
                } else {
                    if (i != 0 && s[i-1] == s[i] && i != s.length-1 && s[i] == s[i+1])
                        i++;
                    else if (i < 2 || s[i-2] != s[i-1] || s[i-1] != s[i])
                        break;
                    i++;
                }
            }
            if (i == s.length && j == w.length)
                res++;
        }
        return res;
    }
}
