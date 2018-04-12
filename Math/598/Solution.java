class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        int row = m, col = n;
        for (int[] i : ops) {
            if (i[0] < row)
                row = i[0];
            if (i[1] < col)
                col = i[1];
        }
        return row * col;
    }
}
