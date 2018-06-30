class Solution {
    public int minDistance(String word1, String word2) {
        char[] ch1 = word1.toCharArray(), ch2 = word2.toCharArray();
        int[][] dp = new int[ch1.length+1][ch2.length+1];
        for (int i = 0; i <= ch1.length; i++) {
            for (int j = 0; j <= ch2.length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j;
                } else {
                    if (ch1[i-1] == ch2[j-1]) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = dp[i-1][j-1] + 1 < dp[i][j-1] + 1 ? dp[i-1][j-1] + 1 : 
                                    dp[i][j-1] + 1;
                        dp[i][j] = dp[i][j] < dp[i-1][j] + 1 ? dp[i][j] : dp[i-1][j] + 1;
                    }
                }
            }
        }
        return dp[ch1.length][ch2.length];
    }
}


class Solution {
    public int minDistance(String word1, String word2) {
        char[] ch1 = word1.toCharArray(), ch2 = word2.toCharArray();
        int[] dp = new int[ch2.length+1];
        int temp1 = 0, temp2 = 0;
        for (int i = 0; i <= ch1.length; i++) {
            for (int j = 0; j <= ch2.length; j++) {
                temp1 = dp[j];
                if (i == 0 || j == 0) {
                    dp[j] = i + j;
                } else {
                    if (ch1[i-1] == ch2[j-1]) {
                        dp[j] = temp2;
                    } else {
                        dp[j] = dp[j] + 1 <  temp2 + 1 ? dp[j] + 1 : temp2 + 1;
                        dp[j] = dp[j] < dp[j-1] + 1 ? dp[j] : dp[j-1] + 1;
                    }
                }
                temp2 = temp1;
            }
        }
        return dp[ch2.length];
    }
}
