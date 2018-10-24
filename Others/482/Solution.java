class Solution {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        char[] ch = S.toCharArray();
        // int count = 0;
        for (int i = ch.length-1; i >= 0; i--) {
            if (ch[i] == '-') {
                continue;
            } else {
                // sb.append(ch[i] > 'Z' ? (char)(ch[i] - 'a' + 'A') : ch[i]);
                if (sb.length() % (K+1) == K)
                    sb.append('-');
                sb.append(ch[i] > 'Z' ? (char)(ch[i] - 'a' + 'A') : ch[i]);
            }
        }
        sb.reverse();
        // return sb.length() != 0 && sb.charAt(0) == '-' ? sb.substring(1) : sb.toString();
        return sb.toString();
    }
}
