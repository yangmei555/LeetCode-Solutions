class Solution {
    public boolean isAnagram(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        if (ch1.length != ch2.length)
            return false;
        int[] index = new int[256];
        for (char c : ch1)
            index[c]++;
        for (char c : ch2) {
            index[c]--;
            if (index[c] < 0)
                return false;
        }
        return true;
    }
}
