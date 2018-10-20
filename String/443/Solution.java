class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0)
            return 0;
        int index = 0, count = 1;
        char ch = chars[0];
        for (int i = 1; i <= chars.length; i++) {
            if (i != chars.length && chars[i] == ch) {
                count++;
            } else {
                chars[index] = ch;
                if (i != chars.length)
                    ch = chars[i];
                index++;
                if (count != 1) {
                    if (count == 1000) {
                        index = 5;
                        chars[1] = '1';
                        chars[2] = '0';
                        chars[3] = '0';
                        chars[4] = '0';
                        break;
                    }
                    if (count >= 100) {
                        chars[index] = (char)(count / 100 + '0');
                        count -= count / 100 * 100;
                        index++;
                    }
                    if (count >= 10) {
                        chars[index] = (char)(count / 10 + '0');
                        count -= count / 10 * 10;
                        index++;
                    }
                    chars[index] = (char)(count + '0');
                    index++;
                }
                count = 1;
            }
        }
        return index > 0 ? index : 1;
    }
}


// a more concise style 
class Solution {
    public int compress(char[] chars) {
        int index = 0, pos = 0;
        while (pos < chars.length) {
            int start = pos++;
            while (pos < chars.length && chars[start] == chars[pos])
                pos++;
            chars[index++] = chars[start];
            if (pos - start > 1) {
                for (char c : (pos - start + "").toCharArray())
                    chars[index++] = c;
            } 
        }
        return index;
    }
}
