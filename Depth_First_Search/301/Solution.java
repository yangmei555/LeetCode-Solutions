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


// a more concise way to implement the above idea 
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        char[] ch = s.toCharArray();
        for (char c : ch) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0)
                    left--;
                else
                    right++;
            }
        }
        Set<String> set = new HashSet<>();
        helper(ch, 0, left, right, 0, new StringBuilder(), set);
        return new LinkedList<>(set);
    }
    
    public void helper(char[] ch, int index, int left, int right, int open, 
                                                            StringBuilder sb, Set<String> set) {
        if (left < 0 || right < 0 || open < 0)
            return;
        if (index == ch.length) {
            if (left == 0 && right == 0 && open == 0)
                set.add(sb.toString());
        } else {
            if (ch[index] == '(') {
                helper(ch, index+1, left-1, right, open, sb, set);
                helper(ch, index+1, left, right, open+1, sb.append('('), set);
            } else if (ch[index] == ')') {
                helper(ch, index+1, left, right-1, open, sb, set);
                helper(ch, index+1, left, right, open-1, sb.append(')'), set);
            } else {
                helper(ch, index+1, left, right, open, sb.append(ch[index]), set);
            }
            sb.deleteCharAt(sb.length()-1);
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


// a brilliant idea borrowed from others 
// every time encounter a miss match, begin to remove the close symbol 
// if the close symbol does not overwhelm, reverse the string and reverse the two symbols 
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new LinkedList<>();
        helper(s, 0, 0, '(', ')', res);
        // System.out.println(res.size());
        return res;
    }
    
    public void helper(String s, int lastI, int lastJ, char open, char close, List<String> res) {
        char[] ch = s.toCharArray();
        for (int count = 0, i = lastI; i < ch.length; i++) {
            if (ch[i] == open)
                count++;
            else if (ch[i] == close)
                count--;
            if (count < 0) {
                for (int j = lastJ; j <= i; j++) {
                    if (ch[j] == close && (j == lastJ || ch[j-1] != ch[j])) {
                        String str = s.substring(0, j) + s.substring(j+1);
                        helper(str, i, j, open, close, res);
                    }
                }
                return;
            }
        }
        String str = new StringBuilder(s).reverse().toString();
        if (open == '(')
            helper(str, 0, 0, close, open, res);
        else
            res.add(str);
    }
}
