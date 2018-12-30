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


class Solution {
    public boolean buddyStrings(String A, String B) {
        char[] ch1 = A.toCharArray(), ch2 = B.toCharArray();
        if (ch1.length != ch2.length)
            return false;
        if (Arrays.equals(ch1, ch2)) {
            int[] map = new int[26];
            for (char c : ch1) {
                if (map[c-'a']++ != 0)
                    return true;
            }
            return false;
        } else {
            int diff1 = -1, diff2 = -1;
            for (int i = 0; i < ch1.length; i++) {
                if (ch1[i] != ch2[i]) {
                    if (diff1 == -1)
                        diff1 = i;
                    else if (diff2 == -1)
                        diff2 = i;
                    else
                        return false;
                }
            }
            return diff2 != -1 && ch1[diff1] == ch2[diff2] && ch1[diff2] == ch2[diff1];
        }
    }
}
