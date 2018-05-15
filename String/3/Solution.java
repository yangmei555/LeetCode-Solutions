class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        char[] ch = s.toCharArray();
        Integer[] index = new Integer[256];
        int res = Integer.MIN_VALUE, start = 0;
        for (int i = 0; i <= ch.length; i++) {
            if (i != ch.length && index[ch[i]] == null) {
                index[ch[i]] = i;
            } else {
                res = res > i-start ? res : i-start;
                if (i != ch.length) {
                    start = start > index[ch[i]] + 1 ? start : index[ch[i]] + 1;
                    index[ch[i]] = i;
                }
            }
        }
        return res;
    }
}
