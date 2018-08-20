class Solution {
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    helper(grid, i, j, sb);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    
    public void helper(int[][] grid, int i, int j, StringBuilder sb) {
        int len = sb.length();
        grid[i][j] = 0;
        if (i-1 >= 0 && grid[i-1][j] == 1) {
            sb.append('u');
            helper(grid, i-1, j, sb);
        }
        if (i+1 < grid.length && grid[i+1][j] == 1) {
            sb.append('d');
            helper(grid, i+1, j, sb);
        }
        if (j-1 >= 0 && grid[i][j-1] == 1) {
            sb.append('l');
            helper(grid, i, j-1, sb);
        }
        if (j+1 < grid[0].length && grid[i][j+1] == 1) {
            sb.append('r');
            helper(grid, i, j+1, sb);
        }
        if (len > 0) {
            char c = sb.charAt(len-1);
            if (c == 'u')
                sb.append('d');
            if (c == 'd')
                sb.append('u');
            if (c == 'l')
                sb.append('r');
            if (c == 'r')
                sb.append('l');
        }
    }
}


class Solution {
    public int numDistinctIslands(int[][] grid) {
        Set<List<List<Integer>>> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<List<Integer>> list = new LinkedList<>();
                    helper(grid, i, j, i, j, list);
                    set.add(list);
                }
            }
        }
        return set.size();
    }
    
    public void helper(int[][] grid, int i, int j, int x, int y, List<List<Integer>> list) {
        grid[i][j] = 0;
        list.add(Arrays.asList(i-x, j-y));
        if (i-1 >= 0 && grid[i-1][j] == 1) 
            helper(grid, i-1, j, x, y, list);
        if (i+1 < grid.length && grid[i+1][j] == 1) 
            helper(grid, i+1, j, x, y, list);
        if (j-1 >= 0 && grid[i][j-1] == 1) 
            helper(grid, i, j-1, x, y, list);
        if (j+1 < grid[0].length && grid[i][j+1] == 1) 
            helper(grid, i, j+1, x, y, list);
    }
}
