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
