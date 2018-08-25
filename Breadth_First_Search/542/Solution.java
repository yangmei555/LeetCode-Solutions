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


// the more proper way to use BFS, enqueue all 0 positions all at once 
class Solution {
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public int[][] updateMatrix(int[][] matrix) {
        int[][] res = new int[matrix.length][matrix[0].length];
        int row = matrix.length, col = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0)
                    queue.offer(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 0; i < dir.length; i++) {
                int x = p[0] + dir[i][0], y = p[1] + dir[i][1];
                if (x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] == 1 && 
                                (res[x][y] == 0 || res[x][y] > res[p[0]][p[1]] + 1)) {
                    res[x][y] = res[p[0]][p[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return res;
    }
}


// dynamic programming , think about the 1-D situation 
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    int up = helper(i-1, j, row, col) ? res[i-1][j] : Integer.MAX_VALUE;
                    int left = helper(i, j-1, row, col) ? res[i][j-1] : Integer.MAX_VALUE;
                    int cand = up < left ? up : left;
                    res[i][j] = cand == Integer.MAX_VALUE ? cand : cand + 1;
                }
            }
        }
        for (int i = row-1; i >= 0; i--) {
            for (int j = col-1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    int right = helper(i, j+1, row, col) ? res[i][j+1] : Integer.MAX_VALUE;
                    int down = helper(i+1, j, row, col) ? res[i+1][j] : Integer.MAX_VALUE;
                    int cand = right < down ? right : down;
                    res[i][j] = Math.min(res[i][j], cand == Integer.MAX_VALUE ? cand : cand + 1);
                }
            }
        }
        return res;
    }
    
    public boolean helper(int i, int j, int row, int col) {
        if (i >= 0 && i < row && j >= 0 && j < col)
            return true;
        else
            return false;
    }
}


// this is the fastest solution, but I don't know why it is so fast 
class Solution {
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0) 
                    helper(matrix, i, j);
            }
        }
        return matrix;
    }
    
    public void helper(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length)
            return;
        int min = Integer.MAX_VALUE;
        for (int[] d : dir) {
            int x = i + d[0], y = j + d[1];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length)
                min = Math.min(min, matrix[x][y] + 1);
        }
        if (min != matrix[i][j]) {
            matrix[i][j] = min;
            for (int[] d : dir)
                helper(matrix, i + d[0], j + d[1]);
        }
    }
}
