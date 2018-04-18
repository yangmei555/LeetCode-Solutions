class Solution {
    public int integerBreak(int n) {
        int[] res = new int[n + 1];
        res[1] = 1;
        int temp = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                temp = i-j > res[i-j] ? i-j : res[i-j];
                res[i] = j * temp > res[i] ? j * temp : res[i];
            }
        }
        return res[n];
    }
}


class Solution {
    public int integerBreak(int n) {
        if (n == 1 || n == 2)
            return 1;
        if (n == 3)
            return 2;
        if (n == 4)
            return 4;
        int three = (n - 5) / 3 + 1;
        int remain = n - 3 * three;
        return (int)Math.pow(3, three) * remain;
    }
}
