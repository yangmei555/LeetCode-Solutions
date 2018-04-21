class Solution {
    public int findSubstringInWraproundString(String p) {
        if (p.length() == 0)
            return 0;
        int cont = 1, res = 0;
        int[][] track = new int[26][26];
        char[] ch = p.toCharArray();
        for (int i = 1; i <= ch.length; i++) {
            if (i != ch.length && (ch[i-1] - 'a' + 1)%26 == (ch[i] - 'a')%26) {
                cont++;
            } else {
                for (int j = i - cont; j < i; j++) {
                    for (int k = j; k < i; k++) {
                        if (track[ch[j]-'a'][ch[k]-'a'] < (k-j) / 26 + 1) {
                            track[ch[j]-'a'][ch[k]-'a']++;
                            res++;
                        }
                    }
                }
                cont = 1;
            }
        }
        return res;
    }
}


class Solution {
    public int findSubstringInWraproundString(String p) {
        if (p.length() == 0)
            return 0;
        int cont = 1, res = 0;
        int[] track = new int[26];
        char[] ch = p.toCharArray();
        for (int i = 1; i <= ch.length; i++) {
            track[ch[i-1]-'a'] = track[ch[i-1]-'a'] > cont ? track[ch[i-1]-'a'] : cont;
            if (i != ch.length) {
                if ((ch[i-1] - 'a' + 1)%26 == (ch[i] - 'a')%26) {
                    cont++;
                } else {
                    cont = 1;
                }
            }
        }
        for (int t : track)
            res += t;
        return res;
    }
}
