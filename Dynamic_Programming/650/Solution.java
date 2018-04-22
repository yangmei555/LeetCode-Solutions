class Solution {
    public int minSteps(int n) {
        int[] res = new int[n+1];
        res[1] = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                res[i] = res[i/2] + 2;
            } else {
                res[i] = Integer.MAX_VALUE;
                for (int j = 1; j <= i/2; j++) {
                    if (i%j== 0) {
                        res[i] = res[i] < res[j] + i/j ? res[i] : res[j] + i/j;
                    }
                }
            }
        }
        return res[n];
    }
}


class Solution {
    public int minSteps(int n) {
        int res = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                res += d;
                n /= d;
            }
            d++;
        }
        return res;
    }
}
