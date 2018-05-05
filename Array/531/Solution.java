class Solution {
    public int findLonelyPixel(char[][] picture) {
        if (picture.length == 0 || picture[0].length == 0)
            return 0;
        List<Integer> list = new LinkedList<>(), forbid = new LinkedList<>();
        for (int i = 0; i < picture.length; i++) {
            int col = -1, j = 0;
            for (; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    if (col == -1)
                        col = j;
                    else
                        break;
                }
            }
            if (j == picture[0].length && col != -1) {
                if (list.contains(col)) {
                    list.remove((Integer)col);
                    forbid.add(col);
                } else if (!forbid.contains(col)) {
                    list.add(col);
                }
            }
        }
        int res = 0;
        for (int col : list) {
            int i = 0, row = -1;
            while (i < picture.length) {
                if (picture[i][col] == 'B') {
                    if (row == -1)
                        row = i;
                    else
                        break;
                }
                i++;
            }
            if (i == picture.length)
                res++;
        }
        return res;
    }
}


class Solution {
    public int findLonelyPixel(char[][] picture) {
        if (picture.length == 0 || picture[0].length == 0)
            return 0;
        int row = picture.length, col = picture[0].length;
        int[] rc = new int[row], cc = new int[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (picture[i][j] == 'B') {
                    rc[i]++;
                    cc[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < row; i++) {
            if (rc[i] == 1) {
                for (int j = 0; j < col; j++) {
                    if (picture[i][j] == 'B') {
                        if (cc[j] == 1)
                            res++;
                        break;
                    }
                }
            }
        }
        return res;
    }
}
