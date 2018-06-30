class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s.equals(t) || Math.abs(s.length()-t.length()) > 1)
            return false;
        int change = 0;
        if (s.length()-t.length() == -1)
            change = 1;
        else if (s.length()-t.length() == 1)
            change = 2;
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        int i = 0, j = 0;
        boolean used = false;
        while (i < ch1.length && j < ch2.length) {
            if (ch1[i] != ch2[j]) {
                if (used == true)
                    return false;
                if (change == 1)
                    j++;
                else if (change == 2)
                    i++;
                else {
                    i++;
                    j++;
                }
                used = true;
            } else {
                i++;
                j++;
            }
        }
        return true;
    }
}


class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s.equals(t) || Math.abs(s.length()-t.length()) > 1)
            return false;
        int change = 0;
        if (s.length()-t.length() == -1)
            change = 1;
        else if (s.length()-t.length() == 1)
            change = 2;
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        int i = 0, j = 0;
        while (i < ch1.length && j < ch2.length) {
            if (ch1[i] != ch2[j]) {
                if (change == 0)
                    return s.substring(i+1).equals(t.substring(j+1));
                else if (change == 1)
                    return s.substring(i).equals(t.substring(j+1));
                else
                    return s.substring(i+1).equals(t.substring(j));
            }
            i++;
            j++;
        }
        return true;
    }
}


class Solution {
    public boolean isOneEditDistance(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        if (!(ch1.length == ch2.length && !s.equals(t) || ch1.length-ch2.length==1 || 
                                                            ch2.length-ch1.length == 1))
            return false;
        boolean flag = false;
        for (int i = 0, j = 0; i < ch1.length && j < ch2.length; i++, j++) {
            if (ch1[i] != ch2[j]) {
                if (flag) {
                    return false;
                } else {
                    flag = true;
                    if (ch1.length > ch2.length)
                        j--;
                    else if (ch1.length < ch2.length)
                        i--;
                }
            }
        }
        return true;
    }
}
