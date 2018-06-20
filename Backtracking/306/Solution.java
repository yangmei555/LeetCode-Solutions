class Solution {
    public boolean isAdditiveNumber(String num) {
        char[] ch = num.toCharArray();
        if (ch.length < 3)
            return false;
        return helper(ch, 0, -1, -1, -1);
    }
    
    public boolean helper(char[] ch, int index, long pre1, long pre2, long cur) {
        if (cur == -1) {
            return helper(ch, index+1, pre1, pre2, ch[0] - '0');
        } else if (pre2 == -1) {
            if (cur != 0 && index < (ch.length-1)/2) {
                if (helper(ch, index+1, pre1, pre2, cur*10+ch[index]-'0'))
                    return true;
            }
            return helper(ch, index+1, pre1, cur, ch[index]-'0');
        } else if (pre1 == -1) {
            if (cur != 0 && index < ch.length/3*2) {
                if (helper(ch, index+1, pre1, pre2, cur*10+ch[index]-'0'))
                    return true;
            }
            return helper(ch, index+1, pre2, cur, ch[index]-'0');
        } else {
            if (index == ch.length)
                return pre1 + pre2 == cur;
            if (pre1 + pre2 == cur) {
                return helper(ch, index+1, pre2, cur, ch[index]-'0');
            } else if (cur != 0 && pre1 + pre2 >= 10 * cur) {
                return helper(ch, index+1, pre1, pre2, cur*10+ch[index]-'0');
            } else {
                return false;
            }
        }
    }
}


class Solution {
    public boolean isAdditiveNumber(String num) {
        char[] ch = num.toCharArray();
        long pre1 = 0;
        for (int i = 0; i < (ch.length-1)/2; i++) {
            if (pre1 == 0 && i != 0)
                break;
            pre1 = pre1 * 10 + ch[i] - '0';
            long pre2 = 0;
            for (int j = i+1; j < ch.length/3*2; j++) {
                if (pre2 == 0 && j != i+1)
                    break;
                pre2 = pre2 * 10 + ch[j] - '0';
                if (helper(ch, j+2, pre1, pre2, ch[j+1]-'0'))
                    return true;
            }
            
        }
        return false;
    }
    
    public boolean helper(char[] ch, int index, long pre1, long pre2, long cur) {
        if (index == ch.length)
            return pre1 + pre2 == cur;
        if (pre1 + pre2 == cur) {
            return helper(ch, index+1, pre2, cur, ch[index]-'0');
        } else if (cur != 0 && pre1 + pre2 >= 10 * cur) {
            return helper(ch, index+1, pre1, pre2, cur*10+ch[index]-'0');
        } else {
            return false;
        }
    }
}
