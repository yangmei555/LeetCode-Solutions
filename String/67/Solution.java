class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null)
            return b == null ? a : b;
        int carry = 0;
        StringBuilder str = new StringBuilder();
        int lena = a.length() - 1, lenb = b.length() - 1, sum = 0;
        while (lena >= 0 || lenb >= 0 || carry != 0) {
            sum = 0;
            if (lena >= 0) {
                sum += a.charAt(lena) - '0';
                lena--;
            }
            if (lenb >= 0) {
                sum += b.charAt(lenb) - '0';
                lenb--;
            }
            sum += carry;
            if (sum >= 2) {
                sum -= 2;
                carry = 1;
            } else {
                carry = 0;
            }
            str.append(sum);
        }
        return str.reverse().toString();
    }
}


class Solution {
    public String addBinary(String a, String b) {
        char[] ch1 = a.toCharArray(), ch2 = b.toCharArray();
        char[] res = new char[Math.max(ch1.length, ch2.length) + 1];
        Arrays.fill(res, '0');
        int index1 = ch1.length-1, index2 = ch2.length-1, index = res.length-1;
        while (index1 >= 0 || index2 >= 0) {
            int sum = (index1 >= 0 ? ch1[index1] - '0' : 0) + (index2 >= 0 ? ch2[index2] - '0' : 0);
            res[index] += sum;
            res[index-1] += (res[index] - '0') / 2;
            res[index] = (char)((res[index] - '0') % 2 + '0');
            index1--;
            index2--;
            index--;
        }
        return res[0] == '0' ? new String(res, 1, res.length-1) : new String(res);
    }
}
