class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return new String();
        int index = 0;
        char[] ch = s.toCharArray();
        char temp = '0';
        while (index < ch.length) {
            if (ch[index] == ' ') {
                index++;
                continue;
            } else {
                int start = index;
                while (index < ch.length && ch[index] != ' ')
                    index++;
                int end = index - 1;
                while (start < end) {
                    temp = ch[start];
                    ch[start++] = ch[end];
                    ch[end--] = temp;
                }
            }
        }
        return String.valueOf(ch);
    }
}
