class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = 0, col = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            row = 0;
            col = i;
            while (row + 1 < matrix.length && col + 1 < matrix[0].length) {
                if (matrix[row][col] != matrix[row+1][col+1])
                    return false;
                row++;
                col++;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            row = i;
            col = 0;
            while (row + 1 < matrix.length && col + 1 < matrix[0].length) {
                if (matrix[row][col] != matrix[row+1][col+1])
                    return false;
                row++;
                col++;
            }
        }
        return true;
    }
}
