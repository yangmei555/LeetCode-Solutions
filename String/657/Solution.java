class Solution {
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() == 0)
            return true;
        int u = 0, r = 0;
        char[] ch = moves.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'U')
                u++;
            else if (ch[i] == 'D')
                u--;
            else if (ch[i] == 'L')
                r--;
            else if (ch[i] == 'R')
                r++;
        }
        return u == 0 && r == 0;
    }
}
