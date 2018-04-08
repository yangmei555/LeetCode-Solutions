class Solution {
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int pre = 0, con = 0, res = 0;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (i != 0 && ch[i] != ch[i-1]) {
                pre = con;
                con = 1;
                res++;
            } else {
                con++;
                if (con <= pre)
                    res++;
            }
        }
        return res;
    }
}
