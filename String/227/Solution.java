class Solution {
    public int calculate(String s) {
        Stack<Character> operator = new Stack<>();
        Stack<Integer> operand = new Stack<>();
        char[] ch = s.toCharArray();
        int num = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                num = num * 10 + ch[i] - '0';
            } else if (ch[i] != ' ') {
                operand.push(num);
                num = 0;
                while (!operator.isEmpty() && priority(operator.peek()) >= priority(ch[i])) {
                    System.out.println(operand);
                    System.out.println(operator);
                    System.out.println(ch[i]);
                    char o = operator.pop();
                    int n1 = operand.pop(), n2 = operand.pop();
                    int n3 = 0;
                    if (o == '+')
                        n3 = n2 + n1;
                    else if (o == '-')
                        n3 = n2 - n1;
                    else if (o == '*')
                        n3 = n2 * n1;
                    else
                        n3 = n2 / n1;
                    operand.push(n3);
                }
                operator.push(ch[i]);
            }
        }
        operand.push(num);
        System.out.println(operand.size() + " " + operator.size());
        while (!operator.isEmpty()) {
            char o = operator.pop();
            int n1 = operand.pop(), n2 = operand.pop();
            int n3 = 0;
            if (o == '+')
                n3 = n2 + n1;
            else if (o == '-')
                n3 = n2 - n1;
            else if (o == '*')
                n3 = n2 * n1;
            else
                n3 = n2 / n1;
            operand.push(n3);
        }
        return operand.peek();
    }
    
    public int priority(char c) {
        if (c == '+' || c == '-')
            return 1;
        else
            return 2;
    }
}


class Solution {
    public int calculate(String s) {
        int[] operand = new int[s.length()/2+1];
        char[] operator = new char[s.length()/2];
        int index1 = 0, index2 = 0;
        char[] ch = s.toCharArray();
        int num = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                num = num * 10 + ch[i] - '0';
            } else if (ch[i] != ' ') {
                operand[index1++] = num;
                num = 0;
                while (index2 != 0 && priority(operator[index2-1]) >= priority(ch[i])) {
                    char o = operator[--index2];
                    int n1 = operand[--index1], n2 = operand[--index1];
                    int n3 = 0;
                    if (o == '+')
                        n3 = n2 + n1;
                    else if (o == '-')
                        n3 = n2 - n1;
                    else if (o == '*')
                        n3 = n2 * n1;
                    else
                        n3 = n2 / n1;
                    operand[index1++] = n3;
                }
                operator[index2++] = ch[i];
            }
        }
        operand[index1++] = num;
        while (index2 != 0) {
            char o = operator[--index2];
            int n1 = operand[--index1], n2 = operand[--index1];
            int n3 = 0;
            if (o == '+')
                n3 = n2 + n1;
            else if (o == '-')
                n3 = n2 - n1;
            else if (o == '*')
                n3 = n2 * n1;
            else
                n3 = n2 / n1;
            operand[index1++] = n3;
        }
        return operand[index1-1];
    }
    
    public int priority(char c) {
        if (c == '+' || c == '-')
            return 1;
        else
            return 2;
    }
}


class Solution {
    public int calculate(String s) {
        int[] operand = new int[s.length()/2+1];
        int index = 0;
        char[] ch = s.toCharArray();
        int num = 0;
        char sign = '+';
        for (int i = 0; i <= ch.length; i++) {
            if (i != ch.length && ch[i] >= '0' && ch[i] <= '9') {
                num = num * 10 + ch[i] - '0';
            } else if (i == ch.length || ch[i] != ' ') {
                if (sign == '+') {
                    operand[index++] = num;
                } else if (sign == '-') {
                    operand[index++] = -num;
                } else if (sign == '*') {
                    operand[index-1] *= num;
                } else if (sign == '/') {
                    operand[index-1] /= num;
                }
                num = 0;
                if (i != ch.length)
                    sign = ch[i];
            }
        }
        int res = 0;
        for (int o : operand)
            res += o;
        return res;
    }
}


class Solution {
    public int calculate(String s) {
        char[] ch = s.toCharArray();
        int num = 0, prenum = 0, res = 0;
        char sign = '+';
        for (int i = 0; i <= ch.length; i++) {
            if (i != ch.length && ch[i] >= '0' && ch[i] <= '9') {
                num = num * 10 + ch[i] - '0';
            } else if (i == ch.length || ch[i] != ' ') {
                if (sign == '+') {
                    prenum = num;
                } else if (sign == '-') {
                    prenum = -num;
                } else if (sign == '*') {
                    prenum *= num;
                } else if (sign == '/') {
                    prenum /= num;
                }
                num = 0;
                if (i != ch.length) {
                    sign = ch[i];
                    if (sign == '+' || sign == '-')
                        res += prenum;
                }
            }
        }
        res += prenum;
        return res;
    }
}
