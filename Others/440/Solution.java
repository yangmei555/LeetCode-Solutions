class Solution {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        while (k != 1) {
            int count = helper(cur, n);
            if (count < k) {
                cur++;
                k -= count;
            } else {
                cur *= 10;
                k--;
            }
        }
        return cur;
    }
    
    public int helper(long cur, int n) {
        long next = cur + 1, res = 0;
        while (cur <= n) {
            if (next <= n)
                res += next - cur;
            else
                res += n - cur + 1;
            cur *= 10;
            next *= 10;
        }
        return (int)res;
    }
}
