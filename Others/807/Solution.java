class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] row = new int[grid.length], col = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                row[i] = Math.max(row[i], grid[i][j]);
                col[j] = Math.max(col[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) 
                res += Math.min(row[i], col[j]) - grid[i][j];
        }
        return res;
    }
}
