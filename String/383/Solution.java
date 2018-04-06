class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0)
            return true;
        if (magazine == null || magazine.length() == 0)
            return false;
        StringBuilder sb = new StringBuilder(magazine);
        char[] ch = ransomNote.toCharArray();
        int index = 0;
        for (int i = 0; i < ch.length; i++) {
            index = sb.indexOf(ch[i] + "");
            if (index == -1)
                return false;
            sb.setCharAt(index, (char)0);
        }
        return true;
    }
}
