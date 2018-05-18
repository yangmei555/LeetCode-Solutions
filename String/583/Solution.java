class Solution {
    public int minDistance(String word1, String word2) {
        int[] dp = new int[word2.length()+1];
        char[] ch1 = word1.toCharArray(), ch2 = word2.toCharArray();
        int temp1 = 0, temp2 = 0;
        for (int i = 0; i < ch1.length; i++) {
            temp1 = dp[0];
            for (int j = 0; j < ch2.length; j++) {
                temp2 = dp[j+1];
                if (ch1[i] == ch2[j]) {
                    dp[j+1] = 1 + temp1;
                } else {
                    dp[j+1] = dp[j] > dp[j+1] ? dp[j] : dp[j+1];
                }
                temp1 = temp2;
            }
        }
        return word1.length() + word2.length() - 2 * dp[word2.length()];
    }
}


class Solution {
    public int minDistance(String word1, String word2) {
        int[] dp = new int[word2.length()+1];
        for (int i = 0; i < dp.length; i++)
            dp[i] = i;
        char[] ch1 = word1.toCharArray(), ch2 = word2.toCharArray();
        int temp1 = 0, temp2 = 0;
        for (int i = 0; i < ch1.length; i++) {
            temp1 = dp[0];
            dp[0] = i+1;
            for (int j = 0; j < ch2.length; j++) {
                temp2 = dp[j+1];
                if (ch1[i] == ch2[j]) {
                    dp[j+1] = temp1;
                } else {
                    dp[j+1] = dp[j] < dp[j+1] ? dp[j]+1 : dp[j+1]+1;
                }
                temp1 = temp2;
            }
        }
        return dp[word2.length()];
    }
}
