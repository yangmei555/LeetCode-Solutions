// 数位dp 
class Solution {
    public int atMostNGivenDigitSet(String[] D, int N) {
        char[] ch = (N + "").toCharArray();
        int[] memo = new int[ch.length];
        // Arrays.fill(memo, -1);
        return helper(ch, D, 0, true, true, memo) - 1;
    }
    
    public int helper(char[] ch, String[] D, int index, boolean limit, boolean leadingZero, 
                                                                                int[] memo) {
        if (index == ch.length)
            return 1;
        if (!limit && !leadingZero && memo[index] != 0)
            return memo[index];
        int res = 0;
        for (int i = 0; i < D.length; i++) {
            int digit = Integer.parseInt(D[i]);
            if (limit && digit > ch[index] - '0')
                break;
            res += helper(ch, D, index+1, limit && digit == ch[index] - '0', false, memo);
        }
        if (leadingZero)
            res += helper(ch, D, index+1, false, true, memo);
        if (!limit && !leadingZero)
            memo[index] = res;
        return res;
    }
}


// once the current digit is not in D, return immediately 
class Solution {
    public int atMostNGivenDigitSet(String[] D, int N) {
        int res = 0, cur = 1;
        char[] ch = (N + "").toCharArray();
        for (int i = 1; i < ch.length; i++) {
            cur *= D.length;
            res += cur;
        }
        int count = 0;
        for (int i = 0; i < ch.length; i++) {
            for (String str : D) {
                int d = Integer.valueOf(str);
                if (d < ch[i] - '0')
                    res += cur;
                else if (d == ch[i] - '0') {
                    count++;
                    break;
                } else {
                    break;
                }
            }
            if (count != i+1)
                return res;
            cur /= D.length;
        }
        return res + 1;
    }
}


class Solution {
    public int atMostNGivenDigitSet(String[] D, int N) {
        char[] ch = (N + "").toCharArray();
        int[] dp = new int[ch.length+1];
        dp[ch.length] = 1;
        int cur = 1, res = 0;
        for (int i = ch.length-1; i >= 0; i--) {
            for (String str : D) {
                int d = Integer.valueOf(str);
                if (d < ch[i] - '0') {
                    dp[i] += cur;
                } else if (d == ch[i] - '0') {
                    dp[i] += dp[i+1];
                } else {
                    break;
                }
            }
            cur *= D.length;
            res += cur;
        }
        return res - cur + dp[0];
    }
}
