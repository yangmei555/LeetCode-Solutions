// seems so easy but tried lots of times before accepted 
class Solution {
    public int countDigitOne(int n) {
        if (n <= 0)
            return 0;
        int res = 0, a = n, base = 1;
        while (a != 0) {
            if (a % 10 == 1)
                res += a / 10 * base + n % base + 1;
            else if (a % 10 == 0)
                res += a / 10 * base;
            else
                res += (a / 10 + 1) * base;
            base *= 10;
            a /= 10;
        }
        return res;
    }
}


class Solution {
    public int countDigitOne(int n) {
        if (n <= 0)
            return 0;
        String str = (n + "");
        int res = 0, base = 1;
        for (int i = str.length()-1; i >= 0; i--) {
            int front = i == 0 ? 0 : Integer.valueOf(str.substring(0, i));
            int back = i == str.length()-1 ? 0 : Integer.valueOf(str.substring(i+1));
            if (str.charAt(i) - '0' == 1) {
                res += front * base + back + 1;
            } else if (str.charAt(i) - '0' == 0) {
                res += (front - 1) * base + base;
            } else {
                res += front * base + base;
            }
            base *= 10;
        }
        return res;
    }
}


// 数位dp !!! 
class Solution {
    public int countDigitOne(int n) {
        char[] ch = (n + "").toCharArray();
        int[] memo = new int[ch.length];
        return helper(ch, 0, true, memo);
    }
    
    public int helper(char[] ch, int index, boolean limit, int[] memo) {
        if (index == ch.length)
            return 0;
        if (!limit && memo[index] != 0)
            return memo[index];
        int res = 0;
        for (int i = 0; i <= (limit ? ch[index]-'0' : 9); i++) {
            res += helper(ch, index+1, limit && i == ch[index]-'0', memo);
            if (i == 1) {
                if (limit && i == ch[index]-'0')
                    res += (index == ch.length-1 ? 0 : 
                                Integer.valueOf(new String(ch, index+1, ch.length-index-1))) + 1;
                else
                    res += (int)Math.pow(10, ch.length-index-1);
            }
        }
        if (!limit)
            memo[index] = res;
        return res;
    }
}
