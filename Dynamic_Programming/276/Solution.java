class Solution {
    public int numWays(int n, int k) {
        if (n == 0)
            return 0;
        if (n == 1)
            return k;
        if (n == 2)
            return k * k;
        int pre = k, cur = k * k, temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = cur;
            cur = cur * (k - 1) + (k - 1) * pre;
            pre = temp;
        }
        return cur;
    }
}


class Solution {
    public int numWays(int n, int k) {
        if (n == 0)
            return 0;
        if (n == 1)
            return k;
        int diff = k, cur = k, temp = 0;
        for (int i = 2; i <= n; i++) {
            temp = cur;
            cur = cur * (k - 1) + diff;
            diff = temp * (k - 1);
        }
        return cur;
    }
}
