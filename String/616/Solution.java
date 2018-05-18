class Solution {
    public String addBoldTag(String s, String[] dict) {
        int[] index = new int[s.length()+1];
        for (int i = 0; i < dict.length; i++) {
            int find = 0;
            while (find != -1) {
                find = s.indexOf(dict[i], find);
                if (find != -1) {
                    index[find]++;
                    index[find+dict[i].length()]--;
                    find++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char[] ch = s.toCharArray();
        for (int i = 0; i < index.length; i++) {
            if (count == 0 && count + index[i] != 0)
                sb.append("<b>");
            if (count != 0 && count + index[i] == 0)
                sb.append("</b>");
            count += index[i];
            if (i != ch.length)
                sb.append(ch[i]);
        }
        return sb.toString();
    }
}


class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] set = new boolean[s.length()];
        for (String d : dict) {
            int index = 0;
            while ((index=(s.indexOf(d, index))) != -1) {
                for (int i = index; i < index + d.length(); i++)
                    set[i] = true;
                index++;
            }
        }
        char[] ch = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            if (set[i] && (i == 0 || !set[i-1]))
                sb.append("<b>");
            if (!set[i] && i != 0 && set[i-1])
                sb.append("</b>");
            sb.append(ch[i]);
        }
        if (set[ch.length-1])
            sb.append("</b>");
        return sb.toString();
    }
}


class Solution {
    public String addBoldTag(String s, String[] dict) {
        int[] index = new int[s.length()+1];
        char[] ch = s.toCharArray();
        for (int i = 0; i < dict.length; i++) {
            int[] pi = helper(dict[i]);
            char[] d = dict[i].toCharArray();
            int q = 0;
            for (int j = 0; j < ch.length; j++) {    // KMP matching
                while (q > 0 && d[q] != ch[j])
                    q = pi[q-1];
                if (d[q] == ch[j])
                    q++;
                if (q == d.length) {
                    index[j+1]--;
                    index[j+1-d.length]++;
                    q = pi[q-1];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < index.length; i++) {
            if (count == 0 && count + index[i] != 0)
                sb.append("<b>");
            if (count != 0 && count + index[i] == 0)
                sb.append("</b>");
            count += index[i];
            if (i != ch.length)
                sb.append(ch[i]);
        }
        return sb.toString();
    }
    
    public int[] helper(String str) {  // generate KMP prefix function
        char[] ch = str.toCharArray();
        int[] res = new int[ch.length];
        res[0] = 0;
        int k = 0;
        for (int q = 1; q < ch.length; q++) {
            while (k > 0 && ch[k] != ch[q])
                k = res[k-1];
            if (ch[k] == ch[q])
                k++;
            res[q] = k;
        }
        return res;
    }
}


class Solution {
    public String addBoldTag(String s, String[] dict) {
        int[] index = new int[s.length()+1];
        char[] ch = s.toCharArray();
        for (int i = 0; i < dict.length; i++) {
            if (dict[i].length() > ch.length)
                continue;
            char[] d = dict[i].toCharArray();
            rabin_karp(ch, d, index);
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < index.length; i++) {
            if (count == 0 && count + index[i] != 0)
                sb.append("<b>");
            if (count != 0 && count + index[i] == 0)
                sb.append("</b>");
            count += index[i];
            if (i != ch.length)
                sb.append(ch[i]);
        }
        return sb.toString();
    }
    // Rabin Karp algorithm
    public void rabin_karp(char[] ch, char[] word, int[] index) {
        int d = 128, mod = 97;
        int t = 0, p = 0, h = 1;
        for (int i = 1; i < word.length; i++)
            h = (h *d) % mod;
        for (int i = 0; i < word.length; i++) {
            p = (p * d + word[i]) % mod;
            t = (t * d + ch[i]) % mod;
        }
        for (int i = 0; i <= ch.length - word.length; i++) {
            if (p == t) {
                int j = 0;
                for (; j < word.length; j++)
                    if (word[j] != ch[i+j])
                        break;
                if (j == word.length) {
                    index[i]++;
                    index[i+word.length]--;
                }
            }
            if (i != ch.length - word.length)
                t = (((t-ch[i]*h)*d+ch[i+word.length])%mod+mod)%mod;
        }
    }
}
