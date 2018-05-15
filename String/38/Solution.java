class Solution {
    public String countAndSay(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        for (int i = 2; i <= n; i++) {
            char[] ch = sb.toString().toCharArray();
            sb.setLength(0);
            int count = 0;
            char c = ch[0];
            for (int j = 0; j <= ch.length; j++) {
                if (j != ch.length && ch[j] == c) {
                    count++;
                } else {
                    sb.append(count + "" + (c-'0'));
                    if (j != ch.length) {
                        count = 1;
                        c = ch[j];
                    }
                }
            }
        }
        return sb.toString();
    }
}
