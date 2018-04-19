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
