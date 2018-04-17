class NumMatrix {
    
    int[][] index;
    public NumMatrix(int[][] matrix) {
        if (matrix.length != 0 && matrix[0].length != 0) {
            index = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i == 0 || j == 0) {
                        index[i][j] = matrix[i][j] + index[i == 0 ? 0 : i-1][j == 0 ? 0 : j-1];
                    } else {
                        index[i][j] = index[i-1][j] + index[i][j-1] - index[i-1][j-1] + matrix[i][j];
                    }
                }
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return index[row2][col2] + (row1 > 0 && col1 > 0 ? index[row1-1][col1-1] : 0) - 
                (row1 > 0 ? index[row1-1][col2] : 0) -  (col1 > 0 ? index[row2][col1-1] : 0);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
