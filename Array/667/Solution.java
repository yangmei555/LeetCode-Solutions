class Solution {
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            if (k != 0) {
                res[i] = res[i-1] + k;
                k = k > 0 ? 1-k : -k-1;
            } else {
                res[i] = i+1;
            }
        }
        return res;
    }
}
