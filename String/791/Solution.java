class Solution {
    public String customSortString(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int[] rank = new int[s.length], index = new int[26];
        for (int i = 0; i < s.length; i++) 
            index[s[i]-'a'] = i + 1;
        StringBuilder sb = new StringBuilder();
        for (char c : t) {
            if (index[c-'a'] == 0) {
                sb.append(c);
            } else {
                rank[index[c-'a']-1]++;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j < rank[i]; j++)
                res.append(s[i]);
        }
        return res.append(sb).toString();
    }
}


class Solution {
    public String customSortString(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int[] index = new int[26];
        for (char c : t) 
            index[c-'a']++;
        StringBuilder sb = new StringBuilder();
        for (char c : s) {
            for (int i = 0; i < index[c-'a']; i++)
                sb.append(c);
            index[c-'a'] = 0;
        }
        for (int i = 0; i < index.length; i++) {
            for (int j = 0; j < index[i]; j++)
                sb.append((char)('a'+i));
        }
        return sb.toString();
    }
}


class Solution {
    public String customSortString(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int[] index = new int[26];
        for (char c : t) 
            index[c-'a']++;
        StringBuilder sb = new StringBuilder();
        for (char c : s) {
            for (int i = 0; i < index[c-'a']; i++)
                sb.append(c);
            index[c-'a'] = 0;
        }
        for (char c : t) {
            for (int j = 0; j < index[c-'a']; j++)
                sb.append(c);
            index[c-'a'] = 0;
        }
        return sb.toString();
    }
}


class Solution {
    public String customSortString(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int[] index = new int[26];
        for (char c : t) 
            index[c-'a']++;
        StringBuilder sb = new StringBuilder();
        for (char c : s) {
            for (int i = 0; i < index[c-'a']; i++)
                sb.append(c);
            index[c-'a'] = 0;
        }
        for (char c : t) {
            if (index[c-'a'] != 0)
                sb.append(c);
        }
        return sb.toString();
    }
}
