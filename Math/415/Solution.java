class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        char[] ch1 = num1.toCharArray();
        char[] ch2 = num2.toCharArray();
        int carry = 0, i = ch1.length - 1, j = ch2.length - 1;
        while (carry != 0 || i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0)
                sum += ch1[i--] - '0';
            if (j >= 0)
                sum += ch2[j--] - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }
}


// an awkward solution 
class Solution {
    public String addStrings(String num1, String num2) {
        char[] ch1 = num1.toCharArray(), ch2 = num2.toCharArray();
        char[] res = new char[Math.max(ch1.length, ch2.length) + 1];
        int i = ch1.length-1, j = ch2.length-1, k = res.length-1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int digit1 = i >= 0 ? ch1[i]-'0' : 0;
            int digit2 = j >= 0 ? ch2[j]-'0' : 0;
            res[k] = (char)('0' + (digit1 + digit2 + carry) % 10);
            carry = (digit1 + digit2 + carry) / 10;
            i--;
            j--;
            k--;
        }
        return res[0] == 0 ? new String(res, 1, res.length-1) : new String(res);
    }
}
