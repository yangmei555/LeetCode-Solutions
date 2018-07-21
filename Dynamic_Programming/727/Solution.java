class Solution {
    public String minWindow(String S, String T) {
        if (S.length() < T.length())
            return "";
        if (S.length() == T.length())
            return S.equals(T) ? S : "";
        char[] s = S.toCharArray(), t = T.toCharArray();
        int[][] len = new int[s.length][t.length];
        int index = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < t.length; j++) {
                if (j > i)
                    break;
                if (s[i] == t[j]) 
                    len[i][j] = j == 0 ? 1 : (len[i-1][j-1] == 0 ? 0 : len[i-1][j-1] + 1);
                else 
                    len[i][j] = i == 0 || len[i-1][j] == 0 ? 0 : len[i-1][j] + 1;
            }
            if (len[i][t.length-1] != 0 && len[i][t.length-1] < min) {
                min = len[i][t.length-1];
                index = i;
            }
        }
        if (index == -1)
            return "";
        else
            return S.substring(index+1-min, index+1);
    }
}


class Solution {
    public String minWindow(String S, String T) {
        if (S.length() < T.length())
            return "";
        if (S.length() == T.length())
            return S.equals(T) ? S : "";
        char[] s = S.toCharArray(), t = T.toCharArray();
        int[] len = new int[t.length];
        int index = -1, min = Integer.MAX_VALUE, temp1 = 0, temp2 = 0;
        for (int i = 0; i < s.length; i++) {
            temp1 = len[0];
            for (int j = 0; j < t.length; j++) {
                temp2 = len[j];
                if (j > i)
                    break;
                if (s[i] == t[j]) 
                    len[j] = j == 0 ? 1 : (temp1 == 0 ? 0 : temp1 + 1);
                else 
                    len[j] = i == 0 || len[j] == 0 ? 0 : len[j] + 1;
                temp1 = temp2;
            }
            if (len[t.length-1] != 0 && len[t.length-1] < min) {
                min = len[t.length-1];
                index = i;
            }
        }
        if (index == -1)
            return "";
        else
            return S.substring(index+1-min, index+1);
    }
}
