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


// only need to record the last appearance of each char in s 
class Solution {
    public String removeDuplicateLetters(String s) {
        char[] ch = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[26];
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i = 0; i < ch.length; i++) 
            last[ch[i]-'a'] = i;
        for (int i = 0; i < ch.length; i++) {
            if (!used[ch[i]-'a']) {
                while (sb.length() != 0 && sb.charAt(sb.length()-1) > ch[i] && 
                                        last[sb.charAt(sb.length()-1)-'a'] > i) {
                    used[sb.charAt(sb.length()-1)-'a'] = false;
                    sb.deleteCharAt(sb.length()-1);
                }
                used[ch[i]-'a'] = true;
                sb.append(ch[i]);
            }
        }
        return sb.toString();
    }
}


// record the number of each char rather than the last appearance 
class Solution {
    public String removeDuplicateLetters(String s) {
        char[] ch = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[26];
        int[] count = new int[26];
        for (char c : ch)
            count[c-'a']++;
        for (int i = 0; i < ch.length; i++) {
            count[ch[i]-'a']--;
            if (!used[ch[i]-'a']) {
                while (sb.length() != 0 && sb.charAt(sb.length()-1) > ch[i] && 
                                        count[sb.charAt(sb.length()-1)-'a'] > 0) {
                    used[sb.charAt(sb.length()-1)-'a'] = false;
                    sb.deleteCharAt(sb.length()-1);
                }
                used[ch[i]-'a'] = true;
                sb.append(ch[i]);
            }
        }
        return sb.toString();
    }
}
