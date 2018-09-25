class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new LinkedList<>(), cols = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    rows.add(i);
            }
        }
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1)
                    cols.add(j);
            }
        }
        int mid = rows.get((rows.size() - 1) / 2);
        int res = 0;
        for (int r : rows)
            res += Math.abs(r - mid);
        mid = cols.get((cols.size() - 1) / 2);
        for (int c : cols)
            res += Math.abs(c - mid);
        return res;
    }
}
