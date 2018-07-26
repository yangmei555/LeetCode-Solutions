class Solution {
    public int[] findPermutation(String s) {
        char[] ch = s.toCharArray();
        int[] res = new int[ch.length+1];
        int base = 1, index = 0, count = 0;
        for (int i = 0; i <= ch.length; i++) {
            if (i == ch.length || ch[i] == 'I') {
                int temp1 = base + count;
                int temp2 = index;
                while (count > 0)
                    res[index++] = base + count--;
                if (temp2 == 0)
                    res[index++] = 1;
                base = temp1;
                count = 1;
            } else {
                count++;
            }
        }
        return res;
    }
}


class Solution {
    public int[] findPermutation(String s) {
        char[] ch = s.toCharArray();
        int[] res = new int[ch.length+1];
        int start = 0;
        for (int i = 0; i <= ch.length; i++) {
            res[i] = i + 1;
            if (i == ch.length || ch[i] == 'I') {
                int end = i;
                while (start < end) {
                    int temp = res[start];
                    res[start++] = res[end];
                    res[end--] = temp;
                }
                start = i + 1;
            }
        }
        return res;
    }
}
