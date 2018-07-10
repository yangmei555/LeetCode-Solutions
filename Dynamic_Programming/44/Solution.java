class Solution {
    public boolean isMatch(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        boolean[][] memo = new boolean[ch1.length+1][ch2.length+1];
        return helper(ch1, ch2, 0, 0, memo);
    }
    
    public boolean helper(char[] ch1, char[] ch2, int index1, int index2, boolean[][] memo) {
        if (index2 == ch2.length) {
            return index1 == ch1.length;
        } else {
            if (memo[index1][index2])
                return false;
            if (ch2[index2] == '*') {
                if (index1 != ch1.length && helper(ch1, ch2, index1+1, index2, memo))
                    return true;
                if (helper(ch1, ch2, index1, index2+1, memo))
                    return true;
                memo[index1][index2] = true;
                return false;
            } else {
                if (index1 != ch1.length && (ch1[index1] == ch2[index2] || ch2[index2] == '?') && 
                                                    helper(ch1, ch2, index1+1, index2+1, memo))
                    return true;
                memo[index1][index2] = true;
                return false;    
            }
        }
    }
}


class Solution {
    public boolean isMatch(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        boolean[][] dp = new boolean[ch1.length+1][ch2.length+1];
        dp[0][0] = true;
        for (int i = 0; i < ch2.length && ch2[i] == '*'; i++)
            dp[0][i+1] = true;
        for (int i = 0; i < ch1.length; i++) {
            for (int j = 0; j < ch2.length; j++) {
                if (ch2[j] == '?' || ch1[i] == ch2[j]) {
                    dp[i+1][j+1] = dp[i][j];
                } else if (ch2[j] == '*') {
                    dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1];
                } 
            }
        }
        return dp[ch1.length][ch2.length];
    }
}


class Solution {
    public boolean isMatch(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        boolean[] dp = new boolean[ch2.length+1];
        dp[0] = true;
        for (int i = 0; i < ch2.length && ch2[i] == '*'; i++)
            dp[i+1] = true;
        boolean temp1 = false, temp2 = false;
        for (int i = 0; i < ch1.length; i++) {
            temp1 = dp[0];
            dp[0] = false;
            for (int j = 0; j < ch2.length; j++) {
                temp2 = dp[j+1];
                if (ch2[j] == '?' || ch1[i] == ch2[j]) {
                    dp[j+1] = temp1;
                } else if (ch2[j] == '*') {
                    dp[j+1] = dp[j] || dp[j+1];
                } else {
                    dp[j+1] = false;
                }
                temp1 = temp2;
            }
        }
        return dp[ch2.length];
    }
}


class Solution {
    public boolean isMatch(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        int index1 = 0, index2 = 0, laststar = -1, shead = 0;
        while (index1 < ch1.length) {
            if (index2 != ch2.length && (ch1[index1] == ch2[index2] || ch2[index2] == '?')) {
                index1++;
                index2++;
            } else if (index2 != ch2.length && ch2[index2] == '*') {
                laststar = index2;
                shead = index1;
                index2++;
            } else {
                if (laststar == -1)
                    return false;
                index2 = laststar + 1;
                shead++;
                index1 = shead;
            } 
        }
        while (index2 < ch2.length && ch2[index2] == '*')
            index2++;
        return index2 == ch2.length;
    }
}
