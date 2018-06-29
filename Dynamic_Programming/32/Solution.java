class Solution {
    public int longestValidParentheses(String s) {
        char[] ch = s.toCharArray();
        int[] stack = new int[ch.length], record = new int[ch.length];
        int res = 0, index = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                stack[index++] = i;
            } else if (index != 0) {
                int pos = stack[--index];
                int cur = i - pos + 1;
                if (pos >= 1 && ch[pos-1] == ')')
                    cur += record[pos-1];
                res = res > cur ? res : cur;
                record[i] = cur;
            }
        }
        return res;
    }
}


class Solution {
    public int longestValidParentheses(String s) {
        char[] ch = s.toCharArray();
        int[] stack = new int[ch.length+1];
        int res = 0, index = 0;
        stack[index++] = -1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                stack[index++] = i;
            } else {
                index--;
                if (index == 0) {
                    stack[index++] = i;
                } else {
                    int pos = stack[index-1];
                    res = res > i - pos ? res : i - pos;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int longestValidParentheses(String s) {
        char[] ch = s.toCharArray();
        int res = 0, left = 0, right = 0;
        for (char c : ch) {
            if (c == '(')
                left++;
            else
                right++;
            if (left == right) {
                res = res > left + right ? res : left + right;
            } else if (left < right) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for (int i = ch.length-1; i >= 0; i--) {
            if (ch[i] == '(')
                left++;
            else
                right++;
            if (left == right) {
                res = res > left + right ? res : left + right;
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return res;
    }
}


class Solution {
    public int longestValidParentheses(String s) {
        char[] ch = s.toCharArray();
        int[] dp = new int[ch.length];
        int res = 0;
        for (int i = 1; i < ch.length; i++) {
            if (ch[i-1] == '(' && ch[i] == ')') {
                dp[i] = i == 1 ? 2 : dp[i-2] + 2;
            } else if (ch[i-1] == ')' && ch[i] == ')') {
                if (i-dp[i-1]-1 >= 0 && ch[i-dp[i-1]-1] == '(') {
                    dp[i] = i-dp[i-1]-1 == 0 ? i+1 : dp[i-dp[i-1]-2] + dp[i-1] + 2;
                }
            }
            res = res > dp[i] ? res : dp[i];
        }
        return res;
    }
}


class Solution {
    public int longestValidParentheses(String s) {
        char[] ch = s.toCharArray();
        int[] dp = new int[ch.length];
        int res = 0;
        for (int i = 1; i < ch.length; i++) {
            if (ch[i] == ')') {
                if (i-dp[i-1]-1 >= 0 && ch[i-dp[i-1]-1] == '(') 
                    dp[i] = i-dp[i-1]-1 == 0 ? i+1 : dp[i-dp[i-1]-2] + dp[i-1] + 2;
                res = res > dp[i] ? res : dp[i];
            }
        }
        return res;
    }
}
