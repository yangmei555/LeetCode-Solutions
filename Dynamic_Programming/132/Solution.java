class Solution {
    public int minCut(String s) {
        char[] ch = s.toCharArray();
        boolean[][] decide = new boolean[ch.length][ch.length];
        int[] dp = new int[ch.length+1];
        dp[0] = -1;
        for (int j = 0; j < ch.length; j++) {
            dp[j+1] = j;
            for (int i = 0; i <= j; i++) {
                if (ch[i] == ch[j] && (j-i <= 1 || decide[i+1][j-1])) {
                    decide[i][j] = true;
                    dp[j+1] = dp[j+1] < dp[i]+1 ? dp[j+1] : dp[i]+1;
                }
            }
        }
        return dp[ch.length];
    }
}


class Solution {
    public int minCut(String s) {
        char[] ch = s.toCharArray();
        boolean[][] decide = new boolean[ch.length][ch.length];
        int[] dp = new int[ch.length+1];
        Arrays.fill(dp, ch.length-1);
        dp[0] = -1;
        for (int i = 0; i < ch.length; i++) {
            for (int start = i, end = i; start >= 0 && end < ch.length && ch[start] == ch[end]; 
                    start--, end++) {
                dp[end+1] = dp[end+1] < dp[start]+1 ? dp[end+1] : dp[start]+1;
            }
            for (int start = i-1, end = i; start >= 0 && end < ch.length && ch[start] == ch[end]; 
                    start--, end++) {
                dp[end+1] = dp[end+1] < dp[start]+1 ? dp[end+1] : dp[start]+1;
            }
        }
        return dp[ch.length];
    }
}


class Solution {
    public int minCut(String s) {
        char[] ch = s.toCharArray();
        boolean[][] decide = new boolean[ch.length][ch.length];
        int[] dp = new int[ch.length+1];
        Arrays.fill(dp, ch.length-1);
        dp[0] = -1;
        for (int i = 0; i < ch.length; i++) {
            helper(ch, dp, i, i);
            helper(ch, dp, i-1, i);
        }
        return dp[ch.length];
    }
    
    public void helper(char[] ch, int[] dp, int start, int end) {
        while (start >= 0 && end < ch.length && ch[start] == ch[end]) {
            dp[end+1] = dp[end+1] < dp[start]+1 ? dp[end+1] : dp[start]+1;
            start--;
            end++;
        }
    }
}
