class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int res = 0, N = A.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int m = 0; m + i < N; m++) {
                    for (int n = 0; n + j < N; n++) {
                        if (A[m][n] == 1 && B[m+i][n+j] == 1)
                            count++;
                    }
                }
                res = Math.max(res, count);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int m = 0; m + i < N; m++) {
                    for (int n = 0; n + j < N; n++) {
                        if (B[m][n] == 1 && A[m+i][n+j] == 1)
                            count++;
                    }
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}


// a more concise way, move A vertically and horizontally
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int res = 0, N = A.length;
        for (int v = 1-N; v <= N-1; v++) {
            for (int h = 1-N; h <= N-1; h++) {
                int count = 0;
                for (int i = Math.max(0, -v); i < Math.min(N, N-v); i++) {
                    for (int j = Math.max(0, -h); j < Math.min(N, N-h); j++) {
                        if (A[i+v][j+h] == 1 && B[i][j] == 1)
                            count++;
                    }
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}


// this one runs a little bit faster 
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        int[][] record = new int[2*N-1][2*N-1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] == 1) {
                    for (int m = 0; m < N; m++) {
                        for (int n = 0; n < N; n++) {
                            if (B[m][n] == 1)
                                record[m-i+N-1][n-j+N-1]++;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int[] re : record) {
            for (int r : re)
                res = Math.max(res, r);
        }
        return res;
    }
}
