class Solution {
    public int maxA(int N) {
        int[] res = new int[N+1];
        res[0] = 0;
        for (int i = 1; i <= N; i++) {
            res[i] = i;
            for (int j = i - 3; j >= 0; j--)
                res[i] = res[i] > res[j]*(i-j-1) ? res[i] : res[j]*(i-j-1);
        }
        return res[N];
    }
}


class Solution {
    public int maxA(int N) {
        int[] res = new int[N];
        res[0] = 1;
        for (int i = 1; i < N; i++) {
            res[i] = res[i-1] + 1;
            for (int j = 0; j + 3 <= i; j++) {
                int cand = (res[j]) * (i - j - 1);
                res[i] = res[i] > cand ? res[i] : cand;
            }
        }
        return res[N-1];
    }
}
