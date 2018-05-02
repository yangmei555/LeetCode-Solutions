class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length, col = matrix[0].length;
        int i = 0, j = 0;
        while (i < row && target > matrix[i][col-1])
            i++;
        if (i == row)
            return false;
        int left = 0, right = col-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[i][mid] == target)
                return true;
            else if (matrix[i][mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }
}


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int r = mid / col, c = mid % col;
            if (matrix[r][c] == target)
                return true;
            else if (matrix[r][c] > target)
                right = mid - 1;
            else 
                left = mid + 1;
        }
        return false;
    }
}
