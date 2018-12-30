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


class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3)
            return 0;
        int res = 0, diff = A[1] - A[0], count = 1;
        for (int i = 2; i < A.length; i++) {
            if (diff == A[i] - A[i-1]) {
                res += count;
                count++;
            } else {
                diff = A[i] - A[i-1];
                count = 1;
            }
        }
        return res;
    }
}


class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0, count = 2;
        for (int i = 2; i < A.length; i++) {
            if (A[i-2] + A[i] == A[i-1] * 2)
                count++;
            else
                count = 2;
            res += count - 2;
        }
        return res;
    }
}
