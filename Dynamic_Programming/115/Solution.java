class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        if (ch1.length < ch2.length)
            return 0;
        int[][] dp = new int[ch1.length+1][ch2.length+1];
        for (int i = 0; i <= ch1.length; i++) {
            for (int j = 0; j <= i && j <= ch2.length; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j];
                    if (ch1[i-1] == ch2[j-1])
                        dp[i][j] += dp[i-1][j-1];
                }
            }
        }
        return dp[ch1.length][ch2.length];
    }
}


class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        if (ch1.length < ch2.length)
            return 0;
        int[] dp = new int[ch2.length+1];
        dp[0] = 1;
        int temp1 = 0, temp2 = 0;
        for (int i = 0; i <= ch1.length; i++) {
            temp2 = dp[0];
            for (int j = 1; j <= i && j <= ch2.length; j++) {
                temp1 = dp[j];
                if (ch1[i-1] == ch2[j-1])
                    dp[j] += temp2;
                temp2 = temp1;
            }
        }
        return dp[ch2.length];
    }
}


class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        Integer[][] memo = new Integer[ch1.length][ch2.length];
        return helper(ch1, ch2, ch1.length-1, ch2.length-1, memo);
    }
    
    public int helper(char[] ch1, char[] ch2, int end1, int end2, Integer[][] memo) {
        if (end1 < end2)
            return 0;
        if (end2 == -1)
            return 1;
        if (memo[end1][end2] != null)
            return memo[end1][end2];
        int res = helper(ch1, ch2, end1-1, end2, memo);
        if (ch1[end1] == ch2[end2])
            res += helper(ch1, ch2, end1-1, end2-1, memo);
        memo[end1][end2] = res;
        return res;
    }
}


class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        if (ch1.length < ch2.length)
            return 0;
        int[] dp = new int[ch2.length+1];
        dp[0] = 1;
        int[][] pos = new int[128][ch2.length+1];
        for (int i = 0; i < ch2.length; i++) {
            pos[ch2[i]][pos[ch2[i]][0]+1] = i+1;
            pos[ch2[i]][0]++;
        }
        for (char c : ch1) {
            if (pos[c][0] != 0) {
                for (int i = pos[c][0]; i > 0; i--)
                    dp[pos[c][i]] += dp[pos[c][i]-1];
            }
        }
        return dp[ch2.length];
    }
}


class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        if (ch1.length < ch2.length)
            return 0;
        int[] dp = new int[ch2.length+1];
        dp[ch2.length] = 1;
        int[][] pos = new int[128][ch2.length+1];
        for (int i = 0; i < ch2.length; i++) {
            pos[ch2[i]][pos[ch2[i]][0]+1] = i;
            pos[ch2[i]][0]++;
        }
        for (int j = ch1.length-1; j >= 0; j--) {
            char c = ch1[j];
            if (pos[c][0] != 0) {
                for (int i = 1; i <= pos[c][0]; i++)
                    dp[pos[c][i]] += dp[pos[c][i]+1];
            }
        }
        return dp[0];
    }
}


class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        int[] last = new int[128];
        Arrays.fill(last, -1);
        int[] next = new int[ch2.length];
        for (int i = ch2.length-1; i >= 0; i--) {
            next[i] = last[ch2[i]];
            last[ch2[i]] = i;
        }
        int[] dp = new int[ch2.length+1];
        dp[ch2.length] = 1;
        for (int i = ch1.length-1; i >= 0; i--) {
            for (int j = last[ch1[i]]; j != -1; j = next[j])
                dp[j] += dp[j+1];
        }
        return dp[0];
    }
}


class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        int[] first = new int[128];
        Arrays.fill(first, -1);
        int[] pre = new int[ch2.length+1];
        for (int i = 0; i < ch2.length; i++) {
            pre[i+1] = first[ch2[i]];
            first[ch2[i]] = i+1;
        }
        int[] dp = new int[ch2.length+1];
        dp[0] = 1;
        for (int i = 0; i < ch1.length; i++) {
            for (int j = first[ch1[i]]; j != -1; j = pre[j])
                dp[j] += dp[j-1];
        }
        return dp[ch2.length];
    }
}


class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        int[] first = new int[128];
        Arrays.fill(first, -1);
        int[] pre = new int[ch2.length];
        for (int i = 0; i < ch2.length; i++) {
            pre[i] = first[ch2[i]];
            first[ch2[i]] = i+1;
        }
        int[] dp = new int[ch2.length+1];
        dp[0] = 1;
        for (int i = 0; i < ch1.length; i++) {
            for (int j = first[ch1[i]]; j != -1; j = pre[j-1])
                dp[j] += dp[j-1];
        }
        return dp[ch2.length];
    }
}


// very concise 1-d version . when reducing 2d space to 1d space, just need to think of the 
// dependency relation between the terms in the dp formula 
class Solution {
    public int numDistinct(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        int[] dp = new int[ch2.length+1];
        dp[0] = 1;
        for (int i = 0; i < ch1.length; i++) {
            for (int j = Math.min(i, ch2.length-1); j >= 0; j--) {
                if (ch1[i] == ch2[j])
                    dp[j+1] += dp[j];
            }
        }
        return dp[ch2.length];
    }
}
