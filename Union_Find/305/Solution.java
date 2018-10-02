class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] map = new boolean[m][n];
        UnionFind uf = new UnionFind(m * n);
        List<Integer> res = new LinkedList<>();
        for (int[] p : positions) {
            map[p[0]][p[1]] = true;
            uf.makeSet(p[0] * n + p[1]);
            for (int[] d : dir) {
                int r = p[0] + d[0], c = p[1] + d[1];
                if (r >= 0 && r < m && c >= 0 && c < n && map[r][c])
                    uf.union(p[0] * n + p[1], r * n + c);
            }
            res.add(uf.count);
        }
        return res;
    }
    
    class UnionFind {
        int[] id, rank;
        int count;
        
        public UnionFind(int num) {
            id = new int[num];
            rank = new int[num];
            count = 0;
        }
        
        public void makeSet(int i) {
            id[i] = i;
            rank[i] = 0;
            count++;
        }
        
        public void union(int i, int j) {
            int x = findSet(i), y = findSet(j);
            if (x != y) {
                link(x, y);
                count--;
            }
        }
        
        public int findSet(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }
        
        public void link(int x, int y) {
            if (rank[x] > rank[y]) {
                id[y] = x;
            } else if (rank[x] < rank[y]) {
                id[x] = y;
            } else {
                id[x] = y;
                rank[y]++;
            }
        }
    }
}
