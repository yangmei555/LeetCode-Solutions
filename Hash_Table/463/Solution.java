class Solution {
    public int islandPerimeter(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (j != 0 && grid[i][j-1] == 1)
                        res -= 2;
                    if (i != 0 && grid[i-1][j] == 1)
                        res -= 2;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int islandPerimeter(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        HashSet<Integer> set = new HashSet<>();
        boolean[][] info = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    help(grid, info, i, j, set);
                    return set.size();
                }
            }
        }
        return -1;
    }
    public void help(int[][] grid, boolean[][] info, int i, int j, HashSet<Integer> set) {
        int row = grid.length;
        int col = grid[0].length;
        int col_ = 2 * col + 1;
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0 || info[i][j] == true)
            return;
        info[i][j] = true;
        if (!set.add(2 * i * col_ + 2 * j + 1))
            set.remove(2 * i * col_ + 2 * j + 1);
        if (!set.add((2 * i + 2) * col_ + 2 * j + 1))
            set.remove((2 * i + 2) * col_ + 2 * j + 1);
        if (!set.add((2 * i + 1) * col_ + 2 * j))
            set.remove((2 * i + 1) * col_ + 2 * j);
        if (!set.add((2 * i + 1) * col_ + 2 * j + 2))
            set.remove((2 * i + 1) * col_ + 2 * j + 2);
        help(grid, info, i - 1, j, set);
        help(grid, info, i + 1, j, set);
        help(grid, info, i, j + 1, set);
        help(grid, info, i, j - 1, set);
    }
}
