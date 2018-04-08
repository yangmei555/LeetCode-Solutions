class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        char[] ch = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (ch[i] != ch[j])
                break;
            i++;
            j--;
        }
        if (i >= j)
            return true;
        int ii = i + 1, jj = j;
        while (ii < jj) {
            if (ch[ii] != ch[jj])
                break;
            ii++;
            jj--;
        }
        if (ii >= jj)
            return true;
        ii = i;
        jj = j - 1;
        while (ii < jj) {
            if (ch[ii] != ch[jj])
                return false;
            ii++;
            jj--;
        }
        return true;
    }
}
