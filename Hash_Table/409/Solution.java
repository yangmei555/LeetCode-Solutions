class Solution {
    public int longestPalindrome(String s) {
        int[] index = new int[128];
        char[] ch = s.toCharArray();
        for (char c : ch)
            index[c]++;
        int res = 0, odd = 0;
        for (int i : index) {
            if (i % 2 == 0)
                res += i;
            else {
                res += i-1;
                odd = 1;
            }
        }
        return res + odd;
    }
}
