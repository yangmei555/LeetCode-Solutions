class Solution {
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public int[][] updateMatrix(int[][] matrix) {
        int[][] res = new int[matrix.length][matrix[0].length];
        int row = matrix.length, col = matrix[0].length;
        // do some preparation otherwise it will be TLE 
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < dir.length; k++) {
                        int x = i + dir[k][0], y = j + dir[k][1];
                        if (x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] == 1) 
                            res[x][y] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    Queue<int[]> queue = new LinkedList<>();
                    for (int k = 0; k < dir.length; k++) {
                        int x = i + dir[k][0], y = j + dir[k][1];
                        if (x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] == 1) 
                            queue.offer(new int[]{x, y});
                    }
                    while (!queue.isEmpty()) {
                        int[] point = queue.poll();
                        for (int k = 0; k < dir.length; k++) {
                            int x = point[0] + dir[k][0], y = point[1] + dir[k][1];
                            if (x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] == 1) {
                                if (res[x][y] == 0 || res[x][y] > res[point[0]][point[1]] + 1) {
                                    res[x][y] = res[point[0]][point[1]] + 1;
                                    queue.offer(new int[]{x, y});
                                }
                            }
                        }
                    }
                    
                }
            }
        }
        return res;
    }
}


// if do not do some preparation it will be TLE 
class Solution {
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public int[][] updateMatrix(int[][] matrix) {
        int[][] res = new int[matrix.length][matrix[0].length];
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < dir.length; k++) {
                        int x = i + dir[k][0], y = j + dir[k][1];
                        if (x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] == 1) 
                            res[x][y] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    helper(matrix, i-1, j, 1, res);
                    helper(matrix, i+1, j, 1, res);
                    helper(matrix, i, j-1, 1, res);
                    helper(matrix, i, j+1, 1, res);
                }
            }
        }
        return res;
    }
    
    public void helper(int[][] matrix, int i, int j, int dist, int[][] res) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length)
            return;
        if (matrix[i][j] == 0 || (res[i][j] != 0 && res[i][j] < dist))
            return;
        res[i][j] = dist;
        helper(matrix, i-1, j, dist+1, res);
        helper(matrix, i+1, j, dist+1, res);
        helper(matrix, i, j-1, dist+1, res);
        helper(matrix, i, j+1, dist+1, res);
    }
}
