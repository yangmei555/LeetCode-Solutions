class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int dir = 0, row = 0, col = 0, count = 1;
        for (int i = 0; i < n * n; i++) {
            matrix[row][col] = count;
            count++;
            if ((row == 0 && col == n-1) || (col == 0 && row == n-1) || (row == n-1 && col == n-1) || 
                (dir == 0 && matrix[row][col+1] != 0) || (dir == 1 && matrix[row+1][col] != 0) || 
               (dir == 2 && matrix[row][col-1] != 0) || (dir == 3 && matrix[row-1][col] != 0))
                dir++;
            dir %= 4;
            if (dir == 0)
                col++;
            else if (dir == 1)
                row++;
            else if (dir == 2)
                col--;
            else
                row--;
        }
        return matrix;
    }
}


class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        int rowStart = 0, rowEnd = n-1, colStart = 0, colEnd = n-1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int j = colStart; j <= colEnd; j++)
                res[rowStart][j] = num++;
            rowStart++;
            for (int i = rowStart; i <= rowEnd; i++)
                res[i][colEnd] = num++;
            colEnd--;
            if (rowStart <= rowEnd) {
                for (int j = colEnd; j >= colStart; j--)
                    res[rowEnd][j] = num++;
            }
            rowEnd--;
            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--)
                    res[i][colStart] = num++;
            }
            colStart++;
        }
        return res;
    }
}
