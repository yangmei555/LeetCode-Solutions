class Solution {
    String[] digits = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", 
                                    "Six", "Seven", "Eight", "Nine"};
    String[] aboveTen = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", 
                                    "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", 
                                        "Seventy", "Eighty", "Ninety"};
    String[] roots = new String[]{"", " Thousand", " Million", " Billion", " Hundred"};
    public String numberToWords(int num) {
        StringBuilder cur = new StringBuilder(), pre = new StringBuilder();
        int i = 0;
        while (num != 0) {
            cur = helper(num % 1000);
            if (cur.length() != 0) {
                cur.append(roots[i]);
                if (pre.length() != 0)
                    cur.append(' ').append(pre);
                pre = cur;
            }
            num /= 1000;
            i++;
        }
        return cur.length() == 0 ? digits[0] : cur.toString();
    }
    
    public StringBuilder helper(int n) {
        StringBuilder sb = new StringBuilder();
        if (n / 100 != 0) {
            sb.append(digits[n/100]).append(roots[4]);
            n -= n / 100 * 100;
        }
        if (n / 10 != 0) {
            if (sb.length() != 0)
                sb.append(' ');
            if (n / 10 == 1) {
                sb.append(aboveTen[n%10]);
                return sb;
            }
            sb.append(tens[n/10]);
            n = n % 10;
        }
        if (n != 0) {
            if (sb.length() != 0)
                sb.append(' ');
            sb.append(digits[n]);
        }
        return sb;
    }
}
