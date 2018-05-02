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
