class Solution {
    int[] root, rank;
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int containVirus(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        boolean[][] wallRight = new boolean[row][col-1];
        boolean[][] wallDown = new boolean[row-1][col];
        root = new int[row*col];
        rank = new int[row*col];
        for (int i = 0; i < root.length; i++)
            root[i] = i;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    for (int[] d : dir) {
                        int r = i + d[0], c = j + d[1];
                        if (r >= 0 && r < row && c >= 0 && c < col && grid[r][c] == 1) {
                            union(i*col+j, r*col+c);
                        }
                    }
                }
            }
        }
        Set<Integer>[][] virusRoot = new Set[row][col];
        boolean[] control = new boolean[row*col];
        int quarantine = -1, walls = 0;
        while ((quarantine = getQuarantine(grid, virusRoot, control)) != -1) {
            control[quarantine] = true;
            walls += process(grid, quarantine, wallRight, wallDown, virusRoot, control);
        }
        return walls;
    }
    
    public int process(int[][] grid, int quarantine, boolean[][] wallRight, boolean[][] wallDown, 
                                                    Set<Integer>[][] virusRoot, boolean[] control) {
        int res = 0, row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    if (find(i*col+j) == quarantine) {
                        if (i-1 >= 0 && virusRoot[i-1][j].contains(quarantine) && 
                                                                !wallDown[i-1][j]) {
                            wallDown[i-1][j] = true;
                            res++;
                        }
                        if (j-1 >= 0 && virusRoot[i][j-1].contains(quarantine) && 
                                                                !wallRight[i][j-1]) {
                            wallRight[i][j-1] = true;
                            res++;
                        }
                        if (i+1 < row && virusRoot[i+1][j].contains(quarantine) && 
                                                                !wallDown[i][j]) {
                            wallDown[i][j] = true;
                            res++;
                        }
                        if (j+1 < col && virusRoot[i][j+1].contains(quarantine) && 
                                                                !wallRight[i][j]) {
                            wallRight[i][j] = true;
                            res++;
                        }
                    } 
                } else {
                    boolean flag = false;
                    for (int r : virusRoot[i][j]) {
                        if (find(r) != quarantine) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        grid[i][j] = 1;
                        for (int[] d : dir) {
                            int r = i + d[0], c = j + d[1];
                            if (r >= 0 && r < row && c >= 0 && c < col && grid[r][c] == 1 && 
                                                                !control[find(r*col+c)]) {
                                union(i*col+j, r*col+c);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
    
    public int getQuarantine(int[][] grid, Set<Integer>[][] virusRoot, boolean[] control) {
        int res = -1, row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                virusRoot[i][j] = new HashSet<>();
        }
        int[] count = new int[row*col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int id = find(i*col+j);
                if (grid[i][j] == 1 && !control[id]) {
                    for (int[] d : dir) {
                        int r = i + d[0], c = j + d[1];
                        if (r >= 0 && r < row && c >= 0 && c < col && grid[r][c] == 0) {
                            if (virusRoot[r][c].add(id)) {
                                count[id]++;
                            }
                        }
                    }
                    if (res == -1 || count[res] < count[id])
                        res = id;
                }
            }
        }
        return res;
    }
    
    public void union(int i, int j) {
        int root1 = find(i), root2 = find(j);
        if (root1 != root2) {
            if (rank[root1] > rank[root2]) {
                root[root2] = root1;
            } else if (rank[root1] < rank[root2]) {
                root[root1] = root2;
            } else {
                root[root2] = root1;
                rank[root1]++;
            }
        }
    }
    
    public int find(int i) {
        while (root[i] != i) {
            root[i] = root[root[i]];
            i = root[i];
        }
        return i;
    }
}
