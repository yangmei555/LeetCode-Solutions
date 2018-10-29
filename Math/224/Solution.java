class Solution {
    public int calculate(String s) {
        char[] ch = s.toCharArray();
        index = 0;
        return helper(ch);
    }
    int index;
    public int helper(char[] ch) {
        boolean plus = true;
        int res = 0;
        while (index < ch.length) {
            if (ch[index] == ' ') {
                index++;
            } else if (ch[index] == '(') {
                index++;
                int ret = helper(ch);
                index++;
                res = plus ? res + ret : res - ret;
            } else if (ch[index] == ')') {
                return res;
            } else if (ch[index] == '+') {
                index++;
                plus = true;
            } else if (ch[index] == '-') {
                index++;
                plus = false;
            } else {
                int num = 0;
                while (index < ch.length && ch[index] >= '0' && ch[index] <= '9')
                    num = num * 10 + ch[index++] - '0';
                res = plus ? res + num : res - num;
            }
        }
        return res;
    }
}
