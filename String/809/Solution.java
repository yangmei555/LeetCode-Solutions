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


class Solution {
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        char[] ch = S.toCharArray();
        for (String word : words) {
            char[] w = word.toCharArray();
            int index1 = 0, index2 = 0;
            boolean flag = true;
            while (index1 < ch.length && index2 < w.length) {
                int start1 = index1++, start2 = index2++;
                while (index1 < ch.length && ch[start1] == ch[index1])
                    index1++;
                while (index2 < w.length && w[start2] == w[index2])
                    index2++;
                if (ch[start1] == w[start2]) {
                    if (index1 - start1 == index2 - start2)
                        continue;
                    if (index1 - start1 > index2 - start2 && index1 - start1 >= 3)
                        continue;
                    else {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag && index1 == ch.length && index2 == w.length)
                res++;
        }
        return res;
    }
}
