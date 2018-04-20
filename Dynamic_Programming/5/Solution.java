class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0)
            return new String("");
        char[] ch = s.toCharArray();
        int len = ch.length, span = 1, start = 0, end = 0;
        boolean[][] track = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (i == j) {
                    track[j][i] = true;
                } else {
                    if (ch[j] == ch[i] && (i==j+1 || track[j+1][i-1])) {
                        track[j][i] = true;
                        if (i - j + 1 > span) {
                            span = i - j + 1;
                            start = j;
                            end = i;
                        }
                    } else {
                        track[j][i] = false;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
