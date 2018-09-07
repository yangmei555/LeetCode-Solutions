// cannot run from back to front to determine the lexicographically smallest answer 
// because the ball running process is irreversible 
// one way is to backtrack every time when meeting a tie 
class Solution {
    int[][] dir = new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    char[] map = new char[]{'d', 'l', 'r', 'u'};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length, col = maze[0].length;
        int[][] dist = new int[row][col];
        int[][][] pred = new int[row][col][];
        for (int[] d : dist)
            Arrays.fill(d, Integer.MAX_VALUE);
        dist[ball[0]][ball[1]] = 0;
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[2] - i2[2];
            }
        });
        queue.offer(new int[]{ball[0], ball[1], 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r = node[0], c = node[1];
            if (dist[r][c] < node[2])
                continue;
            for (int i = 0; i < dir.length; i++) {
                int x = r, y = c;
                while (x >= 0 && x < row && y >= 0 && y < col && maze[x][y] == 0 && 
                                                        (x != hole[0] || y != hole[1])) {
                    x += dir[i][0];
                    y += dir[i][1];
                }
                if (x < 0 || x >= row || y < 0 || y >= col || maze[x][y] == 1) {
                    x -= dir[i][0];
                    y -= dir[i][1];
                }
                int len = Math.abs(x - r) + Math.abs(y - c);
                if (len != 0) {
                    if (dist[x][y] > dist[r][c] + len) {
                        dist[x][y] = dist[r][c] + len;
                        pred[x][y] = new int[]{r, c, i};
                        if (x != hole[0] || y != hole[1])
                            queue.offer(new int[]{x, y, dist[x][y]});
                    } else if (dist[x][y] == dist[r][c] + len) {
                        int a = x, b = y;
                        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
                        while (a != ball[0] || b != ball[1]) {
                            sb1.append(map[pred[a][b][2]]);
                            int t1 = pred[a][b][0], t2 = pred[a][b][1];
                            a = t1;
                            b = t2;
                        }
                        sb1.reverse();
                        sb2.append(map[i]);
                        a = r;
                        b = c;
                        while (a != ball[0] || b != ball[1]) {
                            sb2.append(map[pred[a][b][2]]);
                            int t1 = pred[a][b][0], t2 = pred[a][b][1];
                            a = t1;
                            b = t2;
                        }
                        sb2.reverse();
                        if (sb2.toString().compareTo(sb1.toString()) < 0) {
                            pred[x][y] = new int[]{r, c, i};
                        }
                    }
                }
            }
        }
        if (dist[hole[0]][hole[1]] == Integer.MAX_VALUE) {
            return "impossible";
        } else {
            int r = hole[0], c = hole[1];
            StringBuilder sb = new StringBuilder();
            while (r != ball[0] || c != ball[1]) {
                sb.append(map[pred[r][c][2]]);
                int temp1 = pred[r][c][0], temp2 = pred[r][c][1];
                r = temp1;
                c = temp2;
            }
            return sb.reverse().toString();
        }
    }
}


// store the path information in each point , no need to backtrack when finding a tie 
class Solution {
    int[][] dir = new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    char[] map = new char[]{'d', 'l', 'r', 'u'};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length, col = maze[0].length;
        boolean[][] visited = new boolean[row][col];
        Queue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if (p1.dist != p2.dist)
                    return p1.dist - p2.dist;
                else
                    return p1.str.compareTo(p2.str);
            }
        });
        queue.offer(new Point(ball[0], ball[1], 0, ""));
        while (!queue.isEmpty()) {
            Point node = queue.poll();
            int r = node.x, c = node.y;
            // the first encountered (hole[0],hole[1]) is the smallest possible 
            if (r == hole[0] && c == hole[1])
                return node.str;
            if (visited[r][c])
                continue;
            visited[r][c] = true;
            for (int i = 0; i < dir.length; i++) {
                int x = r, y = c;
                while (x >= 0 && x < row && y >= 0 && y < col && maze[x][y] == 0 && 
                                                        (x != hole[0] || y != hole[1])) {
                    x += dir[i][0];
                    y += dir[i][1];
                }
                if (x < 0 || x >= row || y < 0 || y >= col || maze[x][y] == 1) {
                    x -= dir[i][0];
                    y -= dir[i][1];
                }
                int len = Math.abs(x - r) + Math.abs(y - c);
                // comparing or not before enqueue is the same 
                queue.offer(new Point(x, y, node.dist + len, node.str + map[i]));
            }
        }
        return "impossible";
    }
    
    class Point {
        int x, y, dist;
        String str;
        public Point(int x, int y, int dist, String str) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.str = str;
        }
    }
}
