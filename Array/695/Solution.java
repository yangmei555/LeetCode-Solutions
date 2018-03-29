class Solution {
    int res = 0;
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0|| grid[0].length == 0)
            return 0;
        int max = 0, row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    help(grid, i, j);
                    max = max > res ? max : res;
                    res = 0;
                }
            }
        }
        return max;
    }

    public void help(int[][] grid, int i, int j) {
        if (grid[i][j] == 0)
            return;
        grid[i][j] = 0;
        res++;
        if (i-1 >= 0 && grid[i-1][j] == 1)
            help(grid, i-1, j);
        if (i+1 < grid.length && grid[i+1][j] == 1)
            help(grid, i+1, j);
        if (j-1 >= 0 && grid[i][j-1] == 1)
            help(grid, i, j-1);
        if (j+1 < grid[0].length && grid[i][j+1] == 1)
            help(grid, i, j+1);
        return;
    }
}
