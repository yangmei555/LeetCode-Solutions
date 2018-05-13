class Solution {
    public String solveEquation(String equation) {
        String[] str = equation.split("=");
        int[] a = new int[2], b = new int[2];
        for (int i = 0; i < str.length; i++) {
            char[] ch = str[i].toCharArray();
            int start = 0, end = 0, sign = 1, num = 0;
            while (end <= ch.length) {
                if (end == ch.length || ch[end] == '+' || ch[end] == '-') {
                    if (start == end)
                        b[i] += num;
                    else
                        b[i] += sign * num;
                    num = 0;
                    start = end + 1;
                    sign = end != ch.length && ch[end] == '+' ? 1 : -1;
                } else if (ch[end] == 'x') {
                    if (start == end) 
                        a[i] += sign;
                    else 
                        a[i] += sign * num;
                    num = 0;
                    start = end + 1;
                } else {
                    num = num * 10 + ch[end] - '0';
                }
                end++;
            }
        }
        a[0] -= a[1];
        b[0] -= b[1];
        if (a[0] == 0 && b[0] == 0)
            return "Infinite solutions";
        else if (a[0] == 0 && b[0] != 0)
            return "No solution";
        else
            return "x=" + (-b[0]/a[0]);
    }
}
