class Solution {
    public List<String> removeInvalidParentheses(String s) {
        char[] ch = s.toCharArray(), stack = new char[ch.length];
        int xopen = 0, xclose = 0, index = 0;
        for (char c : ch) {
            if (c == '(') {
                stack[index++] = c;
            } else if (c == ')') {
                if (index == 0)
                    xclose++;
                else
                    index--;
            }
        }
        xopen = index;
        Set<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        helper(ch, sb, 0, 0, 0, xopen, xclose, res);
        return new LinkedList<>(res);
    }
    
    public void helper(char[] ch, StringBuilder sb, int index, int open, int close, 
                                                    int xopen, int xclose, Set<String> res) {
        if (index == ch.length) {
            if (xopen == 0 && xclose == 0) 
                res.add(sb.toString());
        } else {
            if (ch[index] == '(') {
                if (xopen > 0)
                    helper(ch, sb, index+1, open, close, xopen-1, xclose, res);
                sb.append('(');
                helper(ch, sb, index+1, open+1, close, xopen, xclose, res);
                sb.deleteCharAt(sb.length()-1);
            } else if (ch[index] == ')') {
                if (xclose > 0)
                    helper(ch, sb, index+1, open, close, xopen, xclose-1, res);
                if (open > close) {
                    sb.append(')');
                    helper(ch, sb, index+1, open, close+1, xopen, xclose, res);
                    sb.deleteCharAt(sb.length()-1);
                }
            } else {
                sb.append(ch[index]);
                helper(ch, sb, index+1, open, close, xopen, xclose, res);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}


// use linkedlist rather than hashset , and only use the extra number of '('
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        char[] ch = s.toCharArray(), stack = new char[ch.length];
        int xopen = 0, xclose = 0, index = 0;
        for (char c : ch) {
            if (c == '(') {
                stack[index++] = c;
            } else if (c == ')') {
                if (index != 0)
                    index--;
            }
        }
        xopen = index;
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        helper(ch, sb, 0, 0, 0, xopen, res);
        return res;
    }
    
    public void helper(char[] ch, StringBuilder sb, int index, int open, int close, 
                                                                int xopen, List<String> res) {
        if (index == ch.length) {
            if (open == close) 
                res.add(sb.toString());
        } else {
            if (ch[index] == '(') {
                int temp = index, len = sb.length();
                while (index < ch.length && ch[index] == '(')
                    index++;
                int min = Math.max(0, index-temp-xopen);
                for (int k = 0; k < min; k++)
                    sb.append('(');
                for (int k = min; k <= index-temp; k++) {
                    helper(ch, sb, index, open+k, close, xopen-(index-temp-k), res);
                    sb.append('(');
                }
                sb.setLength(len);
            } else if (ch[index] == ')') {
                int max = open - close, temp = index, len = sb.length();
                while (index < ch.length && ch[index] == ')')
                    index++;
                for (int k = 0; k <= max && k <= index - temp; k++) {
                    helper(ch, sb, index, open, close+k, xopen, res);
                    sb.append(')');
                }
                sb.setLength(len);
            } else {
                sb.append(ch[index]);
                helper(ch, sb, index+1, open, close, xopen, res);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}
