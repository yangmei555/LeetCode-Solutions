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


// a cumbersome method using stack 
class Solution {
    public String decodeString(String s) {
        char[] ch = s.toCharArray();
        String[] stringStack = new String[ch.length];
        int[] numStack = new int[ch.length];
        StringBuilder sb = new StringBuilder();
        int index = 0, stringStackIndex = 0, numStackIndex = 0;
        while (index < ch.length) {
            if (ch[index] >= '0' && ch[index] <= '9') {
                int num = 0;
                while (index < ch.length && ch[index] >= '0' && ch[index] <= '9')
                    num = num * 10 + ch[index++] - '0';
                if (index < ch.length && ch[index] == '[') {
                    index++;
                    stringStack[stringStackIndex++] = sb.toString();
                    sb.setLength(0);
                    numStack[numStackIndex++] = num;
                }
            } else if (Character.isLetter(ch[index])) {
                while (index < ch.length && Character.isLetter(ch[index]))
                    sb.append(ch[index++]);
            } else if (ch[index] == ']') {
                String str = stringStack[--stringStackIndex];
                int num = numStack[--numStackIndex];
                for (int i = 0; i < num; i++)
                    str = str + sb.toString();
                sb = new StringBuilder(str);
                index++;
            }
        }
        // System.out.println(stringStackIndex);
        // while (stringStackIndex != 0)
        //     sb.insert(0, stringStack[--stringStackIndex]);
        return sb.toString();
    }
}
