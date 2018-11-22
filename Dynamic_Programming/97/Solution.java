class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray(), ch3 = s3.toCharArray();
        if (ch1.length + ch2.length != ch3.length)
            return false;
        boolean[][] dp = new boolean[ch1.length+1][ch2.length+1];
        for (int i = 0; i <= ch1.length; i++) {
            for (int j = 0; j <= ch2.length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = ch2[j-1] == ch3[j-1] && dp[i][j-1];
                } else if (j == 0) {
                    dp[i][j] = ch1[i-1] == ch3[i-1] && dp[i-1][j];
                } else {
                    dp[i][j] = ch1[i-1] == ch3[i+j-1] && dp[i-1][j] || 
                                ch2[j-1] == ch3[i+j-1] && dp[i][j-1];
                }
            }
        }
        return dp[ch1.length][ch2.length];
    }
}


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray(), ch3 = s3.toCharArray();
        if (ch1.length + ch2.length != ch3.length)
            return false;
        boolean[] dp = new boolean[ch2.length+1];
        for (int i = 0; i <= ch1.length; i++) {
            for (int j = 0; j <= ch2.length; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = ch2[j-1] == ch3[j-1] && dp[j-1];
                } else if (j == 0) {
                    dp[j] = ch1[i-1] == ch3[i-1] && dp[j];
                } else {
                    dp[j] = ch1[i-1] == ch3[i+j-1] && dp[j] || ch2[j-1] == ch3[i+j-1] && dp[j-1];
                }
            }
        }
        return dp[ch2.length];
    }
}


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray(), ch3 = s3.toCharArray();
        if (ch1.length + ch2.length != ch3.length)
            return false;
        Boolean[][] memo = new Boolean[ch1.length+1][ch2.length+1];
        return helper(ch1, ch2, ch3, 0, 0, memo);
    }
    
    public boolean helper(char[] ch1, char[] ch2, char[] ch3, int s1, int s2, Boolean[][] memo) {
        if (s1 == ch1.length && s2 == ch2.length)
            return true;
        if (memo[s1][s2] != null)
            return memo[s1][s2];
        if (s1 < ch1.length && ch1[s1] == ch3[s1+s2] && helper(ch1, ch2, ch3, s1+1, s2, memo)) {
            memo[s1][s2] = true;
            return true;
        }
        if (s2 < ch2.length && ch2[s2] == ch3[s1+s2] && helper(ch1, ch2, ch3, s1, s2+1, memo)) {
            memo[s1][s2] = true;
            return true;
        }
        memo[s1][s2] = false;
        return false;
    }
}


// only need to record those indices which returns false, because once it returns true, 
// the calling function will return true, level by level 
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray(), ch3 = s3.toCharArray();
        if (ch1.length + ch2.length != ch3.length)
            return false;
        boolean[][] dp = new boolean[ch1.length+1][ch2.length+1];
        return helper(ch1, ch2, ch3, ch1.length-1, ch2.length-1, dp);
    }
    
    public boolean helper(char[] ch1, char[] ch2, char[] ch3, int index1, int index2, 
                                                                            boolean[][] dp) {
        if (index1 == -1 && index2 == -1)
            return true;
        if (dp[index1+1][index2+1]) 
            return false;
        if (index1 != -1 && ch1[index1] == ch3[index1+index2+1] && 
                                helper(ch1, ch2, ch3, index1-1, index2, dp)) 
            return true;
        if (index2 != -1 && ch2[index2] == ch3[index1+index2+1] && 
                                helper(ch1, ch2, ch3, index1, index2-1, dp))
            return true;
        dp[index1+1][index2+1] = true;
        return false;
    }
}
