class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return;
        int len = matrix.length-1, start = 0, temp = 0, n = matrix.length-1;
        while (len >= 0) {
            int row = start, col = start;
            for (int i = 0; i < len; i++) {
                temp = matrix[row][col+i];
                matrix[row][col+i] = matrix[n-col-i][row];
                matrix[n-col-i][row] = matrix[n-row][n-col-i];
                matrix[n-row][n-col-i] = matrix[col+i][n-row];
                matrix[col+i][n-row] = temp;
            }
            start++;
            len -= 2;
        }
    }
}


class Solution {
    public void rotate(int[][] matrix) {
        int i = 0, j = matrix.length-1;
        while (i < j) {
            int[] temp = matrix[i];
            matrix[i++] = matrix[j];
            matrix[j--] = temp;
        }
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
