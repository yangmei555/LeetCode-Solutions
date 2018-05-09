class Solution {
    public int myAtoi(String str) {
        if (str.length() == 0)
            return 0;
        int res = 0;
        char[] ch = str.toCharArray();
        boolean start = false, sign = true;
        for (char c : ch) {
            if (c == ' ') {
                if (start)
                    return sign ? res : -res;
            } else {
                if (!(c >= '0' && c <= '9')) {
                    if (!(c == '+' || c == '-') || start) {
                        return sign ? res : -res;
                    } else {
                        start = true;
                        if (c == '-')
                            sign = false;
                    }
                } else {
                    start = true;
                    if (Integer.MAX_VALUE / 10 <= res) {
                        if (res * 10 + (c - '0') < Integer.MAX_VALUE-7) {
                            return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                        } else {
                            res = res * 10 + (c - '0');
                            return sign ? res : -res;
                        }
                    }
                    res = res * 10 + (c - '0');
                }
            }
        }
        return sign ? res : -res;
    }
}


class Solution {
    public int myAtoi(String str) {
        if (str.length() == 0)
            return 0;
        char[] ch = str.toCharArray();
        int index = 0, res = 0, sign = 1;
        while (index < ch.length && ch[index] == ' ')
            index++;
        if (index < ch.length && (ch[index] == '+' || ch[index] == '-')) {
            sign = ch[index] == '+' ? 1 : -1;
            index++;
        }
        while (index < ch.length) {
            if (!(ch[index] >= '0' && ch[index] <= '9'))
                break;
            if (Integer.MAX_VALUE/10 < res || (Integer.MAX_VALUE/10 == res && ch[index] > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else {
                res = res * 10 + (ch[index] - '0');
            }
            index++;
        }
        return res * sign;
    }
}
