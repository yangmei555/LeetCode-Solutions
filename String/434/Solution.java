class Solution {
    public int countSegments(String s) {
        if (s == null || s.length() == 0 || s.trim().length() == 0)
            return 0;
        return s.trim().split("\\s+").length;
    }
}


class Solution {
    public int countSegments(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 0;
        char[] ch = s.toCharArray();
        boolean segment = false;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {
                segment = false;
            } else if (segment == false) {
                res++;
                segment = true;
            }
        }
        return res;
    }
}


class Solution {
    public int countSegments(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 0;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++)
            if (ch[i] != ' ' && (i == 0 || ch[i-1] == ' '))
                res++;
        return res;
    }
}
