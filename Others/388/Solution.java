// notice that '\t' and '\n' are actually 1 character ! 
class Solution {
    public int lengthLongestPath(String input) {
        char[] ch = input.toCharArray();
        // System.out.println(input);
        String[] stack = new String[ch.length];
        int index = 0, stackIndex = 0;
        int res = 0;
        StringBuilder sb = new StringBuilder();
        while (index < ch.length) {
            int count = 0;
            while (index < ch.length && ch[index] == '\t') {
                count++;
                index++;
            }
            int start = index++;
            boolean file = false;
            while (index < ch.length && ch[index] != '\n') {
                if (ch[index] == '.')
                    file = true;
                index++;
            }
            String temp = new String(ch, start, index - start);
            while (stackIndex != count) {
                sb.setLength(sb.length() - stack[stackIndex-1].length());
                stackIndex--;
                if (sb.length() != 0)
                    sb.setLength(sb.length()-1);
            }
            stack[stackIndex++] = temp;
            sb.append(sb.length() == 0 ? "" : '/').append(temp);
            if (file) 
                res = Math.max(res, sb.length());
            if (index < ch.length && ch[index] == '\n')
                index++;
        }
        return res;
    }
}


// no need to store strings in the stack, just store their length is enough 
class Solution {
    public int lengthLongestPath(String input) {
        char[] ch = input.toCharArray();
        // System.out.println(input);
        int[] stack = new int[ch.length];
        int index = 0, stackIndex = 0;
        int res = 0, len = 0;
        while (index < ch.length) {
            int count = 0;
            while (index < ch.length && ch[index] == '\t') {
                count++;
                index++;
            }
            int start = index++;
            boolean file = false;
            while (index < ch.length && ch[index] != '\n') {
                if (ch[index] == '.')
                    file = true;
                index++;
            }
            int temp = index - start + 1;
            while (stackIndex != count) 
                len -= stack[--stackIndex];
            stack[stackIndex++] = temp;
            len += temp;
            if (file) 
                res = Math.max(res, len - 1);
            if (index < ch.length && ch[index] == '\n')
                index++;
        }
        return res;
    }
}
