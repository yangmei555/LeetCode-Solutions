class Solution {
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0) {
                    if (i != 0 )
                        grid[i][j] += grid[i-1][j];
                    if (j != 0)
                        grid[i][j] += grid[i][j-1];
                } else {
                    grid[i][j] += (grid[i][j-1] < grid[i-1][j] ? grid[i][j-1] : grid[i-1][j]);
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}


// be crazy to use Dijkstra ... 
class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dir = new int[][]{{0, 1}, {1, 0}};
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n1.sum - n2.sum;
            }
        });
        queue.offer(new Node(0, 0, grid[0][0]));
        int[][] memo = new int[row][col];
        for (int i = 0; i < memo.length; i++)
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        memo[0][0] = grid[0][0];
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (memo[node.row][node.col] < node.sum)
                continue;
            for (int[] d : dir) {
                int r = node.row + d[0], c = node.col + d[1];
                if (r < row && c < col && node.sum + grid[r][c] < memo[r][c]) {
                    memo[r][c] = node.sum + grid[r][c];
                    queue.offer(new Node(r, c, node.sum + grid[r][c]));
                }
            }
        }
        return memo[row-1][col-1];
    }
    
    class Node {
        int row, col, sum;
        public Node (int row, int col, int sum) {
            this.row = row;
            this.col = col;
            this.sum = sum;
        }
    }
}
