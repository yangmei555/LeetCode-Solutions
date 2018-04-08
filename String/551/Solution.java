class Solution {
    public boolean checkRecord(String s) {
        if (s == null || s.length() == 0)
            return true;
        char[] ch = s.toCharArray();
        int a = 0, l = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'A') {
                if (a == 1)
                    return false;
                a++;
                l = 0;
            } else if (ch[i] == 'L') {
                if (l == 2)
                    return false;
                l++;
            } else {
                l = 0;
            }
        }
        return true;
    }
}
