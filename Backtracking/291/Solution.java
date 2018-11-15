class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        String[] map = new String[26];
        Set<String> set = new HashSet<>();
        char[] ch1 = pattern.toCharArray(), ch2 = str.toCharArray();
        return helper(ch1, ch2, 0, 0, map, set);
    }
    
    public boolean helper(char[] ch1, char[] ch2, int index1, int index2, String[] map, 
                                                                            Set<String> set) {
        if (index1 == ch1.length || index2 == ch2.length)
            return index1 == ch1.length && index2 == ch2.length;
        if (map[ch1[index1]-'a'] != null) {
            for (char c : map[ch1[index1]-'a'].toCharArray()) {
                if (index2 == ch2.length || ch2[index2] != c)
                    return false;
                index2++;
            }
            return helper(ch1, ch2, index1+1, index2, map, set);
        } else {
            StringBuilder sb = new StringBuilder();
            while (ch1.length - index1 <= ch2.length - index2) {
                sb.append(ch2[index2++]);
                if (!set.contains(sb.toString())) {
                    map[ch1[index1]-'a'] = sb.toString();
                    set.add(sb.toString());
                    if (helper(ch1, ch2, index1+1, index2, map, set))
                        return true;
                    set.remove(sb.toString());
                    map[ch1[index1]-'a'] = null;
                }
            }
            return false;
        }
    }
}
