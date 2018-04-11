class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remainder = (n - 1) % 26;
            sb.append((char)(remainder + 'A'));
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }
}
