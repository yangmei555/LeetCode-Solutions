class Solution {
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0)
                    queue.offer(new int[]{i, j});
            }
        }
        int[] dir = new int[]{1, 0, -1, 0, 1};
        int dist = 0;
        while (!queue.isEmpty()) {
            dist++;
            for (int s = queue.size(); s > 0; s--) {
                int[] node = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int r = node[0] + dir[i], c = node[1] + dir[i+1];
                    if (r >= 0 && r < rooms.length && c >= 0 && c < rooms[0].length && 
                                                            rooms[r][c] == Integer.MAX_VALUE) {
                        rooms[r][c] = dist;
                        queue.offer(new int[]{r, c});
                    }
                }
            }
        }
        return;
    }
}


// the DFS solution, should have higher complexity but runs faster than the above BFS 
class Solution {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    for (int[] d : dir)
                        helper(rooms, i + d[0], j + d[1], 1);
                }
            }
        }
    }
    
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public void helper(int[][] rooms, int r, int c, int dist) {
        if (r < 0 || r >= rooms.length || c < 0 || c >= rooms[0].length || rooms[r][c] <= dist)
            return;
        rooms[r][c] = dist;
        for (int[] d : dir)
            helper(rooms, r + d[0], c + d[1], dist + 1);
    }
}


// another style of DFS, borrowed from Problem 542 "01 Matrix" 
class Solution {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    helper(rooms, i, j);
                }
            }
        }
    }
    
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public void helper(int[][] rooms, int r, int c) {
        if (r < 0 || r >= rooms.length || c < 0 || c >= rooms[0].length)
            return;
        int min = Integer.MAX_VALUE;
        for (int[] d : dir) {
            int rr = r + d[0], cc = c + d[1];
            if (rr >= 0 && rr < rooms.length && cc >= 0 && cc < rooms[0].length && 
                                                                    rooms[rr][cc] != -1) 
                min = Math.min(min, rooms[rr][cc]);
        }
        if (min != Integer.MAX_VALUE && rooms[r][c] > min + 1) {
            rooms[r][c] = min + 1;
            for (int[] d : dir)
                helper(rooms, r + d[0], c + d[1]);
        }
    }
}
