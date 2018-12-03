class Solution {
    public int strangePrinter(String s) {
        if (s.length() == 0)
            return 0;
        StringBuilder sb = new StringBuilder();
        char prev = '.';
        for (char c : s.toCharArray()) {
            if (c != prev) {
                sb.append(c);
                prev = c;
            }
        }
        char[] ch = sb.toString().toCharArray();
        int[][] dp = new int[ch.length][ch.length];
        for (int len = 1; len <= ch.length; len++) {
            for (int i = 0; i + len - 1 < ch.length; i++) {
                int j = i + len - 1;
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    int other = 0, start = i+1;
                    for (int k = i+1; k <= j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k-1] + dp[k][j]);
                        if (ch[i] == ch[k]) {
                            other += dp[start][k-1];
                            if (ch[i] == ch[j] && k != j)
                                dp[i][j] = Math.min(dp[i][j], other + dp[k+1][j-1] + 1);
                            start = k+1;
                        }
                    }
                    if (start < ch.length)
                        other += dp[start][j];
                    dp[i][j] = Math.min(dp[i][j], other + 1);
                    other = 0;
                    int end = j-1;
                    for (int k = j-1; k >= i; k--) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
                        if (ch[j] == ch[k]) {
                            other += dp[k+1][end];
                            if (ch[i] == ch[j] && k != i) 
                                dp[i][j] = Math.min(dp[i][j], other + dp[i+1][k-1] + 1);
                            end = k-1;
                        }
                    }
                    if (end >= 0)
                        other += dp[i][end];
                    dp[i][j] = Math.min(dp[i][j], other + 1);
                    if (ch[i] == ch[j])
                        dp[i][j] = Math.min(dp[i][j], dp[i+1][j-1] + 1);
                }
            }
        }
        return dp[0][ch.length-1];
    }
}


// seems quite straight forward but I somehow can not find it myself... 
class Solution {
    public int strangePrinter(String s) {
        StringBuilder sb = new StringBuilder();
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (i == 0 || ch[i-1] != ch[i])
                sb.append(ch[i]);
        }
        ch = sb.toString().toCharArray();
        int[][] memo = new int[ch.length][ch.length];
        return helper(ch, 0, ch.length-1, memo);
    }
    
    public int helper(char[] ch, int left, int right, int[][] memo) {
        if (left > right)
            return 0;
        if (memo[left][right] != 0)
            return memo[left][right];
        int res = helper(ch, left, right-1, memo) + 1;
        for (int i = left; i < right; i++) {
            if (ch[i] == ch[right]) {
                int ret = helper(ch, left, i, memo) + helper(ch, i+1, right-1, memo);
                res = Math.min(res, ret);
            }
        }
        memo[left][right] = res;
        return res;
    }
}
