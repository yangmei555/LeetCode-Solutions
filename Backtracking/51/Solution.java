class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] ch = new char[n][n];
        for (int i = 0; i < ch.length; i++)
            Arrays.fill(ch[i], '.');
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1], diag2 = new boolean[2*n-1];
        List<List<String>> res = new LinkedList<>();
        helper(ch, 0, col, diag1, diag2, res);
        return res;
    }
    
    public void helper(char[][] ch, int rowIndex, boolean[] col, boolean[] diag1, boolean[] diag2, 
                                                                            List<List<String>> res) {
        if (rowIndex == ch.length) {
            List<String> list = new LinkedList<>();
            for (int i = 0; i < ch.length; i++) 
                list.add(new String(ch[i]));
            res.add(list);   
        } else {
            int n = ch.length;
            for (int j = 0; j < ch[rowIndex].length; j++) {
                if (!col[j] && !diag1[rowIndex+j] && !diag2[rowIndex-j+n-1]) {
                    col[j] = true;
                    diag1[rowIndex+j] = true;
                    diag2[rowIndex-j+n-1] = true;
                    ch[rowIndex][j] = 'Q';
                    helper(ch, rowIndex+1, col, diag1, diag2, res);
                    col[j] = false;
                    diag1[rowIndex+j] = false;
                    diag2[rowIndex-j+n-1] = false;
                    ch[rowIndex][j] = '.';
                }
            }
        }
    }
}
