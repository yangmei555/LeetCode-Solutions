class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] dp = new int[s.length()];
        char[] ch = s.toCharArray();
        int temp1 = 0, temp2 = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            // temp1 = dp[i];
            for (int j = i; j < s.length(); j++) {
                temp2 = dp[j];
                if (i == j) {
                    dp[j] = 1;
                } else {
                    if (ch[i] == ch[j]) {
                        dp[j] = 2 + temp1;
                    } else {
                        dp[j] = dp[j] > dp[j-1] ? dp[j] : dp[j-1];
                    }
                }
                temp1 = temp2;
            }
        }
        return dp[s.length() - 1];
    }
}


class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] dp = new int[s.length()];
        char[] ch = s.toCharArray();
        int temp1 = 0, temp2 = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                temp2 = dp[j];
                if (j == i) {
                    dp[j] = 1;
                } else {
                    if (ch[j] == ch[i]) {
                        dp[j] = 2 + temp1;
                    } else {
                        dp[j] = dp[j] > dp[j+1] ? dp[j] : dp[j+1];
                    }
                }
                temp1 = temp2;
            }
        }
        return dp[0];
    }
}
