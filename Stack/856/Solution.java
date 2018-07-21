class Solution {
    int index;
    public int scoreOfParentheses(String S) {
        char[] ch = S.toCharArray();
        index = 0;
        return helper(ch);
    }
    
    public int helper(char[] ch) {
        int res = 0;
        while (index < ch.length && ch[index++] != ')') {
            int ret = helper(ch);
            if (ret == 0)
                res += 1;
            else
                res += ret * 2;
        }
        return res;
    }
}


class Solution {
    int index;
    public int scoreOfParentheses(String S) {
        char[] ch = S.toCharArray();
        index = 0;
        int res = 0;
        while (index < ch.length) 
            res += helper(ch);
        return res;
    }
    
    public int helper(char[] ch) {
        int res = 0;
        index++;
        while (index < ch.length && ch[index] != ')') 
            res += helper(ch);
        index++;
        return res == 0 ? 1 : res * 2;
    }
}


// if no intermediate score exceed 256 
class Solution {
    public int scoreOfParentheses(String S) {
        char[] ch = S.toCharArray();
        int index = 0;
        for (char c : ch) {
            if (c == '(') {
                ch[index++] = '(';
            } else {
                int num = 0;
                while (ch[index-1] != '(') 
                    num += ch[--index];
                ch[index-1] = (char)(num == 0 ? 1 : num * 2);
            }
        }
        int res = 0;
        while (index != 0)
            res += ch[--index];
        return res;
    }
}


class Solution {
    public int scoreOfParentheses(String S) {
        char[] ch = S.toCharArray();
        int res = 0, paren = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                paren++;
            } else {
                paren--;
                if (i != 0 && ch[i-1] == '(')
                    res += 1 << paren;
            }
        }
        return res;
    }
}
