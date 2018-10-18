// brainless brute force BFS 
class Solution {
    public int shortestDistance(int[][] grid) {
        int count = 0;
        for (int[] gr : grid) {
            for (int g : gr) {
                if (g == 1)
                    count++;
            }
        }
        int res = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    int ret = helper(grid, i, j, count);
                    if (res == -1 && ret != Integer.MAX_VALUE || res > ret)
                        res = ret;
                }
            }
        }
        return res;
    }
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int helper(int[][] grid, int row, int col, int count) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;
        int res = 0, dist = 0;
        while (!queue.isEmpty()) {
            dist++;
            for (int i = queue.size(); i > 0; i--) {
                int[] node = queue.poll();
                for (int[] d : dir) {
                    int r = node[0] + d[0], c = node[1] + d[1];
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && 
                                                                        !visited[r][c]) {
                        visited[r][c] = true;
                        if (grid[r][c] == 1) {
                            res += dist;
                            count--;
                            if (count == 0)
                                return res;
                        }
                        if (grid[r][c] == 0)
                            queue.offer(new int[]{r, c});
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}


// process according to 1 cells rather then 0 cells. 
// same complexity as above, but much faster on the OJ. 
class Solution {
    public int shortestDistance(int[][] grid) {
        int[][] totalDist = new int[grid.length][grid[0].length];
        int[][] reachable = new int[grid.length][grid[0].length];
        int ones = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    ones++;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && helper(grid, totalDist, reachable, i, j) != ones)
                    return -1;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && reachable[i][j] == ones && res > totalDist[i][j])
                    res = totalDist[i][j];
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int helper(int[][] grid, int[][] totalDist, int[][] reachable, int i, int j) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        int count = 1, dist = 0;
        while (!queue.isEmpty()) {
            dist++;
            for (int s = queue.size(); s > 0; s--) {
                int[] node = queue.poll();
                for (int[] d : dir) {
                    int r = node[0] + d[0], c = node[1] + d[1];
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && 
                                                                        !visited[r][c]) {
                        if (grid[r][c] == 0) {
                            totalDist[r][c] += dist;
                            reachable[r][c]++;
                            queue.offer(new int[]{r, c});
                        } else if (grid[r][c] == 1) {
                            count++;
                        }
                        visited[r][c] = true;
                    }
                }
            }
        }
        return count;
    }
}
