class Excel {
    
    int[][] grid;
    String[][][] formula;
    public Excel(int H, char W) {
        grid = new int[H][W-'A'+1];
        formula = new String[H][W-'A'+1][];
    }
    
    public void set(int r, char c, int v) {
        grid[r-1][c-'A'] = v;
        formula[r-1][c-'A'] = null;
    }
    
    public int get(int r, char c) {
        if (formula[r-1][c-'A'] == null)
            return grid[r-1][c-'A'];
        else
            return sum(r, c, formula[r-1][c-'A']);
    }
    
    public int sum(int r, char c, String[] strs) {
        formula[r-1][c-'A'] = strs;
        int res = 0;
        for (String str : strs) {
            int index = -1;
            if ((index = str.indexOf(':')) == -1) {
                int[] pos = helper(str);
                res += get(pos[0]+1, (char)('A'+pos[1]));
            } else {
                int[] pos1 = helper(str.substring(0, index));
                int[] pos2 = helper(str.substring(index+1));
                res += rangeSum(pos1[0], pos1[1], pos2[0], pos2[1]);
            }
        }
        return grid[r-1][c-'A'] = res;
    }
    
    public int rangeSum(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++)
                res += get(i+1, (char)('A'+j));
        }
        return res;
    }
    
    public int[] helper(String str) {
        int col = str.charAt(0) - 'A';
        int row = 0;
        for (int i = 1; i < str.length(); i++)
            row = row * 10 + str.charAt(i) - '0';
        row--;
        return new int[]{row, col};
    }
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(H, W);
 * obj.set(r,c,v);
 * int param_2 = obj.get(r,c);
 * int param_3 = obj.sum(r,c,strs);
 */
