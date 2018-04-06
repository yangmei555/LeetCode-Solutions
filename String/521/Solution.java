class Solution {
    public int findLUSlength(String a, String b) {
        if (a == null || b == null)
            return -1;
        if (a.length() != b.length())
            return a.length() > b.length() ? a.length() : b.length();
        if (a.equals(b))
            return -1;
        else
            return a.length();
    }
}
