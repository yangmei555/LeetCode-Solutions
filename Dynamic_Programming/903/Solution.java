// a stupid O(N^3) solution, notice that we can use a cumulative variable to avoid the most inner 
// loop 
class Solution {
    public int numPermsDISequence(String S) {
        char[] ch = S.toCharArray();
        int mod = 1000000007;
        int[][] dp = new int[ch.length+1][ch.length+1];
        dp[0][0] = 1;
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j <= i+1; j++) {
                if (ch[i] == 'I') {
                    for (int k = 0; k < j; k++)
                        dp[i+1][j] = (dp[i+1][j] + dp[i][k]) % mod;
                } else {
                    for (int k = j; k <= i; k++)
                        dp[i+1][j] = (dp[i+1][j] + dp[i][k]) % mod;
                }
            }
        }
        int res = 0;
        for (int d : dp[ch.length])
            res = (res + d) % mod;
        return res;
    }
}


// O(N^2) solution 
class Solution {
    public int numPermsDISequence(String S) {
        char[] ch = S.toCharArray();
        int mod = 1000000007;
        int[][] dp = new int[ch.length+1][ch.length+1];
        dp[0][0] = 1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'I') {
                int total = 0;
                for (int j = 1; j <= i+1; j++) {
                    total = (total + dp[i][j-1]) % mod;
                    dp[i+1][j] = total;
                }
            } else {
                int total = 0;
                for (int j = i; j >= 0; j--) {
                    total = (total + dp[i][j]) % mod;
                    dp[i+1][j] = total;
                }
            }
        }
        int res = 0;
        for (int d : dp[ch.length])
            res = (res + d) % mod;
        return res;
    }
}


// 1 dimension dp 
class Solution {
    public int numPermsDISequence(String S) {
        char[] ch = S.toCharArray();
        int mod = 1000000007;
        int[] dp = new int[ch.length+1];
        dp[0] = 1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'I') {
                int total = 0, temp = 0;
                for (int j = 0; j <= i+1; j++) {
                    temp = dp[j];
                    dp[j] = total;
                    total = (total + temp) % mod;
                }
            } else {
                int total = 0;
                for (int j = i; j >= 0; j--) {
                    total = (total + dp[j]) % mod;
                    dp[j] = total;
                }
            }
        }
        int res = 0;
        for (int d : dp)
            res = (res + d) % mod;
        return res;
    }
}


// divide and conquer : divide the string according to where to put the element 0 
// if the first char is 'I', can put 0 at the very left , if the last char is 'D', 
// can put 0 at the very right. if two consecutive chars are "DI", can put 0 between 
// the left of that 'D' and the right of that 'I' 

// one difficult point is to calculate the combination number when n and m are very large . 
// if we define the res to be long, it will definitely overflow, and you cannot mod 1000000007 
// in the process, otherwise the res may not remain precise when doing division afterwards. 
// if we define the res to be double, after rounding, the outcome and the true value may differ 
// by greater than 1, so this will also cause wrong answer. 
// it seems the only way to deal with the combination numbers here is to use the BigInteger class. 
// but it is really slow and cumbersome. if you do not use memoization on the combination numbers, 
// it will cause time limit exceed. I guess this is why the official solution only give Python 
// version of this method but not the Java version, in Python the integers can be
// arbitrary precision. 

// also, this method is not so fast, mainly because of the BigInteger class I think. 
// just provide a different approach other than the standard DP 
import java.math.*;
class Solution {
    int mod = 1000000007;
    public int numPermsDISequence(String S) {
        char[] ch = S.toCharArray();
        int[][] memo = new int[ch.length][ch.length];
        int[][] memoComb = new int[ch.length+1][ch.length];
        return helper(ch, 0, ch.length-1, memo, memoComb);
    }
    
    public int helper(char[] ch, int left, int right, int[][] memo, int[][] memoComb) {
        if (left == right)
            return 1;
        else if (left > right)
            return 1;
        if (memo[left][right] != 0)
            return memo[left][right];
        long res = 0;
        if (ch[left] == 'I')
            res = (res + helper(ch, left+1, right, memo, memoComb)) % mod;
        if (ch[right] == 'D')
            res = (res + helper(ch, left, right-1, memo, memoComb)) % mod;
        for (int i = left; i+1 <= right; i++) {
            if (ch[i] == 'D' && ch[i+1] == 'I') {
                long part1 = helper(ch, left, i-1, memo, memoComb);
                long part2 = helper(ch, i+2, right, memo, memoComb);
                long select = combination(right-left+1, Math.min(i-left+1, right-i), memoComb) % mod;
                res = (res + (select * part1) % mod * part2) % mod;
            }
        }
        memo[left][right] = (int)res;
        return memo[left][right];
    }
    
    public long combination(int n, int m, int[][] memoComb) {
        if (memoComb[n][m] != 0)
            return memoComb[n][m];
        BigInteger res = new BigInteger("1");
        BigInteger one = BigInteger.ONE;
        BigInteger x = new BigInteger(n + ""), y = new BigInteger(1 + "");
        BigInteger M = new BigInteger(m + "");
        while (y.compareTo(M) <= 0) {
            res = res.multiply(x).divide(y);
            x = x.subtract(one);
            y = y.add(one);
        }
        res = res.mod(BigInteger.valueOf(mod));
        memoComb[n][m] = res.intValue();
        return memoComb[n][m];
    }
}
