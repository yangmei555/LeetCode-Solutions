class Solution {
    public int calculate(String s) {
        char[] ch = s.toCharArray();
        return helper(ch);
    }
    int index = 0;
    public int helper(char[] ch) {
        int[] stack1 = new int[ch.length];
        char[] stack2 = new char[ch.length];
        int index1 = 0, index2 = 0, num = 0;
        while (index < ch.length) {
            if (ch[index] >= '0' && ch[index] <= '9') {
                while (index < ch.length && ch[index] >= '0' && ch[index] <= '9')
                    num = num * 10 + ch[index++] - '0';
                stack1[index1++] = num;
                num = 0;
            } else if (ch[index] == '(') {
                index++;
                num = helper(ch);
                index++;
                stack1[index1++] = num;
                num = 0;
            } else if (ch[index] == ')') {
                break;
            } else if (ch[index] != ' ') {
                while (index2 != 0 && priority(stack2[index2-1]) >= priority(ch[index])) {
                    int num1 = stack1[--index1];
                    int num2 = stack1[--index1];
                    char op = stack2[--index2];
                    if (op == '+')
                        stack1[index1++] = num2 + num1;
                    else if (op == '-')
                        stack1[index1++] = num2 - num1;
                    else if (op == '*')
                        stack1[index1++] = num2 * num1;
                    else if (op == '/')
                        stack1[index1++] = num2 / num1;        
                }
                stack2[index2++] = ch[index++];
            } else if (ch[index] == ' ') {
                index++;
            }
        }
        while (index2 > 0) {
            int num1 = stack1[--index1];
            int num2 = stack1[--index1];
            char op = stack2[--index2];
            if (op == '+')
                stack1[index1++] = num2 + num1;
            else if (op == '-')
                stack1[index1++] = num2 - num1;
            else if (op == '*')
                stack1[index1++] = num2 * num1;
            else if (op == '/')
                stack1[index1++] = num2 / num1;
        }
        return stack1[0];
    }
    
    public int priority(char c) {
        if (c == '+' || c == '-')
            return 0;
        else
            return 1;
    }
}


// a more concise way of parsing expressions 
class Solution {
    public int calculate(String s) {
        char[] ch = s.toCharArray();
        return helper(ch);
    }
    int index = 0;
    public int helper(char[] ch) {
        int[] stack = new int[ch.length];
        char op = '+';
        int num = 0, stackIndex = 0;
        while (index <= ch.length) {
            if (index != ch.length && ch[index] >= '0' && ch[index] <= '9') {
                num = num * 10 + ch[index] - '0';
            } else if (index == ch.length || ch[index] != ' ' && ch[index] != '(') {
                if (op == '+')
                    stack[stackIndex++] = num;
                else if (op == '-')
                    stack[stackIndex++] = -num;
                else if (op == '*')
                    stack[stackIndex-1] *= num;
                else if (op == '/')
                    stack[stackIndex-1] /= num;
                if (index == ch.length || ch[index] == ')')
                    break;
                op = ch[index];
                num = 0;
            } else if (ch[index] == '(') {
                ++index;
                num = helper(ch);
            } 
            index++;
        }
        int res = 0;
        while (stackIndex > 0)
            res += stack[--stackIndex];
        return res;
    }
}


// using no stack 
class Solution {
    public int calculate(String s) {
        char[] ch = s.toCharArray();
        return helper(ch);
    }
    int index = 0;
    public int helper(char[] ch) {
        int num = 0, preNum = 0, res = 0;
        char op = '+';
        while (index <= ch.length) {
            if (index != ch.length && ch[index] >= '0' && ch[index] <= '9') {
                while (index < ch.length && ch[index] >= '0' && ch[index] <= '9')
                    num = num * 10 + ch[index++] - '0';
            } else if (index != ch.length && ch[index] == '(') {
                index++;
                num = helper(ch);
                index++;
            } else if (index == ch.length || ch[index] != ' ') {
                if (op == '+') 
                    preNum = num;
                else if (op == '-')
                    preNum = -num;
                else if (op == '*')
                    preNum *= num;
                else if (op == '/')
                    preNum /= num;
                if (index == ch.length || ch[index] == ')')
                    break;
                op = ch[index];
                if (op == '+' || op == '-') {
                    res += preNum;
                    preNum = 0;
                } 
                num = 0;
                index++;
            } else if (ch[index] == ' ') {
                index++;
            }
        }
        return res + preNum;
    }
}
