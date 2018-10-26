// somewhat like Dijkstra, quite slow ... 
class Solution {
    public int cherryPickup(int[][] grid) {
        int[][] dir = new int[][]{{0, 1}, {1, 0}};
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n2.count - n1.count;
            }
        });
        int res = 0, N = grid.length;
        if (N == 1)
            return grid[0][0];
        queue.offer(new Node(0, 0, 0, 0, grid[0][0]));
        int[][][][] memo = new int[N][N][N][N];
        for (int[][][] mem : memo) {
            for (int[][] me : mem) {
                for (int[] m : me)
                    Arrays.fill(m, -1);
            }
        }
        memo[0][0][0][0] = grid[0][0];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row1 = node.row1, col1 = node.col1, row2 = node.row2, col2 = node.col2;
            if (memo[row1][col1][row2][col2] > node.count)
                continue;
            for (int [] d1 : dir) {
                int r1 = row1 + d1[0], c1 = col1 + d1[1];
                if (r1 == N || c1 == N || grid[r1][c1] == -1)
                    continue;
                for (int[] d2 : dir) {
                    int r2 = row2 + d2[0], c2 = col2 + d2[1];
                    if (r2 == N || c2 == N || grid[r2][c2] == -1)
                        continue;
                    int count = node.count;
                    if (grid[r1][c1] == 1)
                        count++;
                    if ((r1 != r2 || c1 != c2) && grid[r2][c2] == 1)
                        count++;
                    if (r1 * N + c1 >= r2 * N + c2 && memo[r1][c1][r2][c2] < count) {
                        memo[r1][c1][r2][c2] = count;
                        queue.offer(new Node(r1, c1, r2, c2, count));
                    }
                }
            }
        }
        return Math.max(0, memo[N-1][N-1][N-1][N-1]);
    }
    
    class Node {
        int row1, col1, row2, col2, count;
        public Node(int row1, int col1, int row2, int col2, int count) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.count = count;
        }
    }
}


// time complexity O(N^3), space complexity O(N^4) 
class Solution {
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int[][] dir = new int[][]{{-1, 0}, {0, -1}};
        int[][][][] dp = new int[N][N][N][N]; 
        for (int[][][] dp1 : dp) {
            for (int[][] dp2 : dp1) {
                for (int[] dp3 : dp2)
                    Arrays.fill(dp3, -1);
            }
        }
        dp[0][0][0][0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == -1)
                    continue;
                int start = Math.max(0, i + j - N + 1);
                // let ii * N + jj <= i * N + j 
                for (int ii = start; ii <= i; ii++) {
                    int jj = i + j - ii;
                    if (grid[ii][jj] == -1)
                        continue;
                    for (int[] d1 : dir) {
                        if (i+d1[0] >= 0 && j+d1[1] >= 0 && grid[i+d1[0]][j+d1[1]] != -1) {
                            for (int[] d2 : dir) {
                                if (ii+d2[0] >= 0 && jj+d2[1] >= 0 && 
                                                        grid[ii+d2[0]][jj+d2[1]] != -1) {
                                    dp[i][j][ii][jj] = Math.max(dp[i][j][ii][jj], 
                                                        dp[i+d1[0]][j+d1[1]][ii+d2[0]][jj+d2[1]]);
                                }
                            }
                        }
                    }
                    if (dp[i][j][ii][jj] != -1) {
                        if (grid[i][j] == 1)
                            dp[i][j][ii][jj]++;
                        if ((ii != i || jj != j) && grid[ii][jj] == 1)
                            dp[i][j][ii][jj]++;
                    }
                }
            }
        }
        return Math.max(0, dp[N-1][N-1][N-1][N-1]);
    }
}


