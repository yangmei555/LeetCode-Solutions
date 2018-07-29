class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        char[] ch = S.toCharArray();
        long sum = 0;
        for (int i = ch.length-1; i >= 0; i--) {
            sum += shifts[i];
            ch[i] = (char)('a' + (ch[i] - 'a' + sum) % 26);
        }
        return String.valueOf(ch);
    }
}


class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        char[] ch = S.toCharArray();
        int sum = 0;
        for (int i = ch.length-1; i >= 0; i--) {
            sum = (sum + shifts[i]) % 26;
            ch[i] = (char)('a' + (ch[i] - 'a' + sum) % 26);
        }
        return new String(ch);
    }
}
