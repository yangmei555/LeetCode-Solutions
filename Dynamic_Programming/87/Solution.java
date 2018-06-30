class Solution {
    public boolean isScramble(String s1, String s2) {
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        if (ch1.length != ch2.length)
            return false;
        return helper(ch1, ch2, 0, ch1.length-1, 0, ch2.length-1);
    }
    
    public boolean helper(char[] ch1, char[] ch2, int start1, int end1, int start2, int end2) {
        if (start1 > end1)
            return true;
        if (start1 == end1)
            return ch1[start1] == ch2[start2];
        int[] map = new int[26];
        for (int i = start1; i <= end1; i++)
            map[ch1[i]-'a']++;
        for (int i = start2; i <= end2; i++) {
            map[ch2[i]-'a']--;
            if (map[ch2[i]-'a'] < 0)
                return false;
        }
        int sum1 = 0, sum2 = 0, sum3 = 0;
        for (int i = start1; i < end1; i++) {
            sum1 += ch1[i]-'a';
            sum2 += ch2[i-start1+start2]-'a';
            sum3 += ch2[end2-(i-start1)]-'a';
            if (sum1 == sum2 && helper(ch1, ch2, start1, i, start2, i-start1+start2) && 
                                helper(ch1, ch2, i+1, end1, i+1-end1+end2, end2))
                return true;
            if (sum1 == sum3 && helper(ch1, ch2, start1, i, start1-i+end2, end2) && 
                                helper(ch1, ch2, i+1, end1, start2, end1-i-1+start2))
                return true;
        }
        return false;
    }
}


class Solution {
    public boolean isScramble(String s1, String s2) {
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        if (ch1.length != ch2.length)
            return false;
        int size = ch1.length;
        boolean[][][] dp = new boolean[size][size][size+1];
        for (int len = 1; len <= size; len++) {
            for (int i = 0; i + len - 1 < size; i++) {
                for (int j = 0; j + len - 1 < size; j++) {
                    if (len == 1) {
                        dp[i][j][len] = ch1[i] == ch2[j];
                    } else {
                        for (int k = 1; k < len; k++) {
                            if (dp[i][j][k] && dp[i+k][j+k][len-k] || 
                                dp[i][j+len-k][k] && dp[i+k][j][len-k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                        }         
                    }
                }
            }
        }
        return dp[0][0][size];
    }
}
