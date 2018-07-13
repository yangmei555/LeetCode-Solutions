class Solution {
    int pos = 0;
    public String decodeString(String s) {
        char[] ch = s.toCharArray();
        return helper(ch);
    }
    
    public String helper(char[] ch) {
        StringBuilder sb = new StringBuilder();
        while (pos < ch.length && ch[pos] != ']') {
            while (pos < ch.length && (ch[pos] >= 'a' && ch[pos] <= 'z' || 
                                        ch[pos] >= 'A' && ch[pos] <= 'Z'))
                sb.append(ch[pos++]);
            if (pos < ch.length && ch[pos] >= '0' && ch[pos] <= '9') {
                int num = 0;
                while (pos < ch.length && ch[pos] >= '0' && ch[pos] <= '9')
                    num = num * 10 + (ch[pos++] - '0');
                pos++;
                String ret = helper(ch);
                for (int i = 0; i < num; i++)
                    sb.append(ret);
            }
        }
        if (pos < ch.length && ch[pos] == ']')
            pos++;
        return sb.toString();
    }
}
