class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    helper(grid, i, j);
                }
            }
        }
        return res;
    }
    
    public void helper(char[][] grid, int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
            grid[i][j] = '0';
            helper(grid, i+1, j);
            helper(grid, i-1, j);
            helper(grid, i, j+1);
            helper(grid, i, j-1);
        }
    }
}


// the so-called Union Find !!! 
// noticed that after n makeSet operations, each link operation will decrease 
// the number of sets by 1 
class Solution {
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[] parent, rank;
    int total;
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int row = grid.length, col = grid[0].length;
        parent = new int[row * col];
        rank = new int[row * col];
        total = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    total++;
                    makeSet(i * col + j);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    for (int[] d : dir) {
                        int r = i + d[0], c = j + d[1];
                        if (r >= 0 && r < row && c >= 0 && c < col && grid[r][c] == '1')
                            union(i * col + j, r * col + c);
                    }
                }
            }
        }
        return total;
    }
    
    public void makeSet(int i) {
        parent[i] = i;
        rank[i] = 0;
    }
    
    public void union(int i, int j) {
        int x = findSet(i), y = findSet(j);
        if (x != y) {
            link(x, y);
            total--;
        }
    }
    
    public int findSet(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
    
    public void link(int i, int j) {
        if (rank[i] > rank[j]) {
            parent[j] = i;
        } else if (rank[i] < rank[j]) {
            parent[i] = j;
        } else {
            parent[j] = i;
            rank[i]++;
        }
    }
}
