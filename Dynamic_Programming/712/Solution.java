class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j != 0) {
                    dp[i][j] = dp[i][j-1] + ch2[j-1];
                } else if (i != 0 && j == 0) {
                    dp[i][j] = dp[i-1][j] + ch1[i-1];
                } else if (i != 0 && j != 0) {
                    if (ch1[i-1] == ch2[j-1]) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = ch1[i-1]+dp[i-1][j] < ch2[j-1]+dp[i][j-1] ? ch1[i-1]+dp[i-1][j] : 
                                    ch2[j-1] + dp[i][j-1];
                    }
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}


class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int[] dp = new int[s2.length() + 1];
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int temp1 = 0, temp2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            temp1 = dp[i];
            for (int j = 0; j < s2.length(); j++) {
                temp2 = dp[j+1];
                if (ch1[i] == ch2[j]) {
                    dp[j+1] = temp1 + ch1[i];
                } else {
                    dp[j+1] = dp[j+1] > dp[j] ? dp[j+1] : dp[j];
                }
                temp1 = temp2;
            }
        }
        int sum = 0;
        for (char c : ch1)
            sum += c;
        for (char c : ch2)
            sum += c;
        return sum - 2 * dp[s2.length()];
    }
}


class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int[] dp = new int[s2.length() + 1];
        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray();
        int temp1 = 0, temp2 = 0;
        for (int i = s1.length() - 1; i >= 0; i--) {
            temp1 = dp[s2.length()];
            for (int j = s2.length() - 1; j >= 0; j--) {
                temp2 = dp[j];
                if (ch1[i] == ch2[j]) {
                    dp[j] = temp1 + ch1[i];
                } else {
                    dp[j] = dp[j] > dp[j+1] ? dp[j] : dp[j+1];
                }
                temp1 = temp2;
            }
        }
        int sum = 0;
        for (char c : ch1)
            sum += c;
        for (char c : ch2)
            sum += c;
        return sum - 2 * dp[0];
    }
}
