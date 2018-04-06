class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0)
            return new String();
        char[] ch = s.toCharArray();
        char temp;
        int index = 0;
        while (index < ch.length) {
            int start = index;
            int end = (index + k - 1 < ch.length) ? index + k - 1 : ch.length - 1;
            while (start < end) {
                temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;
                start++;
                end--;
            }
            index += 2 * k;
        }
        return new String(ch);
    }
}
