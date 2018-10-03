class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int largestIsland(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < dir.length; k++) {
                        int x = i + dir[k][0], y = j + dir[k][1];
                        if (x >= 0 && x < row && y >= 0 && y < col) {
                            set.add(uf.find(x * col + y));
                        }
                    }
                    int sum = 0;
                    for (int s : set)
                        sum += uf.size[s];
                    res = Math.max(res, sum + 1);
                } else {
                    res = Math.max(res, uf.size[i * col + j]);
                }
            }
        }
        return res == 0 ? 1 : res;
    }
    
    class UnionFind {
        int[] id, rank, size;
        
        public UnionFind(int[][] grid) {
            int row = grid.length, col = grid[0].length;
            id = new int[row * col];
            rank = new int[row * col];
            size = new int[row * col];
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                if (grid[i / col][i % col] == 1)
                    size[i] = 1;
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1) {
                        for (int[] d : dir) {
                            int x = i + d[0], y = j + d[1];
                            if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) 
                                union(i * col + j, x * col + y);
                        }
                    }
                }
            }
        }
        
        public void union(int i, int j) {
            int x = find(i), y = find(j);
            if (x != y) {
                if (rank[x] > rank[y]) {
                    id[y] = x;
                    size[x] += size[y];
                } else if (rank[x] < rank[y]) {
                    id[x] = y;
                    size[y] += size[x];
                } else {
                    id[x] = y;
                    size[y] += size[x];
                    rank[y]++;
                }
            }
        }
        
        public int find(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }
    }
}
