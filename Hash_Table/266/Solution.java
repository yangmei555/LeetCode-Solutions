class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        char[] ch = s.toCharArray();
        int[] index = new int[256];
        for (int i = 0; i < ch.length; i++)
            index[ch[i]]++;
        int res = 1;
        for (int i = 0; i < index.length; i++) {
            if (index[i] % 2 == 1)
                res--;
            if (res < 0)
                return false;
        }
        return true;
    }
}
