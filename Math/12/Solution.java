class Solution {
    public String intToRoman(int num) {
        String[] list1 = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] list2 = new String[]{"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] list3 = new String[]{"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] list4 = new String[]{"M", "MM", "MMM"};
        StringBuilder sb = new StringBuilder();
        int a = num / 1000;
        if (a != 0)
            sb.append(list4[a-1]);
        num %= 1000;
        int b = num / 100;
        if (b != 0) 
            sb.append(list3[b-1]);
        num %= 100;
        int c = num / 10;
        if (c != 0)
            sb.append(list2[c-1]);
        num %= 10;
        if (num != 0)
            sb.append(list1[num-1]);
        return sb.toString();
    }
}


class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int n = num / 1000;
        if (n != 0) {
            for (int i = 0; i < n; i++)
                sb.append('M');
            num -= n * 1000;
        }
        n = num / 100;
        if (n != 0) {
            if (n >= 1 && n <= 3) {
                for (int i = 0; i < n; i++)
                    sb.append('C');
            } else if (n == 4) {
                sb.append('C').append('D');
            } else if (n < 9) {
                sb.append('D');
                for (int i = 6; i <= n; i++)
                    sb.append('C');
            } else {
                sb.append('C').append('M');
            }
            num -= n * 100;
        }
        n = num / 10;
        if (n != 0) {
            if (n >= 1 && n <= 3) {
                for (int i = 0; i < n; i++)
                    sb.append('X');
            } else if (n == 4) {
                sb.append('X').append('L');
            } else if (n < 9) {
                sb.append('L');
                for (int i = 6; i <= n; i++)
                    sb.append('X');
            } else {
                sb.append('X').append('C');
            }
            num -= n * 10;
        }
        n = num / 1;
        if (n != 0) {
            if (n >= 1 && n <= 3) {
                for (int i = 0; i < n; i++)
                    sb.append('I');
            } else if (n == 4) {
                sb.append('I').append('V');
            } else if (n < 9) {
                sb.append('V');
                for (int i = 6; i <= n; i++)
                    sb.append('I');
            } else {
                sb.append('I').append('X');
            }
        }
        return sb.toString();
    }
}
