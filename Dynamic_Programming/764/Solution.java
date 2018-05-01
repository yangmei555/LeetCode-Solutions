class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(matrix[i], 1);
        for (int[] m : mines)
            matrix[m[0]][m[1]] = 0;
        int[][][] index = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            int ones = 0;
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    ones++;
                    index[i][j][0] = ones;
                    if (i == 0) {
                        index[i][j][1] = 1;
                    } else {
                        index[i][j][1] = index[i-1][j][1] + 1;
                    }
                } else {
                    ones = 0;
                }
            }
        }
        int res = 0, local1 = 0, local2 = 0, local = 0;
        for (int i = N-1; i >= 0; i--) {
            int ones = 0;
            for (int j = N-1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    ones++;
                    index[i][j][2] = ones;
                    if (i == N-1) {
                        index[i][j][3] = 1;
                    } else {
                        index[i][j][3] = index[i+1][j][3] + 1;
                    }
                    local1 = index[i][j][0] < index[i][j][1] ? index[i][j][0] : index[i][j][1];
                    local2 = index[i][j][2] < index[i][j][3] ? index[i][j][2] : index[i][j][3];
                    local = local1 < local2 ? local1 : local2;
                    res = res > local ? res : local;
                } else {
                    ones = 0;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) 
            Arrays.fill(matrix[i], Integer.MAX_VALUE);
        for (int[] m : mines)
            matrix[m[0]][m[1]] = 0;
        for (int i = 0; i < N; i++) {
            int up = 0, down = 0, left = 0, right = 0;
            for (int j = 0; j < N; j++) {
                int ii = j, jj = i, rs = N-1-j, ds = N-1-ii;
                if (matrix[i][j] != 0) {
                    left++;
                    matrix[i][j] = matrix[i][j] < left ? matrix[i][j] : left;
                } else {
                    left = 0;
                }
                if (matrix[i][rs] != 0) {
                    right++;
                    matrix[i][rs] = matrix[i][rs] < right ? matrix[i][rs] : right;
                } else {
                    right = 0;
                }
                if (matrix[ii][jj] != 0) {
                    up++;
                    matrix[ii][jj] = matrix[ii][jj] < up ? matrix[ii][jj] : up;
                } else {
                    up = 0;
                }
                if (matrix[ds][jj] != 0) {
                    down++;
                    matrix[ds][jj] = matrix[ds][jj] < down ? matrix[ds][jj] : down;
                } else {
                    down = 0;
                }
            }
        }
        int res = 0;
        for (int[] m : matrix)
            for (int n : m)
                if (n > res)
                    res = n;
        return res;
    }
}
