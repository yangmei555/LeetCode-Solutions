class Solution {
    public int[][] imageSmoother(int[][] M) {
        int row = M.length;
        int col = M[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = 0, sum = 0;
                for (int m = i - 1; m <= i + 1; m++) {
                    for (int n = j - 1; n <= j + 1; n++) {
                        if (m >= 0 && m < row && n >= 0 && n < col) {
                            count++;
                            sum += M[m][n];
                        }
                    }
                }
                res[i][j] = sum / count;
            }
        }
        return res;
    }
}
