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
        char[] run = new char[ch.length];
        helper(ch, run, 0, 0, 0, 0, xopen, xclose, res);
        return new LinkedList<>(res);
    }
    
    public void helper(char[] ch, char[] run, int index1, int index2, int open, int close, 
                                                    int xopen, int xclose, Set<String> res) {
        if (index1 == ch.length) {
            if (xopen == 0 && xclose == 0) {
                res.add(String.valueOf(run).trim());
                // System.out.println(String.valueOf(run).trim());
            }
        } else {
            if (ch[index1] == '(') {
                if (xopen > 0)
                    helper(ch, run, index1+1, index2, open, close, xopen-1, xclose, res);
                run[index2++] = '(';
                helper(ch, run, index1+1, index2, open+1, close, xopen, xclose, res);
                run[index2-1] = ' ';
            } else if (ch[index1] == ')') {
                if (xclose > 0)
                    helper(ch, run, index1+1, index2, open, close, xopen, xclose-1, res);
                if (open > close) {
                    run[index2++] = ')';
                    helper(ch, run, index1+1, index2, open, close+1, xopen, xclose, res);
                    run[index2-1] = ' ';
                }
            } else {
                run[index2++] = ch[index1];
                helper(ch, run, index1+1, index2, open, close, xopen, xclose, res);
                run[index2-1] = ' ';
            }
        }
    }
}
