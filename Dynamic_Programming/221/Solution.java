class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int res = 0, temp1 = 0, temp2 = 0;
        int[] index = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp2 = index[j];
                if (matrix[i][j] == '0') {
                    index[j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        index[j] = 1;
                    } else {
                        index[j] = (index[j-1] < temp1 ? index[j-1] : temp1) < index[j] ? 
                                    (index[j-1] < temp1 ? index[j-1] : temp1) : index[j];
                        index[j]++;
                    }
                    res = res > index[j] * index[j] ? res : index[j] * index[j];
                }
                temp1 = temp2;
            }
        }
        return res;
    }
}
