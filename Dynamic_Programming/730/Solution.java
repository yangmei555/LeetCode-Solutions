class Solution {
    public int countPalindromicSubsequences(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        // if use [4][ch.length][ch.length] the program will run significantly faster. strange
        int[][][] dp = new int[ch.length][ch.length][4];
        for (int len = 1; len <= ch.length; len++) {
            for (int i = 0; i + len - 1 < ch.length; i++) {
                int j = i + len - 1;
                if (i == j) {
                    dp[i][j][ch[i]-'a'] = 1;
                } else {
                    for (int k = 0; k < 4; k++) {
                        if (ch[i] - 'a' == k && ch[j] - 'a' == k) {
                            dp[i][j][k] = (2 + (dp[i+1][j-1][0] + dp[i+1][j-1][1]) % mod + 
                                                (dp[i+1][j-1][2] + dp[i+1][j-1][3]) % mod) % mod;
                        } else if (ch[i] - 'a' == k) {
                            dp[i][j][k] = dp[i][j-1][k];
                        } else {
                            dp[i][j][k] = dp[i+1][j][k];
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int k = 0; k < 4; k++)
            res = (res + dp[0][ch.length-1][k]) % mod;
        return res;
    }
}


class Solution {
    int mod = 1000000007;
    public int countPalindromicSubsequences(String S) {
        char[] ch = S.toCharArray();
        Integer[][][] dp = new Integer[ch.length][ch.length][4];
        int res = 0;
        for (int k = 0; k < 4; k++)
            res = (res + helper(ch, 0, ch.length-1, k, dp)) % mod;
        return res;
    }
    
    public int helper(char[] ch, int i, int j, int k, Integer[][][] dp) {
        if (i > j)
            return 0;
        if (dp[i][j][k] != null)
            return dp[i][j][k];
        if (i == j) {
            dp[i][j][k] = ch[i] -'a' == k ? 1 : 0;
        } else {
            if (ch[i] - 'a' == k && ch[j] - 'a' == k) {
                dp[i][j][k] = (2 + (helper(ch, i+1, j-1, 0, dp) + helper(ch, i+1, j-1, 1, dp)) % mod
                                 + (helper(ch, i+1, j-1, 2, dp) + helper(ch, i+1, j-1, 3, dp)) % mod)
                                     % mod;
            } else if (ch[i] - 'a' == k) {
                dp[i][j][k] = helper(ch, i, j-1, k, dp);
            } else {
                dp[i][j][k] = helper(ch, i+1, j, k, dp);
            }
        }
        return dp[i][j][k];
    }
}


// use 1D array to track pre and next is enough 
class Solution {
    public int countPalindromicSubsequences(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        int[][] dp = new int[ch.length][ch.length];
        int[][] pre = new int[ch.length][], next = new int[ch.length][];
        int[] seen = new int[]{-1, -1, -1, -1};
        for (int i = 0; i < ch.length; i++) {
            seen[ch[i]-'a'] = i;
            pre[i] = seen.clone();
        }
        Arrays.fill(seen, -1);
        for (int i = ch.length-1; i >= 0; i--) {
            seen[ch[i]-'a'] = i;
            next[i] = seen.clone();
        }
        for (int len = 1; len <= ch.length; len++) {
            for (int i = 0; i + len - 1 < ch.length; i++) {
                int j = i + len - 1;
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    if (ch[i] != ch[j]) {
                        dp[i][j] = ((dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1]) % mod + mod) % mod;
                    } else {
                        dp[i][j] = (2 * dp[i+1][j-1]) % mod;    
                        int left = next[i+1][ch[i]-'a'], right = pre[j-1][ch[j]-'a'];
                        // int left = i + 1, right = j - 1;
                        // while (left < j && ch[left] != ch[j])
                        //     left++;
                        // while (right > i && ch[right] != ch[i])
                        //     right--;
                        if (left < right) {
                            dp[i][j] = ((dp[i][j] - dp[left+1][right-1]) % mod + mod) % mod;
                        } else if (left == right) {
                            dp[i][j] += 1;
                        } else {
                            dp[i][j] += 2;
                        }
                    }
                }
            }
        }
        return dp[0][ch.length-1];
    }
}


class Solution {
    public int countPalindromicSubsequences(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        int[][] dp = new int[ch.length][ch.length];
        int[][] pre = new int[ch.length][], next = new int[ch.length][];
        int[] seen = new int[]{-1, -1, -1, -1};
        for (int i = 0; i < ch.length; i++) {
            seen[ch[i]-'a'] = i;
            pre[i] = seen.clone();
        }
        Arrays.fill(seen, -1);
        for (int i = ch.length-1; i >= 0; i--) {
            seen[ch[i]-'a'] = i;
            next[i] = seen.clone();
        }
        for (int i = 1; i < ch.length; i++)
            dp[i][i-1] = 1;           // the empty string ""
        for (int len = 1; len <= ch.length; len++) {
            for (int i = 0; i + len - 1 < ch.length; i++) {
                int j = i + len - 1;
                dp[i][j] = 1;      // the empty string ""
                for (int k = 0; k < 4; k++) {
                    int left = next[i][k], right = pre[j][k];
                    if (left >= i && left <= j) 
                        dp[i][j] += 1;      // the single character
                    if (0 <= left && left < right)
                        dp[i][j] = (dp[i][j] + dp[left+1][right-1]) % mod;
                }
            }
        }
        return dp[0][ch.length-1] - 1;     // remove the empty string ""
    }
}


class Solution {
    int mod = 1000000007;
    public int countPalindromicSubsequences(String S) {
        char[] ch = S.toCharArray();
        int[][] dp = new int[ch.length][ch.length];
        int[][] pre = new int[ch.length][], next = new int[ch.length][];
        int[] seen = new int[]{-1, -1, -1, -1};
        for (int i = 0; i < ch.length; i++) {
            seen[ch[i]-'a'] = i;
            pre[i] = seen.clone();
        }
        Arrays.fill(seen, -1);
        for (int i = ch.length-1; i >= 0; i--) {
            seen[ch[i]-'a'] = i;
            next[i] = seen.clone();
        }
        return helper(ch, 0, ch.length-1, pre, next, dp) - 1;   // remove the empty string ""
    }
    
    public int helper(char[] ch, int i, int j, int[][] pre, int[][] next, int[][] dp) {
        if (i > j)
            return 1;   // the empty string
        if (dp[i][j] != 0)
            return dp[i][j];
        dp[i][j] = 1;
        for (int k = 0; k < 4; k++) {
            int left = next[i][k], right = pre[j][k];
            if (left >= i && left <= j) 
                dp[i][j] += 1;      // the single character
            if (0 <= left && left < right)
                dp[i][j] = (dp[i][j] + helper(ch, left+1, right-1, pre, next, dp)) % mod;
        }
        return dp[i][j];
    }
}


class Solution {
    public int countPalindromicSubsequences(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        // dp[i]: all different palindrom subsequences which start with ch[i] 
        int[] dp = new int[ch.length]; 
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            int[] total = new int[4];
            for (int j = i-1; j >= 0; j--) {
                int temp = dp[j];
                if (ch[j] == ch[i]) {
                    // update dp[j] if ch[j] == ch[i]
                    // dp[j] is just all different palindrom subsequences 
                    // that start with 'a' 'b' 'c' 'd' plus 2 
                    dp[j] = 0;
                    for (int t = 0; t < total.length; t++)
                        dp[j] = (dp[j] + total[t]) % mod;
                    dp[j] = (dp[j] + 2) % mod;
                }
                total[ch[j]-'a'] = temp;
            }
        }
        int[] total = new int[4];
        for (int i = ch.length-1; i >= 0; i--)
            total[ch[i]-'a'] = dp[i];
        int res = 0;
        for (int t = 0; t < total.length; t++)
            res = (res + total[t]) % mod;
        return res;
    }
}


class Solution {
    public int countPalindromicSubsequences(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        int[] dp = new int[ch.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            int[] total = new int[4];
            // the sum of 'total' array in the run
            int sum = 0;
            for (int j = i-1; j >= 0; j--) {
                int temp = dp[j];
                if (ch[j] == ch[i]) 
                    dp[j] = (sum + 2) % mod;
                sum += temp - total[ch[j]-'a'];
                if (sum < 0)
                    sum = (sum + mod) % mod;
                else
                    sum = sum % mod;
                total[ch[j]-'a'] = temp;
            }
        }
        int[] total = new int[4];
        for (int i = ch.length-1; i >= 0; i--)
            total[ch[i]-'a'] = dp[i];
        int res = 0;
        for (int t = 0; t < total.length; t++)
            res = (res + total[t]) % mod;
        return res;
    }
}
