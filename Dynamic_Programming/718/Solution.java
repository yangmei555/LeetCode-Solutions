class Solution {
    public int findLength(int[] A, int[] B) {
        int res = 0;
        int[][] index = new int[A.length+1][B.length+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    index[i+1][j+1] = index[i][j] + 1;
                    res = index[i+1][j+1] > res ? index[i+1][j+1] : res;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int findLength(int[] A, int[] B) {
        int res = 0, temp1 = 0, temp2 = 0;
        int[] index = new int[B.length+1];
        for (int i = 0; i < A.length; i++) {
            temp1 = index[0];
            for (int j = 0; j < B.length; j++) {
                temp2 = index[j+1];
                if (A[i] == B[j]) {
                    index[j+1] = temp1 + 1;
                    res = index[j+1] > res ? index[j+1] : res;
                } else {
                    index[j+1] = 0;
                }
                temp1 = temp2;
            }
        }
        return res;
    }
}
