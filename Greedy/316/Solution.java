class Solution {
    public String removeDuplicateLetters(String s) {
        char[] ch = s.toCharArray();
        boolean[] hasNext = new boolean[ch.length], temp = new boolean[26];
        for (int i = ch.length-1; i >= 0; i--) {
            if (temp[ch[i]-'a'])
                hasNext[i] = true;
            temp[ch[i]-'a'] = true;
        }
        char[] stack = new char[ch.length];
        boolean[] appears = new boolean[26], hasNextChar = new boolean[26];
        int index = 0;
        for (int i = 0; i < ch.length; i++) {
            if (!appears[ch[i]-'a']) {
                while (index != 0 && stack[index-1] > ch[i] && hasNextChar[stack[index-1]-'a']) {
                    appears[stack[index-1]-'a'] = false;
                    index--;
                }
                stack[index++] = ch[i];
                appears[ch[i]-'a'] = true;
                hasNextChar[ch[i]-'a'] = hasNext[i];
            } else {
                hasNextChar[ch[i]-'a'] = hasNext[i];
            }
        }
        //   (char[] data, int offset, int count)
        // return String.valueOf(stack, 0, index);
        return new String(stack, 0, index);
    }
}
