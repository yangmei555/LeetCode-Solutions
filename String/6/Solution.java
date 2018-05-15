class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        int len = (s.length()/(2*numRows-2)+1)*(numRows-1);
        char[][] res = new char[numRows][len];
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            int a = i % (2*numRows-2), b = i / (2*numRows-2) * (numRows-1);
            int row = a < numRows ? a : (2*numRows-2) - a;
            int col = b + (a < numRows ? 0 : a-numRows+1);
            res[row][col] = ch[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < len; j++)
                if (res[i][j] != 0)
                    sb.append(res[i][j]);
        }
        return sb.toString();
    }
}


class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        StringBuilder sb = new StringBuilder();
        char[] ch = s.toCharArray();
        int dist = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < ch.length; j += dist) {
                sb.append(ch[j]);
                if (i != 0 && i != numRows - 1 && j+2*(numRows-1-i) < ch.length)
                    sb.append(ch[j+2*(numRows-1-i)]);
            }
        }
        return sb.toString();
    }
}
