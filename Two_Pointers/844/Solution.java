class Solution {
    public boolean backspaceCompare(String S, String T) {
        char[] ch1 = S.toCharArray(), ch2 = T.toCharArray();
        int index1 = 0, index2 = 0;
        for (char c : ch1) {
            if (c == '#' && index1 != 0)
                index1--;
            else if (c != '#')
                ch1[index1++] = c;
        }
        for (char c : ch2) {
            if (c == '#' && index2 != 0)
                index2--;
            else if (c != '#')
                ch2[index2++] = c;
        }
        if (index1 != index2)
            return false;
        for (int i = 0; i < index1; i++) {
            if (ch1[i] != ch2[i])
                return false;
        }
        return true;
    }
}


class Solution {
    public boolean backspaceCompare(String S, String T) {
        char[] ch1 = S.toCharArray(), ch2 = T.toCharArray();
        int index1 = ch1.length-1, index2 = ch2.length-1;
        while (index1 >= 0 || index2 >= 0) {
            int count1 = index1 >= 0 && ch1[index1] == '#' ? 1 : 0;
            int count2 = index2 >= 0 && ch2[index2] == '#' ? 1 : 0;
            if (count1 == 0 && count2 == 0) {
                if (index1 < 0 || index2 < 0 || ch1[index1--] != ch2[index2--])
                    return false;
                continue;
            }
            index1 = count1 == 0 ? index1 : index1 - 1;
            index2 = count2 == 0 ? index2 : index2 - 1;
            while (index1 >= 0 && count1 != 0) {
                if (ch1[index1--] == '#')
                    count1++;
                else
                    count1--;
            }
            while (index2 >= 0 && count2 != 0) {
                if (ch2[index2--] == '#')
                    count2++;
                else
                    count2--;
            }
        }
        return true;
    }
}


class Solution {
    public boolean backspaceCompare(String S, String T) {
        char[] ch1 = S.toCharArray(), ch2 = T.toCharArray();
        int index1 = ch1.length-1, index2 = ch2.length-1;
        while (index1 >= 0 || index2 >= 0) {
            int count1 = 0, count2 = 0;
            while (index1 >= 0) {
                if (ch1[index1] == '#') {
                    index1--;
                    count1++;
                } else if (count1 != 0) {
                    index1--;
                    count1--;
                } else {
                    break;
                }
            }
            while (index2 >= 0) {
                if (ch2[index2] == '#') {
                    index2--;
                    count2++;
                } else if (count2 != 0) {
                    index2--;
                    count2--;
                } else {
                    break;
                }
            }
            if (index1 >= 0 != index2 >= 0)
                return false;
            if (index1 >= 0 && ch1[index1] != ch2[index2])
                return false;
            index1--;
            index2--;
        }
        return true;
    }
}


class Solution {
    public boolean backspaceCompare(String S, String T) {
        char[] ch1 = S.toCharArray(), ch2 = T.toCharArray();
        for (int i = ch1.length-1, j = ch2.length-1; i >= 0 || j >= 0; i--, j--) {
            for (int count = 0; i >= 0 && (count > 0 || ch1[i] == '#'); i--)
                count = ch1[i] == '#' ? count + 1 : count - 1;
            for (int count = 0; j >= 0 && (count > 0 || ch2[j] == '#'); j--) 
                count = ch2[j] == '#' ? count + 1 : count - 1;
            if ((i >= 0 != j >= 0) || (i >= 0 && ch1[i] != ch2[j]))
                return false;
        }
        return true;
    }
}


// without termination condition
class Solution {
    public boolean backspaceCompare(String S, String T) {
        char[] ch1 = S.toCharArray(), ch2 = T.toCharArray();
        for (int i = ch1.length-1, j = ch2.length-1; ; i--, j--) {
            for (int count = 0; i >= 0 && (count > 0 || ch1[i] == '#'); i--)
                count = ch1[i] == '#' ? count + 1 : count - 1;
            for (int count = 0; j >= 0 && (count > 0 || ch2[j] == '#'); j--) 
                count = ch2[j] == '#' ? count + 1 : count - 1;
            if (i < 0 || j < 0 || ch1[i] != ch2[j])
                return i < 0 && j < 0;
        }
    }
}
