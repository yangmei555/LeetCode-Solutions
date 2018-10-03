class Solution {
    public int kSimilarity(String A, String B) {
        char[] ch1 = A.toCharArray(), ch2 = B.toCharArray();
        return helper(ch1, ch2, 0);
    }
    
    public int helper(char[] ch1, char[] ch2, int index) {
        if (index == ch1.length)
            return 0;
        if (ch1[index] == ch2[index])
            return helper(ch1, ch2, index+1);
        int res = Integer.MAX_VALUE;
        for (int i = index+1; i < ch1.length; i++) {
            if (ch1[i] == ch2[index]) {
                swap(ch1, i, index);
                res = Math.min(res, 1 + helper(ch1, ch2, index+1));
                swap(ch1, i, index);
            }
        }
        return res;
    }
    
    public void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}
