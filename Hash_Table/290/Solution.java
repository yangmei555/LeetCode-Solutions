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
                if (map.containsValue(s[i]))
                    return false;
                map.put(ch[i], s[i]);
            }
        }
        return true;
    }
}
