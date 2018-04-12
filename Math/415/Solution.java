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
