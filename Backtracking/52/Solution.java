class Solution {
    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1], diag2 = new boolean[2*n-1];
        return helper(0, col, diag1, diag2);
    }
    
    public int helper(int rowIndex, boolean[] col, boolean[] diag1, boolean[] diag2) {
        if (rowIndex == col.length) {
            return 1;
        } else {
            int n = col.length;
            int res = 0;
            for (int j = 0; j < col.length; j++) {
                if (!col[j] && !diag1[rowIndex+j] && !diag2[rowIndex-j+n-1]) {
                    col[j] = true;
                    diag1[rowIndex+j] = true;
                    diag2[rowIndex-j+n-1] = true;
                    res += helper(rowIndex+1, col, diag1, diag2);
                    col[j] = false;
                    diag1[rowIndex+j] = false;
                    diag2[rowIndex-j+n-1] = false;
                }
            }
            return res;
        }
    }
}
