class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        int m = A.length, n = A[0].length, l = B[0].length;
        if (n != B.length)
            return null;
        boolean[] rows = new boolean[m], cols = new boolean[l];
        for (int i = 0; i < m; i++) {
            int j = 0;
            for (; j < n; j++)
                if (A[i][j] != 0)
                    break;
            if (j == n)
                rows[i] = true;
        }
        for (int i = 0; i < l; i++) {
            int j = 0;
            for (; j < n; j++)
                if (B[j][i] != 0)
                    break;
            if (j == n)
                cols[i] = true;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < l; j++) {
                if (rows[i] || cols[j]) {
                    res[i][j] = 0;
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }
}


class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        int m = A.length, n = A[0].length, l = B[0].length;
        if (n != B.length)
            return null;
        List<int[]> list1 = new LinkedList<>(), list2 = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] != 0) {
                    list1.add(new int[]{i, j});       
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                if (B[i][j] != 0)
                    list2.add(new int[]{i, j});
            }
        }
        for (int[] i1 : list1) {
            for (int[] i2 : list2) {
                if (i1[1] == i2[0]) {
                    res[i1[0]][i2[1]] += A[i1[0]][i1[1]] * B[i2[0]][i2[1]];
                }
            }
        }
        return res;
    }
}


class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        int m = A.length, n = A[0].length, l = B[0].length;
        if (n != B.length)
            return null;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < l; k++) {
                        if (B[j][k] != 0)
                            res[i][k] += A[i][j] * B[j][k];
                    }
                }
            }
        }
        return res;
    }
}
