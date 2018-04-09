class Solution {
    public boolean isStrobogrammatic(String num) {
        char[] ch = num.toCharArray();
        int[] index = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        int i = 0, j = ch.length - 1;
        while (i <= j) {
            if (index[ch[i] - '0'] != (ch[j] - '0'))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
