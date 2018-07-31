class Solution {
    public boolean canTransform(String start, String end) {
        char[] ch1 = start.toCharArray(), ch2 = end.toCharArray();
        int rightX = 0, rightL = 0;
        for (int i = 0; i < ch1.length; i++) {
            if (ch1[i] != ch2[i]) {
                if (ch1[i] == 'R' && ch2[i] == 'X') {
                    rightX = rightX > i + 1 ? rightX : i + 1;
                    while (rightX < ch1.length && ch1[rightX] == 'R')
                        rightX++;
                    if (rightX == ch1.length || ch1[rightX] == 'L')
                        return false;
                    // ch1[i] = 'X';
                    ch1[rightX++] = 'R';
                } else if (ch1[i] == 'X' && ch2[i] == 'L') {
                    rightL = rightL > i + 1 ? rightL : i + 1;
                    while (rightL < ch1.length && ch1[rightL] == 'X')
                        rightL++;
                    if (rightL == ch1.length || ch1[rightL] == 'R')
                        return false;
                    // ch1[i] = 'L';
                    ch1[rightL++] = 'X';
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}


class Solution {
    public boolean canTransform(String start, String end) {
        char[] ch1 = start.toCharArray(), ch2 = end.toCharArray();
        int index1 = 0, index2 = 0;
        while (index1 < ch1.length && index2 < ch2.length) {
            while (index1 < ch1.length && ch1[index1] == 'X')
                index1++;
            while (index2 < ch2.length && ch2[index2] == 'X')
                index2++;
            if (index1 < ch1.length && index2 < ch2.length) {
                if (ch1[index1] != ch2[index2])
                    return false;
                if (ch1[index1] == 'L' && index1 < index2)
                    return false;
                if (ch1[index1] == 'R' && index1 > index2)
                    return false;
            }
            index1++;
            index2++;
        }
        while (index1 < ch1.length && ch1[index1] == 'X')
            index1++;
        while (index2 < ch2.length && ch2[index2] == 'X')
            index2++;
        return index1 == index2;
    }
}


class Solution {
    public boolean canTransform(String start, String end) {
        char[] ch1 = start.toCharArray(), ch2 = end.toCharArray();
        int l = 0, r = 0;
        for (int i = 0; i< ch1.length; i++) {
            if (ch1[i] == 'L')
                l++;
            else if (ch1[i] == 'R')
                r++;
            if (ch2[i] == 'L')
                l--;
            else if (ch2[i] == 'R')
                r--;
            // interpret the conditions in a simple way 
            if (!(l == 0 && r >= 0) && !(r == 0 && l <= 0))
                return false;
        }
        return l == 0 && r == 0;
    }
}
