class Solution {
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0) {
                    if (i != 0 )
                        grid[i][j] += grid[i-1][j];
                    if (j != 0)
                        grid[i][j] += grid[i][j-1];
                } else {
                    grid[i][j] += (grid[i][j-1] < grid[i-1][j] ? grid[i][j-1] : grid[i-1][j]);
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
