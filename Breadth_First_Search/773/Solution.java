class Solution {
    public int slidingPuzzle(int[][] board) {
        Set<Integer> visited = new HashSet<>();
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int start = 0;
        for (int i = 0; i < 6; i++)
            start = start * 10 + board[i/3][i%3];
        if (start == 123450)
            return 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int step = 0;
        while (!queue.isEmpty()) {
            for (int s = queue.size(); s > 0; s--) {
                int node = queue.poll();
                if (node == 123450)
                    return step;
                int[] puzzle = new int[6];
                for (int i = 5; i >= 0; i--) {
                    puzzle[i] = node % 10;
                    node /= 10;
                }
                for (int i = 0; i < 6; i++) {
                    if (puzzle[i] == 0) {
                        int row = i / 3, col = i % 3;
                        for (int[] d : dir) {
                            int r = row + d[0], c = col + d[1];
                            if (r >= 0 && r < 2 && c >= 0 && c < 3) {
                                int pos = r * 3 + c;
                                swap(puzzle, i, pos);
                                int v = helper(puzzle);
                                swap(puzzle, i, pos);
                                if (!visited.contains(v)) {
                                    queue.offer(v);
                                    visited.add(v);
                                }
                            }
                        }
                        break;
                    }
                }
            }
            step++;
        }
        return -1;
    }
    
    public void swap(int[] puzzle, int i, int j) {
        // int temp = puzzle[i];
        // puzzle[i] = puzzle[j];
        // puzzle[j] = temp;
        puzzle[i] ^= puzzle[j];
        puzzle[j] ^= puzzle[i];
        puzzle[i] ^= puzzle[j];
    }
    
    public int helper(int[] puzzle) {
        int res = 0;
        for (int p : puzzle)
            res = res * 10 + p;
        return res;
    }
}
