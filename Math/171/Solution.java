class Solution {
    public int titleToNumber(String s) {
        char[] ch = s.toCharArray();
        int base = 1;
        int res = 0;
        for (int i = ch.length - 1; i >= 0; i--) {
            res += (ch[i] - 'A' + 1) * base;
            base *= 26;
        }
        return res;
    }
}
