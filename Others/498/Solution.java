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
