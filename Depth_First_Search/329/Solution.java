class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int[][] path = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < path.length; i++)
            Arrays.fill(path[i], -1);
        int res = 0;
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++)
                res = Math.max(res, helper(matrix, path, i, j, Integer.MIN_VALUE));
        }
        return res;
    }
    
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int helper(int[][] matrix, int[][] path, int r, int c, int prev) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || matrix[r][c] <= prev)
            return 0;
        if (path[r][c] != -1)
            return path[r][c];
        int res = 0;
        for (int[] d : dir) 
            res = Math.max(res, helper(matrix, path, r + d[0], c + d[1], matrix[r][c]));
        path[r][c] = res + 1;
        return path[r][c];
    }
}


// notice that the matrix is actually a DAG, smaller elements point to larger elements 
// so, we can use topological sort in the BFS fashion 
class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int[][] indegree = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                for (int[] d : dir) {
                    int r = i + d[0], c = j + d[1];
                    if (r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length && 
                        matrix[i][j] < matrix[r][c])
                        indegree[r][c]++;
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            for (int j = 0; j < indegree[0].length; j++) {
                if (indegree[i][j] == 0)
                    queue.offer(new int[]{i, j});
            }
        }
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            for (int i = queue.size(); i > 0; i--) {
                int[] node = queue.poll();
                for (int[] d : dir) {
                    int r = node[0] + d[0], c = node[1] + d[1];
                    if (r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length && 
                        matrix[node[0]][node[1]] < matrix[r][c]) {
                        if (--indegree[r][c] == 0)
                            queue.offer(new int[]{r, c});
                    }
                }
            }
        }
        return res;
    }
}
