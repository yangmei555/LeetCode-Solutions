class Solution {
    public int numDecodings(String s) {
        char[] ch = s.toCharArray();
        if (ch.length == 0 || ch[0] == '0')
            return 0;
        long temp = 0, cur = ch[0] == '*' ? 9 : 1, pre = 1;
        int mod = 1000000007;
        for (int i = 1; i < ch.length; i++) {
            temp = cur;
            if (ch[i] == '0')
                cur = 0;
            if (ch[i] == '*') {
                cur = cur * 9 % mod;
                if (ch[i-1] == '2')
                    cur = (cur + pre * 6) % mod;
                else if (ch[i-1] == '1')
                    cur = (cur + pre * 9) % mod;
                else if (ch[i-1] == '*')
                    cur = (cur + pre * 15) % mod;
            } else {
                if (ch[i-1] == '2' && ch[i] >= '0' && ch[i] <= '6')
                    cur = (cur + pre) % mod;
                else if (ch[i-1] == '1' && ch[i] >= '0' && ch[i] <= '9')
                    cur = (cur + pre) % mod;
                else if (ch[i-1] == '*') {
                    if (ch[i] >= '0' && ch[i] <= '6')
                        cur = (cur + pre * 2) % mod;
                    else
                        cur = (cur + pre) % mod;
                }
            }
            pre = temp;
        }
        return (int)cur;
    }
}


class Solution {
    int mod = 1000000007;
    public int numDecodings(String s) {
        char[] ch = s.toCharArray();
        Long[] memo = new Long[ch.length];
        return (int)helper(ch, ch.length-1, memo);
    }
    
    public long helper(char[] ch, int index, Long[] memo) {
        if (index == -1 || index == -2)
            return index + 2;
        if (memo[index] != null)
            return memo[index];
        long res = 0;
        if (ch[index] == '*') {
            res = helper(ch, index-1, memo) * 9 % mod;
            if (index != 0 && ch[index-1] == '1')
                res = (res + helper(ch, index-2, memo) * 9) % mod;
            else if (index != 0 && ch[index-1] == '2')
                res = (res + helper(ch, index-2, memo) * 6) % mod;
            else if (index != 0 && ch[index-1] == '*')
                res = (res + helper(ch, index-2, memo) * 15) % mod;
        } else {
            if (ch[index] != '0')
                res = helper(ch, index-1, memo);
            if (index != 0) {
                if (ch[index-1] == '2' && ch[index] >= '0' && ch[index] <= '6')
                    res = (res + helper(ch, index-2, memo)) % mod;
                else if (ch[index-1] == '1' && ch[index] >= '0' && ch[index] <= '9')
                    res = (res + helper(ch, index-2, memo)) % mod;
                else if (ch[index-1] == '*') {
                    if (ch[index] >= 0 && ch[index] <= '6')
                        res = (res + helper(ch, index-2, memo) * 2) % mod;
                    else
                        res = (res + helper(ch, index-2, memo)) % mod;
                }
            }
        }
        memo[index] = res;
        return res;
    }
}
