class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int cur = 1, pre = 1, temp = 0;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            temp = cur;
            if (ch[i] == '0')
                cur = 0;
            if (i > 0 && ((ch[i-1] == '1' && ch[i] >= '0' && ch[i] <= '9') || 
                        (ch[i-1] == '2' && ch[i] >= '0' && ch[i] <= '6')))
                cur += pre;
            pre = temp;
        }
        return cur;
    }
}
