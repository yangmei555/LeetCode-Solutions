class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        int row = forest.size(), col = forest.get(0).size();
        int[][] indices = new int[row*col][], matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = forest.get(i).get(j);
                indices[i*col+j] = new int[]{i, j};
            }
        }
        Arrays.sort(indices, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return matrix[i1[0]][i1[1]] - matrix[i2[0]][i2[1]];
            }
        });
        int ptr = 0;
        while (ptr < row * col && matrix[indices[ptr][0]][indices[ptr][1]] <= 1)
            ptr++;
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.offer(new int[]{0, 0});
        int step = 0;
        boolean reset = false;
        while (!queue.isEmpty()) {
            for (int s = queue.size(); s > 0; s--) {
                int[] node = queue.poll();
                if (node[0] == indices[ptr][0] && node[1] == indices[ptr][1]) {
                    reset = true;
                    break;
                }
                for (int[] d : dir) {
                    int r = node[0] + d[0], c = node[1] + d[1];
                    if (r >= 0 && r < row && c >= 0 && c < col && !visited[r][c] && 
                                                                    matrix[r][c] != 0) {
                        visited[r][c] = true;
                        queue.offer(new int[]{r, c});
                    }
                }
            }
            if (reset) {
                queue.clear();
                visited = new boolean[row][col];
                queue.offer(new int[]{indices[ptr][0], indices[ptr][1]});
                visited[indices[ptr][0]][indices[ptr][1]] = true;
                ptr++;
                if (ptr == row * col)
                    return step;
                reset = false;
            } else {
                step++;
            }
        }
        return -1;
    }
}
