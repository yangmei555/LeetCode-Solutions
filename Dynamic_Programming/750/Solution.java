class Solution {
    public int countCornerRectangles(int[][] grid) {
        int res = 0;
        int[][] index = new int[grid[0].length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = j+1; k < grid[0].length; k++) {
                    if (grid[i][j] == 1 && grid[i][k] == 1)
                        index[j][k]++;
                }
            }
        }
        for (int[] i : index)
            for (int j : i)
                res += j*(j-1)/2;
        return res;
    }
}


class Solution {
    public int countCornerRectangles(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    for (int ii = 0; ii < i; ii++) {
                        for (int jj = 0; jj < j; jj++) {
                            if (grid[ii][j] == 1)
                                res += grid[ii][jj] * grid[i][jj];
                            else
                                break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
