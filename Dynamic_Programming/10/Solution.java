class Solution {
    public boolean isMatch(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        return helper(ch1, ch2, 0, 0);
    }
    
    public boolean helper(char[] ch1, char[] ch2, int index1, int index2) {
        if (index2 == ch2.length) {
            return index1 == ch1.length;
        } else {
            if (index2 < ch2.length-1 && ch2[index2+1] == '*') {
                if (helper(ch1, ch2, index1, index2+2))
                    return true;
                while (index1 < ch1.length && (ch1[index1] == ch2[index2] || ch2[index2] == '.')) {
                    if (helper(ch1, ch2, index1+1, index2+2))
                        return true;
                    index1++;
                }
                return false;
            } else {
                if (index1 < ch1.length && (ch1[index1] == ch2[index2] || ch2[index2] == '.'))
                    return helper(ch1, ch2, index1+1, index2+1);
                else
                    return false;
            }
        }
    }
}


class Solution {
    public boolean isMatch(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        Boolean[][] memo = new Boolean[ch1.length+1][ch2.length+1];
        return helper(ch1, ch2, 0, 0, memo);
    }
    
    public boolean helper(char[] ch1, char[] ch2, int index1, int index2, Boolean[][] memo) {
        if (memo[index1][index2] != null)
            return memo[index1][index2];
        if (index2 == ch2.length) {
            return index1 == ch1.length;
        } else {
            if (index2 < ch2.length-1 && ch2[index2+1] == '*') {
                if (helper(ch1, ch2, index1, index2+2, memo)) 
                    return memo[index1][index2] = true;
                if (index1 < ch1.length && (ch1[index1] == ch2[index2] || ch2[index2] == '.')) {
                    if (helper(ch1, ch2, index1+1, index2, memo)) 
                        return memo[index1][index2] = true;
                }
                return memo[index1][index2] = false;
            } else {
                if (index1 < ch1.length && (ch1[index1] == ch2[index2] || ch2[index2] == '.'))
                    return memo[index1][index2] = helper(ch1, ch2, index1+1, index2+1, memo);
                else
                    return memo[index1][index2] = false;
            }
        }
    }
}


class Solution {
    public boolean isMatch(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        boolean[][] dp = new boolean[ch1.length+1][ch2.length+1];
        dp[0][0] = true;
        for (int i = -1; i < ch1.length; i++) {
            for (int j = 0; j < ch2.length; j++) {
                if (i == -1) {
                    if (ch2[j] == '*')
                        dp[i+1][j+1] = dp[i+1][j-1];
                } else {
                    if (ch1[i] == ch2[j] || ch2[j] == '.') {
                        dp[i+1][j+1] = dp[i][j];
                    } else if (ch2[j] == '*') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                        if (ch1[i] == ch2[j-1] || ch2[j-1] == '.')
                            dp[i+1][j+1] |= dp[i][j+1];
                    }
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
        boolean temp1 = false, temp2 = false, temp3 = false;
        for (int i = -1; i < ch1.length; i++) {
            temp1 = dp[0];
            if (i != -1)
                dp[0] = false;
            for (int j = 0; j < ch2.length; j++) {
                temp2 = dp[j+1];
                if (i == -1) {
                    if (ch2[j] == '*')
                        dp[j+1] = dp[j-1];
                } else {
                    if (ch1[i] == ch2[j] || ch2[j] == '.') {
                        dp[j+1] = temp1;
                    } else if (ch2[j] == '*') {
                        temp3 = dp[j+1];
                        dp[j+1] = dp[j-1];
                        if (ch1[i] == ch2[j-1] || ch2[j-1] == '.')
                            dp[j+1] |= temp3;
                    } else {
                        dp[j+1] = false;
                    }
                }
                temp1 = temp2;
            }
        }
        return dp[ch2.length];
    }
}


// a concise style of 1 dimension dp 
class Solution {
    public boolean isMatch(String s, String p) {
        char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
        boolean[] dp = new boolean[ch2.length+1];
        dp[0] = true;
        for (int i = 1; i < ch2.length && ch2[i] == '*'; i += 2)
            dp[i+1] = true;
        for (int i = 0; i < ch1.length; i++) {
            boolean temp1 = dp[0];
            dp[0] = false;
            for (int j = 0; j < ch2.length; j++) {
                boolean temp2 = dp[j+1];
                if (ch1[i] == ch2[j] || ch2[j] == '.') {
                    dp[j+1] = temp1;
                } else if (ch2[j] == '*') {
                    dp[j+1] = dp[j-1];
                    if (ch2[j-1] == '.' || ch2[j-1] == ch1[i])
                        dp[j+1] |= temp2;
                } else {
                    dp[j+1] = false;
                }
                temp1 = temp2;
            }
        }
        return dp[ch2.length];
    }
}
