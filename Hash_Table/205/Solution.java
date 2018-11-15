class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0)
            return true;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < cs.length; i++) {
            if (map1.containsKey(cs[i]) || map2.containsKey(ct[i])) {
                if (map1.getOrDefault(cs[i], (char)0) != ct[i] && 
                    map2.getOrDefault(ct[i], (char)0) != cs[i])
                    return false;
            } else {
                map1.put(cs[i], ct[i]);
                map2.put(ct[i], cs[i]);
            }
        }
        return true;
    }
}


class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0)
            return true;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int[] is = new int[256];
        int[] it = new int[256];
        for (int i = 0; i < cs.length; i++) {
            if (is[cs[i]] != it[ct[i]])
                return false;
            is[cs[i]] = i+1;
            it[ct[i]] = i+1;
        }
        return true;
    }
}


class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        char[] map1 = new char[128], map2 = new char[128];
        for (int i = 0; i < ch1.length; i++) {
            if (map1[ch1[i]] != 0 && map1[ch1[i]] != ch2[i] || 
                map2[ch2[i]] != 0 && map2[ch2[i]] != ch1[i])
                return false;
            map1[ch1[i]] = ch2[i];
            map2[ch2[i]] = ch1[i];
        }
        return true;
    }
}
