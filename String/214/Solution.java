class Solution {
    public String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        char[] ch = s.toCharArray();
        int i = 0, j = 0;
        for (int mid = (0 + ch.length - 1)/2; mid >= 0; mid--) {
            i = mid;
            j = mid+1;
            while (i >= 0 && j < ch.length && ch[i] == ch[j]) {
                i--;
                j++;
            }
            if (i < 0)
                break;
            i = mid;
            j = mid;
            while (i >= 0 && j < ch.length && ch[i] == ch[j]) {
                i--;
                j++;
            }
            if (i < 0)
                break;
        }
        sb.reverse();
        while (j < ch.length) 
            sb.append(ch[j++]);
        return sb.reverse().toString();
    }
}
