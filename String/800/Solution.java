class Solution {
    public String similarRGB(String color) {
        char[] ch = color.toCharArray();
        StringBuilder sb = new StringBuilder("#");
        int d1 = 0, d2 = 0, d3 = 0, num = 0, n1 = 0, n2 = 0;
        for (int i = 1; i < 7; i += 2) {
            if (ch[i] == ch[i+1]) {
                sb.append(ch[i] + "" + ch[i+1]);
            } else {
                d1 = ch[i] > '9' ? ch[i]-'a'+10 : ch[i]-'0';
                d2 = ch[i+1] > '9' ? ch[i+1]-'a'+10 : ch[i+1]-'0';
                d3 = d1 > d2 ? d1-1 : d1+1;
                num = d1 * 16 + d2;
                n1 = d1 * 16 + d1;
                n2 = d3 * 16 + d3;
                if (Math.abs(n1 - num) > Math.abs(num - n2)) {
                    sb.append((char)(d3 > 9 ? d3-10+'a' : d3+'0') + "" + (char)(d3 > 9 ? d3-10+'a' : d3+'0'));
                } else {
                    sb.append((char)(d1 > 9 ? d1-10+'a' : d1+'0') + "" + (char)(d1 > 9 ? d1-10+'a' : d1+'0'));
                }
            }
        }
        return sb.toString();
    }
}

