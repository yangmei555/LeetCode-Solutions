class Solution {
    public String parseTernary(String expression) {
        char[] ch = expression.toCharArray();
        char[] stack = new char[ch.length];
        int index = 0;
        int i = ch.length-1;
        while (i >= 0) {
            if (ch[i] != '?' && ch[i] != ':') {
                stack[index++] = ch[i];
            } else if (ch[i] == '?') {
                if (ch[i-1] == 'T') 
                    stack[index-2] = stack[index-1];
                index--;
                i--;
            }
            i--;
        }
        return stack[index-1] + "";
    }
}


class Solution {
    public String parseTernary(String expression) {
        char[] ch = expression.toCharArray();
        return helper(ch, 0, ch.length-1) + "";
    }
    
    public char helper(char[] ch, int start, int end) {
        if (start == end)
            return ch[start];
        int i = start, count = 0;
        for (; i <= end; i++) {
            if (ch[i] == '?') {
                count++;
            } else if (ch[i] == ':') {
                count--;
                if (count == 0)
                    break;
            }
        }
        return ch[start] == 'T' ? helper(ch, start+2, i-1) : helper(ch, i+1, end);
    }
}
