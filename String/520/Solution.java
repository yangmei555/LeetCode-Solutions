class Solution {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0)
            return true;
        boolean first = false, cont = false;
        char[] ch = word.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (i == 0) {
                if (ch[i] >= 'A' && ch[i] <= 'Z')
                    first = true;
                else 
                    first = false;
            } else if (i == 1) {
                if (ch[i] >= 'A' && ch[i] <= 'Z') {
                    if (first == true)
                        cont = true;
                    else 
                        return false;
                } else {
                    cont = false;
                }
            } else {
                if (ch[i] >= 'A' && ch[i] <= 'Z') {
                    if (first == false)
                        return false;
                    if (cont == false)
                        return false;
                } else {
                    if (cont == true)
                        return false;
                }
            }
            
        }
        return true;
    }
}


class Solution {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0)
            return true;
        int count = 0;
        char[] ch = word.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z')
                count++;
        }
        if (count == ch.length || count == 0 || (count == 1 && ch[0] >= 'A' && ch[0] <= 'Z'))
            return true;
        else
            return false;
    }
}
