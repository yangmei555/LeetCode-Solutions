class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if (matrix.length == 0 || matrix[0].length == 0)
            return res;
        int row = matrix.length, col = matrix[0].length;
        boolean[][][] visited = new boolean[row][col][2];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < row; i++) {
            queue.offer(new int[]{i, 0});
            visited[i][0][0] = true;
        }
        for (int j = 1; j < col; j++) {
            queue.offer(new int[]{0, j});
            visited[0][j][0] = true;
        }
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int[] d : dir) {
                int r = node[0] + d[0], c = node[1] + d[1];
                if (r >= 0 && r < row && c >= 0 && c < col && 
                        matrix[node[0]][node[1]] <= matrix[r][c] && !visited[r][c][0]) {
                    visited[r][c][0] = true;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        for (int i = 0; i < row; i++) {
            queue.offer(new int[]{i, col-1});
            visited[i][col-1][1] = true;
        }
        for (int j = 0; j < col-1; j++) {
            queue.offer(new int[]{row-1, j});
            visited[row-1][j][1] = true;
        }
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int[] d : dir) {
                int r = node[0] + d[0], c = node[1] + d[1];
                if (r >= 0 && r < row && c >= 0 && c < col && 
                        matrix[node[0]][node[1]] <= matrix[r][c] && !visited[r][c][1]) {
                    visited[r][c][1] = true;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j][0] && visited[i][j][1])
                    res.add(new int[]{i, j});
            }
        }
        return res;
    }
}


class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if (matrix.length == 0 || matrix[0].length == 0)
            return res;
        int row = matrix.length, col = matrix[0].length;
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            helper(matrix, i, 0, 0, pacific);
            helper(matrix, i, col-1, 0, atlantic);
        }
        for (int j = 0; j < col; j++) {
            helper(matrix, 0, j, 0, pacific);
            helper(matrix, row-1, j, 0, atlantic);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                if (pacific[i][j] && atlantic[i][j])
                    res.add(new int[]{i, j});
        }
        return res;
    }
    
    public void helper(int[][] matrix, int r, int c, int val, boolean[][] visited) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || 
                                                            visited[r][c] || matrix[r][c] < val)
            return;
        visited[r][c] = true;
        for (int[] d : dir)
            helper(matrix, r + d[0], c + d[1], matrix[r][c], visited);
    }
}
