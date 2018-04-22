class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3)
            return 0;
        int len = A.length, res = 0;
        boolean[][] track = new boolean[len][len];
        for (int l = 3; l <= len; l++) {
            for (int i = 0; i < len + 1 - l; i++) {
                int j = i + l - 1;
                if (l == 3) {
                    if (A[i+1] - A[i] == A[j] - A[j-1]) {
                        res++;
                        track[i][j] = true;
                    }
                } else {
                    if (track[i+1][j] && track[i][j-1]) {
                        res++;
                        track[i][j] = true;
                    }
                }
            }
        }
        return res;
    }
}


class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        if (len < 3)
            return 0;
        int diff = A[1] - A[0], cont = 1, res = 0;
        for (int i = 2; i <= len; i++) {
            if (i != len && A[i] - A[i-1] == diff) {
                cont++;
            } else {
                res += cont * (cont - 1) / 2;
                if (i != len) {
                    diff = A[i] - A[i-1];
                    cont = 1;
                }
            }
        }
        return res;
    }
}