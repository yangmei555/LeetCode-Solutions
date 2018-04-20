class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0)
            return true;
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int[] index = new int[ch1.length+1];
        int temp1 = 0, temp2 = 0;
        for (int i = 0; i < ch2.length; i++) {
            temp1 = index[0];
            for (int j = 0; j < ch1.length; j++) {
                temp2 = index[j+1];
                if (ch2[i] == ch1[j]) {
                    index[j+1] = temp1 + 1;
                } else {
                    index[j+1] = index[j+1] > index[j] ? index[j+1] : index[j];
                }
                temp1 = temp2;
                if (j == ch1.length - 1 && index[j+1] == j + 1)
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0)
            return true;
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int index1 = 0, index2 = 0;
        while (index1 < ch1.length && index2 < ch2.length) {
            if (ch1[index1] == ch2[index2]) {
                index1++;
                index2++;
            } else {
                index2++;
            }
        }
        return index1 == ch1.length;
    }
}


class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0)
            return true;
        char[] ch = s.toCharArray();
        int index = -1, i = 0;
        while (i < ch.length) {
            index = t.indexOf(ch[i], index + 1);
            if (index == -1)
                return false;
            i++;
        }
        return true;
    }
}
