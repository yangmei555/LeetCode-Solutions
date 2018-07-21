class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length())
            return false;
        char[] ch1 = A.toCharArray(), ch2 = B.toCharArray(); 
        int[] map = new int[26];
        int pos = -1;
        for (int i = 0; i < ch1.length; i++) {
            map[ch1[i]-'a']++;
            if (ch1[i] != ch2[i]) {
                if (pos == -1) {
                    pos = i;
                } else if (pos == ch1.length) {
                    return false;
                } else {
                    if (ch1[pos] != ch2[i] || ch1[i] != ch2[pos])
                        return false;
                    pos = ch1.length;
                    return true;
                }
            }
        }
        if (pos == ch1.length)
            return true;
        if (pos != -1)
            return false;
        for (int i : map) {
            if (i > 1)
                return true;
        }
        return false;
    }
}
