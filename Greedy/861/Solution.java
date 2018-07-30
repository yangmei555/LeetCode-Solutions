class Solution {
    public int matrixScore(int[][] A) {
        int row = A.length, col = A[0].length, res = 0;
        res += (1 << col - 1) * row;
        for (int i = 0; i < row; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < col; j++)
                    A[i][j] = 1 - A[i][j];
            }
        }
        for (int j = 1; j < col; j++) {
            int count = 0;
            for (int i = 0; i < row; i++) {
                if (A[i][j] == 1)
                    count++;
            }
            count = count > row - count ? count : row - count;
            res += (1 << col - 1 - j) * count;
        }
        return res;
    }
}


class Solution {                           // one pass version
    public int matrixScore(int[][] A) {
        int row = A.length, col = A[0].length, res = 0;
        for (int j = 0; j < col; j++) {
            int count = 0;
            for (int i = 0; i < row; i++) {
                if (A[i][j] == A[i][0])
                    count++;
            }
            count = count > row - count ? count : row - count;
            res += (1 << col - 1 - j) * count;
        }
        return res;
    }
}
