class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] s = str.trim().split(" ");
        if (pattern.length() != s.length)
            return false;
        HashMap<Character, String> map = new HashMap<>();
        char[] ch = pattern.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (map.containsKey(ch[i])) {
                if (map.get(ch[i]).equals(s[i]) == false)
                    return false;
            } else {
                if (map.containsValue(s[i]))  // containsValue() is costly 
                    return false;
                map.put(ch[i], s[i]);
            }
        }
        return true;
    }
}


class Solution {
    public boolean wordPattern(String pattern, String str) {
        char[] ch = pattern.toCharArray();
        String[] strs = str.split(" ");
        if (ch.length != strs.length)
            return false;
        String[] map1 = new String[26];
        Map<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < ch.length; i++) {
            if (map1[ch[i]-'a'] != null && !map1[ch[i]-'a'].equals(strs[i]))
                return false;
            if (map2.containsKey(strs[i]) && map2.get(strs[i]) != ch[i])
                return false;
            map1[ch[i]-'a'] = strs[i];
            map2.put(strs[i], ch[i]);
        }
        return true;
    }
}


class Solution {
    public boolean wordPattern(String pattern, String str) {
        char[] ch = pattern.toCharArray();
        String[] strs = str.split(" ");
        if (ch.length != strs.length)
            return false;
        int[] map1 = new int[26];
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < ch.length; i++) {
            if (map1[ch[i]-'a'] != map2.getOrDefault(strs[i], 0))
                return false;
            if (map1[ch[i]-'a'] == 0) {
                map1[ch[i]-'a'] = i + 1;
                map2.put(strs[i], i + 1);
            }
        }
        return true;
    }
}
