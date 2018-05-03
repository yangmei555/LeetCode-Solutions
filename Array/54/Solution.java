class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if (matrix.length == 0 || matrix[0].length == 0)
            return list;
        int row = matrix.length, col = matrix[0].length;
        int x = 0, y = 0, n = 0, dir = 0, h = col, v = row, walk = 0;
        while (n < row * col) {
            list.add(matrix[y][x]);
            walk++;
            if ((dir == 0 || dir == 2) && walk == h) {
                dir++;
                v--;
                walk = 0;
            } else if ((dir == 1 || dir == 3) && walk == v) {
                dir++;
                h--;
                walk = 0;
            }
            dir %= 4;
            if (dir == 0)
                x++;
            else if (dir == 1)
                y++;
            else if (dir == 2)
                x--;
            else
                y--;
            n++;
        }
        return list;
    }
}


class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if (matrix.length == 0 || matrix[0].length == 0)
            return list;
        int rs = 0, re = matrix[0].length-1;
        int cs = 0, ce = matrix.length-1;
        while (rs <= re && cs <= ce) {
            for (int i = rs; i <= re; i++)
                list.add(matrix[cs][i]);
            cs++;
            for (int i = cs; i <= ce; i++) 
                list.add(matrix[i][re]);
            re--;
            if (cs <= ce) {
                for (int i = re; i >= rs; i--)
                    list.add(matrix[ce][i]);
                ce--;
            }
            if (rs <= re) {
                for (int i = ce; i >= cs; i--)
                    list.add(matrix[i][rs]);
                rs++;
            }
        }
        return list;
    }
}
