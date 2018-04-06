class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0)
            return false;
        if (s.indexOf(s.charAt(s.length()-1)) == s.length() - 1)
            return false;
        StringBuilder rotate = new StringBuilder(s);
        String first;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.length() % (i+1) == 0) {
                rotate = new StringBuilder(s);
                first = s.substring(0, i+1);
                rotate.delete(0, i+1).append(first);
                // System.out.println(rotate);
                if (rotate.toString().equals(s))
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0)
            return false;
        StringBuilder rotate = new StringBuilder(s + s);
        rotate.deleteCharAt(0);
        rotate.deleteCharAt(rotate.length() - 1);
        return rotate.indexOf(s) != -1;
    }
}


class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0)
            return false;
        int len = s.length(), times = 0;
        for (int i = len / 2; i >=1; i--) {
            if (len % i == 0) {
                times = len / i;
                String sub = s.substring(0, i);
                int j = 1;
                for (; j < times; j++) {
                    if (!s.substring(j*i, (j+1)*i).equals(sub))
                        break;
                }
                if (j == times)
                    return true;
            }
        }
        return false;
    }
}
