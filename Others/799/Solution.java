class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] cups = new double[query_row+1][query_row+1];
        cups[0][0] = poured;
        for (int i = 0; i < query_row; i++) {
            for (int j = 0; j <= i; j++) {
                if (cups[i][j] > 1) {
                    cups[i+1][j] += (cups[i][j]-1) / 2;
                    cups[i+1][j+1] += (cups[i][j]-1) / 2;
                    cups[i][j] = 1;
                }
            }
        }
        return cups[query_row][query_glass] > 1 ? 1 : cups[query_row][query_glass];
    }
}
