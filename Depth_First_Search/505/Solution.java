// DFS directly , quite slow 
class Solution {
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] dist = new int[maze.length][maze[0].length];
        for (int[] d : dist)
            Arrays.fill(d, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        helper(maze, start[0], start[1], dist);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : 
                dist[destination[0]][destination[1]];
    }
    
    public void helper(int[][] maze, int r, int c, int[][] dist) {
        int row = maze.length, col = maze[0].length;
        for (int i = 0; i < dir.length; i++) {
            int x = r, y = c;
            while (x >= 0 && x < row && y >= 0 && y < col && maze[x][y] == 0) {
                x += dir[i][0];
                y += dir[i][1];
            }
            x -= dir[i][0];
            y -= dir[i][1];
            int len = Math.abs(x - r) + Math.abs(y - c);
            if (dist[r][c] + len < dist[x][y]) {
                dist[x][y] = dist[r][c] + len;
                helper(maze, x, y, dist);
            }
        }
    }
}


// Dijkstra . for queue operations, enqueue when a vertex is relaxed , 
// different from the CLRS version, because Java does not support O(log n) decrease key operation 
class Solution {
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;
        int[][] dist = new int[maze.length][maze[0].length];
        for (int[] d : dist)
            Arrays.fill(d, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[2] - i2[2];
            }
        });
        queue.offer(new int[]{start[0], start[1], 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r = node[0], c = node[1];
            if (dist[r][c] < node[2] || dist[r][c] >= dist[destination[0]][destination[1]])
                continue;
            for (int i = 0; i < dir.length; i++) {
                int x = r, y = c;
                while (x >= 0 && x < row && y >= 0 && y < col && maze[x][y] == 0) {
                    x += dir[i][0];
                    y += dir[i][1];
                }
                x -= dir[i][0];
                y -= dir[i][1];
                int len = Math.abs(x - r) + Math.abs(y - c);
                if (dist[r][c] + len < dist[x][y]) {
                    dist[x][y] = dist[r][c] + len;
                    queue.offer(new int[]{x, y, dist[x][y]});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : 
                dist[destination[0]][destination[1]];
    }
}


// the BFS way, just replace the PriorityQueue in Dijkstra with an ordinary Queue 
class Solution {
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;
        int[][] dist = new int[maze.length][maze[0].length];
        for (int[] d : dist)
            Arrays.fill(d, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1]});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r = node[0], c = node[1];
            if (dist[r][c] >= dist[destination[0]][destination[1]])
                continue;
            for (int i = 0; i < dir.length; i++) {
                int x = r, y = c;
                while (x >= 0 && x < row && y >= 0 && y < col && maze[x][y] == 0) {
                    x += dir[i][0];
                    y += dir[i][1];
                }
                x -= dir[i][0];
                y -= dir[i][1];
                int len = Math.abs(x - r) + Math.abs(y - c);
                if (dist[r][c] + len < dist[x][y]) {
                    dist[x][y] = dist[r][c] + len;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : 
                                                dist[destination[0]][destination[1]];
    }
}
