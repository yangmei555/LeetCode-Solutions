class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)
            return 0;
        int[][] paths = new int[obstacleGrid.length][obstacleGrid[0].length];
        boolean obs1 = false, obs2 = false;
        for (int i = 0; i < paths.length; i++) {
            for (int j = 0; j < paths[0].length; j++) {
                if (i == 0) {
                    if (obstacleGrid[i][j] == 1)
                        obs1 = true;
                    else if (obs1 == false)
                        paths[i][j] = 1;
                } else if (j == 0) {
                    if (obstacleGrid[i][j] == 1)
                        obs2 = true;
                    else if (obs2 == false)
                        paths[i][j] = 1;
                } else {
                    if (obstacleGrid[i][j] == 0)
                        paths[i][j] = paths[i-1][j] + paths[i][j-1];
                }
            }
        }
        return paths[paths.length-1][paths[0].length-1];
    }
}
