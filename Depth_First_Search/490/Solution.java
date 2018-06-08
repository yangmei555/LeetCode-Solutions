class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze[start[0]][start[1]] == -1)
            return false;
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        maze[start[0]][start[1]] = -1;
        int x = start[0], y = start[1], row = maze.length, col = maze[0].length;
        while (start[0]-1 >= 0 && maze[start[0]-1][start[1]] != 1)
            start[0]--;
        if (hasPath(maze, start, destination))
            return true;
        start[0] = x;
        while (start[0]+1 < row && maze[start[0]+1][start[1]] != 1)
            start[0]++;
        if (hasPath(maze, start, destination))
            return true;
        start[0] = x;
        while (start[1]-1 >= 0 && maze[start[0]][start[1]-1] != 1)
            start[1]--;
        if (hasPath(maze, start, destination))
            return true;
        start[1] = y;
        while (start[1]+1 < col && maze[start[0]][start[1]+1] != 1)
            start[1]++;
        if (hasPath(maze, start, destination))
            return true;
        start[1] = y;
        return false;
    }
}


class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1]});
        maze[start[0]][start[1]] = -1;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            if (x == destination[0] && y == destination[1])
                return true;
            while (pos[0]-1 >= 0 && maze[pos[0]-1][pos[1]] != 1)
                pos[0]--;
            if (maze[pos[0]][pos[1]] != -1) {
                queue.offer(new int[]{pos[0], pos[1]});
                maze[pos[0]][pos[1]] = -1;
            }
            pos[0] = x;
            while (pos[0]+1 < row && maze[pos[0]+1][pos[1]] != 1)
                pos[0]++;
            if (maze[pos[0]][pos[1]] != -1) {
                queue.offer(new int[]{pos[0], pos[1]});
                maze[pos[0]][pos[1]] = -1;
            }
            pos[0] = x;
            while (pos[1]-1 >= 0 && maze[pos[0]][pos[1]-1] != 1)
                pos[1]--;
            if (maze[pos[0]][pos[1]] != -1) {
                queue.offer(new int[]{pos[0], pos[1]});
                maze[pos[0]][pos[1]] = -1;
            }
            pos[1] = y;
            while (pos[1]+1 < col && maze[pos[0]][pos[1]+1] != 1)
                pos[1]++;
            if (maze[pos[0]][pos[1]] != -1) {
                queue.offer(new int[]{pos[0], pos[1]});
                maze[pos[0]][pos[1]] = -1;
            }
            pos[1] = y;
        }
        return false;
    }
}
