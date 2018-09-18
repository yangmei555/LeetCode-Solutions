// view the matrix as a binary search tree, if standing at top right or bottom left 
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = 0, col = matrix[0].length-1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] < target)
                row++;
            else
                col--;
        }
        return false;
    }
}


// another way of traversing the matrix 
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length-1, col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] < target)
                col++;
            else
                row--;
        }
        return false;
    }
}


// binary search line by line 
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int left = 0, right = matrix[i].length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (matrix[i][mid] < target)
                    left = mid + 1;
                else
                    right = mid;
            }
            if (left < matrix[i].length && matrix[i][left] == target)
                return true;
        }    
        return false;
    }
}


// another way of writing binary search 
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int left = 0, right = matrix[i].length-1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (matrix[i][mid] < target)
                    left = mid + 1;
                else if (matrix[i][mid] > target)
                    right = mid - 1;
                else
                    return true;
            }
        }    
        return false;
    }
}


// divide and conquer 
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        return helper(matrix, target, 0, 0, matrix.length-1, matrix[0].length-1);
    }
    
    public boolean helper(int[][] matrix, int target, int row1, int col1, int row2, int col2) {
        if (row1 > row2 || col1 > col2)
            return false;
        int mid = (col1 + col2) / 2;
        int index = row1;
        while (index <= row2) {
            if (matrix[index][mid] == target)
                return true;
            else if (matrix[index][mid] > target)
                break;
            index++;
        }
        return helper(matrix, target, index, col1, row2, mid-1) || 
                helper(matrix, target, row1, mid+1, index-1, col2);
    }
}
