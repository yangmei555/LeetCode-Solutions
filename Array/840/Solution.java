class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length-2; i++) {
            for (int j = 0; j < grid[0].length-2; j++) {
                if (grid[i+1][j+1] != 5 || grid[i][j] < 1 || grid[i][j] > 9 || 
                    grid[i+1][j] < 1 || grid[i+1][j] > 9 || 
                    grid[i+2][j] < 1 || grid[i+2][j] > 9)
                    continue;
                if (grid[i][j+1] < 1 || grid[i][j+1] > 9 || 
                    grid[i+1][j+1] < 1 || grid[i+1][j+1] > 9 || 
                    grid[i+2][j+1] < 1 || grid[i+2][j+1] > 9) {
                    j++;
                    continue;
                }
                if (grid[i][j+2] < 1 || grid[i][j+2] > 9 || 
                    grid[i+1][j+2] < 1 || grid[i+1][j+2] > 9 || 
                    grid[i+2][j+2] < 1 || grid[i+2][j+2] > 9) {
                    j += 2;
                    continue;
                }
                int row1 = grid[i][j] + grid[i][j+1] + grid[i][j+2];
                int row2 = grid[i+1][j] + grid[i+1][j+1] + grid[i+1][j+2];
                if (row1 != 15 || row1 != row2)
                    continue;
                int row3 = grid[i+2][j] + grid[i+2][j+1] + grid[i+2][j+2];
                if (row1 != row3)
                    continue;
                int col1 = grid[i][j] + grid[i+1][j] + grid[i+2][j];
                if (row1 != col1)
                    continue;
                int col2 = grid[i][j+1] + grid[i+1][j+1] + grid[i+2][j+1];
                if (row1 != col2)
                    continue;
                int col3 = grid[i][j+2] + grid[i+1][j+2] + grid[i+2][j+2];
                if (row1 != col3)
                    continue;
                int dig1 = grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2];
                if (row1 != dig1)
                    continue;
                int dig2 = grid[i][j+2] + grid[i+1][j+1] + grid[i+2][j];
                if (row1 != dig2)
                    continue;
                res++;
            }
        }
        return res;
    }
}
