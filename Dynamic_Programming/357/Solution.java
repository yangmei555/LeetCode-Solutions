class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;
        int cur = 9, diff = 9, res = 9;
        for (int i = 1; i < n; i++) {
            if (i == 10)
                break;
            cur *= diff;
            diff--;
            res += cur;
        }
        return res + 1;
    }
}


class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;
        int cur = 9, diff = 9, res = 9;
        for (int i = 1; i < n && diff >= 1; i++) {
            cur *= diff;
            diff--;
            res += cur;
        }
        return res + 1;
    }
}


class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        boolean[] v = new boolean[10];
        int res = helper(v, 0, n);
        return res;
    }
    
    public int helper(boolean[] v, int len, int n) {
        if (len == n)
            return 1;
        int res = 1;
        for (int i = 0; i < 10; i++) {
            if (v[i] == false && (i + len != 0)) {
                v[i] = true;
                res += helper(v, len + 1, n);
                v[i] = false;
            }
        }
        return res;
    }
}
