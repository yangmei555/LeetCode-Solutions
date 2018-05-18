class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        char[][] ch = new char[strs.length][];
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            ch[i] = strs[i].toCharArray();
            len = len < ch[i].length ? len : ch[i].length;
        }
        int i = 0;
        for (; i < len; i++) {
            char c = ch[0][i];
            int j = 0;
            for (; j < strs.length; j++)
                if (ch[j][i] != c)
                    break;
            if (j != strs.length)
                break;
        }
        return strs[0].substring(0, i);
    }
}
