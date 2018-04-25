class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        boolean r = false, c = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0)
                        r = true;
                    if (j == 0)
                        c = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[0].length; j++)
                    matrix[i][j] = 0;
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if ((j == 0 && c) || (j != 0 && matrix[0][j] == 0)) {
                for (int i = 0; i < matrix.length; i++)
                    matrix[i][j] = 0;
            }
        }
        if (r) {
            for (int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        }
        return;
    }
}
