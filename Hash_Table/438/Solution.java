class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new LinkedList<>();
        if (s.length() < p.length())
            return list;
        char[] ch1 = s.toCharArray();
        char[] ch2 = p.toCharArray();
        int[] index = new int[26];
        int notz = 0;
        for (char c : ch2)
            index[c - 'a']++;
        for (int i = 0; i < ch2.length; i++)
            index[ch1[i] - 'a']--;
        for (int i = 0; i < index.length; i++)
            if (index[i] != 0)
                notz++;
        for (int i = 0; i < ch1.length + 1 - ch2.length; i++) {
            if (notz == 0)
                list.add(i);
            if (i != ch1.length - ch2.length) {
                if (index[ch1[i] - 'a'] == 0)
                    notz++;
                if (index[ch1[i + ch2.length] - 'a'] == 0)
                    notz++;
                index[ch1[i] - 'a']++;
                index[ch1[i + ch2.length] - 'a']--;
                if (index[ch1[i] - 'a'] == 0)
                    notz--;
                if (index[ch1[i + ch2.length] - 'a'] == 0)
                    notz--;
            }
            
        }
        return list;
    }
}
