class Solution {
    public int numTilings(int N) {
        if (N == 1)
            return 1;
        if (N == 2)
            return 2;
        int mod = 1000000007;
        int[] res = new int[N+1], up = new int[N+1];
        res[1] = 1;
        res[2] = 2;
        up[2] = 1;
        for (int i = 3; i <= N; i++) {
            res[i] = ((res[i-2] + res[i-1])%mod + (up[i-1] + up[i-1])%mod)%mod;
            up[i] = (res[i-2] + up[i-1])%mod;
        }
        return res[N];
    }
}


class Solution {
    public int numTilings(int N) {
        if (N == 1 || N == 2)
            return N;
        int mod = 1000000007;
        int pre = 1, cur = 2, incomplete = 1, temp;
        for (int i = 3; i <= N; i++) {
            temp = cur;
            cur = ((cur + pre) % mod + (incomplete * 2) % mod) % mod;
            incomplete = (incomplete + pre) % mod;
            pre = temp;
        }
        return cur;
    }
}
