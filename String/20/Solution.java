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
