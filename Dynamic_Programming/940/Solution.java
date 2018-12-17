// count the empty sequence, then subtract it at last 
class Solution {
    public int distinctSubseqII(String S) {
        int mod = 1000000007;
        char[] ch = S.toCharArray();
        int[] last = new int[26], dp = new int[ch.length+1];
        dp[0] = 1;
        for (int i = 0; i < ch.length; i++) {
            int dup = last[ch[i]-'a'] == 0 ? 0 : dp[last[ch[i]-'a']-1];
            dp[i+1] = ((dp[i] + dp[i]) % mod + mod - dup) % mod;
            last[ch[i]-'a'] = i + 1;
        }
        return (dp[ch.length] - 1 + mod) % mod;
    }
}
