class Solution {
    public boolean checkValidString(String s) {
        char[] ch = s.toCharArray();
        int[] left = new int[ch.length], asterisk = new int[ch.length];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                left[index1++] = i;
            } else if (ch[i] == '*') {
                asterisk[index2++] = i;
            } else if (ch[i] == ')') {
                if (index1 == 0 && index2 == 0) 
                    return false;
                if (index1 != 0)
                    index1--;
                else
                    index2--;
            }
        }
        while (index1 != 0) {
            if (index2 == 0 || left[index1-1] > asterisk[index2-1])
                return false;
            index2--;
            index1--;
        }
        return true;
    }
}


class Solution {
    public boolean checkValidString(String s) {
        char[] ch = s.toCharArray();
        int small = 0, large = 0; // smallest possible '(' number, largest possible '(' number
        for (char c : ch) {
            if (c == '(')
                small++;
            else
                small--;
            if (c == ')')
                large--;
            else
                large++;
            if (large < 0)
                return false;
            small = small < 0 ? 0 : small;
        }
        return small == 0;
    }
}


class Solution {
    public boolean checkValidString(String s) {
        int asterisk = 0, left = 0, right = 0;
        char[] ch = s.toCharArray();
        for (char c : ch) {
            if (c == '*') {
                asterisk++;
            } else if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0 && asterisk == 0)
                    return false;
                if (left == 0)
                    asterisk--;
                else
                    left--;
            }
        }
        if (left == 0)
            return true;
        left = asterisk = 0;
        for (int i = ch.length-1; i >= 0; i--) {
            if (ch[i] == ')') {
                right++;
            } else if (ch[i] == '*') {
                asterisk++;
            } else if (ch[i] == '(') {
                if (right == 0 && asterisk == 0) {
                    System.out.println(i);
                    return false;
                }
                if (right == 0)
                    asterisk--;
                else
                    right--;
            }
        }
        return right == 0 || asterisk >= right;
    }
}
