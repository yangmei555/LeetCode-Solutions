class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            int left = 0, right = A[i].length-1;
            while (left <= right) {
                if (A[i][left] == A[i][right]) 
                    A[i][left] = A[i][right] = 1 - A[i][left];
                left++;
                right--;
            }
        }
        return A;
    }
}
