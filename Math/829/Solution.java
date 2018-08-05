class Solution {
    public int consecutiveNumbersSum(int N) {
        int res = 0;
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) {
                for (int j = 0, a = i; j < 2; j++, a = N / a) {
                    if (a % 2 != 0) {
                        if (N / a - a / 2 >= 1)
                            res++;
                    }
                    if (N / a % 2 == 1) {
                        int r = N / a / 2;
                        if (r >= a)
                            res++;
                    }
                    if (a == N / a)
                        break;
                }
            }
        }
        return res;
    }
}
