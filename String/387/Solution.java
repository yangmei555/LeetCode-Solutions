class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0)
            return -1;
        char[] ch = s.toCharArray();
        int[] table = new int[26];
        for (int i = 0; i < ch.length; i++)
            table[ch[i] - 'a']++;
        for (int i = 0; i < ch.length; i++)
            if (table[ch[i] - 'a'] == 1)
                return i;
        return -1;
    }
}


class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0)
            return -1;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++)
            if (s.indexOf(ch[i]) == s.lastIndexOf(ch[i]))
                return i;
        return -1;
    }
}
