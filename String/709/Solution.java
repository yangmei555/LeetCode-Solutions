class Solution {
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c >= 'A' && c <= 'Z')
                sb.append((char)(c - 'A' + 'a'));
            else
                sb.append(c);    
        }
        return sb.toString();
    }
}
