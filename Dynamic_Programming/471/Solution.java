class Solution {
    public String encode(String s) {
        String[][] dp = new String[s.length()][s.length()];
        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i + len - 1 < s.length(); i++) {
                int j = i + len - 1;
                String sub = s.substring(i, j+1);
                if (len <= 4) {
                    dp[i][j] = sub;
                } else {
                    StringBuilder sb = new StringBuilder(sub);
                    char[] ch = sub.toCharArray();
                    int[] prefix = new int[ch.length];
                    prefix[0] = 0;
                    int matched = 0;
                    for (int k = 1; k < ch.length; k++) {       // Using KMP to get the prefix
                        while (matched > 0 && ch[matched] != ch[k])
                            matched = prefix[matched-1];
                        if (ch[matched] == ch[k])
                            matched++; 
                        prefix[k] = matched;
                    }

                    // decide whether sub is repeated by its substring, 
                    // according to the method used in Problem 459
                    if (prefix[ch.length-1] != 0 &&    
                        ch.length % (ch.length - prefix[ch.length-1]) == 0) {
                        String subsub = dp[i][i + ch.length - prefix[ch.length-1] - 1];
                        int times = ch.length / (ch.length - prefix[ch.length-1]);
                        sb.setLength(0);
                        sb.append(times).append('[').append(subsub).append(']');
                    } else {
                        for (int k = i; k < j; k++) {
                            if (dp[i][k].length() + dp[k+1][j].length() < sb.length()) {
                                sb.setLength(0);
                                sb.append(dp[i][k]).append(dp[k+1][j]);
                            }
                        }
                    }
                    dp[i][j] = sb.toString();
                }
            }
        }
        return dp[0][s.length()-1];
    }
}


class Solution {
    public String encode(String s) {
        String[][] dp = new String[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = s.substring(i, i+1);
            for (int j = 0; j < i; j++)
                dp[j][i] = dp[j][i-1] + dp[i][i];
            for (int j = i; i-j+1 <= (i+1)/2; j--) {
                String sub = s.substring(j, i+1);
                // find repeated pattern
                for (int k = j-(i+1-j); k >= 0 && s.substring(k, k+i+1-j).equals(sub); k -= i+1-j) {
                    String replace = (i+1-k) / (i+1-j) + "[" + dp[j][i] + "]";
                    if (replace.length() < dp[k][i].length()) {
                        dp[k][i] = replace;
                        for (int start = 0; start < k; start++) {
                            if (dp[start][k-1].length() + replace.length() < dp[start][i].length())
                                dp[start][i] = dp[start][k-1] + replace;
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
