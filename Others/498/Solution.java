class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return new int[0];
        int[][] dir = new int[][]{{-1, 1}, {1, 0}, {1, -1}, {0, 1}};
        int x = 0, y = 0, index = 0, step = 1;
        int[] res = new int[matrix.length * matrix[0].length];
        while (index < res.length) {
            for (int i = 0; i < dir.length; i += 2, step++) {
                for (int j = 0; j < step; j++) {
                    if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
                        res[index++] = matrix[x][y];
                        if (index == res.length)
                            return res;
                    }
                    x += dir[i][0];
                    y += dir[i][1];
                }
                x += dir[i+1][0];
                y += dir[i+1][1];
            }
        }
        return res;
    }
}


// correct the point once it goes outside the matrix 
// much faster than the above solution 
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return new int[0];
        int[][] dir = new int[][]{{-1, 1}, {1, 0}, {1, -1}, {0, 1}};
        int x = 0, y = 0, index = 0, step = 1;
        int[] res = new int[matrix.length * matrix[0].length];
        while (index < res.length) {
            for (int i = 0; i < dir.length; i += 2, step++) {
                for (int j = 0; j < step; j++) {
                    res[index++] = matrix[x][y];
                    if (index == res.length)
                        return res;
                    x += dir[i][0];
                    y += dir[i][1];
                    if (!(x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length))
                        break;
                }
                x += dir[i+1][0];
                y += dir[i+1][1];
                if (y >= matrix[0].length) {
                    x++;
                    y--;
                } else if (x >= matrix.length) {
                    x--;
                    y++;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new int[0];
        int[] res = new int[matrix.length * matrix[0].length];
        int index = 0, x = 0, y = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[x][y];
            if ((x + y) % 2 == 0) {
                if (x == 0 && y != matrix[0].length-1)
                    y++;
                else if (y == matrix[0].length-1) 
                    x++;
                else {
                    x--;
                    y++;
                }
            } else {
                if (y == 0 && x != matrix.length-1)
                    x++;
                else if (x == matrix.length-1)
                    y++;
                else {
                    x++;
                    y--;
                }
            }
        }
        return res;
    }
}


// amazing ideas, sort the indices directly 
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0)
            return new int[0];
        Integer[] indices = new Integer[matrix.length * matrix[0].length];
        for (int i = 0; i < indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                int row1 = i1 / matrix[0].length, col1 = i1 % matrix[0].length;
                int row2 = i2 / matrix[0].length, col2 = i2 % matrix[0].length;
                if (row1 + col1 == row2 + col2) {
                    if ((row1 + col1) % 2 == 0)
                        return col1 - col2;
                    else
                        return row1 - row2;
                } else {
                    return row1 + col1 - row2 - col2;
                }
            }    
        });
        int[] res = new int[indices.length];
        for (int i = 0; i < res.length; i++)
            res[i] = matrix[indices[i] / matrix[0].length][indices[i] % matrix[0].length];
        return res;
    }
}