// notice that, i, j, ii can determine jj ! so only O(N^3) space is enough 
// also notice that if i + j == ii + jj then (i, j) and (ii, jj) are on the same line !!!  
class Solution {
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int[][] dir = new int[][]{{-1, 0}, {0, -1}};
        int[][][] dp = new int[N][N][N]; 
        for (int[][] dp1 : dp) {
            for (int[] dp2 : dp1) 
                Arrays.fill(dp2, -1);
        }
        dp[0][0][0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == -1)
                    continue;
                int start = Math.max(0, i + j - N + 1);
                for (int ii = start; ii <= i; ii++) {
                    int jj = i + j - ii;
                    if (grid[ii][jj] == -1)
                        continue;
                    for (int[] d1 : dir) {
                        if (i+d1[0] >= 0 && j+d1[1] >= 0 && grid[i+d1[0]][j+d1[1]] != -1) {
                            for (int[] d2 : dir) {
                                if (ii+d2[0] >= 0 && jj+d2[1] >= 0 && 
                                                        grid[ii+d2[0]][jj+d2[1]] != -1) 
                                    dp[i][j][ii] = Math.max(dp[i][j][ii], 
                                                            dp[i+d1[0]][j+d1[1]][ii+d2[0]]);
                                
                            }
                        }
                    }
                    if (dp[i][j][ii] != -1) {
                        if (grid[i][j] == 1)
                            dp[i][j][ii]++;
                        if (ii != i && grid[ii][jj] == 1)
                            dp[i][j][ii]++;
                    }
                }
            }
        }
        return Math.max(0, dp[N-1][N-1][N-1]);
    }
}


// space complexity O(N^2) 
class Solution {
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int[][] dir = new int[][]{{-1, 0}, {0, -1}};
        int[][][] dp = new int[2][N][N]; 
        for (int[][] dp1 : dp) {
            for (int[] dp2 : dp1)
                Arrays.fill(dp2, -1);
        }
        dp[0][0][0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == -1) 
                    continue;
                int start = Math.max(0, i + j - N + 1);
                for (int ii = start; ii <= i; ii++) {
                    int jj = i + j - ii;
                    if (grid[ii][jj] == -1)
                        continue;
                    if (i != 0 || j != 0)
                        dp[0][j][ii] = -1;
                    for (int[] d1 : dir) {
                        if (i+d1[0] >= 0 && j+d1[1] >= 0 && grid[i+d1[0]][j+d1[1]] != -1) {
                            for (int[] d2 : dir) {
                                if (ii+d2[0] >= 0 && jj+d2[1] >= 0 && 
                                                        grid[ii+d2[0]][jj+d2[1]] != -1) {
                                    dp[0][j][ii] = Math.max(dp[0][j][ii], 
                                d1[0] == -1 ? dp[1][j+d1[1]][ii+d2[0]] : dp[0][j+d1[1]][ii+d2[0]]);
                                }
                            }
                        }
                    }
                    if (dp[0][j][ii] != -1) {
                        if (grid[i][j] == 1)
                            dp[0][j][ii]++;
                        if (ii != i && grid[ii][jj] == 1)
                            dp[0][j][ii]++;
                    }
                }
            }
            for (int j = 0; j < N; j++)
                dp[1][j] = dp[0][j].clone();
        }
        return Math.max(0, dp[0][N-1][N-1]);
    }
}


// a more concise O(N^2) space complexity solution 
class Solution {
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int[][] dir = new int[][]{ {0, -1}, {-1, 0}};
        int[][] dp = new int[N][N]; 
        for (int[] dp1 : dp) 
            Arrays.fill(dp1, -1);
        dp[0][0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == -1) 
                    continue;
                int start = Math.max(0, i + j - N + 1);
                for (int ii = i; ii >= start; ii--) {
                    int jj = i + j - ii;
                    if (grid[ii][jj] == -1) 
                        continue;
                    int cand = -1;
                    for (int[] d1 : dir) {
                        if (i+d1[0] >= 0 && j+d1[1] >= 0 && grid[i+d1[0]][j+d1[1]] != -1) {
                            for (int[] d2 : dir) {
                                if (ii+d2[0] >= 0 && jj+d2[1] >= 0 && 
                                                        grid[ii+d2[0]][jj+d2[1]] != -1) {
                                    cand = Math.max(cand, dp[j+d1[1]][ii+d2[0]]);
                                }
                            }
                        }
                    }
                    if (cand != -1) {
                        dp[j][ii] = cand;
                        if (grid[i][j] == 1)
                            dp[j][ii]++;
                        if (ii != i && grid[ii][jj] == 1)
                            dp[j][ii]++;
                    } else {
                        dp[j][ii] = i + j == 0 ? grid[i][j] : -1;
                    }
                }
            }
        }
        return Math.max(0, dp[N-1][N-1]);
    }
}
