class Solution {
    public int rotatedDigits(int N) {
        int num = N, ten = 1, seven = 1, three = 1, digit = 0, res = 0, itself = 0;
        while (num != 0) {
            ten *= 10;
            three *= 3;
            seven *= 7;
            num /= 10;
        }
        ten /= 10;
        num = N;
        while (num != 0) {
            digit = num / ten;
            num -= digit * ten;
            ten /= 10;
            seven /= 7;
            three /= 3;
            switch (digit) {
                case 9 : res += itself == -1 ? 0 : (itself == 1 ? seven : seven - three);
                case 8 : res += 0;
                case 7 : res += itself == -1 ? 0 : seven;
                case 6 : res += itself == -1 ? 0 : seven;
                case 5 : res += 0;
                case 4 : res += 0;
                case 3 : res += itself == -1 ? 0 : seven;
                case 2 : res += itself == -1 ? 0 : (itself == 1 ? seven : seven - three);
                case 1 : res += itself == -1 ? 0 : (itself == 1 ? seven : seven - three);
            } 
            if (itself != -1 && (digit == 3 || digit == 4 || digit == 7)) {
                itself = -1;
            } else if (itself == 0 && (digit == 2 || digit == 5 || digit == 6 || digit == 9)) {
                itself = 1;
            }
        }
        if (itself == 1)
            res++;
        return res;
    }
}


class Solution {
    public int rotatedDigits(int N) {
        int res = 0;
        for (int i = 1; i <= N; i++)
            if (check(i))
                res++;
        return res;
    }
    
    public boolean check(int num) {
        boolean has = false;
        int digit = 0;
        while (num != 0) {
            digit = num % 10;
            if (digit == 3 || digit == 4 || digit == 7)
                return false;
            if (!has && (digit == 2 || digit == 5 || digit == 6 || digit == 9))
                has = true;
            num /= 10;
        }
        return has;
    }
}


// O(logN) method, similar to Problem 902 
class Solution {
    public int rotatedDigits(int N) {
        boolean[] valid = new boolean[10];
        valid[0] = valid[1] = valid[8] = true;
        int ret1 = helper(N, valid);
        valid[2] = valid[5] = valid[6] = valid[9] = true;
        int ret2 = helper(N, valid);
        return ret2 - ret1;
    }
    
    // find how many numbers in [0, N] only consist of valid digits 
    public int helper(int N, boolean[] valid) {
        int count = 0;
        for (boolean b : valid) {
            if (b)
                count++;
        }
        char[] ch = String.valueOf(N).toCharArray();
        int[] dp = new int[ch.length+1];
        dp[ch.length] = 1;
        int cur = 1;
        for (int i = ch.length-1; i >= 0; i--) {
            for (int j = 0; j < valid.length; j++) {
                if (valid[j]) {
                    if (j < ch[i]-'0') 
                        dp[i] += cur;
                    else if (j == ch[i]-'0') 
                        dp[i] += dp[i+1];
                    else 
                        break;
                }
            }
            cur *= count;
        }
        return dp[0];
    }
}


// another O(logN) method, similar to Problem 902 
class Solution {
    public int rotatedDigits(int N) {
        boolean[] valid = new boolean[10];
        valid[0] = valid[1] = valid[8] = true;
        int ret1 = helper(N, valid);
        valid[2] = valid[5] = valid[6] = valid[9] = true;
        int ret2 = helper(N, valid);
        return ret2 - ret1;
    }
    
    // find how many numbers in [0, N] only consist of valid digits 
    public int helper(int N, boolean[] valid) {
        int count = 0;
        for (boolean b : valid) {
            if (b)
                count++;
        }
        char[] ch = String.valueOf(N).toCharArray();
        int cur = 1, res = 0;
        for (int i = 1; i < ch.length; i++)
            cur *= count;
        for (int i = 0; i < ch.length; i++) {
            boolean end = true;
            for (int j = 0; j < valid.length; j++) {
                if (valid[j]) {
                    if (j < ch[i]-'0') {
                        res += cur;
                    } else if (j == ch[i]-'0') {
                        end = false;
                        break;
                    } else {
                        break;
                    }
                }
            }
            if (end)
                return res;
            cur /= count;
        }
        return res + 1;
    }
}
