class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0)
            return false;
        if (s.charAt(0) == '+' || s.charAt(0) == '-')
            s = s.substring(1);
        boolean dot = false, e = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                if (s.charAt(i) == '.') {
                    if (dot || e || (i == 0 || !Character.isDigit(s.charAt(i-1))) && 
                                    (i == s.length()-1 || !Character.isDigit(s.charAt(i+1))))
                        return false;
                    dot = true;
                } else if (s.charAt(i) == 'e') {
                    if (e || i == s.length()-1 || i == 0)
                        return false;
                    e = true;
                    if (s.charAt(i+1) == '+' || s.charAt(i+1) == '-') {
                        i++;
                        if (i == s.length()-1)
                            return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}


class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0)
            return false;
        boolean dot = false, e = false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                if (s.charAt(i) == '.') {
                    if (dot || e || (i == 0 || !Character.isDigit(s.charAt(i-1))) && 
                                    (i == s.length()-1 || !Character.isDigit(s.charAt(i+1))))
                        return false;
                    dot = true;
                } else if (s.charAt(i) == 'e') {
                    if (e || i == s.length()-1 || i == 0)
                        return false;
                    e = true;
                } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                    if (i != 0 && s.charAt(i-1) != 'e' || i == s.length()-1 || s.charAt(i+1) == 'e')
                        return false;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}


// only focus on chars seen so far 
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        char[] ch = s.toCharArray();
        // actually only one of num and numAfterE is enough 
        boolean e = false, dot = false, num = false, numAfterE = false;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                num = true;
                numAfterE = true;
            } else if (ch[i] == '.') {
                if (e || dot)
                    return false;
                dot = true;
            } else if (ch[i] == 'e' || ch[i] == 'E') {
                if (e || !num)
                    return false;
                e = true;
                numAfterE = false;
            } else if (ch[i] == '+' || ch[i] == '-') {
                if (i != 0 && ch[i-1] != 'e' && ch[i-1] != 'E')
                    return false;
            } else {
                return false;
            }
        }
        return num && numAfterE;
    }
}
