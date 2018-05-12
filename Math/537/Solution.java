class Solution {
    public String complexNumberMultiply(String a, String b) {
        String[] sa = a.split("\\+"), sb = b.split("\\+");
        int x1 = Integer.valueOf(sa[0]);
        int y1 = Integer.valueOf(sa[1].substring(0, sa[1].length()-1));
        int x2 = Integer.valueOf(sb[0]);
        int y2 = Integer.valueOf(sb[1].substring(0, sb[1].length()-1));
        int x = x1 * x2 - y1 * y2;
        int y = x1 * y2 + y1 * x2;
        return x + "+" + y + "i";
    }
}
