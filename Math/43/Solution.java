class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        StringBuilder res = new StringBuilder();
        StringBuilder adder;
        char[] ch1 = num1.toCharArray(), ch2 = num2.toCharArray();
        int carry = 0;
        for (int i = ch1.length-1; i >= 0; i--) {
            adder = new StringBuilder();
            carry = 0;
            for (int j = ch1.length-1; j > i; j--)
                adder.append('0');
            for (int j = ch2.length-1; j >= 0; j--) {
                int c1 = ch1[i] - '0', c2 = ch2[j] - '0';
                int c3 = c1 * c2 + carry;
                carry = c3 / 10;
                c3 %= 10;
                adder.insert(0, c3);
            }
            if (carry != 0)
                adder.insert(0, carry);
            res = helper(res, adder);
        }
        return res.toString();
    }
    
    public StringBuilder helper(StringBuilder s1, StringBuilder s2) {
        int i = s1.length()-1, j = s2.length()-1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int c1 = i < 0 ? 0 : s1.charAt(i) - '0';
            int c2 = j < 0 ? 0 : s2.charAt(j) - '0';
            c1 = c1 + c2 + carry;
            carry = c1 / 10;
            c1 %= 10;
            if (i < 0)
                s1.insert(0, c1);
            else
                s1.setCharAt(i, (char)(c1 + '0'));
            i--;
            j--;
        }
        return s1;
    }
}


class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        StringBuilder res = new StringBuilder();
        int len1 = num1.length(), len2 = num2.length(), carry1 = 0, carry2 = 0, index = 0;
        int[] digits = new int[len1 + len2];
        char[] ch1 = num1.toCharArray(), ch2 = num2.toCharArray();
        for (int i = len1-1; i >= 0; i--) {
            carry1 = 0;
            carry2 = 0;
            index = i + len2;
            for (int j = len2-1; j >= 0; j--, index--) {
                int c1 = ch1[i] - '0', c2 = ch2[j] - '0';
                int c3 = c1 * c2 + carry1;
                carry1 = c3 / 10;
                c3 %= 10;
                digits[index] += c3 + carry2;
                carry2 = digits[index] / 10;
                digits[index] %= 10;
            }
            if (carry1 + carry2 != 0)
                digits[index] += carry1 + carry2;
        }
        for (int i = 0; i < digits.length; i++) {
            if (i == 0 && digits[i] == 0)
                continue;
            res.append(digits[i]);
        }
        return res.toString();
    }
}


class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        StringBuilder res = new StringBuilder();
        int len1 = num1.length(), len2 = num2.length(), carry1 = 0, carry2 = 0, index = 0;
        int[] digits = new int[len1 + len2];
        char[] ch1 = num1.toCharArray(), ch2 = num2.toCharArray();
        for (int i = len1-1; i >= 0; i--) {
            int c1 = ch1[i] - '0';
            for (int j = len2-1; j >= 0; j--, index--) {
                index = i + j + 1;
                int c2 = ch2[j] - '0';
                int c3 = c1 * c2;
                digits[index] += c3;
                digits[index-1] += digits[index] / 10;
                digits[index] %= 10;
            }
        }
        for (int i = 0; i < digits.length; i++) {
            if (i == 0 && digits[i] == 0)
                continue;
            res.append(digits[i]);
        }
        return res.toString();
    }
}
