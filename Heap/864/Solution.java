class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        char[][] ch = new char[grid.length][];
        for (int i = 0; i < ch.length; i++)
            ch[i] = grid[i].toCharArray();
        int sr = 0, sc = 0, K = 0;
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < ch[0].length; j++) {
                if (ch[i][j] == '@') {
                    sr = i;
                    sc = j;
                } else if (ch[i][j] >= 'a' && ch[i][j] <= 'f') {
                    K++;
                }
            }
        }
        Set<Integer>[][] keys = new Set[ch.length][ch[0].length];
        keys[sr][sc] = new HashSet<>();
        keys[sr][sc].add(0);
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sr, sc, 0, 0, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int[] d : dir) {
                int r = cur.r + d[0], c = cur.c + d[1];
                if (r >= 0 && r < ch.length && c >= 0 && c < ch[0].length && ch[r][c] != '#') {
                    if (keys[r][c] != null && keys[r][c].contains(cur.keys))
                        continue;
                    if (keys[r][c] == null)
                        keys[r][c] = new HashSet<>();
                    keys[r][c].add(cur.keys);
                    Point p = new Point(r, c, cur.n, cur.step + 1, cur.keys);
                    if (ch[r][c] >= 'a' && ch[r][c] <= 'f') {
                        if (((1 << ch[r][c]-'a') & p.keys) == 0) {
                            p.keys |= (1 << ch[r][c]-'a');
                            p.n++;
                            if (p.n == K)
                                return p.step;
                        }
                    } else if (ch[r][c] >= 'A' && ch[r][c] <= 'F') {
                        if (((1 << ch[r][c]-'A') & p.keys) == 0)
                            continue;
                    }
                    queue.offer(p);
                }
            }
        }
        return -1;
    }
    
    class Point {
        int r, c, n, step, keys;
        public Point(int r, int c, int n, int step, int keys) {
            this.r = r;
            this.c = c;
            this.n = n;
            this.step = step;
            this.keys = keys;
        }
    }
}


// use boolean array rather than hash set to record the key states for each cell 
// there are at most 6 keys, so there will be at most 64 states, array size will not 
// exceed 64 . this way it runs significantly faster than the above one 
// also, no need to record the number of possessed keys "n" in the Point class, 
// use  keys == (1 << K) - 1  to decide 
class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        char[][] ch = new char[grid.length][];
        for (int i = 0; i < ch.length; i++)
            ch[i] = grid[i].toCharArray();
        int sr = 0, sc = 0, K = 0;
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < ch[0].length; j++) {
                if (ch[i][j] == '@') {
                    sr = i;
                    sc = j;
                } else if (ch[i][j] >= 'a' && ch[i][j] <= 'f') {
                    K++;
                }
            }
        }
        boolean[][][] keys = new boolean[ch.length][ch[0].length][1 << K];
        keys[sr][sc][0] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sr, sc, 0, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int[] d : dir) {
                int r = cur.r + d[0], c = cur.c + d[1];
                if (r >= 0 && r < ch.length && c >= 0 && c < ch[0].length && ch[r][c] != '#') {
                    if (keys[r][c][cur.keys])
                        continue;
                    keys[r][c][cur.keys] = true;
                    Point p = new Point(r, c, cur.step + 1, cur.keys);
                    if (ch[r][c] >= 'a' && ch[r][c] <= 'f') {
                        if (((1 << ch[r][c]-'a') & p.keys) == 0) {
                            p.keys |= (1 << ch[r][c]-'a');
                            if (p.keys == (1 << K) - 1)
                                return p.step;
                        }
                    } else if (ch[r][c] >= 'A' && ch[r][c] <= 'F') {
                        if (((1 << ch[r][c]-'A') & p.keys) == 0)
                            continue;
                    }
                    queue.offer(p);
                }
            }
        }
        return -1;
    }
    
    class Point {
        int r, c, step, keys;
        public Point(int r, int c, int step, int keys) {
            this.r = r;
            this.c = c;
            this.step = step;
            this.keys = keys;
        }
    }
}
