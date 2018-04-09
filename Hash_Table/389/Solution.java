class Solution {
    public char findTheDifference(String s, String t) {
        int[] index = new int[26];
        for (char c : s.toCharArray())
            index[c - 'a']++;
        for (char c : t.toCharArray()) {
            index[c - 'a']--;
            if (index[c - 'a'] == -1)
                return c;
        }
        return (char)0;
    }
}


class Solution {
    public char findTheDifference(String s, String t) {
        char x = 0;
        for (char c : s.toCharArray())
            x ^= c;
        for (char c : t.toCharArray()) {
            x ^= c;
        }
        return x;
    }
}
