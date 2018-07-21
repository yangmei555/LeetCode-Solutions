class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(' || ch[i] == '[' || ch[i] == '{') {
                stack.push(ch[i]);
            } else {
                if (stack.isEmpty())
                    return false;
                char t = stack.pop();
                if (ch[i] == ']' && t != '[')
                    return false;
                if (ch[i] == ')' && t != '(')
                    return false;
                if (ch[i] == '}' && t != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }
}


class Solution {
    public boolean isValid(String s) {
        char[] ch = s.toCharArray();
        int index = 0;
        for (char c : ch) {
            if (c == '(' || c == '[' || c == '{') {
                ch[index++] = c;
            } else {
                if (index == 0)
                    return false;
                if (c == ')' && ch[index-1] != '(')
                    return false;
                if (c == ']' && ch[index-1] != '[')
                    return false;
                if (c == '}' && ch[index-1] != '{')
                    return false;
                index--;
            }
        }
        return index == 0;
    }
}
