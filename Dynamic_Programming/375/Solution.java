class Solution {
    public int getMoneyAmount(int n) {
        int[][] index = new int[n+1][n+1];
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                index[i][j] = Integer.MAX_VALUE;
                for (int k = (i + j) / 2; k < j; k++) {
                    if (k + (index[i][k-1]<index[k+1][j]?index[k+1][j]:index[i][k-1]) < index[i][j])
                        index[i][j] = k + (index[i][k-1]<index[k+1][j]?index[k+1][j]:index[i][k-1]);
                }
            }
        }
        return index[1][n];
    }
}


class Solution {
    public int getMoneyAmount(int n) {
        int[][] memo = new int[n+1][n+1];
        return helper(1, n, memo);
    }
    
    public int helper(int left, int right, int[][] memo) {
        if (left >= right)
            return 0;
        if (memo[left][right] != 0)
            return memo[left][right];
        int min = Integer.MAX_VALUE;
        for (int i = (left+right)/2; i < right; i++) {
            int cand1 = helper(left, i-1, memo), cand2 = helper(i+1, right, memo);
            int cand = cand1 > cand2 ? cand1 : cand2;
            min = min < cand + i ? min : cand + i;
        }
        memo[left][right] = min;
        return min;
    }
}
