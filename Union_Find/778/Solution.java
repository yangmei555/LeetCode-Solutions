class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int left = 0, right = N * N - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (helper(grid, 0, 0, mid, new boolean[N][N]))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
    
    public boolean helper(int[][] grid, int r, int c, int t, boolean[][] visited) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || visited[r][c] || 
                                                                            grid[r][c] > t)
            return false;
        if (r == grid.length-1 && c == grid[0].length-1)
            return true;
        visited[r][c] = true;
        boolean res = false;
        for (int[] d : dir) {
            if (helper(grid, r + d[0], c + d[1], t, visited)) {
                res = true;
                break;
            }
        }
        // notice that we cannot set visited[r][c] = false here . 
        return res;
    }
}


// use a priority queue to swim to the lowest viable (feasible) point first 
class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return grid[i1[0]][i1[1]] - grid[i2[0]][i2[1]];
            }
        });
        boolean[][] visited = new boolean[N][N];
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int max = 0;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            max = Math.max(max, grid[node[0]][node[1]]);
            if (node[0] == N-1 && node[1] == N-1)
                return max;
            for (int[] d : dir) {
                int r = node[0] + d[0], c = node[1] + d[1];
                if (r >= 0 && r < N && c >= 0 && c < N && !visited[r][c]) {
                    visited[r][c] = true;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        return max;
    }
}
