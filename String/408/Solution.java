class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || word.length() == 0) {
            if (abbr == null || abbr.length() == 0)
                return true;
            else 
                return false;
        }
        int num = 0;
        int i = 0, j = 0;
        char[] chw = word.toCharArray();
        char[] cha = abbr.toCharArray();
        while (i < chw.length && j < cha.length) {
            if (Character.isDigit(cha[j])) {
                if (cha[j] == '0' && num == 0)
                    return false;
                num = num * 10 + (cha[j] - '0');
                j++;
            } else {
                i += num;
                if (i >= chw.length || chw[i] != cha[j])
                    return false;
                num = 0;
                i++;
                j++;
            }
        }
        i += num;
        if (i != chw.length || j != cha.length)
            return false;
        return true;
    }
}
