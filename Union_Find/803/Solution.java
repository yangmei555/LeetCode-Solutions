class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[] id, rank, size;
    public int[] hitBricks(int[][] grid, int[][] hits) {
        boolean[] isone = new boolean[hits.length];
        for (int i = 0; i < hits.length; i++) {
            if (grid[hits[i][0]][hits[i][1]] == 1) {
                isone[i] = true;
                grid[hits[i][0]][hits[i][1]] = 0;
            }
        }
        int row = grid.length, col = grid[0].length;
        id = new int[row * col];
        rank = new int[row * col];
        size = new int[row * col];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            if (grid[i/col][i%col] == 1)
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
        int[] res = new int[hits.length];
        for (int i = hits.length-1; i >= 0; i--) {
            if (!isone[i]) {
                res[i] = 0;
            } else {
                int sum = 0, r = hits[i][0], c = hits[i][1];
                grid[r][c] = 1;
                size[r * col + c] = 1;
                boolean top = false;
                for (int[] d : dir) {
                    int x = r + d[0], y = c + d[1];
                    if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) {
                        if (find(x * col + y) >= col) 
                            sum += size[find(x * col + y)];
                        union(r * col + c, x * col + y);
                    }
                }
                if (find(r * col + c) < col)
                    res[i] = sum;
                else
                    res[i] = 0;
            }
        }
        return res;
    }
    
    // actually just find(n) < col is enough 
    public boolean belongsTop(int n, int col) {
        int rep = find(n);
        for (int i = 0; i < col; i++) {
            if (id[i] == rep)
                return true;
        }
        return false;
    }
    
    public void union(int i, int j) {
        int x = find(i), y = find(j);
        if (x != y) {
            // if (x < y) {
            //     id[y] = x;
            //     size[x] += size[y];
            // } else {
            //     id[x] = y;
            //     size[y] += size[x];
            // }
            if (rank[x] > rank[y]) {        // not sure whether this is correct 
                id[y] = x;                  // when tie, decide by id, otherwise still by rank 
                size[x] += size[y];         // seems, wrong, because non-top components may 
            } else if (rank[x] < rank[y]) { // get higher rank than top components 
                id[x] = y;
                size[y] += size[x];
            } else {
                if (x < y) {
                    id[y] = x;
                    size[x] += size[y];
                } else {
                    id[x] = y;
                    size[y] += size[x];
                }
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
