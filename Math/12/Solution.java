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
