class Solution {
    public int lengthOfLastWord(String s) {
        int index = s.length()-1;
        while (index >= 0 && s.charAt(index) == ' ')
            index--;
        int pos = index;
        while (index >= 0 && s.charAt(index) != ' ')
            index--;
        return  pos - index;
    }
}
