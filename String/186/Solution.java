class Solution {
    public void reverseWords(char[] str) {
        int i = 0, j = str.length-1;
        char temp = 0;
        while (i < j) {
            temp = str[i];
            str[i++] = str[j];
            str[j--] = temp;
        }
        int start = 0;
        i = 0;
        while (i <= str.length) {
            if (i == str.length || str[i] == ' ') {
                for (int m = start, k = i-1; m < k; m++, k--) {
                    temp = str[m];
                    str[m] = str[k];
                    str[k] = temp;
                }
                start = i + 1;
            }
            i++;
        }
    }
}
