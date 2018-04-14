class Solution {
    public String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8 && num != 0; i++) {
            int last = num & 15;
            sb.append(last < 10 ? (char)(last + '0') : (char)(last - 10 + 'a'));
            num >>>= 4;
        }
        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }
}
