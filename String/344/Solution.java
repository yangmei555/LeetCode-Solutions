class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() == 0)
            return new String();
        char[] ch = s.toCharArray();
        int i = 0, j = ch.length - 1;
        char temp = '0';
        while (i < j) {
            temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            i++;
            j--;
        }
        return new String(ch);
    }
}
