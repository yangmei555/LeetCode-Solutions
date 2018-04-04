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
